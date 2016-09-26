package com.eshop.trash;

import javax.servlet.http.HttpServletRequest;

public class Pagination {

	private final int firstPage = 1;
	private int itemsPerPage = 5;
	
	private int currentPage;
	private int noOfPages;
	private String categoryName;
	
	public Pagination()	{
	}
	
	public Pagination(HttpServletRequest request) {
		this.currentPage = this.firstPage;
		Integer page = (Integer) request.getAttribute("currentPage");
		if (page != null) {
			this.currentPage = page;
		}
	}
	
	public Pagination(Integer currentPage, Integer noOfPages, String categoryName) {
		
	}
	
	public void setNoOfPages(long noOfRecords) {
		this.noOfPages = (int) Math.ceil(noOfRecords * 1.0 / this.itemsPerPage);
	}
	
	
}
