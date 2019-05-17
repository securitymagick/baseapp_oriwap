package com.securitymagick.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.securitymagick.domain.MenuItem;
import com.securitymagick.domain.Permissions;
import com.securitymagick.domain.SiteLook;
import com.securitymagick.domain.dao.MenuDao;
import com.securitymagick.domain.dao.SiteLookDao;
import com.securitymagick.domain.dao.UserDao;
import com.securitymagick.web.cookie.PermissionsCookie;

@Controller
public class HelloController {
	
	@Autowired
	MenuDao menuDao;

	@Autowired
	SiteLookDao siteLookDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		PermissionsCookie pCookie = new PermissionsCookie();
		if (!pCookie.checkForCookie(request)) {
			Permissions p = new Permissions();
			pCookie.addCookie(response, p);
		}
		
		model.addAttribute("title", " -- Zoo Babies");
		List<MenuItem> menuItems = menuDao.getMenuDB("main");
		List<MenuItem> navbarMenuItems = menuDao.getMenuDB("navbar");
		List<SiteLook> siteLooks = siteLookDao.getSiteLook();
		if (!siteLooks.isEmpty()) {
			model.addAttribute("siteLook", siteLooks.get(0));
		}
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("navbarMenuItems", navbarMenuItems);
		return "hello";

	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(HttpServletRequest request, HttpServletResponse response, @PathVariable("name") String name) {
		PermissionsCookie pCookie = new PermissionsCookie();
		if (!pCookie.checkForCookie(request)) {
			Permissions p = new Permissions();
			pCookie.addCookie(response, p);
		}
	
		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("msg", name);

		return model;

	}

}