package com.securitymagick.domain;

public class MenuItem {
	public String heading;
	public String url;
	public String glyph;
	public String phrase;

	/**
	 * @param heading
	 * @param url
	 * @param glyph
	 * @param phrase
	 */
	public MenuItem(String heading, String url, String glyph, String phrase) {
		this.heading = heading;
		this.url = url;
		this.glyph = glyph;
		this.phrase = phrase;
	}

	/**
	 * @return the heading
	 */
	public final String getHeading() {
		return heading;
	}

	/**
	 * @param heading the heading to set
	 */
	public final void setHeading(String heading) {
		this.heading = heading;
	}

	/**
	 * @return the url
	 */
	public final String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public final void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the glyph
	 */
	public final String getGlyph() {
		return glyph;
	}

	/**
	 * @param glyph the glyph to set
	 */
	public final void setGlyph(String glyph) {
		this.glyph = glyph;
	}

	/**
	 * @return the phrase
	 */
	public final String getPhrase() {
		return phrase;
	}

	/**
	 * @param phrase the phrase to set
	 */
	public final void setPhrase(String phrase) {
		this.phrase = phrase;
	}

	
	

}
