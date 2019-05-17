package com.securitymagick.domain;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

/**
 * @author leggosgirl
 *
 */
public class Post {
	private Integer id = null;
	private String title = null;
	private String imageName= null;
	private String author= null;
	private String awesomeWord=null;
	private String description=null;
	List<String> comments = null;

	/**
	 * 
	 */
	public Post() {
		comments = new ArrayList<>();
	}

	/**
	 * @param id
	 * @param title
	 * @param imageName
	 * @param author
	 * @param comments
	 */
	public Post(Integer id, String title, String imageName, String author,
			List<String> comments) {
		this.id = id;
		this.title = title;
		this.imageName = imageName;
		this.author = author;
		this.comments = new ArrayList<>(); 
		if (comments != null) {
			for (String c: comments) {
				this.comments.add(c);
			}
		}
		String words[] = title.split("\\s+");
		Random rand = new Random();
		this.awesomeWord = words[rand.nextInt(words.length)].toUpperCase();
	}
	
	

	/**
	 * @param id
	 * @param title
	 * @param imageName
	 * @param author
	 * @param description
	 * @param comments
	 */
	public Post(Integer id, String title, String imageName, String author, String description, List<String> comments) {
		this(id, title, imageName, author, comments);
		this.description = description;	
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the awesomeWord
	 */
	public String getAwesomeWord() {
		return awesomeWord;
	}	
	
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
		String words[] = title.split("\\s+");
		Random rand = new Random();
		awesomeWord = words[rand.nextInt(words.length)].toUpperCase();		
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the comments
	 */
	public List<String> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public void addComment(String comment) {
		if (comments == null) {
			comments = new ArrayList<>();
		}
		comments.add(comment);
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", imageName="
				+ imageName + ", author=" + author + ", Comments=" + comments
				+ "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((imageName == null) ? 0 : imageName.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		Post other = (Post) obj;
		if (comments == null) {
			if (other.comments != null) {
				return false;
			}
		} else if (!comments.equals(other.comments)) {
			return false;
		}
		if (author == null) {
			if (other.author != null) {
				return false;
			}
		} else if (!author.equals(other.author)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (imageName == null) {
			if (other.imageName != null) {
				return false;
			}
		} else if (!imageName.equals(other.imageName)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

	
}
