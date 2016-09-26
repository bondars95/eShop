package com.eshop.main.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.dao.CategoryDao;

	@Component
	public class CategoryService {

		@Autowired
		private CategoryDao categoryDao;
		
		@Transactional
		public void saveCategory(Category category) {
			categoryDao.saveCategory(category);
		}
		
		@Transactional
		public void removeCategory(int id) {
			categoryDao.removeCategory(id);
		}
		
		public Category findById(int id) {
			return categoryDao.findById(id);
		}
		
		public Category findByName(String name) {
			return categoryDao.findByName(name);
		}
		
		public List<Category> showAll() {
			return categoryDao.showAll();
		}
		
		public List<Category> showMainNode(){
			return categoryDao.showMainNode();
		}
		
		public List<Category> setSubForCategory(Category category){
			return categoryDao.setSubForCategory(category);
		}
		
		public List<Category> showCategoriesToChoose(){
			return categoryDao.showCategoriesToChoose();
		}

	}

