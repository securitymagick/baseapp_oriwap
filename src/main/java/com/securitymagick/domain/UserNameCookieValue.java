/**
 * 
 */
package com.securitymagick.domain;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.securitymagick.domain.dao.UserDao;

/**
 * @author leggosgirl
 *
 */
public class UserNameCookieValue {
	private static final String NOTVALID = "NOTVALID";

	private String cookieValue="";
	

	/**
	 * @param userName
	 */
	public UserNameCookieValue(String userName) {
		this.cookieValue = createCookieValue(userName);
	}
	
	

	/**
	 * Default
	 */
	public UserNameCookieValue() {
	}



	private String createCookieValue(String userName) {
		if (userName.isEmpty()) {
			return Base64.getEncoder().encodeToString(NOTVALID.getBytes());
		} else {
			return Base64.getEncoder().encodeToString(userName.getBytes());
		}		
	}

	/**
	 * @return the cookieValue
	 */
	public final String getCookieValue() {
		return cookieValue;
	}

	/**
	 * @param cookieValue the cookieValue to set
	 */
	public final void setCookieValue(String cookieValue) {
		this.cookieValue = cookieValue;
	}
	
	
	public final String getUserName() {
		return new String(Base64.getDecoder().decode(cookieValue.getBytes()));
	}

}
