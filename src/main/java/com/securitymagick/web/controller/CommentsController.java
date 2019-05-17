package com.securitymagick.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.securitymagick.domain.MenuItem;
import com.securitymagick.domain.PostComment;
import com.securitymagick.domain.SiteLook;
import com.securitymagick.domain.dao.MenuDao;
import com.securitymagick.domain.dao.PostDao;
import com.securitymagick.domain.dao.SiteLookDao;

import static com.securitymagick.AppConstants.COMMENTS_URL;

@Controller
public class CommentsController {
	private static final String COMMENTS_LABEL = "comments";
	@Autowired
	PostDao postDao;
	
	@Autowired
	MenuDao menuDao;
	
	@Autowired
	SiteLookDao siteLookDao;

	@RequestMapping(value = "/" + COMMENTS_URL, method = RequestMethod.GET)
	public String showPost(HttpServletRequest request, ModelMap model) {
		List<MenuItem> menuItems = menuDao.getMenuDB("admin");
		List<MenuItem> navbarMenuItems = menuDao.getMenuDB("navbar");
		List<SiteLook> siteLooks = siteLookDao.getSiteLook();
		if (!siteLooks.isEmpty()) {
			model.addAttribute("siteLook", siteLooks.get(0));
		}
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("navbarMenuItems", navbarMenuItems);
		
		List<PostComment> comments= postDao.getComments();
		request.setAttribute(COMMENTS_LABEL, comments);
		return COMMENTS_LABEL;
	}
	
	@RequestMapping(value = "/" + COMMENTS_URL, method = RequestMethod.GET, params={"delete"})
	public String showPost(HttpServletRequest request, @RequestParam("delete") String id, ModelMap model) {
		List<MenuItem> menuItems = menuDao.getMenuDB("admin");
		List<MenuItem> navbarMenuItems = menuDao.getMenuDB("navbar");
		List<SiteLook> siteLooks = siteLookDao.getSiteLook();
		if (!siteLooks.isEmpty()) {
			model.addAttribute("siteLook", siteLooks.get(0));
		}
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("navbarMenuItems", navbarMenuItems);

		postDao.deleteComment(id);
		List<PostComment> comments= postDao.getComments();
		request.setAttribute(COMMENTS_LABEL, comments);
		return COMMENTS_LABEL;
	}
	
}