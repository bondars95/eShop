package com.eshop.main;

public enum PictureType {
	ARTICLE("article_"),
	BANNER("banner_"),
	PRODUCT("_prod_");
	
	private final String pictureType;
	
	PictureType(String pictureType) {
		this.pictureType = pictureType;
	}

	public String getPictureType() {
		return this.pictureType;
	}
	
}
