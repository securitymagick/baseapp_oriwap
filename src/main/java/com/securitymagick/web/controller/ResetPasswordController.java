package com.securitymagick.web.controller;

import static com.securitymagick.AppConstants.USER_COOKIE_NAME;
import static com.securitymagick.AppConstants.UnExpectedErrorMessage;
import static com.securitymagick.AppConstants.PasswordMatchMessage;
import static com.securitymagick.AppConstants.usernameCookieBase64Encode;
import static com.securitymagick.AppConstants.ResetPasswordInValidRandomToken;
import static com.securitymagick.AppConstants.forgotPasswordRandomToken;
import static com.securitymagick.AppConstants.forgotPasswordTokenVerifyAllOnChange;
import static com.securitymagick.AppConstants.FORGOTPASSWORD_COOKIE_NAME;
import static com.securitymagick.AppConstants.LOGIN_URL;
import static com.securitymagick.AppConstants.FORGOTPASSWORD_URL;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.securitymagick.domain.AuthToken;
import com.securitymagick.domain.ForgotPasswordToken;
import com.securitymagick.domain.FrontEndValidation;
import com.securitymagick.domain.LogMessage;
import com.securitymagick.domain.MenuItem;
import com.securitymagick.domain.ResetPasswordForm;
import com.securitymagick.domain.SiteLook;
import com.securitymagick.domain.User;
import com.securitymagick.domain.UserNameCookieValue;
import com.securitymagick.domain.dao.LogDao;
import com.securitymagick.domain.dao.MenuDao;
import com.securitymagick.domain.dao.SiteLookDao;
import com.securitymagick.domain.dao.UserDao;
import com.securitymagick.service.ForgotPasswordTokenChecker;
import com.securitymagick.service.ValueGetterAndCreator;
import com.securitymagick.web.cookie.CookieHandler;

@Controller
public class ResetPasswordController {

	private static final String RESETPASSWORD = "resetpassword";

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
	
	@Autowired
	ForgotPasswordTokenChecker tokenChecker;
	
	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public String showResetPasswordForm(Model model, HttpServletRequest request) {
		ResetPasswordForm resetPasswordForm = new ResetPasswordForm();
		model.addAttribute("resetPasswordForm", resetPasswordForm);
		FrontEndValidation validation = new FrontEndValidation(request);
		model.addAttribute("validation", validation);
		List<MenuItem> menuItems = menuDao.getMenuDB("main");
		List<MenuItem> navbarMenuItems = menuDao.getMenuDB("navbar");
		List<SiteLook> siteLooks = siteLookDao.getSiteLook();
		if (!siteLooks.isEmpty()) {
			model.addAttribute("siteLook", siteLooks.get(0));
		}
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("navbarMenuItems", navbarMenuItems);
		return RESETPASSWORD;
	}

