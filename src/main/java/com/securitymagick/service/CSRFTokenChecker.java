package com.securitymagick.service;

import static com.securitymagick.AppConstants.csrfProtection;
import static com.securitymagick.AppConstants.CSRF_COOKIE_NAME;
import static com.securitymagick.AppConstants.localStorageCsrfProtection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.securitymagick.web.cookie.CookieHandler;

@Component("csrfTokenChecker")
public class CSRFTokenChecker {
	
	/**
	 * Default Constructor
	 */
	public CSRFTokenChecker() {
		
	}
	
	public Boolean isValid(String csrfTokenValue, HttpServletRequest request) {
		if (csrfProtection) {
			if (csrfTokenValue.isEmpty()) {
				return false;
			}
			if (localStorageCsrfProtection) {
				CookieHandler csrfCookie = new CookieHandler(CSRF_COOKIE_NAME);
				if (csrfCookie.checkForCookie(request)) {
					if (!csrfTokenValue.equals(csrfCookie.getCookie().getValue())) {
						return false;
					}
			   } else {
				   return false;
			   }
			}
		} else {
			if (!csrfTokenValue.equals(request.getSession().getAttribute(CSRF_COOKIE_NAME))) {
				return false;
			}
		}
		return true;
	}
}
