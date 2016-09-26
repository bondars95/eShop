package com.eshop.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshop.main.FileUpload;
import com.eshop.main.Picture;
import com.eshop.main.Tree;
import com.eshop.main.category.Category;
import com.eshop.main.category.CategoryService;
import com.eshop.main.orderItems.OrderItems;
import com.eshop.main.orderItems.OrderItemsService;
import com.eshop.main.ordersDetails.OrdersDetails;
import com.eshop.main.ordersDetails.OrdersDetailsService;
import com.eshop.main.product.Product;
import com.eshop.main.product.ProductService;
import com.eshop.main.user.*;

@Controller
@RequestMapping(value="/admin")
public class AdminGoodsController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private OrdersDetailsService orderDetailsService;
	@Autowired
	private OrderItemsService orderItemsService;
	@Autowired
	private Picture picture;
	@Autowired
	private Tree tree;


	@ModelAttribute("user")
	public User addBeanToRequestScope(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			user = new User();
			session.setAttribute("user", user);
		}
		//tree to view
		session.setAttribute("categoryTree", tree.createDropDown("newProduct"));
		return user;
	}

	
	@RequestMapping(value = "/newProduct", method = RequestMethod.GET)
	public ModelAndView createProduct() {
		ModelAndView model = new ModelAndView("newProduct");
		model.addObject("product", new Product());
		return model;
	}

	
	/**
	 *Add new product @POST
	 * @param product
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView addProduct(	@Valid @ModelAttribute("product") Product product, 
									BindingResult bindingResult) {
		ModelAndView model = null;
		if (bindingResult.hasErrors()) {
			model = new ModelAndView("newProduct");
			return model;
		}
		String categoryChoise = product.getCategory().getCategoryName();
		product.setCategory(categoryService.findByName(categoryChoise));
		productService.saveProduct(product);
		int productId = product.getId();
		return new ModelAndView("redirect:/admin/editProduct&" + productId);
	}
	

	//show all products for admin
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showProductsForAdmin() {
		ModelAndView model = null;
		List<Product> listOfProducts = productService.showAll();
		model = new ModelAndView("adminProducts");
		model.addObject("listOfProducts", listOfProducts);
		return model;
	}

	
	/**
	 * Edit product by Id
	 * @param productId - path variable (int)
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/editProduct&{productId}", method = RequestMethod.GET)
	public ModelAndView editProduct(@PathVariable @NumberFormat int productId) {
		ModelAndView mav = new ModelAndView("newProduct");
		Product product = productService.findById(productId);
		mav.addObject("product", product);
		mav.addObject("imagesList", picture.getCertainImgNames(productId).stream()
						.map(File::getName).collect(Collectors.toList()));
		return mav;
	}

	
	/**
	 *Remove product by Id
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/deleteProduct&{productId}", method = RequestMethod.GET)
	public ModelAndView deleteProduct(	@PathVariable @NumberFormat int productId) {
		productService.removeProduct(productId);
		picture.deleteAllProductPictures(productId);
		return showProductsForAdmin();
	}

	
	/**
	 *Product details admin (inline view)
	 * @param productId
	 * @return
	 */
	//remake in future
	@RequestMapping(value = "/product{productId}", method = RequestMethod.GET)
	public ModelAndView showProductById(@PathVariable @NumberFormat Integer productId) {
			List<Product> listOfProducts = new ArrayList<Product>();
			listOfProducts.add(productService.findById(productId));
			ModelAndView model = new ModelAndView("adminProducts");
			model.addObject("listOfProducts", listOfProducts);
			return model;
		}
	
	
	/**
	 *How tree of all categories
	 * @return
	 */
	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	public ModelAndView showTreeOfCategories() {
		ModelAndView model = new ModelAndView("Tree");
		model.addObject("categoryEditTree", tree.createTreeEdit());
		return model;
	}
	
	
	/**New item of tree of categories
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "/createCategory", method = RequestMethod.GET)
	public ModelAndView createCategory(@RequestParam(required = false) @NumberFormat Integer parentId) {
		ModelAndView model = new ModelAndView("newCategory");
		Category category = new Category();
		if (parentId != null && parentId >= 0) {
			Category parent = categoryService.findById(parentId);
			category.setParentCategory(parent);
			category.setParentId(parentId);
		}
		model.addObject("category", category);
		return model;
	}
	
	
	/** Add category @POST
	 * @param category
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public ModelAndView addCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {
		ModelAndView model = null;
		if (bindingResult.hasErrors()) {
			model = new ModelAndView("createCategory");
			return model;
		}
		categoryService.saveCategory(category);
		return showTreeOfCategories();
	}

	
	/**Edit item
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value = "/editCategory&{categoryId}", method = RequestMethod.GET)
	public ModelAndView editCategory(@PathVariable Integer categoryId) {
		ModelAndView model = null;
		model = new ModelAndView("newCategory");
		Category category = categoryService.findById(categoryId);
		Integer parentId = category.getParentId();
		if (parentId != null) {
			category.setParentCategory(categoryService.findById(parentId));
		}
		model.addObject("category", category );
		return model;
	}
	
	
	/**Delete item
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value = "/removeCategory&{categoryId}", method = RequestMethod.GET)
	public ModelAndView deleteCategory(@PathVariable Integer categoryId) {
		ModelAndView model = new ModelAndView("Tree");
		categoryService.removeCategory(categoryId);
		model.addObject("categoryEditTree", tree.createTreeEdit());
		return model;
	}
	
	
	/**
	 * Order details for product
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/productOrders&{productId}", method = RequestMethod.GET)
	public ModelAndView showProductsForAdmin(@PathVariable @NumberFormat int productId) {
		ModelAndView model = new ModelAndView("adminOrders");
		model.addObject("listOfOrders", orderDetailsService.getOrderDetailsByProductId(productId));
		return model;
	}

	
	/**
	 * Adding new order by admin
	 * @return
	 */
	@RequestMapping(value = "/newOrder", method = RequestMethod.GET)
	public ModelAndView createOrder() {
		ModelAndView model = new ModelAndView("newOrder");
		model.addObject("order", new OrdersDetails());
		return model;
	}
	
	
	/**
	 * Add order @POST
	 * @param ordersDetails
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	public ModelAndView addOrder(@Valid @ModelAttribute("ordersDetails") OrdersDetails ordersDetails,
			BindingResult bindingResult) {
		ModelAndView model = null;
		if (bindingResult.hasErrors()) {
			model = new ModelAndView("newOrder");
			return model;
		}
		int userId = ordersDetails.getUser().getId();
		ordersDetails.setUser(userService.findById(userId));
		orderDetailsService.saveOrdersDetails(ordersDetails);
		return showOrdersForAdmin();
	}

	
	/**Build view of orders page for admin
	 * @return ModelAndView object for rendering orders page
	 */
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ModelAndView showOrdersForAdmin() {
		List<OrdersDetails> listOfOrders = orderDetailsService.showAll();
		ModelAndView model = new ModelAndView("adminOrders");
		model.addObject("listOfOrders", listOfOrders);
		return model;
	}

	
	/**
	 *Edit order by Id
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/editOrder&{orderId}", method = RequestMethod.GET)
	public ModelAndView editOrder(@PathVariable @NumberFormat Integer orderId) {
		OrdersDetails order = orderDetailsService.findById(orderId);
		ModelAndView model = new ModelAndView("newOrder");
		model.addObject("order", order);
		return model;
	}

	/**
	 * Delete order
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/deleteOrder&{orderId}", method = RequestMethod.GET)
	public ModelAndView deleteOrder(@PathVariable @NumberFormat Integer orderId) {
		orderDetailsService.removeOrdersDetails(orderId);
		return showOrdersForAdmin();
	}

	
	/**
	 * Change order Status (done/undone)
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/changeOrderStatus&{orderId}", method = RequestMethod.GET)
	public ModelAndView changeOrderStatus(@PathVariable @NumberFormat Integer orderId) {
		OrdersDetails orderChange = orderDetailsService.findById(orderId);
		orderChange.setStatus(orderChange.getStatus() * -1);
		orderDetailsService.saveOrdersDetails(orderChange);
		return showOrdersForAdmin();
	}


	/**
	 * Show detailed content of order
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/orderDetails&{orderId}", method = RequestMethod.GET)
	public ModelAndView showOrderItems(@PathVariable @NumberFormat Integer orderId) {
		ModelAndView model = new ModelAndView("orderInfo");
		OrdersDetails od = orderDetailsService.findById(orderId);
		model.addObject("orderId", orderId);
		model.addObject("listOfItems",od.getOrdersItemsCollection());
		return model;
	}


	/**
	 *New orderItem (slow update after adding)
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/newOrderItem{orderId}", method = RequestMethod.GET)
	public ModelAndView createOrderItem(@PathVariable @NumberFormat Integer orderId) {
		ModelAndView model = new ModelAndView("newOrderItem");
		OrderItems oi = new OrderItems();
		oi.setOrder(orderDetailsService.findById(orderId));
		model.addObject("orderItems", oi);
		return model;
	}

	
	/**
	 * Add orderItem (slow update after adding)
	 * @param orderItems
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/addOrderItem", method = RequestMethod.POST)
	public ModelAndView addOrderItem(@Valid @ModelAttribute("orderItems") OrderItems orderItems, 
									 BindingResult bindingResult) {
		ModelAndView model = null;
		if (bindingResult.hasErrors()) {
			System.out.println("i am here");
			model = new ModelAndView("newOrderItem");
			return model;
		}
		int productId = orderItems.getProduct().getId();
		int orderId = orderItems.getOrder().getId();
		orderItems.setProduct(productService.findById(productId));
		orderItems.setOrder(orderDetailsService.findById(orderId));
		orderItemsService.saveOrderItems(orderItems);
		return showOrderItems(orderId);
		}
	
		
	/**
	 * Edit order item 
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value = "/editItem{itemId}", method = RequestMethod.GET)
	public ModelAndView editItem(@PathVariable @NumberFormat Integer itemId) {
		OrderItems item = orderItemsService.findById(itemId);
		ModelAndView model = new ModelAndView("newOrderItem");
		model.addObject("orderItems", item);
		return model;
		}
		
	
	/**
	 *Delete order item
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value = "/deleteItem{itemId}", method = RequestMethod.GET)
	public ModelAndView deleteItem(@PathVariable @NumberFormat Integer itemId) {
		OrderItems item = orderItemsService.findById(Integer.valueOf(itemId));
		int orderId = item.getOrder().getId();
		orderItemsService.removeOrderItems(Integer.valueOf(itemId));
		return showOrderItems(orderId);
	}
	
	
	@RequestMapping(value = "/image", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getImage(@RequestParam(value = "imgName") String imgName) throws IOException {
        return picture.getPicture(imgName);
    }
	

	@RequestMapping(value = "/saveProductImg{productId}", method = RequestMethod.POST)
	public String saveFiles(@PathVariable @NumberFormat Integer productId, 
							@ModelAttribute("uploadForm") FileUpload uploadForm)
					throws IllegalStateException, IOException {
		picture.savePicture(uploadForm, productId);
		return "redirect:/admin/editProduct&" + productId;
	}
	

	@RequestMapping(value = "/deleteProdImg{productId}", method = RequestMethod.GET)
	public String deleteImg(@RequestParam(value = "imgName") String imgName,
								   @PathVariable @NumberFormat Integer productId) {
		picture.deletePicture(imgName);
		return "redirect:/admin/editProduct&" + productId;
	}
}
