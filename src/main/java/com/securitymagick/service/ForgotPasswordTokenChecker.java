package com.securitymagick.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.securitymagick.domain.ForgotPasswordToken;

import static com.securitymagick.AppConstants.forgotPasswordTokenVerifyValidForUser;
import static com.securitymagick.AppConstants.forgotPasswordTokenVerifyOnChange;
import static com.securitymagick.AppConstants.forgotPasswordTokenVerifyExists;
import static com.securitymagick.AppConstants.forgotPasswordTokenVerifyExpired;

@Component("tokenChecker")
public class ForgotPasswordTokenChecker {
	private List<ForgotPasswordToken> forgotPasswordTokens = new ArrayList<ForgotPasswordToken>();
	String helper = "";
	
	/**
	 * @return the helper
	 */
	public final String getHelper() {
		return helper;
	}

	public void addToken(ForgotPasswordToken token) {
		forgotPasswordTokens.add(token);
	}
	
	public boolean removeToken(ForgotPasswordToken token) {
		boolean result = forgotPasswordTokens.remove(token);
		if (forgotPasswordTokenVerifyOnChange) {
			return result;
		}
		return true;
	}
	
	public Boolean isTokenValid(ForgotPasswordToken token, String user) {	
		
		if (forgotPasswordTokenVerifyValidForUser) {
			if (!token.getUser().equals(user)) {
				helper = "failed user match";
				return false;
			}
		}
		if (forgotPasswordTokenVerifyExpired) {
			if (token.isExpired()) {
				helper = "failed expiration";
				return false;
			}
		}
		if (forgotPasswordTokenVerifyExists) {
			if (!forgotPasswordTokens.contains(token)) {
				helper="failed contains";
				return false;
			}
		}		
		helper = "passed all";
		return true;
	}
}
