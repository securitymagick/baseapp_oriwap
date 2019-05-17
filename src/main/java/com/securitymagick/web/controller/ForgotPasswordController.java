package com.securitymagick.web.controller;

import static com.securitymagick.AppConstants.ForgotPasswordEmailMessage;
import static com.securitymagick.AppConstants.ForgotPasswordMessage;
import static com.securitymagick.AppConstants.ResetPasswordEmailLinkText;
import static com.securitymagick.AppConstants.ResetPasswordEmailMessageText;
import static com.securitymagick.AppConstants.USER_COOKIE_NAME;
import static com.securitymagick.AppConstants.UnExpectedErrorMessage;
import static com.securitymagick.AppConstants.UserEnumerationUsernameWrongMessage;
import static com.securitymagick.AppConstants.forgotPasswordEmail;
import static com.securitymagick.AppConstants.forgotPasswordMultiple;
import static com.securitymagick.AppConstants.forgotPasswordUserEnumeration;
import static com.securitymagick.AppConstants.forgotPasswordTokenInUrl;
import static com.securitymagick.AppConstants.forgotPasswordRandomToken;
import static com.securitymagick.AppConstants.forgotPasswordRandomGood;
import static com.securitymagick.AppConstants.FORGOT_PASSWORD_TOKEN_EXPIRATION_IN_SECONDS;
import static com.securitymagick.AppConstants.FORGOTPASSWORD_COOKIE_NAME;
import static com.securitymagick.AppConstants.FORGOTPASSWORD_URL;

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

import com.securitymagick.domain.BadRandomToken;
import com.securitymagick.domain.ForgotPasswordToken;
import com.securitymagick.domain.GoodRandomToken;
import com.securitymagick.domain.MenuItem;
import com.securitymagick.domain.RandomToken;
import com.securitymagick.domain.SiteLook;
import com.securitymagick.domain.User;
import com.securitymagick.domain.UsernameForm;
import com.securitymagick.domain.dao.MenuDao;
import com.securitymagick.domain.dao.SiteLookDao;
import com.securitymagick.domain.dao.UserDao;
import com.securitymagick.service.ForgotPasswordTokenChecker;
import com.securitymagick.service.ValueGetterAndCreator;
import com.securitymagick.web.cookie.CookieHandler;


@Controller
public class ForgotPasswordController {
	@Autowired
	UserDao userDao;
	
	@Autowired
	MenuDao menuDao;
	
	@Autowired
	SiteLookDao siteLookDao;
	
	@Autowired
	ValueGetterAndCreator vgc;
	
	@Autowired
	ForgotPasswordTokenChecker tokenChecker;

	@RequestMapping(value = "/" + FORGOTPASSWORD_URL, method = RequestMethod.GET)
	public String showForgotPassword(Model model, @RequestParam(value = "message", required = false) String message, HttpServletRequest request) {
		UsernameForm usernameForm = new UsernameForm();
		model.addAttribute("usernameForm", usernameForm);
		if (message != null) {
			request.setAttribute("message", message);
		}
		List<MenuItem> menuItems = menuDao.getMenuDB("main");
		List<MenuItem> navbarMenuItems = menuDao.getMenuDB("navbar");
		List<SiteLook> siteLooks = siteLookDao.getSiteLook();
		if (!siteLooks.isEmpty()) {
			model.addAttribute("siteLook", siteLooks.get(0));
		}
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("navbarMenuItems", navbarMenuItems);	
		return "forgotpassword";
	}	
	
	@RequestMapping(value = "/" + FORGOTPASSWORD_URL, method = RequestMethod.POST)
	public String checkAccount(@ModelAttribute("usernameForm") UsernameForm usernameForm,
		BindingResult result, Model model, HttpServletResponse response, HttpServletRequest request) {

		String path="/";
		List<MenuItem> menuItems = menuDao.getMenuDB("main");
		List<MenuItem> navbarMenuItems = menuDao.getMenuDB("navbar");
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("navbarMenuItems", navbarMenuItems);		
		List<SiteLook> siteLooks = siteLookDao.getSiteLook();
		if (!siteLooks.isEmpty()) {
			model.addAttribute("siteLook", siteLooks.get(0));
			path += siteLooks.get(0).getUrl();
		}		
		
		String message = ForgotPasswordMessage;
		if (forgotPasswordEmail) {
			message = ForgotPasswordEmailMessage;
		}
		
		Boolean error = false;
		CookieHandler userCookie = new CookieHandler(USER_COOKIE_NAME);
		List<User> ulist = userDao.getUser(usernameForm.getUsername());
		if (ulist.isEmpty()) {
			error = true;
			if (forgotPasswordUserEnumeration) {
				message = UserEnumerationUsernameWrongMessage;
			}
		}
		if (!forgotPasswordMultiple) {
			if (ulist.size() > 1) {
				error = true;
				if (forgotPasswordUserEnumeration) {
					message = UnExpectedErrorMessage;
				}
			}
		}
		
		request.setAttribute("message", message);
		if (error) {
			userCookie.addCookie(response, "", path);
			return "forgotpassword";	
		}
		
		User u = ulist.get(0);
		String userCookieValue = vgc.createValue(USER_COOKIE_NAME, u);
		String resetPasswordToken = "/" + userCookieValue;
		String resetRandomToken = "";
		if (forgotPasswordRandomToken) {
			RandomToken r;
			if (forgotPasswordRandomGood) {
				r = new GoodRandomToken();			
			} else {
				r = new BadRandomToken();
			}
			ForgotPasswordToken fPToken = new ForgotPasswordToken(r.getNewToken(),u.getUsername(),FORGOT_PASSWORD_TOKEN_EXPIRATION_IN_SECONDS);
			tokenChecker.addToken(fPToken);
			resetRandomToken = fPToken.toString();
			resetPasswordToken += "/" + resetRandomToken;
		}
		
		
		if (!forgotPasswordTokenInUrl) {
			resetPasswordToken = "";
			userCookie.addCookie(response, userCookieValue, path);
			if (forgotPasswordRandomToken) {
				CookieHandler fptCookie = new CookieHandler(FORGOTPASSWORD_COOKIE_NAME);
				fptCookie.addCookie(response, resetRandomToken, path);
			}
		}
		String resetpasswordEmail = ResetPasswordEmailMessageText + "<a href=\"resetpassword" + resetPasswordToken + "\">" + ResetPasswordEmailLinkText + "</a>";
		
		if (forgotPasswordEmail) {
			model.addAttribute("resetpasswordEmail", resetpasswordEmail);
			return "forgotpassword";
		}		
		
		return "redirect:/resetpassword" + resetPasswordToken;

	}
	
}