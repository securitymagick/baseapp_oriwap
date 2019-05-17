package com.securitymagick.web.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.securitymagick.domain.AuthToken;
import com.securitymagick.domain.FrontEndValidation;
import com.securitymagick.domain.LogMessage;
import com.securitymagick.domain.MenuItem;
import com.securitymagick.domain.SiteLook;
import com.securitymagick.domain.UpdatePasswordForm;
import com.securitymagick.domain.UpdateFavoriteForm;
import com.securitymagick.domain.User;
import com.securitymagick.domain.UserNameCookieValue;
import com.securitymagick.domain.dao.LogDao;
import com.securitymagick.domain.dao.MenuDao;
import com.securitymagick.domain.dao.SiteLookDao;
import com.securitymagick.domain.dao.UserDao;
import com.securitymagick.service.ValueGetterAndCreator;
import com.securitymagick.web.cookie.CookieHandler;

import static com.securitymagick.AppConstants.updatePasswordMatchTest;
import static com.securitymagick.AppConstants.usernameCookieAuthToken;
import static com.securitymagick.AppConstants.usernameCookieBase64Encode;
import static com.securitymagick.AppConstants.updateEmptyTests;
import static com.securitymagick.AppConstants.EmptyMessage;
import static com.securitymagick.AppConstants.PasswordMatchMessage;
import static com.securitymagick.AppConstants.UnExpectedErrorMessage;

import static com.securitymagick.AppConstants.UPDATEACCOUNT_URL;
import static com.securitymagick.AppConstants.USER_COOKIE_NAME;

@Controller
public class UpdateAccountController {
	private static final String UPDATE_ACCOUNT = "updateAccount";

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

	@RequestMapping(value = "/" + UPDATEACCOUNT_URL, method = RequestMethod.GET)
	public String showUpdateAccountForm(Model model, HttpServletRequest request) {
		List<MenuItem> menuItems = menuDao.getMenuDB("account");
		List<MenuItem> navbarMenuItems = menuDao.getMenuDB("navbar");
		List<SiteLook> siteLooks = siteLookDao.getSiteLook();
		FrontEndValidation validation = new FrontEndValidation(request);
		model.addAttribute("validation", validation);
		if (!siteLooks.isEmpty()) {
			model.addAttribute("siteLook", siteLooks.get(0));
		}
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("navbarMenuItems", navbarMenuItems);
		
		String userCookieValue = vgc.getCookieValue(USER_COOKIE_NAME, request);

		if (!userCookieValue.isEmpty()) {
			request.setAttribute("user", userCookieValue);
			List<User> ulist = userDao.getUser(userCookieValue);
			if (ulist.size() == 1) {
				User u = ulist.get(0);
				request.setAttribute("favorite", u.getFavorite());
			}
		}

		UpdatePasswordForm updatePasswordForm = new UpdatePasswordForm();
		model.addAttribute("updatePasswordForm", updatePasswordForm);
		UpdateFavoriteForm updateZooBabyForm = new UpdateFavoriteForm();
		model.addAttribute("updateZooBabyForm", updateZooBabyForm);
		return UPDATE_ACCOUNT;
	}	
	
	@RequestMapping (value="/" + UPDATEACCOUNT_URL, method=RequestMethod.POST, params={"updatefavorite"})
	public String updateFavorite(@ModelAttribute("updateZooBabyForm") UpdateFavoriteForm updateZooBabyForm,
		BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		FrontEndValidation validation = new FrontEndValidation(request);
		model.addAttribute("validation", validation);
		List<MenuItem> menuItems = menuDao.getMenuDB("account");
		List<MenuItem> navbarMenuItems = menuDao.getMenuDB("navbar");
		List<SiteLook> siteLooks = siteLookDao.getSiteLook();
		if (!siteLooks.isEmpty()) {
			model.addAttribute("siteLook", siteLooks.get(0));
		}
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("navbarMenuItems", navbarMenuItems);
		
		String userCookieValue = vgc.getCookieValue(USER_COOKIE_NAME, request);
		if (!userCookieValue.isEmpty()) {	
			request.setAttribute("user", userCookieValue);
		}
			
		UpdatePasswordForm updatePasswordForm = new UpdatePasswordForm();
		model.addAttribute("updatePasswordForm", updatePasswordForm);		

		String message = UnExpectedErrorMessage;	
		// check tests
		if (updateEmptyTests) {
			if (updateZooBabyForm.getFavorite().isEmpty()) {
				message = EmptyMessage;
				request.setAttribute("message", message);			
				return UPDATE_ACCOUNT;	
			}
		}

		
		List<User> ulist = userDao.getUser(userCookieValue);
		if (ulist.size() == 1) {
				User u = ulist.get(0);
				u.setFavorite(updateZooBabyForm.getFavorite());
				userDao.updateUser(u);		
				message = "The favorite zoo baby has been updated!";	
				LogMessage lm = new LogMessage(null, u.getUsername(), request.getHeader("user-agent"), "Favorite Zoo Baby update successful for user.");	
				logDao.addLog(lm);				
		}
		
		
		request.setAttribute("message", message);			
		return UPDATE_ACCOUNT;	
	}	
	
	
	@RequestMapping (value="/" + UPDATEACCOUNT_URL, method=RequestMethod.POST, params={"updatepassword"})
	public String updatePassword(@ModelAttribute("updatePasswordForm") UpdatePasswordForm updatePasswordForm,
		BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		FrontEndValidation validation = new FrontEndValidation(request);
		model.addAttribute("validation", validation);
		List<MenuItem> menuItems = menuDao.getMenuDB("account");
		List<MenuItem> navbarMenuItems = menuDao.getMenuDB("navbar");
		List<SiteLook> siteLooks = siteLookDao.getSiteLook();
		if (!siteLooks.isEmpty()) {
			model.addAttribute("siteLook", siteLooks.get(0));
		}
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("navbarMenuItems", navbarMenuItems);

		String userCookieValue = vgc.getCookieValue(USER_COOKIE_NAME, request);
		if (!userCookieValue.isEmpty()) {
			request.setAttribute("user", userCookieValue);
		}
		
		UpdateFavoriteForm updateZooBabyForm = new UpdateFavoriteForm();
		model.addAttribute("updateZooBabyForm", updateZooBabyForm);		
		
		String message = UnExpectedErrorMessage;	

		// check tests
		if (updateEmptyTests) {
			if (updatePasswordForm.getPassword().isEmpty() || updatePasswordForm.getConfirmPassword().isEmpty()) {
				message = EmptyMessage;
				request.setAttribute("message", message);			
				return UPDATE_ACCOUNT;	
			}
		}
		if (updatePasswordMatchTest) {
			if (!updatePasswordForm.getPassword().equals(updatePasswordForm.getConfirmPassword())) {
				message = PasswordMatchMessage;
				request.setAttribute("message", message);			
				return UPDATE_ACCOUNT;	
			}
		}
				
		List<User> ulist = userDao.getUser(userCookieValue);
		if (ulist.size() == 1) {
				message = "Password update successful!";
				User u = ulist.get(0);
				request.setAttribute("favorite", u.getFavorite());						
				LogMessage lm = new LogMessage(null, u.getUsername(), request.getHeader("user-agent"), "Password update successful for user.");	
				logDao.addLog(lm);
				userDao.updatePassword(u.getUsername(), updatePasswordForm.getPassword());
		}		
		
		request.setAttribute("message", message);
		return UPDATE_ACCOUNT;	
			
	}	

}