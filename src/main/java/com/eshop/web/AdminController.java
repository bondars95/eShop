package com.eshop.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.servlet.ModelAndView;

import com.eshop.main.FileUpload;
import com.eshop.main.Picture;
import com.eshop.main.PictureType;
import com.eshop.main.Tree;
import com.eshop.main.article.Article;
import com.eshop.main.article.ArticleService;
import com.eshop.main.category.CategoryService;
import com.eshop.main.ordersDetails.OrdersDetails;
import com.eshop.main.ordersDetails.OrdersDetailsService;
import com.eshop.main.user.*;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private OrdersDetailsService orderDetailsService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private Picture picture;
	@Autowired
	private Tree tree;


	/**initialization method
	 * need to redo!!!
	*/
	@ModelAttribute("user")
	public User addBeanToRequestScope(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			user = new User();
			session.setAttribute("user", user);
		}
		// tree to view
		session.setAttribute("categoryTree", tree.createDropDown("newProduct"));
		return user;
	}

	
	@RequestMapping(value = "/newArticle", method = RequestMethod.GET)
	public ModelAndView createArticle() {
		ModelAndView model = new ModelAndView("newArticle");
		model.addObject("article", new Article());
		return model;
	}
	
	
	@RequestMapping(value = "/addArticle", method = RequestMethod.POST)
	public ModelAndView addArticle(@ModelAttribute("article") Article article) {
		articleService.saveArticle(article);
		return showArticles();
	}
	
	
	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	public ModelAndView showArticles() {
		ModelAndView modelAndView = new ModelAndView("adminArticles");
		List<Article> list = articleService.showAll();
		modelAndView.addObject("articleList", list);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/article&{id}", method = RequestMethod.GET)
	public ModelAndView operateArticle(	@PathVariable @NumberFormat int id,
										@RequestParam(value = "action", required = false) String action) {
		if (action != null) {
			if (!action.equals("remove") && !action.equals("edit")) {
				return showArticles();
			} else if (action.equals("remove")) {
				articleService.removeArticle(id);
				return showArticles();
			}
		}
		ModelAndView mav = new ModelAndView("newArticle");
		Article article = articleService.findById(id);
		mav.addObject("article", article);
		mav.addObject("imagesList", picture.getCertainImgNames(PictureType.ARTICLE).stream()
				.map(File::getName).collect(Collectors.toList()));
		return mav;
	}
	
	
	// all users view for admin
	public ModelAndView showUsersForAdmin() {
		return showUsersForAdmin(null);
	}
	
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView showUsersForAdmin(@RequestParam(required = false) @NumberFormat Integer currentPage) {
		int pageSize = 10;
		
		int noOfPages = userService.getNoOfPages(pageSize);
		if (currentPage == null || currentPage > noOfPages || currentPage < 1) currentPage = 1;
		List<User> list = userService.getPart(currentPage, pageSize);
		
		ModelAndView modelAndView = new ModelAndView("adminUsers");
		modelAndView.addObject("currentPage", currentPage);
		modelAndView.addObject("noOfPages", noOfPages);
		modelAndView.addObject("listOfUsers", list);
		return modelAndView;
	}


	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView createUser() {
		ModelAndView model = new ModelAndView("newUser");
		model.addObject("user", new User());
		return model;
	}
	
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		ModelAndView model = null;
		if (bindingResult.hasErrors()) {
			model = new ModelAndView("newUser");
			return model;
		}
		userService.saveUsers(user);
		return showUsersForAdmin();
	}

	
	@RequestMapping(value = "/user&{id}", method = RequestMethod.GET)
	public ModelAndView operateUser(	@PathVariable @NumberFormat int id,
										@RequestParam(value = "action", required = false) String action) {
		if (action != null) {
			if (!action.equals("remove") && !action.equals("edit")) {
				return showUsersForAdmin();
			} else if (action.equals("remove")) {
				userService.removeUsers(id);
				return showUsersForAdmin();
			}
		}
		ModelAndView modelAndView = new ModelAndView("newUser");
		User user = userService.findById(id);
		modelAndView.addObject("user", user);
		return modelAndView;
	}

 	
	// change user Status
	@RequestMapping(value = "/changeUserStatus{userId}", method = RequestMethod.GET)
	public ModelAndView changeUserStatus(@PathVariable @NumberFormat Integer userId) {
		User userChange = userService.findById(userId);
		userChange.setRole(userChange.getRole() * -1);
		userService.saveUsers(userChange);
		return showUsersForAdmin();
	}
	
	
//	 user details admin
	@RequestMapping(value = "/user{userId}", method = RequestMethod.GET)
	public ModelAndView showUserfromOrder(@PathVariable @NumberFormat int userId) {
		List<User> listOfUsers = new ArrayList<User>();
		listOfUsers.add(userService.findById(userId));
		ModelAndView model = new ModelAndView("adminUsers");
		model.addObject("listOfUsers", listOfUsers);
		return model;
	}
	

	//show orders by particular user
	@RequestMapping(value = "/ordersUser{userId}", method = RequestMethod.GET)
	public ModelAndView showUserOrders(@PathVariable @NumberFormat Integer userId) {
		List<OrdersDetails> listOfOrders = orderDetailsService.findUserOrders(userId);
		ModelAndView model = new ModelAndView("adminOrders");
		model.addObject("listOfOrders", listOfOrders);
		return model;
	}


	
	//get all images names
	public List<File> getProductImgs() {
		String saveDirectory = "C://Users//Sergey//Desktop//eShopImages//Images//";
		File[] files = new File(saveDirectory).listFiles();
		return Arrays.asList(files);
	}
	
	
	//get list of image names by productId
	public List<File> getProductImgNames(int productId) {
		return getProductImgs().stream().filter(f -> f.getName()
				.matches(productId + "_prod.*")).collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/saveArticleImg{articleId}", method = RequestMethod.POST)
	public String saveFiles(@PathVariable @NumberFormat Integer articleId, 
							@ModelAttribute("uploadForm") FileUpload uploadForm)
					throws IllegalStateException, IOException {
		picture.savePicture(uploadForm, PictureType.ARTICLE);
		return "redirect:/admin/article&" + articleId + "?action=edit";
	}
	

	@RequestMapping(value = "/deleteImg{articleId}", method = RequestMethod.GET)
	public String deleteImg(@RequestParam(value = "imgName") String imgName,
								   @PathVariable @NumberFormat Integer articleId) {
		picture.deletePicture(imgName);
		return "redirect:/admin/article&" + articleId + "?action=edit";
	}
	
}
