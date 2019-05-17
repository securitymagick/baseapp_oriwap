package com.securitymagick.web.controller;

import static com.securitymagick.AppConstants.USER_COOKIE_NAME;
import static com.securitymagick.AppConstants.VOTE_URL;

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

import com.securitymagick.domain.FavoriteVote;
import com.securitymagick.domain.FrontEndValidation;
import com.securitymagick.domain.MenuItem;
import com.securitymagick.domain.SiteLook;
import com.securitymagick.domain.dao.MenuDao;
import com.securitymagick.domain.dao.SiteLookDao;
import com.securitymagick.service.CSRFTokenChecker;
import com.securitymagick.service.ValueGetterAndCreator;
import com.securitymagick.service.VoteTracker;

@Controller
public class VoteController {
	@Autowired
	MenuDao menuDao;
	
	@Autowired
	SiteLookDao siteLookDao;
	
	@Autowired
	ValueGetterAndCreator vgc;	
	
	@Autowired
	VoteTracker voteTracker;
	
	@Autowired
	CSRFTokenChecker csrfTokenChecker;

	@RequestMapping(value = "/" + VOTE_URL, method = RequestMethod.GET)
	public String showVote(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		FavoriteVote favoriteVote = new FavoriteVote();
		model.addAttribute("favoriteVote", favoriteVote);

		String userCookieValue = vgc.getCookieValue(USER_COOKIE_NAME, request);
		if (!userCookieValue.isEmpty()) {
			request.setAttribute("user", userCookieValue);	
			if (voteTracker.canUserVote(userCookieValue)) {
				model.addAttribute("canVote", "true");
			} else {
				model.addAttribute("message", "You have already voted!");
			}
		}
		model.addAttribute("items", voteTracker.getVotes());
		return "vote";

	}

	@RequestMapping(value = "/" + VOTE_URL, method = RequestMethod.POST)
	public String addVote(@ModelAttribute("favoriteVote") FavoriteVote favoriteVote,
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
		if (csrfTokenChecker.isValid(favoriteVote.getCsrfToken(), request)) {
			if (voteTracker.addVote(favoriteVote.getUsername(), favoriteVote.getVoteFor())) {
				message = "Your vote has been cast!  Share the following link to get other to vote the same: <a href=\"" + VOTE_URL + "?voteFor="+favoriteVote.getVoteFor()+"\">Vote For " + favoriteVote.getVoteFor() + "</a>" ;

			} else {
				message = "You have already voted!";
			}
		}
		request.setAttribute("message", message);
		
		model.addAttribute("items", voteTracker.getVotes());
		
		return "vote";
	}	
	
}
