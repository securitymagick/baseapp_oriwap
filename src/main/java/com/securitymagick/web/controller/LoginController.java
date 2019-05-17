package com.securitymagick.web.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.securitymagick.domain.AuthToken;
import com.securitymagick.domain.BadRandomToken;
import com.securitymagick.domain.GoodRandomToken;
import com.securitymagick.domain.LogMessage;
import com.securitymagick.domain.LoginForm;
import com.securitymagick.domain.MenuItem;
import com.securitymagick.domain.Permissions;
import com.securitymagick.domain.RandomToken;
import com.securitymagick.domain.SiteLook;
import com.securitymagick.domain.User;
import com.securitymagick.domain.UserNameCookieValue;
import com.securitymagick.domain.dao.LogDao;
import com.securitymagick.domain.dao.MenuDao;
import com.securitymagick.domain.dao.SiteLookDao;
import com.securitymagick.domain.dao.UserDao;
import com.securitymagick.service.ValueGetterAndCreator;
import com.securitymagick.web.cookie.CookieHandler;

import static com.securitymagick.AppConstants.LOGIN_URL;
import static com.securitymagick.AppConstants.LOGOUT_URL;
import static com.securitymagick.AppConstants.ACCOUNT_URL;
import static com.securitymagick.AppConstants.loginErrorIfMultiple;
import static com.securitymagick.AppConstants.loginUserEnumeration;
import static com.securitymagick.AppConstants.usernameCookieAuthToken;
import static com.securitymagick.AppConstants.LoginUserEnumerationPasswordWrongMessage;
import static com.securitymagick.AppConstants.USER_COOKIE_NAME;
import static com.securitymagick.AppConstants.LoginNoUserEnumerationMessage;
import static com.securitymagick.AppConstants.UserEnumerationUsernameWrongMessage;
import static com.securitymagick.AppConstants.UnExpectedErrorMessage;
import static com.securitymagick.AppConstants.usernameCookieBase64Encode;
import static com.securitymagick.AppConstants.csrfProtection;
import static com.securitymagick.AppConstants.csrfProtectionGoodRandom;
import static com.securitymagick.AppConstants.CSRF_COOKIE_NAME;
import static com.securitymagick.AppConstants.localStorageCsrfProtection;

@Controller
public class LoginController {
	private static final String MESSAGE2 = "message";
	private static final String LOGIN = "login";

	@Autowired
	UserDao userDao;
	
	@Autowired
	LogDao logDao;
	
	@Autowired
	MenuDao menuDao;
	
	@Autowired
	SiteLookDao siteLookDao;
	
	@Autowired
	ValueGetterAndCreator vgc;

	@RequestMapping(value = "/" + LOGIN_URL, method = RequestMethod.GET)
	public String showLoginForm(Model model, @RequestParam(value = MESSAGE2, required = false) String message, HttpServletRequest request) {
		LoginForm loginForm = new LoginForm();
		model.addAttribute("loginForm", loginForm);
		if (message != null) {
			request.setAttribute(MESSAGE2, message);
		}
		List<MenuItem> menuItems = menuDao.getMenuDB("main");
		List<MenuItem> navbarMenuItems = menuDao.getMenuDB("navbar");
		List<SiteLook> siteLooks = siteLookDao.getSiteLook();
		if (!siteLooks.isEmpty()) {
			model.addAttribute("siteLook", siteLooks.get(0));
		}
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("navbarMenuItems", navbarMenuItems);		
		return LOGIN;
	}	
	
	@RequestMapping(value = "/" + LOGIN_URL, method = RequestMethod.POST)
	public String showAccount(@ModelAttribute("loginForm") LoginForm loginForm,
		BindingResult result, Model model, HttpServletResponse response, HttpServletRequest request) {
		LogMessage lm = new LogMessage(null, null, request.getHeader("user-agent"), "Trying to log in user with credentials: " + loginForm.toString());	
		logDao.addLog(lm);
		
		CookieHandler pCookie = new CookieHandler("permissions");
		CookieHandler userCookie = new CookieHandler(USER_COOKIE_NAME);
		
		String path="/";
		List<MenuItem> menuItems = menuDao.getMenuDB("main");
		List<MenuItem> navbarMenuItems = menuDao.getMenuDB("navbar");
		List<SiteLook> siteLooks = siteLookDao.getSiteLook();
		if (!siteLooks.isEmpty()) {
			model.addAttribute("siteLook", siteLooks.get(0));
			path += siteLooks.get(0).getUrl();
		}
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("navbarMenuItems", navbarMenuItems);
		
		String message = LoginNoUserEnumerationMessage;
		List<User> ulist = userDao.getUser(loginForm.getUsername());
		
		if (ulist.isEmpty()) {
			if (loginUserEnumeration) {
				message = UserEnumerationUsernameWrongMessage;
			} else {
				Permissions p = new Permissions();
				pCookie.addCookie(response, p.getCookieValue(), path);
				userCookie.addCookie(response, "", path);
			}
			request.setAttribute(MESSAGE2, message);
			return LOGIN;		
		}
		if (loginErrorIfMultiple) {
			if (ulist.size() != 1) {
				if (loginUserEnumeration) {
					message = UnExpectedErrorMessage;
				} else {
					Permissions p = new Permissions();
					pCookie.addCookie(response, p.getCookieValue(), path);
					userCookie.addCookie(response, "", path);
				}
				request.setAttribute(MESSAGE2, message);
				return LOGIN;		
			}
		}	
						
		User u = ulist.get(0);
		
		if (loginForm.getPassword().equals(u.getPassword())) {
			Permissions p = new Permissions(u.getIsUser().equals(1), u.getIsAdmin().equals(1));
			pCookie.addCookie(response, p.getCookieValue(), path);
			String userCookieValue = vgc.createValue(USER_COOKIE_NAME, u);		
			userCookie.addCookie(response, userCookieValue, path);
			String token= "";
			// if there is protection create token and set in appropriate spot
			if (csrfProtection) {
				RandomToken r;
				if (csrfProtectionGoodRandom) {
					r = new GoodRandomToken();			
				} else {
					r = new BadRandomToken();
				}
				token = r.getNewToken();
				if (localStorageCsrfProtection) {
					CookieHandler csrfCookie = new CookieHandler(CSRF_COOKIE_NAME);
					csrfCookie.addCookie(response, token, path);
					token = "?newCsrfToken=" + token;
				} else {
					request.getSession().setAttribute(CSRF_COOKIE_NAME, token);
					token = "";
				}
			}
			return "redirect:/" + ACCOUNT_URL + token;
		} else {
			Permissions p = new Permissions();
			pCookie.addCookie(response, p.getCookieValue());
			userCookie.addCookie(response, "");
			if (loginUserEnumeration) {
				message = LoginUserEnumerationPasswordWrongMessage;
			}
			request.setAttribute(MESSAGE2, message);
			return LOGIN;
		}
	}
	
	@RequestMapping(value = "/" + LOGOUT_URL, method = RequestMethod.GET)
	public String logoutUser(Model model, HttpServletRequest request, HttpServletResponse response) {
		CookieHandler pCookie = new CookieHandler("permissions");
		CookieHandler userCookie = new CookieHandler(USER_COOKIE_NAME);	
		
		Permissions p = new Permissions();
		pCookie.addCookie(response, p.getCookieValue());
		userCookie.addCookie(response, "");		
		
		return "redirect:/";
	}			
	
}