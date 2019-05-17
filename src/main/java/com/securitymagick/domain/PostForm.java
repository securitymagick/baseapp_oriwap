package com.securitymagick.domain;

/**
 * @author leggosgirl
 *
 */
public class PostForm {

	private String title;
	private String author;
	private String imageName;
	private String description;
	private String csrfToken = "";
	

	public PostForm() {
		super();
	}
	
	
	public PostForm(String title, String author, String imageName) {
		super();
		this.title = title;
		this.author = author;
		this.imageName = imageName;
	}
	
	
	
	/**
	 * @param title
	 * @param author
	 * @param imageName
	 * @param description
	 */
	public PostForm(String title, String author, String imageName, String description) {
		this(title, author, imageName);
		this.description = description;
	}

	

	/**
	 * @param title
	 * @param author
	 * @param imageName
	 * @param description
	 * @param csrfToken
	 */
	public PostForm(String title, String author, String imageName, String description, String csrfToken) {
		this.title = title;
		this.author = author;
		this.imageName = imageName;
		this.description = description;
		this.csrfToken = csrfToken;
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
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public final void setDescription(String description) {
		this.description = description;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "ZooBabyForm [" + "title=" + title + ", author=" + author + "imageName=" + imageName + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((imageName == null) ? 0 : imageName.hashCode());
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
		PostForm other = (PostForm) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (imageName == null) {
			if (other.imageName != null)
				return false;
		} else if (!imageName.equals(other.imageName))
			return false;		
		return true;
	}
	
	
	
}
