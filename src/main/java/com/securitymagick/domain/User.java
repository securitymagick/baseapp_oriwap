package com.securitymagick.domain;

/**
 * @author leggosgirl
 *
 */
public class User {
	private Integer id;
	private String username;
	private String password;
	private String favorite;
	private Integer isUser;
	private Integer isAdmin;
	
	public User() {
		super();
	}
	
	
	public User(String username, String password, Integer id, String favorite, Integer isUser, Integer isAdmin) {
		super();
		this.username = username;
		this.password = password;
		this.id = id;
		this.favorite = favorite;
		this.isUser = isUser;
		this.isAdmin = isAdmin;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
		public Integer getIsUser() {
		return isUser;
	}
	public void setIsUser(Integer isUser) {
		if (isUser > 0 ) {
			this.isUser = 1;
		} else {
			this.isUser = 0;
		}
	}
	public Integer getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Integer isAdmin) {
		if (isAdmin > 0) {
			this.isAdmin = 1;
		} else {
			this.isAdmin = 0;
		}
	}
	
	public String getFavorite() {
		return favorite;
	}
	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", id="
				+ id.toString() + ", favorite=" + favorite + ", isUser=" + isUser + ", isAdmin=" + isAdmin + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((favorite == null) ? 0 : favorite.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((isUser == null) ? 0 : isUser.hashCode());
		result = prime * result + ((isAdmin == null) ? 0 : isAdmin.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (favorite == null) {
			if (other.favorite != null)
				return false;
		} else if (!favorite.equals(other.favorite))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (isUser == null) {
			if (other.isUser != null)
				return false;
		} else if (!isUser.equals(other.isUser))
			return false;
		if (isAdmin == null) {
			if (other.isAdmin != null)
				return false;
		} else if (!isAdmin.equals(other.isAdmin))
			return false;
		return true;
	}
	
	
	
}
