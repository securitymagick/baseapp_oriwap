package com.securitymagick.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.securitymagick.domain.AboutContent;
import com.securitymagick.domain.MenuItem;
import com.securitymagick.domain.SiteLook;
import com.securitymagick.domain.dao.MenuDao;
import com.securitymagick.domain.dao.SiteLookDao;

import static com.securitymagick.AppConstants.AboutLayout;
import static com.securitymagick.AppConstants.ABOUT_URL;

@Controller
public class AboutController {
	
	@Autowired
	MenuDao menuDao;
	
	@Autowired
	SiteLookDao siteLookDao;

	@RequestMapping(value = "/" + ABOUT_URL, method = RequestMethod.GET)
	public String about(ModelMap model) {
		AboutContent content = new AboutContent(AboutLayout);
		
		List<MenuItem> menuItems = menuDao.getMenuDB("main");
		List<MenuItem> navbarMenuItems = menuDao.getMenuDB("navbar");
		List<SiteLook> siteLooks = siteLookDao.getSiteLook();
		if (!siteLooks.isEmpty()) {
			model.addAttribute("siteLook", siteLooks.get(0));
		}
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("navbarMenuItems", navbarMenuItems);
		model.addAttribute("firstSentence", content.getFirstSentence());
		model.addAttribute("middleSentences", content.getMiddleSentences());
		model.addAttribute("lastSentence", content.getLastSentences());
		
		return "about";

	}


}