package com.securitymagick.domain;

/**
 * @author leggosgirl
 *
 */
public class UpdateFavoriteForm {

	private String favorite;
	
	
	public UpdateFavoriteForm() {
		super();
	}
	
	
	public UpdateFavoriteForm(String favorite) {
		super();
		this.favorite = favorite;
	}
	
	public String getFavorite() {
		return favorite;
	}
	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}


	@Override
	public String toString() {
		return "UpdateFavoriteForm [" + "favorite=" + favorite + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((favorite == null) ? 0 : favorite.hashCode());
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
		UpdateFavoriteForm other = (UpdateFavoriteForm) obj;
		if (favorite == null) {
			if (other.favorite != null)
				return false;
		} else if (!favorite.equals(other.favorite))
			return false;
		return true;
	}
	
	
	
}
