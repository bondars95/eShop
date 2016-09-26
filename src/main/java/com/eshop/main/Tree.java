package com.eshop.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.eshop.main.category.Category;
import com.eshop.main.category.CategoryService;

@Component
public class Tree {
	
	@Autowired
	private CategoryService categoryService;
	
	public Tree() {
//		ApplicationContext appContext = new ClassPathXmlApplicationContext("application-config.xml");
//		categoryService = appContext.getBean(CategoryService.class);
	}
	

	public void setSubCategories(List<Category> tree, int num) {
		for (Category c : tree) {
			c.setSub(categoryService.setSubForCategory(c));
			if (!c.getSub().isEmpty()) {
				setSubCategories(c.getSub(), num + 1);
			} else {
			}
		}
	}
	
	
	public ArrayList<String> createTreeEdit() {
		List<Category> listCategory = categoryService.showMainNode();
		setSubCategories(listCategory, 1);
		ArrayList<String> treeJsp = getTreeJspEdit(listCategory, new ArrayList<>());
		return treeJsp;
	}
	
	
	/**Return HTML unordered list for drop-down menu
	 * @param model
	 * @return
	 */
	public ArrayList<String> createDropDown(String model) {
	   List<Category> listCategory = categoryService.showMainNode();
	   setSubCategories(listCategory, 1);
	   ArrayList<String> treeJsp = getDropDownCategories(listCategory, new ArrayList<>(), model);
	   return treeJsp;
	}
	

	// this method prints for jsp
	public ArrayList<String> getTreeJspEdit(List<Category> tree, ArrayList<String> array) {
		ArrayList<String> jspText = array;
		for (Category c : tree) {
			if (!c.getSub().isEmpty()) {
				jspText.add("<li style=\"display:inline\">" + c.getCategoryName());
				jspText.add("<div style=\"display:inline\">&nbsp<a href='createCategory?parentId=" + c.getId()+"'><span class=\"glyphicon glyphicon-plus-sign\"></span></a>"+
						"&nbsp<a href='editCategory&" + c.getId()+"'><span class=\"glyphicon glyphicon-pencil\"></span></a>"
						+ "&nbsp<a href='removeCategory&" + c.getId()+"'><span class=\"glyphicon glyphicon-remove-sign\"></span></a></div><br>");
				jspText.add("<ul>");
				getTreeJspEdit(c.getSub(), jspText);
				jspText.add("</ul>");			
				jspText.add("</li>");
			} else {
				jspText.add("<li><div><a>" + c.getCategoryName() 
							+ "</a>&nbsp<a href='createCategory?parentId=" 
							+ c.getId() + "'><span class=\"glyphicon glyphicon-plus-sign\"></span></a>"
							+ "&nbsp<a href='editCategory&"+c.getId()+"'><span class=\"glyphicon glyphicon-pencil\"></span></a>"
							+ "&nbsp<a href='removeCategory&"+c.getId()+"'><span class=\"glyphicon glyphicon-remove-sign\"></span></a></div></li>");
			}
		}
		return jspText;
	}
	
	
	public ArrayList<String> getDropDownCategories(List<Category> tree, ArrayList<String> array, String method) {
		ArrayList<String> jspText = array;
		for (Category c : tree) {
			if (!c.getSub().isEmpty()) {
				jspText.add("<li class=\"dropdown-submenu\"><a class=\"test\" href=\"#\">" 
							+ c.getCategoryName() + "<span class=\"caret\"></span></a>");
				jspText.add("<ul class=\"dropdown-menu\">");
				getDropDownCategories(c.getSub(), jspText, method);
				jspText.add("</ul>");
				jspText.add("</li>");
			} else {
				if(method.equals("main")){
			    jspText.add("<li><a tabindex=\"-1\" href=\"/eShop/goods?category="+c.getCategoryName()+"\">" 
			    		 	+ c.getCategoryName() + "</a></li>");
				} else if (method.equals("newProduct")){
					jspText.add("<li><a tabindex=\"-1\" href=\"javascript:changeCategory('"
			    				+ c.getCategoryName()+"')\">" + c.getCategoryName() + "</a></li>");
			    }
		   }
	  }
	  return jspText;
	 }
}
