package com.securitymagick.domain;

import static com.securitymagick.AppConstants.CSRF_COOKIE_NAME;
import static com.securitymagick.AppConstants.clientSidePasswordMatch;
import static com.securitymagick.AppConstants.clientSidePasswordMeter;
import static com.securitymagick.AppConstants.clientSideUsernameCheck;
import static com.securitymagick.AppConstants.clientSideXSSComments;
import static com.securitymagick.AppConstants.clientSideXSSPost;
import static com.securitymagick.AppConstants.clientSideXSSPostTitle;
import static com.securitymagick.AppConstants.localStorageCsrfProtection;
import static com.securitymagick.AppConstants.csrfProtection;

import javax.servlet.http.HttpServletRequest;



public class FrontEndValidation {
	private String passwordMeter="";
	private String passwordMatch="";
	private String usernameCheck = "";
	private String commentXss = "";
	private String postXss="";
	private String postTitleXss = "";
	private String csrfProtect = "";
	private String csrfLocalStorage = "";
	private String sessionCSRFStorage = "";
	
	/**
	 * Default Constructor
	 */
	public FrontEndValidation(HttpServletRequest request) {
		if (clientSidePasswordMeter) {
			this.passwordMeter = "true";
		}
		if (clientSidePasswordMatch) {
			this.passwordMatch = "true";
		}
		if (clientSideUsernameCheck) {
			this.usernameCheck = "true";
		}
		if (clientSideXSSComments) {
			this.commentXss = "true";
		}
		if (clientSideXSSPost) {
			this.postXss = "true";
		}
		if (clientSideXSSPostTitle) {
			this.postTitleXss = "true";
		}
		if (localStorageCsrfProtection) { 
			this.csrfLocalStorage = "true";
		}
		if (csrfProtection && !localStorageCsrfProtection) {
			this.sessionCSRFStorage = (String) request.getSession().getAttribute(CSRF_COOKIE_NAME); 
		}
		if (csrfProtection) {
			this.csrfProtect = "true";
		}
	}
	
	

	/**
	 * @return the csrfProtect
	 */
	public final String getCsrfProtect() {
		return csrfProtect;
	}



	/**
	 * @param csrfProtect the csrfProtect to set
	 */
	public final void setCsrfProtect(String csrfProtect) {
		this.csrfProtect = csrfProtect;
	}



	/**
	 * @return the sessionCSRFStorage
	 */
	public final String getSessionCSRFStorage() {
		return sessionCSRFStorage;
	}


	/**
	 * @param sessionCSRFStorage the sessionCSRFStorage to set
	 */
	public final void setSessionCSRFStorage(String sessionCSRFStorage) {
		this.sessionCSRFStorage = sessionCSRFStorage;
	}


	/**
	 * @return the passwordMeter
	 */
	public final String getPasswordMeter() {
		return passwordMeter;
	}

	/**
	 * @param passwordMeter the passwordMeter to set
	 */
	public final void setPasswordMeter(String passwordMeter) {
		this.passwordMeter = passwordMeter;
	}

	/**
	 * @return the passwordMatch
	 */
	public final String getPasswordMatch() {
		return passwordMatch;
	}

	/**
	 * @return the csrfLocalStorage
	 */
	public final String getCsrfLocalStorage() {
		return csrfLocalStorage;
	}

	/**
	 * @param csrfLocalStorage the csrfLocalStorage to set
	 */
	public final void setCsrfLocalStorage(String csrfLocalStorage) {
		this.csrfLocalStorage = csrfLocalStorage;
	}

	/**
	 * @param passwordMatch the passwordMatch to set
	 */
	public final void setPasswordMatch(String passwordMatch) {
		this.passwordMatch = passwordMatch;
	}

	/**
	 * @return the usernameCheck
	 */
	public final String getUsernameCheck() {
		return usernameCheck;
	}

	/**
	 * @param usernameCheck the usernameCheck to set
	 */
	public final void setUsernameCheck(String usernameCheck) {
		this.usernameCheck = usernameCheck;
	}

	/**
	 * @return the commentXss
	 */
	public final String getCommentXss() {
		return commentXss;
	}

	/**
	 * @param commentXss the commentXss to set
	 */
	public final void setCommentXss(String commentXss) {
		this.commentXss = commentXss;
	}

	/**
	 * @return the postXss
	 */
	public final String getPostXss() {
		return postXss;
	}

	/**
	 * @param postXss the postXss to set
	 */
	public final void setPostXss(String postXss) {
		this.postXss = postXss;
	}

	/**
	 * @return the postTitleXss
	 */
	public final String getPostTitleXss() {
		return postTitleXss;
	}

	/**
	 * @param postTitleXss the postTitleXss to set
	 */
	public final void setPostTitleXss(String postTitleXss) {
		this.postTitleXss = postTitleXss;
	}

	
}
