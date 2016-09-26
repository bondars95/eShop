package com.eshop.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import com.eshop.main.category.Category;

	@Repository
	public class CategoryDao {
		
		@PersistenceContext
		private EntityManager em;
		
		
		public void saveCategory(Category category) {
			if (category.getId() == 0) {
				em.persist(category);
			} else {
				em.merge(category);
			}
		}

		
		public void removeCategory(int id) {
			Category category = em.find(Category.class, id);
			if (category != null) {
				em.remove(category);
			}
		}
		
		
		public Category findById(int id) {
			Category category = null;
			category = em.find(Category.class, id);
			return category;
		}
		
		
		public Category findByName(String name) {
			TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.categoryName='"+name+"'", Category.class);
			Category category = null;
			category = query.getSingleResult();
			return category;
		}
		
		
		public List<Category> showAll() {
			TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c", Category.class);
			List<Category> listCategory = null;
			listCategory = query.getResultList();
			return listCategory;
		}
		
		
		public List<Category> showCategoriesToChoose() {
			TypedQuery<Category> query = em.createQuery("SELECT c1 FROM Category c1 WHERE "
					+ "c1.id not in (select c2.parentId from Category c2 where c2.parentId>0)", Category.class);
			List<Category> listCategory = null;
			listCategory = query.getResultList();
			return listCategory;
		}
		
		
		public List<Category> showMainNode() {
			TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.parentId=null", Category.class);
			List<Category> listCategory = null;
			listCategory = query.getResultList();
			return listCategory;
		}
		
		
		public List<Category> setSubForCategory(Category category){
			TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.parentId=" + category.getId(), Category.class);
			List<Category> listSub = null;
			listSub = query.getResultList();
			return listSub;
		}
		
	}

