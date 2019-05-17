package com.securitymagick.web.controller;

import static com.securitymagick.AppConstants.USER_COOKIE_NAME;
import static com.securitymagick.AppConstants.PUBLIC_URL;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.securitymagick.domain.MenuItem;
import com.securitymagick.domain.Post;
import com.securitymagick.domain.SiteLook;
import com.securitymagick.domain.dao.MenuDao;
import com.securitymagick.domain.dao.PostDao;
import com.securitymagick.domain.dao.SiteLookDao;
import com.securitymagick.service.ValueGetterAndCreator;
import com.securitymagick.web.cookie.CookieHandler;


@Controller
public class PublicController {

	@Autowired
	PostDao postDao;
	
	@Autowired
	MenuDao menuDao;
	
	@Autowired
	SiteLookDao siteLookDao;

	@RequestMapping(value = "/" + PUBLIC_URL, method = RequestMethod.GET)
	public String showPublic(HttpServletRequest request, ModelMap model) {
		List<MenuItem> menuItems;
	
		CookieHandler userCookie = new CookieHandler(USER_COOKIE_NAME);
		if (!userCookie.checkForCookie(request)) {
			menuItems = menuDao.getMenuDB("main");
		} else {
			menuItems = menuDao.getMenuDB("account");
		}
		List<MenuItem> navbarMenuItems = menuDao.getMenuDB("navbar");
		List<SiteLook> siteLooks = siteLookDao.getSiteLook();
		if (!siteLooks.isEmpty()) {
			model.addAttribute("siteLook", siteLooks.get(0));
		}
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("navbarMenuItems", navbarMenuItems);

		
		List<Post> posts= postDao.getPosts();
		request.setAttribute("posts", posts);

		return "public";
	}
}