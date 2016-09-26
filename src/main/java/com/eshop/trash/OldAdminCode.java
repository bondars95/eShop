package com.eshop.trash;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eshop.main.product.Product;

public class OldAdminCode {

	
//	@RequestMapping(value = "/admin/newProduct", method = RequestMethod.GET)
//	public ModelAndView newProduct(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView model = null;
//		model = new ModelAndView("newProduct");
//		model.addObject("product", new Product());
//		return model;
//	}
//
//	
//	@RequestMapping(value = "/admin/addProduct", method = RequestMethod.POST)
//	public ModelAndView addProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult,
//			HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		ModelAndView model = null;
//		if (bindingResult.hasErrors()) {
//			model = new ModelAndView("newProduct");
//			return model;
//		}
//		int id = Integer.valueOf(request.getParameter("categorySelected"));
//		product.setCategory(categoryService.findById(id));
//		productService.saveProduct(product);
//		model = new ModelAndView("uploadfile");
//		model.addObject("productId", product.getId());
//		return model;
//	}
	
	
//	@RequestMapping(value = "/admin/savefiles{productId}", method = RequestMethod.POST)
//	public String saveFiles(@PathVariable String productId, @ModelAttribute("uploadForm") FileUpload uploadForm,
//			Model map, HttpServletRequest request, HttpServletResponse response)
//			throws IllegalStateException, IOException {
//		// creating directory to save
//		String pathToMeta = request.getServletContext().getRealPath("/");
//		String projectName = request.getContextPath().replace("/", "");
//		String[] path = pathToMeta.split(projectName);
//		String saveDirectory = productImagePath;
//
//		List<MultipartFile> userFiles = uploadForm.getFiles();
//		File[] productImages = ((File[]) request.getSession().getAttribute("imagesList"));
//		List<String> fileNames = new ArrayList<String>();
//
//		if (null != userFiles && userFiles.size() > 0) {
//			int i = 1;
//			for (MultipartFile multipartFile : userFiles) {
//
//				String fileName = multipartFile.getOriginalFilename();
//				if (!"".equalsIgnoreCase(fileName)) {
//					for (File f : productImages) {
//						if (f.toString().equals(saveDirectory + productId + "img_" + i + ".jpg")) {
//							i++;
//						}
//					}
//					multipartFile.transferTo(new File(saveDirectory + productId + "img_" + i + ".jpg")); // !!!CONSTANTA
//																								// //
//																								// const
//					fileNames.add(fileName);
//					i++;
//				}
//			}
//		}
//		// adding list of files to display for user
//		map.addAttribute("files", fileNames);
//		return "uploadfilesuccess";
//	}	
	
//	// all products admin
//@RequestMapping(value = "/admin/products", method = RequestMethod.GET)
//public ModelAndView showProductsForAdmin(HttpServletRequest request, HttpServletResponse response,
//		@ModelAttribute("product") Product product) {
//	ModelAndView model = null;
//	List<Product> listOfProducts = productService.showAll();
//	model = new ModelAndView("adminProducts");
//	model.addObject("product", new Product());
//	model.addObject("listOfProducts", listOfProducts);
//	return model;
//}
//
//// product details admin
//@RequestMapping(value = "/admin/product{productId}", method = RequestMethod.GET)
//public ModelAndView showProductsForAdmin(@PathVariable String productId, HttpServletRequest request,
//		HttpServletResponse response, @ModelAttribute("product") Product product) {
//	ModelAndView model = null;
//	List<Product> listOfProducts = new ArrayList<Product>();
//	listOfProducts.add(productService.findById(Integer.valueOf(productId)));
//	model = new ModelAndView("adminProducts");
//	model.addObject("product", new Product());
//	model.addObject("listOfProducts", listOfProducts);
//	return model;
//}
//
//// edit product editProduct${product.id}
//@RequestMapping(value = "/admin/editProduct{productId}", method = RequestMethod.GET)
//public ModelAndView editProduct(@PathVariable String productId, HttpServletRequest request,
//		HttpServletResponse response, @ModelAttribute("product") Product product) {
//	ModelAndView model = null;
//	model = new ModelAndView("newProduct");
//	model.addObject("product", productService.findById(Integer.valueOf(productId)));
//	return model;
//}
//
//// edit product deleteProduct${product.id}
//@RequestMapping(value = "/admin/deleteProduct{productId}", method = RequestMethod.GET)
//public ModelAndView deleteProduct(@PathVariable String productId, HttpServletRequest request,
//		HttpServletResponse response, @ModelAttribute("product") Product product) {
//	ModelAndView model = null;
//	HttpSession session = request.getSession();
//	File[] productImages = (File[]) session.getAttribute("imagesList");
//	for (File img : productImages) {
//		if (img.toString().indexOf(productId + "img") != -1) {
//			new File(img.toString()).delete();
//		}
//	}
//	model = new ModelAndView("adminProducts");
//	productService.removeProduct(Integer.valueOf(productId));
//	List<Product> listOfProducts = productService.showAll();
//	model.addObject("product", new Product());
//	model.addObject("listOfProducts", listOfProducts);
//	return model;
//}
//
//// all ordersDetails admin
//@RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
//public ModelAndView showOrdersForAdmin(HttpServletRequest request, HttpServletResponse response,
//		@ModelAttribute("ordersDetails") OrdersDetails ordersDetails) {
//	ModelAndView model = null;
//	List<OrdersDetails> listOfOrders = orderDetailsService.showAll();
//	model = new ModelAndView("adminOrders");
//	model.addObject("ordersDetails", new OrdersDetails());
//	model.addObject("listOfOrders", listOfOrders);
//	return model;
//}
////new order
//@RequestMapping(value = "/admin/newOrder", method = RequestMethod.GET)
//public ModelAndView newOrder(HttpServletRequest request, HttpServletResponse response) {
//	ModelAndView model = null;
//	model = new ModelAndView("newOrder");
//	model.addObject("order", new OrdersDetails());
//	return model;
//}
//// edit order
//@RequestMapping(value = "/admin/editOrder{orderId}", method = RequestMethod.GET)
//public ModelAndView editUser(@PathVariable String orderId, HttpServletRequest request, HttpServletResponse response,
//		@ModelAttribute("order") OrdersDetails order) {
//	ModelAndView model = null;
//	OrdersDetails currentUser = orderDetailsService.findById(Integer.valueOf(orderId));
//	model = new ModelAndView("newOrder");
//	model.addObject("order", currentUser);
//	return model;
//}
//
//// delete order
//@RequestMapping(value = "/admin/deleteOrder{orderId}", method = RequestMethod.GET)
//public ModelAndView deleteUser(@PathVariable String orderId, HttpServletRequest request,
//		HttpServletResponse response, @ModelAttribute("order") OrdersDetails order) {
//	orderDetailsService.removeOrdersDetails(Integer.valueOf(orderId));
//	return showOrdersForAdmin(request, response, new OrdersDetails());
//}
//
//// add products
//@RequestMapping(value = "/admin/addOrder", method = RequestMethod.POST)
//public ModelAndView addOrder(@Valid @ModelAttribute("ordersDetails") OrdersDetails ordersDetails,
//		BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
//	ModelAndView model = null;
//	if (bindingResult.hasErrors()) {
//		model = new ModelAndView("newOrder");
//		return model;
//	}
//	int productId= ordersDetails.getProduct().getId();
//	int userId= ordersDetails.getUser().getId();
//	System.out.println(userId);
//	ordersDetails.setProduct(productService.findById(productId));
//	ordersDetails.setUser(usersService.findById(userId));
//	orderDetailsService.saveOrdersDetails(ordersDetails);
//	return showOrdersForAdmin(request, response, new OrdersDetails());
//	// binding result is not working (maybe proble of css)
//}
//
//// user orders
//@RequestMapping(value = "admin/ordersUser{userId}", method = RequestMethod.GET)
//public ModelAndView showUserOrders(@PathVariable String userId, HttpServletRequest request,
//		HttpServletResponse response, @ModelAttribute("ordersDetails") OrdersDetails ordersDetails) {
//	ModelAndView model = null;
//	List<OrdersDetails> listOfOrders = orderDetailsService.findUserOrders(Integer.valueOf(userId));
//	model = new ModelAndView("adminOrders");
//	model.addObject("ordersDetails", new OrdersDetails());
//	model.addObject("listOfOrders", listOfOrders);
//	return model;
//}
//
//// user details for product
//@RequestMapping(value = "/admin/productOrders{productId}", method = RequestMethod.GET)
//public ModelAndView showProductsForAdmin(@PathVariable String productId, HttpServletRequest request,
//		HttpServletResponse response, @ModelAttribute("ordersDetails") OrdersDetails ordersDetails) {
//	ModelAndView model = null;
//	List<OrdersDetails> listOfOrders = orderDetailsService.findProductOrders(Integer.valueOf(productId));
//	model = new ModelAndView("adminOrders");
//	model.addObject("ordersDetails", new OrdersDetails());
//	model.addObject("listOfOrders", listOfOrders);
//	return model;
//}
//
//// all users admin
//@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
//public ModelAndView showUsersForAdmin(HttpServletRequest request, HttpServletResponse response,
//		@ModelAttribute("user") Users user) {
//	ModelAndView model = null;
//	List<Users> listOfUsers = usersService.showAll();
//	model = new ModelAndView("adminUsers");
//	model.addObject("user", new Users());
//	model.addObject("listOfUsers", listOfUsers);
//	return model;
//}
//
//// new user
//@RequestMapping(value = "/admin/newUser", method = RequestMethod.GET)
//public ModelAndView editUsers(HttpServletRequest request, HttpServletResponse response,
//		@ModelAttribute("user") Users user) {
//	ModelAndView model = null;
//	model = new ModelAndView("newUser");
//	model.addObject("user", new Users());
//	return model;
//}
//
//// edit user
//@RequestMapping(value = "/admin/editUser{userId}", method = RequestMethod.GET)
//public ModelAndView editUser(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response,
//		@ModelAttribute("user") Users user) {
//	ModelAndView model = null;
//	Users currentUser = usersService.findById(Integer.valueOf(userId));
//	model = new ModelAndView("newUser");
//	model.addObject("user", currentUser);
//	return model;
//}
//
//// delete user
//@RequestMapping(value = "/admin/deleteUser{userId}", method = RequestMethod.GET)
//public ModelAndView deleteUser(@PathVariable String userId, HttpServletRequest request,
//		HttpServletResponse response, @ModelAttribute("user") Users user) {
//	usersService.removeUsers(Integer.valueOf(userId));
//	return showUsersForAdmin(request, response, new Users());
//}
//
//// change user Status
//
//@RequestMapping(value = "/admin/changeStatus{userId}", method = RequestMethod.GET)
//public ModelAndView showUsersForAdmin(@PathVariable String userId, HttpServletRequest request,
//		HttpServletResponse response, @ModelAttribute("user") Users user) {
//	ModelAndView model = null;
//	Users userChange = usersService.findById(Integer.valueOf(userId));
//	userChange.setRole(userChange.getRole() * -1);
//	usersService.saveUsers(userChange);
//	return showUsersForAdmin(request, response, new Users());
//}
//
//// addUser
//@RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
//public ModelAndView addProduct(@Valid @ModelAttribute("user") Users user, BindingResult bindingResult,
//		HttpServletRequest request, HttpServletResponse response) {
//	ModelAndView model = null;
//	if (bindingResult.hasErrors()) {
//		model = new ModelAndView("newUser");
//		return model;
//	}
//	usersService.saveUsers(user);
//	return showUsersForAdmin(request, response, new Users());
//}
//
//// user details admin
//@RequestMapping(value = "/admin/user{userId}", method = RequestMethod.GET)
//public ModelAndView showProductsForAdmin(@PathVariable String userId, HttpServletRequest request,
//		HttpServletResponse response, @ModelAttribute("user") Users user) {
//	ModelAndView model = null;
//	List<Users> listOfUsers = new ArrayList<Users>();
//	listOfUsers.add(usersService.findById(Integer.valueOf(userId)));
//	model = new ModelAndView("adminUsers");
//	model.addObject("user", new Users());
//	model.addObject("listOfUsers", listOfUsers);
//	return model;
//}
//
//@RequestMapping(value = "/admin/deleteImg{productId}", method = RequestMethod.POST)
//public ModelAndView addProduct(@ModelAttribute("product") Product product, @PathVariable String productId,
//		HttpServletRequest request, HttpServletResponse response) {
//	ModelAndView model = null;
//	HttpSession session = request.getSession();
//	File img = new File(request.getParameter("file"));
//	img.delete();
//	model = new ModelAndView("uploadfile");
//	model.addObject("productId", productId);
//	model.addObject("product", productService.findById(Integer.valueOf(productId)));
//	File[] files = new File(productImagePath).listFiles();
//	session.setAttribute("imagesList", files);
//	return model;
//}
	
//	// edit product deleteProduct${product.id}
//	@RequestMapping(value = "/deleteProduct{productId}", method = RequestMethod.GET)
//	public ModelAndView deleteProduct(@PathVariable String productId, HttpServletRequest request,
//			HttpServletResponse response, @ModelAttribute("product") Product product) {
//		ModelAndView model = null;
//		HttpSession session = request.getSession();
//		File[] productImages = (File[]) session.getAttribute("imagesList");
//		for (File img : productImages) {
//			if (img.toString().indexOf(productId + "img") != -1) {
//				new File(img.toString()).delete();
//			}
//		}
//		model = new ModelAndView("adminProducts");
//		productService.removeProduct(Integer.valueOf(productId));
//		List<Product> listOfProducts = productService.showAll();
//		model.addObject("product", new Product());
//		model.addObject("listOfProducts", listOfProducts);
//		return model;
//	}
	
}
