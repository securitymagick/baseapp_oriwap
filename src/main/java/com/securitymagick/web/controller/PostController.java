package com.securitymagick.web.controller;

import static com.securitymagick.AppConstants.USER_COOKIE_NAME;
import static com.securitymagick.AppConstants.usernameCookieAuthToken;
import static com.securitymagick.AppConstants.usernameCookieBase64Encode;

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
import com.securitymagick.domain.FrontEndValidation;
import com.securitymagick.domain.MenuItem;
import com.securitymagick.domain.Post;
import com.securitymagick.domain.PostComment;
import com.securitymagick.domain.SiteLook;
import com.securitymagick.domain.User;
import com.securitymagick.domain.UserNameCookieValue;
import com.securitymagick.domain.dao.MenuDao;
import com.securitymagick.domain.dao.PostDao;
import com.securitymagick.domain.dao.SiteLookDao;
import com.securitymagick.domain.dao.UserDao;
import com.securitymagick.service.CSRFTokenChecker;
import com.securitymagick.service.ValueGetterAndCreator;
import com.securitymagick.web.cookie.CookieHandler;

@Controller
public class PostController {
	@Autowired
	PostDao postDao;
	
	@Autowired
	MenuDao menuDao;
	
	@Autowired
	SiteLookDao siteLookDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ValueGetterAndCreator vgc;	
	
	@Autowired
	CSRFTokenChecker csrfTokenChecker;

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String showPost(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id, Model model) {
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

		PostComment postComment = new PostComment();
		model.addAttribute("postComment", postComment);
		List<Post> posts= postDao.getPostsWithComments();
		Integer postid = new Integer(id);

		for (Post p1 : posts) {
			if (p1.getId().equals(postid)) {
				request.setAttribute("post", p1);
			} 
		}

		String userCookieValue = vgc.getCookieValue(USER_COOKIE_NAME, request);
		if (!userCookieValue.isEmpty()) {
			request.setAttribute("user", userCookieValue);			
		}
		
		return "post";

	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String addComment(@ModelAttribute("postComment") PostComment postComment,
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
		String message = "There is a problem with your request.  You may need to logout and login again to continue.";
		if (csrfTokenChecker.isValid(postComment.getCsrfToken(), request)) {
			postDao.addComment(postComment);	
			message = "Your comment has been added!";
		}
		request.setAttribute("message", message);
		
		List<Post> posts= postDao.getPostsWithComments();
		Integer postid = new Integer(postComment.getPostid());	
		for (Post p1 : posts) {
			if (p1.getId().equals(postid)) {
				request.setAttribute("post", p1);
			} 
		}
		
		return "post";
	}
}