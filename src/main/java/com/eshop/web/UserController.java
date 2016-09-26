package com.eshop.web;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshop.main.Mailer;
import com.eshop.main.Picture;
import com.eshop.main.SortParams;
import com.eshop.main.Tree;
import com.eshop.main.article.Article;
import com.eshop.main.article.ArticleService;
import com.eshop.main.orderItems.OrderItems;
import com.eshop.main.orderItems.OrderItemsService;
import com.eshop.main.ordersDetails.OrdersDetails;
import com.eshop.main.ordersDetails.OrdersDetailsService;
import com.eshop.main.product.Product;
import com.eshop.main.product.ProductService;
import com.eshop.main.product.ProductToJson;
import com.eshop.main.user.*;

@Controller
public class UserController {

	@Autowired
	private UserService usersService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrdersDetailsService orderDetailsService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private OrderItemsService orderItemsService;
	@Autowired
	private Mailer mm;
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
		
		//adding tree of categories to session
		session.setAttribute("categoryTreeMain", tree.createDropDown("main"));

		@SuppressWarnings("unchecked")
		ArrayList<Product> basketList = (ArrayList<Product>) session.getAttribute("basketList");
		if (basketList == null) {
			ArrayList<Product> emptyBasket = new ArrayList<Product>();
			session.setAttribute("basketList", emptyBasket);
		}
		return user;
	}

	
	//Methods for displaying products
	public ModelAndView buildGoodsView() {
		return buildGoodsView(null, null, SortParams.NEW);
	}
	
	
	//building mainPage view 
	public ModelAndView buildGoodsView(Integer currentPage, String categoryName, SortParams param) {
		int pageSize = 8;
		
		int noOfPages = productService.getNoOfPages(pageSize, categoryName);
		if (currentPage == null || currentPage > noOfPages || currentPage < 1) currentPage = 1;
		List<Product> list = productService.getPart(currentPage, pageSize, categoryName, param);
		
		ModelAndView mav = new ModelAndView("goods");
		mav.addObject("productList", list);
		mav.addObject("currentPage", currentPage);
		mav.addObject("noOfPages", noOfPages);
		mav.addObject("sortParams", Arrays.asList(SortParams.values()));
		return mav;
	}
	
	//index page viewing
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showMainPage(HttpServletRequest request) {
		ProductToJson.saveJsonToFile(productService.showAll(), request);
		ModelAndView mav = buildGoodsView();
		mav.setViewName("index");
		return mav;
	}	
	
	
	@RequestMapping(value = "/sortGoods", method = RequestMethod.GET)
	public String showGoods(@RequestParam(required = false) String sortParam,
			HttpSession session, HttpServletRequest request) {
		if (sortParam != null) {
			session.setAttribute("sortParam", SortParams.valueOf(sortParam));
		}
		return "redirect:" + request.getHeader("Referer");
	}
	
	
	@RequestMapping(value = "/goods", method = RequestMethod.GET)
	public ModelAndView showGoods(HttpServletRequest request, 
			@RequestParam(required = false) @NumberFormat Integer currentPage,
			@RequestParam(required = false) String category) {
		
		ProductToJson.saveJsonToFile(productService.showAll(), request);
		
		request.setAttribute("category", category);
		SortParams param = (SortParams) request.getSession().getAttribute("sortParam");
		if (param == null) {
			param = SortParams.NEW;
		}
		return buildGoodsView(currentPage, category, param);
	}
	
	
	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	public ModelAndView showArticles(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("articles");
		List<Article> list = articleService.showAll();
		modelAndView.addObject("articleList", list);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/articles&{id}", method = RequestMethod.GET)
	public ModelAndView getArticle(HttpSession session, @PathVariable @NumberFormat int id) {
		ModelAndView mav = new ModelAndView("userArticle");
		Article article = articleService.findById(id);
		mav.addObject("article", article);
		return mav;
	}
	
	
	//methods for users registration
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView showSignupForm(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("signup");
		User user = (User)session.getAttribute("user");
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	
	//methods for users registration
		@RequestMapping(value = "/signup", method = RequestMethod.POST)
		public ModelAndView signup(HttpServletRequest request, @Valid @ModelAttribute("user") User user, 
									BindingResult bindingResult) {
			ModelAndView mav = null;
			if (bindingResult.hasErrors()) {
				mav = new ModelAndView("signup");
				return mav;
			}
			if (usersService.isEmailExists(user.getEmail())) {
				mav = new ModelAndView("signup");
				mav.addObject("user", user);
				request.setAttribute("errorMessage", "Current email has already existed!");
				return mav;
			}
			user.setRole(2);
			usersService.saveUsers(user);
			return buildGoodsView();
		}


	//methods for login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, @ModelAttribute("user") User user,
							  HttpSession session, Model model) {
		User gottenUser = null;
		gottenUser = usersService.isValidUser(user.getEmail(), user.getPassword());
		session.setAttribute("user", gottenUser);
		if (gottenUser.getId() == 0) {
			return showMainPage(request).addObject("errorMessage", "Invalid login or password!!");
		}
		return new ModelAndView("redirect:/");
	}

	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpSession session) {
		User user = new User();
		session.setAttribute("user", user);
		return buildGoodsView().addObject("user", user);
	}

	//methods for displaying details of products
	@RequestMapping(value = "/product{productId}", method = RequestMethod.GET)
	public ModelAndView showProduct(@PathVariable Integer productId, HttpServletRequest request) {
		ModelAndView model = new ModelAndView("showProduct");
		model.addObject("product", productService.findById(productId));
		model.addObject("imagesList", picture.getCertainImgNames(productId).stream()
				.map(File::getName).collect(Collectors.toList()));
		return model;
	}
	
	
	@RequestMapping(value = "/buy{productId}", method = RequestMethod.GET)
	public ModelAndView buy(@PathVariable String productId, HttpSession session) {
		ModelAndView model = null;
		@SuppressWarnings("unchecked")
		ArrayList<Product> basketList = (ArrayList<Product>)session.getAttribute("basketList");
		Product productToSave = productService.findById(Integer.valueOf(productId));
		model = new ModelAndView("basket");
		for (Product p : basketList) {
			if(String.valueOf(p.getId()).equals(productId)) {
				p.setBasketQty(p.getBasketQty() + 1);
				return model;
			}
		}
		basketList.add(productToSave);	
		return model;
	}
	
	
	// new method using ajax
	@RequestMapping(value = "/addToCart", method = RequestMethod.GET)
	@ResponseBody
	public void addToCart(@RequestParam @NumberFormat Integer productId, HttpSession session) {
		@SuppressWarnings("unchecked")
		ArrayList<Product> basketList = (ArrayList<Product>) session.getAttribute("basketList");
		
		Product productToSave = productService.findById(productId);
		for (Product p : basketList) {
			if (p.getId() == productId) {
				p.setBasketQty(p.getBasketQty() + 1);
				return;
			}
		}
		basketList.add(productToSave);
	}
	
	
	@RequestMapping(value = "/basket", method = RequestMethod.GET)
	public ModelAndView showBasket() {
		//add containment
		return new ModelAndView("basket");
	}
	
	
	@RequestMapping(value = "/remove{productId}", method = RequestMethod.GET)
	public ModelAndView removeFromBasket(@PathVariable String productId, HttpSession session) {
		ModelAndView model = null;
		
		@SuppressWarnings("unchecked")
		ArrayList<Product> basketList = (ArrayList<Product>)session.getAttribute("basketList");
		Product productToRemove = new Product();
		for(Product p:basketList){
			if(p.getId() == Integer.valueOf(productId)){
				productToRemove = p;
			}
		}
		basketList.remove(productToRemove);
		session.setAttribute("basketList", basketList);
		model = new ModelAndView("basket");		
		return model;
	}
	
	
	//methods add and minus products in basket
	@RequestMapping(value = "/plusBasket", method = RequestMethod.GET)
	@ResponseBody
	public void  plusBasket(@RequestParam String productId, HttpServletRequest request) {
		changeBasketQty(productId, request, 1);
	}

	
	@RequestMapping(value = "/minusBasket", method = RequestMethod.GET)
	@ResponseBody
	public void  minusBasket(@RequestParam String productId, HttpServletRequest request) {
		changeBasketQty(productId, request, -1);
	}

	
	public void changeBasketQty(String productid, HttpServletRequest request,
			int numberToAdd) {
		int productId = Integer.valueOf(productid);
		HttpSession session = request.getSession();
		
		@SuppressWarnings("unchecked")
		ArrayList<Product> basketList = (ArrayList<Product>) session.getAttribute("basketList");
		for (Product p : basketList) {
			if ((p.getBasketQty() == 1 && numberToAdd < 1)) {
				numberToAdd=0;
			}
			if (p.getId() == (productId)) {
				p.setBasketQty(p.getBasketQty() + numberToAdd);
			}
		}
		session.setAttribute("basketList", basketList);
	}
	
	
	//buying confirmation
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public ModelAndView confirmOrder(HttpServletRequest request, HttpSession session) {
		ModelAndView model = null;
		
		@SuppressWarnings("unchecked")
		ArrayList<Product> basketList = (ArrayList<Product>) session.getAttribute("basketList");
		OrdersDetails od = new OrdersDetails();
		User user = (User) session.getAttribute("user");
		od.setOrderDate(java.sql.Date.valueOf(LocalDate.now()));
		od.setUser(user);
		orderDetailsService.saveOrdersDetails(od);
		int lineItem = 1;
		int total = 0;
		String message = new String();
		message+="\n Orderid= "+ od.getId()+"\n";
		for (Product product : basketList) {
			String qty = request.getParameter(product.getId() + "idQty");
			OrderItems item = new OrderItems();
			item.setOrder(od);
			item.setProduct(product);
			item.setLineItem(lineItem);
			int qtyInt = Integer.valueOf(qty);
			total += qtyInt*product.getPrice();
			item.setQty(qtyInt);
			message += String.format("Name %1$s Price %2$5.2f  Qty %3$3d Total %4$6.2f \n", product.getProductName(),product.getPrice(),qtyInt,
					product.getPrice()*qtyInt);
			orderItemsService.saveOrderItems(item);
			lineItem++;		
		}
		message += "\n Order total= "+ total * 10;
		message += "\n Thank you for your order!";
		model = new ModelAndView("successfullPayment");
		ArrayList<Product> emptyBasket = new ArrayList<Product>();
		session.setAttribute("basketList", emptyBasket);
		
		//test mail sending to user email
		model.addObject("adminMail", new String("bondar.s1995@gmail.com"));
		model.addObject("userMail", user.getEmail());
		model.addObject("theme", new String( od.getId()+  " order"));
		model.addObject("message", new String(message));
		return model;
		}
	
	
	
		 @RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
		 public void sendEmail(String adminMail,String userMail,String theme,String message) {
		  mm.sendMail(adminMail, userMail, theme, message);
		 }

}
