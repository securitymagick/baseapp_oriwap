package com.securitymagick.domain;

public class SiteLook {
	private String year;
	private String url;
	private String title;
	private String fontColor;
	private String glyphSize;
	private String logoSize;
	private String menuCols;
	private String preLogoText;
	private String logoGlyphOne;
	private String logoGlyph2;
	private String logoGlyph3;
	private String logoGlyph4;
	private String labelCols;
	private String formCols;
	private String favorite;
	private String addWhat = "Post";
	
	/**
	 * Default Constructor
	 */
	public SiteLook() {
	}

	/**
	 * @param title
	 * @param fontColor
	 * @param glyphSize
	 * @param logoSize
	 * @param menuCols
	 * @param preLogoText
	 * @param logoGlyphOne
	 * @param logoGlyph2
	 * @param logoGlyph3
	 * @param logoGlyph4
	 * @param labelCols
	 * @param formCols
	 */
	public SiteLook(String title, String fontColor, String glyphSize, String logoSize, String menuCols,
			String preLogoText, String logoGlyphOne, String logoGlyph2, String logoGlyph3, String logoGlyph4,
			String labelCols, String formCols) {
		this.title = title;
		this.fontColor = fontColor;
		this.glyphSize = glyphSize;
		this.logoSize = logoSize;
		this.menuCols = menuCols;
		this.preLogoText = preLogoText;
		this.logoGlyphOne = logoGlyphOne;
		this.logoGlyph2 = logoGlyph2;
		this.logoGlyph3 = logoGlyph3;
		this.logoGlyph4 = logoGlyph4;
		this.labelCols = labelCols;
		this.formCols = formCols;
	}

	public SiteLook(String title, String fontColor, String glyphSize, String logoSize, String menuCols,
			String preLogoText, String logoGlyphOne, String logoGlyph2, String logoGlyph3, String logoGlyph4,
			String labelCols, String formCols, String url, String year, String favorite) {
		this.title = title;
		this.fontColor = fontColor;
		this.glyphSize = glyphSize;
		this.logoSize = logoSize;
		this.menuCols = menuCols;
		this.preLogoText = preLogoText;
		this.logoGlyphOne = logoGlyphOne;
		this.logoGlyph2 = logoGlyph2;
		this.logoGlyph3 = logoGlyph3;
		this.logoGlyph4 = logoGlyph4;
		this.labelCols = labelCols;
		this.formCols = formCols;
		this.url = url;
		this.year = year;
		this.favorite = favorite;
	}
	
	
	
	/**
	 * @param year
	 * @param url
	 * @param title
	 * @param fontColor
	 * @param glyphSize
	 * @param logoSize
	 * @param menuCols
	 * @param preLogoText
	 * @param logoGlyphOne
	 * @param logoGlyph2
	 * @param logoGlyph3
	 * @param logoGlyph4
	 * @param labelCols
	 * @param formCols
	 * @param favorite
	 * @param addWhat
	 */
	public SiteLook(String year, String url, String title, String fontColor, String glyphSize, String logoSize,
			String menuCols, String preLogoText, String logoGlyphOne, String logoGlyph2, String logoGlyph3,
			String logoGlyph4, String labelCols, String formCols, String favorite, String addWhat) {
		this.year = year;
		this.url = url;
		this.title = title;
		this.fontColor = fontColor;
		this.glyphSize = glyphSize;
		this.logoSize = logoSize;
		this.menuCols = menuCols;
		this.preLogoText = preLogoText;
		this.logoGlyphOne = logoGlyphOne;
		this.logoGlyph2 = logoGlyph2;
		this.logoGlyph3 = logoGlyph3;
		this.logoGlyph4 = logoGlyph4;
		this.labelCols = labelCols;
		this.formCols = formCols;
		this.favorite = favorite;
		this.addWhat = addWhat;
	}
	
	

	/**
	 * @return the addWhat
	 */
	public final String getAddWhat() {
		return addWhat;
	}

	/**
	 * @param addWhat the addWhat to set
	 */
	public final void setAddWhat(String addWhat) {
		this.addWhat = addWhat;
	}

	/**
	 * @return the favorite
	 */
	public final String getFavorite() {
		return favorite;
	}

	/**
	 * @param favorite the favorite to set
	 */
	public final void setFavorite(String favorite) {
		this.favorite = favorite;
	}

	/**
	 * @return the year
	 */
	public final String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public final void setYear(String year) {
		this.year = year;
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
	 * @return the title
	 */
	public final String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public final void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the fontColor
	 */
	public final String getFontColor() {
		return fontColor;
	}

	/**
	 * @param fontColor the fontColor to set
	 */
	public final void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	/**
	 * @return the glyphSize
	 */
	public final String getGlyphSize() {
		return glyphSize;
	}

	/**
	 * @param glyphSize the glyphSize to set
	 */
	public final void setGlyphSize(String glyphSize) {
		this.glyphSize = glyphSize;
	}

	/**
	 * @return the logoSize
	 */
	public final String getLogoSize() {
		return logoSize;
	}

	/**
	 * @param logoSize the logoSize to set
	 */
	public final void setLogoSize(String logoSize) {
		this.logoSize = logoSize;
	}

	/**
	 * @return the menuCols
	 */
	public final String getMenuCols() {
		return menuCols;
	}

	/**
	 * @param menuCols the menuCols to set
	 */
	public final void setMenuCols(String menuCols) {
		this.menuCols = menuCols;
	}

	/**
	 * @return the preLogoText
	 */
	public final String getPreLogoText() {
		return preLogoText;
	}

	/**
	 * @param preLogoText the preLogoText to set
	 */
	public final void setPreLogoText(String preLogoText) {
		this.preLogoText = preLogoText;
	}

	/**
	 * @return the logoGlyphOne
	 */
	public final String getLogoGlyphOne() {
		return logoGlyphOne;
	}

	/**
	 * @param logoGlyphOne the logoGlyphOne to set
	 */
	public final void setLogoGlyphOne(String logoGlyphOne) {
		this.logoGlyphOne = logoGlyphOne;
	}

	/**
	 * @return the logoGlyph2
	 */
	public final String getLogoGlyph2() {
		return logoGlyph2;
	}

	/**
	 * @param logoGlyph2 the logoGlyph2 to set
	 */
	public final void setLogoGlyph2(String logoGlyph2) {
		this.logoGlyph2 = logoGlyph2;
	}

	/**
	 * @return the logoGlyph3
	 */
	public final String getLogoGlyph3() {
		return logoGlyph3;
	}

	/**
	 * @param logoGlyph3 the logoGlyph3 to set
	 */
	public final void setLogoGlyph3(String logoGlyph3) {
		this.logoGlyph3 = logoGlyph3;
	}

	/**
	 * @return the logoGlyph4
	 */
	public final String getLogoGlyph4() {
		return logoGlyph4;
	}

	/**
	 * @param logoGlyph4 the logoGlyph4 to set
	 */
	public final void setLogoGlyph4(String logoGlyph4) {
		this.logoGlyph4 = logoGlyph4;
	}

	/**
	 * @return the labelCols
	 */
	public final String getLabelCols() {
		return labelCols;
	}

	/**
	 * @param labelCols the labelCols to set
	 */
	public final void setLabelCols(String labelCols) {
		this.labelCols = labelCols;
	}

	/**
	 * @return the formCols
	 */
	public final String getFormCols() {
		return formCols;
	}

	/**
	 * @param formCols the formCols to set
	 */
	public final void setFormCols(String formCols) {
		this.formCols = formCols;
	}
	
	
	
}
