//package com.eshop.trash;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.eshop.main.ordersDetails.OrdersDetails;
//import com.eshop.main.ordersDetails.OrdersDetailsService;
//import com.eshop.main.product.Product;
//import com.eshop.main.product.ProductService;
//import com.eshop.main.users.Users;
//import com.eshop.main.users.UsersService;
//
//@Component
//public class PaginationBean {
//
//	@Autowired
//	ProductService productService;
//
//	@Autowired
//	UsersService usersService;
//	
//	@Autowired
//	OrdersDetailsService ordersDetailsService;
//	
//	private int firstProductPage = 1;
//	private int productsPerPage = 5;
//	
//	private int firstUserPage = 1;
//	private int usersPerPage = 20;
//	private int firstOrderPage = 1;
//	private int ordersPerPage = 20;
//	
//	public PaginationBean() { }
//
//
//	public List<Product> getPartOfProduct(HttpServletRequest request, int currentPage, 
//										  String categoryName) {
//		//first page
//		int page = this.firstProductPage;
//		//getting number of page from request
//		if (currentPage != 0) 
//			page = currentPage;
//		long noOfRecords = productService.getNoOfRecords();
//		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / this.productsPerPage);
//		
//		//saving number of current page to request attributes - temperately data
//		request.setAttribute("currentPage", page);
//		//saving count pages to session attributes - persistent data
//		request.getSession().setAttribute("productsNoOfPages", noOfPages);
//		
//		return productService.getPart(page, this.productsPerPage, categoryName);
//	}
//
//
//	public List<Users> getPartOfUsers(HttpServletRequest request, String currentPage) {
//		//first page
//		int page = this.firstUserPage;
//		//getting number of page from request
//		if (!currentPage.equals("")) {
//			page = Integer.parseInt(currentPage);
//		}
//		long noOfRecords = usersService.getNoOfRecords();
//		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / this.productsPerPage);
//		//saving number of current page to request attributes - temperately data
//		request.setAttribute("usersCurrentPage", page);
//		//saving count pages to session attributes - persistent data
//		request.getSession().setAttribute("usersNoOfPages", noOfPages);
//		return usersService.getSome(page, this.usersPerPage);
//	}
//	
//	
//	public List<OrdersDetails> getPartOfOrders(HttpServletRequest request, String currentPage) {
//		//first page
//		int page = this.firstOrderPage;
//		//getting number of page from request
//		if (!currentPage.equals("")) {
//			page = Integer.parseInt(currentPage);
//		}
//		long noOfRecords = ordersDetailsService.getNoOfRecords();
//		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / this.ordersPerPage);
//		//saving number of current page to request attributes - temperately data
//		request.setAttribute("ordersCurrentPage", page);
//		//saving count pages to session attributes - persistent data
//		request.getSession().setAttribute("ordersNoOfPages", noOfPages);
//		return ordersDetailsService.getSome(page, this.ordersPerPage);
//	}
//
//	
//	
//	public void setFirstProductPage(int firstProductPage) {
//		this.firstProductPage = firstProductPage;
//	}
//
//
//
//	public void setProductsPerPage(int productsPerPage) {
//		this.productsPerPage = productsPerPage;
//	}
//
//
//
//	public void setFirstUserPage(int firstUserPage) {
//		this.firstUserPage = firstUserPage;
//	}
//
//
//
//	public void setUsersPerPage(int usersPerPage) {
//		this.usersPerPage = usersPerPage;
//	}
//}
