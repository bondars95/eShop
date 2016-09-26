package com.eshop.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.eshop.main.article.Article;


	@Repository
	public class ArticleDao {
		
		@PersistenceContext
		private EntityManager em;
		
		
		public void saveArticle(Article article) {
			if (article.getId() == 0) {
				em.persist(article);
			} else {
				em.merge(article);
			}
		}

		public void removeArticle(int id) {
			Article article = em.find(Article.class, id);
			if (article != null) {
				em.remove(article);
			}
		}
		
		public Article findById(int id) {
			Article article = null;
			article = em.find(Article.class, id);
			return article;
		}
		
		public Article findByHeader(String header) {
			TypedQuery<Article> query = em.createQuery("SELECT a FROM Article a WHERE a.headers='" + header+"'", Article.class);
			Article article = null;
			article = query.getSingleResult();
			return article;
		}
		
		public List<Article> showAll() {
			TypedQuery<Article> query = em.createQuery("SELECT a FROM Article a", Article.class);
			List<Article> listArticle = null;
			listArticle = query.getResultList();
			return listArticle;
		}
		
	}

