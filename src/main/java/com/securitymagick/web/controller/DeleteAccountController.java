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

import com.securitymagick.domain.MenuItem;
import com.securitymagick.domain.SiteLook;
import com.securitymagick.domain.User;
import com.securitymagick.domain.dao.MenuDao;
import com.securitymagick.domain.dao.SiteLookDao;
import com.securitymagick.domain.dao.UserDao;

import static com.securitymagick.AppConstants.ACCOUNTS_URL;

@Controller
public class DeleteAccountController {
	@Autowired
	UserDao userDao;
	
	@Autowired
	MenuDao menuDao;
	
	@Autowired
	SiteLookDao siteLookDao;
	
	@RequestMapping(value = "/deleteaccount", method = RequestMethod.GET)
	public String showPost(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id, Model model) {
		List<MenuItem> menuItems = menuDao.getMenuDB("admin");
		List<MenuItem> navbarMenuItems = menuDao.getMenuDB("navbar");
		List<SiteLook> siteLooks = siteLookDao.getSiteLook();
		if (!siteLooks.isEmpty()) {
			model.addAttribute("siteLook", siteLooks.get(0));
		}
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("navbarMenuItems", navbarMenuItems);

		User userToDelete = new User();
		model.addAttribute("userToDelete", userToDelete);
		List<User> users= userDao.getUsers();
		Integer userid = new Integer(id);

		for (User u1 : users) {
			if (u1.getId().equals(userid)) {
				request.setAttribute("user", u1);
			} 
		}
		
		return "deleteaccount";

	}

	@RequestMapping(value = "/deleteaccount", method = RequestMethod.POST)
	public String addComment(@ModelAttribute("user") User userToDelete,
		BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {

		userDao.deleteUser(userToDelete.getId());	
		String message = "The user has been deleted!";
		request.setAttribute("message", message);
		
		return "redirect:/" + ACCOUNTS_URL;
	}
}