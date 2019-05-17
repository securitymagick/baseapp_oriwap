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

import com.securitymagick.domain.FrontEndValidation;
import com.securitymagick.domain.LogMessage;
import com.securitymagick.domain.MenuItem;
import com.securitymagick.domain.RegistrationForm;
import com.securitymagick.domain.SiteLook;
import com.securitymagick.domain.User;
import com.securitymagick.domain.dao.LogDao;
import com.securitymagick.domain.dao.MenuDao;
import com.securitymagick.domain.dao.SiteLookDao;
import com.securitymagick.domain.dao.UserDao;

import static com.securitymagick.AppConstants.registrationEmptyTests;
import static com.securitymagick.AppConstants.registrationExistingTests;
import static com.securitymagick.AppConstants.registrationPasswordMatchTest;
import static com.securitymagick.AppConstants.EmptyMessage;
import static com.securitymagick.AppConstants.RegistrationExistingMessage;
import static com.securitymagick.AppConstants.PasswordMatchMessage;

import static com.securitymagick.AppConstants.REGISTER_URL;
import static com.securitymagick.AppConstants.LOGIN_URL;

@Controller
public class RegisterController {
	@Autowired
	UserDao userDao;
	
	@Autowired
	LogDao logDao;
	
	@Autowired
	MenuDao menuDao;
	
	@Autowired
	SiteLookDao siteLookDao;

	@RequestMapping(value = "/" + REGISTER_URL, method = RequestMethod.GET)
	public String showRegistrationForm(Model model, HttpServletRequest request) {
		RegistrationForm registrationForm = new RegistrationForm();
		model.addAttribute("registerForm", registrationForm);
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
		return "register";
	}
	
	@RequestMapping(value = "/" + REGISTER_URL, method = RequestMethod.POST)
	public String showAccount(@ModelAttribute("registerForm") RegistrationForm registrationForm,
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
		
		// check registration tests
		if (registrationEmptyTests) {
			if (registrationForm.getUsername().isEmpty() || registrationForm.getPassword().isEmpty() || registrationForm.getConfirmPassword().isEmpty() || registrationForm.getFavorite().isEmpty()) {
				String message = EmptyMessage;
				request.setAttribute("message", message);
				return "register";
			}		
		}
		if (registrationExistingTests) {
			List<User> ulist = userDao.getUser(registrationForm.getUsername());		
			if (!ulist.isEmpty()) {
				String message = RegistrationExistingMessage;
				request.setAttribute("message", message);
				return "register";
			}		
		}
		if (registrationPasswordMatchTest) {
			if (!registrationForm.getPassword().equals(registrationForm.getConfirmPassword())) {
				String message = PasswordMatchMessage;
				request.setAttribute("message", message);
				return "register";
			}			
		}
		
		userDao.addUser(registrationForm);	
		LogMessage lm = new LogMessage(null, registrationForm.getUsername(), request.getHeader("user-agent"), "Registered new user");	
		logDao.addLog(lm);
		return "redirect:/"+ LOGIN_URL + "?message=Registration successful.  Please log in.";
			
	}
	
}