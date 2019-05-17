package com.securitymagick.domain;

public class FavoriteVote {
	private String voteFor;
	private String username;
	private String csrfToken = "";
	
	

	/**
	 *  Default Constructor
	 */
	public FavoriteVote() {
	}

	/**
	 * @param voteFor
	 * @param username
	 */
	public FavoriteVote(String voteFor, String username) {
		this.voteFor = voteFor;
		this.username = username;
	}

	/**
	 * @param voteFor
	 * @param username
	 * @param csrfToken
	 */
	public FavoriteVote(String voteFor, String username, String csrfToken) {
		this.voteFor = voteFor;
		this.username = username;
		this.csrfToken = csrfToken;
	}

	/**
	 * @return the voteFor
	 */
	public final String getVoteFor() {
		return voteFor;
	}

	/**
	 * @param voteFor the voteFor to set
	 */
	public final void setVoteFor(String voteFor) {
		this.voteFor = voteFor;
	}

	/**
	 * @return the username
	 */
	public final String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public final void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the csrfToken
	 */
	public final String getCsrfToken() {
		return csrfToken;
	}

	/**
	 * @param csrfToken the csrfToken to set
	 */
	public final void setCsrfToken(String csrfToken) {
		this.csrfToken = csrfToken;
	}

	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "favoriteVote [voteFor=" + voteFor + ", username=" + username + ", csrfToken=" + csrfToken + "]";
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((csrfToken == null) ? 0 : csrfToken.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((voteFor == null) ? 0 : voteFor.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof FavoriteVote)) {
			return false;
		}
		FavoriteVote other = (FavoriteVote) obj;
		if (csrfToken == null) {
			if (other.csrfToken != null) {
				return false;
			}
		} else if (!csrfToken.equals(other.csrfToken)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		if (voteFor == null) {
			if (other.voteFor != null) {
				return false;
			}
		} else if (!voteFor.equals(other.voteFor)) {
			return false;
		}
		return true;
	}



	
	
	
}
