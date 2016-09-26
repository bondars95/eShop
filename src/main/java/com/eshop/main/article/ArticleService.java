package com.eshop.main.article;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.dao.ArticleDao;

	@Component
	public class ArticleService {

		@Autowired
		private ArticleDao articleDao;
		
		@Transactional
		public void saveArticle(Article article) {
			articleDao.saveArticle(article);
		}
		
		@Transactional
		public void removeArticle(int id) {
			articleDao.removeArticle(id);
		}
		
		public Article findById(int id) {
			return articleDao.findById(id);
		}
		
		public Article findByHeader(String header) {
			return articleDao.findByHeader(header);
		}
		
		public List<Article> showAll() {
			return articleDao.showAll();
		}
		
	}

