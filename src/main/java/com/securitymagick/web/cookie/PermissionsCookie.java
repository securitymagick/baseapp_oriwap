package com.securitymagick.web.cookie;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;

import com.securitymagick.domain.Permissions;
/**
 * @author leggosgirl
 *
 */
public class PermissionsCookie {
	private static final String PERMISSIONS_LABEL = "permissions";
	private Cookie permissions = null;

	/**
	 * 
	 */
	public PermissionsCookie() {
		super();
	}
	
	public Boolean checkForCookie(HttpServletRequest request) {
		Boolean found = false;
		Cookie[] requestCookies = request.getCookies();
		if(requestCookies != null){
			for(Cookie c : requestCookies){
				if (c.getName().equals(PERMISSIONS_LABEL)) {
					found = true;
					permissions = c;
				}
			}
		}
		return found;
	}

	public void addCookie(HttpServletResponse response, Permissions permissions) {
		this.permissions = new Cookie(PERMISSIONS_LABEL, permissions.getCookieValue());
		response.addCookie(this.permissions);
	}
	
	public void addCookie(HttpServletResponse response, Permissions permissions, String path) {
		this.permissions = new Cookie(PERMISSIONS_LABEL, permissions.getCookieValue());
		this.permissions.setPath(path);
		response.addCookie(this.permissions);
	}

	public void setPath(String path) {
		permissions.setPath(path);
	}
	
	public Cookie getPermissionsCookie() {
		return permissions;
	}
}