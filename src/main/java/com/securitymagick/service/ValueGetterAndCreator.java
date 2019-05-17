/**
 * 
 */
package com.securitymagick.service;

import static com.securitymagick.AppConstants.usernameCookieAuthToken;
import static com.securitymagick.AppConstants.usernameCookieBase64Encode;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.securitymagick.domain.AuthToken;
import com.securitymagick.domain.User;
import com.securitymagick.domain.UserNameCookieValue;
import com.securitymagick.domain.dao.UserDao;
import com.securitymagick.web.cookie.CookieHandler;

import static com.securitymagick.AppConstants.USER_COOKIE_NAME;

/**
 * @author NTISNS01
 * This class will return the value of cookie or parameter
 */
@Component("vgc")
public class ValueGetterAndCreator {
	
	@Autowired
	UserDao userDao;

   
   public String getCookieValue(String cookieName, HttpServletRequest request) {	
	   CookieHandler theCookie = new CookieHandler(cookieName);
	   if (theCookie.checkForCookie(request)) {
			Cookie c = theCookie.getCookie();
			return getParameterValue(cookieName, c.getValue());
	   }
	   return "";
   }
   
   public String getParameterValue(String paramName, String paramValue) {
	   if (paramName.equals(USER_COOKIE_NAME)) {
		   return getUserValue(paramValue);
	   } 
	   return paramValue;
   }
   
   private String getUserValue(String value) {
	   String userValue = value;
	   
		if (usernameCookieBase64Encode) {
			UserNameCookieValue v = new UserNameCookieValue();
			v.setCookieValue(userValue);
			userValue = v.getUserName();
		}
		if (usernameCookieAuthToken) {
			AuthToken aToken = new AuthToken(userValue);
			if (aToken.parseToken()) {
				List<User> ulist = userDao.getUserById(aToken.getUid());
				if (!ulist.isEmpty()) {
					userValue = ulist.get(0).getUsername();
				}
			}
		}
		return userValue;
	}

   public String createValue(String valueName, Object valueValue) {
	   if (valueName.equals(USER_COOKIE_NAME)) {
		   return createUserValue(valueValue);
	   } 	
	   if (valueValue instanceof String) {
		   return (String) valueValue;   
	   }
	   return "";
   }

   private String createUserValue(Object valueValue) {
	   User u = null;
	   String userValue = "";
	   if (valueValue instanceof User) {
		   u = (User) valueValue;
		   userValue = u.getUsername();
	   } else if (valueValue instanceof String) {
		   userValue = (String) valueValue;
	   }
		if (usernameCookieAuthToken && u != null) {
			AuthToken aToken = new AuthToken(u.getId());
			userValue =  aToken.getToken();
		}
		if (usernameCookieBase64Encode) {
			UserNameCookieValue v = new UserNameCookieValue(userValue);
			userValue = v.getCookieValue();
		}
		return userValue;
   }
}