package com.securitymagick.web.controller;

import static com.securitymagick.AppConstants.USER_COOKIE_NAME;
import static com.securitymagick.AppConstants.usernameCookieAuthToken;
import static com.securitymagick.AppConstants.usernameCookieBase64Encode;
import static com.securitymagick.AppConstants.ADDPOST_URL;
import static com.securitymagick.AppConstants.LOGIN_URL;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.securitymagick.domain.AdminDBItem;
import com.securitymagick.domain.AuthToken;
import com.securitymagick.domain.FrontEndValidation;
import com.securitymagick.domain.MenuItem;
import com.securitymagick.domain.Post;
import com.securitymagick.domain.SiteLook;
import com.securitymagick.domain.User;
import com.securitymagick.domain.UserNameCookieValue;
import com.securitymagick.domain.PostForm;
import com.securitymagick.domain.dao.AdminDao;
import com.securitymagick.domain.dao.MenuDao;
import com.securitymagick.domain.dao.PostDao;
import com.securitymagick.domain.dao.SiteLookDao;
import com.securitymagick.domain.dao.UserDao;
import com.securitymagick.service.CSRFTokenChecker;
import com.securitymagick.service.ValueGetterAndCreator;
import com.securitymagick.web.cookie.CookieHandler;

@Controller
public class AddZooBabyController {
	private static final String PART1 = "part1";

//public static final String ROOT = "C:\\Users\\NTISNS01\\Desktop\\Demo\\Tools\\apache-tomcat-8.0.33\\webapps\\zoo-babies-1.0-SNAPSHOT\\resources\\core\\images";
public static final String ROOT = "/var/lib/tomcat7/webapps/zoo-babies-1.0-SNAPSHOT/resources/core/images";

	@Autowired
	PostDao postDao;
	
	@Autowired
	AdminDao adminDao;	
	
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

	@RequestMapping(value = "/" + ADDPOST_URL, method = RequestMethod.GET)
	public String addZooBaby(HttpServletRequest request, ModelMap model) {
		request.setAttribute(PART1, PART1);
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

		return "addZooBaby";

	}
	
	@RequestMapping(value = "/" + ADDPOST_URL, method = RequestMethod.POST, params={"uploadfile"})
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
								    ModelMap model, HttpServletRequest request) {
		String root = ROOT;
		
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

		String userCookieValue = vgc.getCookieValue(USER_COOKIE_NAME, request);
		
		if (!userCookieValue.isEmpty()) {
			request.setAttribute("user", userCookieValue);
		}								
		List<AdminDBItem> items= adminDao.getAdminDB();
		for (AdminDBItem item: items) {
			if (item.getSettingName().equals("uploadDirectory")) {
				root = item.getSettingValue();
			}
		}				
		String message = "Unable to upload file.";
		if (!file.isEmpty()) {
			try {
				Files.copy(file.getInputStream(), Paths.get(root, file.getOriginalFilename()));
				request.setAttribute("imageName", file.getOriginalFilename());
				request.setAttribute(PART1, "");
				request.setAttribute("part2", "part2");
				PostForm postForm = new PostForm();
				model.addAttribute("zooBabyForm", postForm);
				message = "File uploaded successfully";
			}  catch (IOException|RuntimeException e) {
				message = "Exception:" + e.toString();
			}
		}
		request.setAttribute("message", message);
		return "addZooBaby";
	}
	
	@RequestMapping(value = "/" + ADDPOST_URL, method = RequestMethod.POST, params={"createpost"}) 
	public String addPost(@ModelAttribute("zooBabyForm") PostForm postForm,
		BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		if (csrfTokenChecker.isValid(postForm.getCsrfToken(), request)) {
			Post p = new Post(null, postForm.getTitle(), postForm.getImageName(), postForm.getAuthor(), postForm.getDescription(), null);
			postDao.addPost(p);
			return "redirect:/public";		
		}

		return "redirect:/"+ LOGIN_URL +"?message=There is a problem with your acocunt.  Try logging in again.";
		

		
	}
	
}