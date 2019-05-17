package com.securitymagick.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.securitymagick.domain.MenuItem;
import com.securitymagick.domain.SiteLook;
import com.securitymagick.domain.User;
import com.securitymagick.domain.dao.MenuDao;
import com.securitymagick.domain.dao.SiteLookDao;
import com.securitymagick.domain.dao.UserDao;

import static com.securitymagick.AppConstants.ACCOUNTS_URL;

@Controller
public class AccountsController {
	@Autowired
	UserDao userDao;
	
	@Autowired
	MenuDao menuDao;
	
	@Autowired
	SiteLookDao siteLookDao;

	@RequestMapping(value = "/" + ACCOUNTS_URL, method = RequestMethod.GET)
	public String showAccounts(HttpServletRequest request, ModelMap model) {
		List<User> users= userDao.getUsers();
		request.setAttribute("users", users);
		List<MenuItem> menuItems = menuDao.getMenuDB("admin");
		List<MenuItem> navbarMenuItems = menuDao.getMenuDB("navbar");
		List<SiteLook> siteLooks = siteLookDao.getSiteLook();
		if (!siteLooks.isEmpty()) {
			model.addAttribute("siteLook", siteLooks.get(0));
		}
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("navbarMenuItems", navbarMenuItems);		
		return "accounts";

	}

}