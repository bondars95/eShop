package com.eshop.main;

public enum SortParams {

	NEW(" ORDER BY p.id DESC", "novelties"),
	PRICE(" ORDER BY p.price", "price ascending"),
	PRICEREV(" ORDER BY p.price DESC", "price descending"),
	NAME(" ORDER BY p.productName", "name");

	private final String sortParam;
	private final String description;
	
	SortParams(String sortParam, String description) {
		this.sortParam = sortParam;
		this.description = description;
	}

	public String getSortParam() {
		return sortParam;
	}

	public String getDescription() {
		return description;
	}
	
}
