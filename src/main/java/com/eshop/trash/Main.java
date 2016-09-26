package com.eshop.trash;

import com.eshop.main.SortParams;
import com.eshop.main.Tree;
import com.eshop.main.category.Category;
import com.eshop.main.category.CategoryService;
import com.eshop.main.ordersDetails.OrdersDetails;
import com.eshop.main.ordersDetails.OrdersDetailsService;
import com.eshop.main.product.Product;
import com.eshop.main.product.ProductService;
import com.eshop.main.user.User;
import com.eshop.main.user.UserService;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.multipart.MultipartFile;


public class Main {

	public static void setSubCategories(List<Category> tree, int num) { // this method
																	// creates
																	// list with
		ApplicationContext appContext = new ClassPathXmlApplicationContext("application-config.xml");
		CategoryService categoryService = appContext.getBean(CategoryService.class);
		
		String star = "*";
		for (Category c : tree) {
			c.setSub(categoryService.setSubForCategory(c));
			if (!c.getSub().isEmpty()) {
				// System.out.println(new String(new char[num]).replace("\0",
				// star) + c.getCategoryName());
				setSubCategories(c.getSub(), num + 1);
			} else {
				// System.out.println(new String(new char[num]).replace("\0",
				// star) + c.getCategoryName());
			}

		}

	}

	public static ArrayList<String>  printTreeJsp(List<Category> tree,ArrayList<String> array) {
		// this method prints for jsp
		ArrayList<String> jspText = array;
		for (Category c : tree) {
			if (!c.getSub().isEmpty()) {
				jspText.add("<li>" + c.getCategoryName());
				jspText.add("<ul>");
				printTreeJsp(c.getSub(),jspText);
				jspText.add("</ul>");
				jspText.add("</li>");
			} else {
				jspText.add("<li><a href='#'>" + c.getCategoryName() + "</a></li>");
			}
		}
		return jspText;

	}
	
	

	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("application-config.xml");

		ProductService productService = appContext.getBean(ProductService.class);
		CategoryService categoryService = appContext.getBean(CategoryService.class);
		UserService usersService = appContext.getBean(UserService.class);
		OrdersDetailsService ordersDetailsService = appContext.getBean(OrdersDetailsService.class);
		
		System.out.println(productService.getNoOfPages(5, "ALL"));
		
		
//		List<Product> fullList = productService.showAll();
//		System.out.println("All products list");
//		fullList.forEach(p -> System.out.println(p.toString()));
//		
		
		
//		int page = 2;
//		int max = 5;
//		List<OrdersDetails> productsList = ordersDetailsService.getSome(page, max);
//		System.out.println("List from " + page + " with " + max + " elements");
//		productsList.forEach(p -> System.out.println(p.toString()));
		
//		List<Category> listCategory = categoryService.showMainNode();
//		setSubCategories(listCategory, 1);
//		printTreeJsp(listCategory,new ArrayList<>()).forEach(System.out::println);
		
		// categoryService.removeCategory(2);



//		Mailer mm = (Mailer) appContext.getBean("mailMail");
//		mm.sendMail("bondar.s1995@gmail.com", "eugene.yarmack@gmail.com", "Testing123",
//				"Testing only \n\n Hello Spring Email Sender");
//		
//		Users user = usersService.isValidUser("a.dedov@gmail.com", "123456");
//		
//		System.out.println(user != null ? user.toString() : "nothing!");
		

		/*ApplicationContext appContext = new ClassPathXmlApplicationContext("application-config.xml");
		Mailer mm = (Mailer) appContext.getBean("mailMail");
		mm.sendMail("bondar.s1995@gmail.com", "eugene.yarmack@gmail.com", "Testing123",
				"Testing only \n\n Hello Spring Email Sender");*/

	/*	UsersService usersService = appContext.getBean(UsersService.class);
		
		Users user = usersService.isValidUser("a.dedov@gmail.com", "123456");
		
		System.out.println(user != null ? user.toString() : "nothing!");*/
//		String message = new String();
//		message = String.format("Name %1$s Price %2$5.2f  Qty %3$3d Total %2$5.2f*%3$3d ", "ball",12.12,5);
//		System.out.println(message);

	}

}
