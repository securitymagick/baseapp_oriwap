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
public class EditAccountController {
	@Autowired
	UserDao userDao;
	
	@Autowired
	MenuDao menuDao;
	
	@Autowired
	SiteLookDao siteLookDao;

	@RequestMapping(value = "/editaccount", method = RequestMethod.GET)
	public String showEditAccount(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id, Model model) {
		List<MenuItem> menuItems = menuDao.getMenuDB("admin");
		List<MenuItem> navbarMenuItems = menuDao.getMenuDB("navbar");
		List<SiteLook> siteLooks = siteLookDao.getSiteLook();
		if (!siteLooks.isEmpty()) {
			model.addAttribute("siteLook", siteLooks.get(0));
		}
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("navbarMenuItems", navbarMenuItems);

		User userToEdit = new User();
		model.addAttribute("userToEdit", userToEdit);
		List<User> users= userDao.getUsers();
		Integer userid = new Integer(id);

		for (User u1 : users) {
			if (u1.getId().equals(userid)) {
				request.setAttribute("user", u1);
			} 
		}
		
		return "editaccount";

	}

	@RequestMapping(value = "/editaccount", method = RequestMethod.POST)
	public String editAccount(@ModelAttribute("userToEdit") User userToEdit,
		BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {
		userDao.updateUser(userToEdit);	
		String message = "The account has been modified!";
		request.setAttribute("message", message);
		
		return "redirect:/" + ACCOUNTS_URL;
	}
}