	@RequestMapping(value = {"/resetpassword","/resetpassword/resetpassword","/resetpassword/{usertoken:.+}/resetpassword"}, method = RequestMethod.POST)
	public String checkAccount(@ModelAttribute("resetPasswordForm") ResetPasswordForm resetPasswordForm,
		BindingResult result, Model model, HttpServletResponse response, HttpServletRequest request) {

		FrontEndValidation validation = new FrontEndValidation(request);
		model.addAttribute("validation", validation);
		List<MenuItem> menuItems = menuDao.getMenuDB("main");
		List<MenuItem> navbarMenuItems = menuDao.getMenuDB("navbar");
		List<SiteLook> siteLooks = siteLookDao.getSiteLook();
		if (!siteLooks.isEmpty()) {
			model.addAttribute("siteLook", siteLooks.get(0));
		}
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("navbarMenuItems", navbarMenuItems);

		String userCookieValue = vgc.getCookieValue(USER_COOKIE_NAME, request);
		if (forgotPasswordRandomToken) {
			String randomToken = vgc.getCookieValue(FORGOTPASSWORD_COOKIE_NAME, request);
			ForgotPasswordToken token = new ForgotPasswordToken(randomToken);
			if (forgotPasswordTokenVerifyAllOnChange) {
				if (!tokenChecker.isTokenValid(token, userCookieValue)) {
					return "redirect:/" + FORGOTPASSWORD_URL + "?message=isValid2:" + ResetPasswordInValidRandomToken;
				}
			}
			if (!tokenChecker.removeToken(token)) {
				return "redirect:/" + FORGOTPASSWORD_URL + "?message=remove:" + ResetPasswordInValidRandomToken;
			}
		}
		if (!userCookieValue.isEmpty()) {		
			if (resetPasswordForm.getPassword().equals(resetPasswordForm.getConfirmPassword())) {
					List<User> ulist = userDao.getUser(userCookieValue);
					if (ulist.size() != 1) {
						return "redirect:/" + FORGOTPASSWORD_URL + "?message=" + UnExpectedErrorMessage;		
					}
					User u = ulist.get(0);
					if (u.getFavorite().equals(resetPasswordForm.getFavorite())) {
						LogMessage lm = new LogMessage(null, u.getUsername(), request.getHeader("user-agent"), "Password reset successful for user.");	
						logDao.addLog(lm);
						userDao.updatePassword(u.getUsername(), resetPasswordForm.getPassword());
						return "redirect:/" + LOGIN_URL + "?message=Password updated.  Please login.";
					}
					else {
						LogMessage lm = new LogMessage(null, u.getUsername(), request.getHeader("user-agent"), "Password reset failed for user.");	
						logDao.addLog(lm);
						String message = "Incorrect information!";
						request.setAttribute("message", message);
						return RESETPASSWORD;	
					}
			} else {
				String message = PasswordMatchMessage;
				request.setAttribute("message", message);
				return RESETPASSWORD;	
			}
		} else {
			return "redirect:/" + FORGOTPASSWORD_URL + "?message=" + UnExpectedErrorMessage;
		}
	}
	
	
	@RequestMapping(value = "/resetpassword/{usertoken:.+}", method = RequestMethod.GET)
	public String resetWithUserToken(HttpServletResponse response, HttpServletRequest request, Model model, @PathVariable("usertoken") String usertoken) {
		
		String username = vgc.getParameterValue(USER_COOKIE_NAME, usertoken);
		if (username.isEmpty()) {
			return "redirect:/" + FORGOTPASSWORD_URL  + "?message=" + UnExpectedErrorMessage;
		}
		List<User> ulist = userDao.getUser(username);
		if (ulist.isEmpty()) {
			return "redirect:/" + FORGOTPASSWORD_URL + "?message=" + UnExpectedErrorMessage;
		}
		CookieHandler userCookie = new CookieHandler(USER_COOKIE_NAME);
		userCookie.addCookie(response, usertoken);
		return showResetPasswordForm(model, request);
	}
	

	@RequestMapping(value = "/resetpassword/{usertoken:.+}/{randomtoken:.+}", method = RequestMethod.GET)
	public String resetWithToken(HttpServletResponse response, HttpServletRequest request, Model model, 
								@PathVariable("usertoken") String usertoken,  @PathVariable("randomtoken") String randomtoken) {
		String username = vgc.getParameterValue(USER_COOKIE_NAME, usertoken);
		if (username.isEmpty()) {
			return "redirect:/" + FORGOTPASSWORD_URL + "?message=" + UnExpectedErrorMessage;
		}
		List<User> ulist = userDao.getUser(username);
		if (ulist.isEmpty()) {
			return "redirect:/" + FORGOTPASSWORD_URL + "?message=" + UnExpectedErrorMessage;
		}
		ForgotPasswordToken token = new ForgotPasswordToken(randomtoken);
		if (!tokenChecker.isTokenValid(token, username)) {
			return "redirect:/" + FORGOTPASSWORD_URL + "?message=" + ResetPasswordInValidRandomToken;
		}
		
		CookieHandler userCookie = new CookieHandler(USER_COOKIE_NAME);
		userCookie.addCookie(response, usertoken);
		CookieHandler fptCookie = new CookieHandler(FORGOTPASSWORD_COOKIE_NAME);
		fptCookie.addCookie(response, randomtoken);
		return showResetPasswordForm(model, request);
	}
}