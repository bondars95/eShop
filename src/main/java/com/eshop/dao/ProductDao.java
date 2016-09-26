package com.eshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.eshop.main.SortParams;
import com.eshop.main.ordersDetails.OrdersDetails;
import com.eshop.main.product.Product;

@Repository
public class ProductDao {

	@PersistenceContext
	private EntityManager em;

	public void saveProduct(Product product) {
		if (product.getId() == 0) {
			em.persist(product);
		} else {
			em.merge(product);
		}
	}


	public void removeProduct(int id) {
		Product product = em.find(Product.class, id);
		if (product != null) {
			em.remove(product);
		}
	}

	public Product findById(int id) {
		Product user = null;
		user = em.find(Product.class, id);
		return user;
	}


	//show sold qty for admin
	public int showSoldQty(int id){
		TypedQuery<OrdersDetails> query = em.createQuery("SELECT count(*) FROM OrdersDetails od WHERE od.productId="+id, OrdersDetails.class);
		return query.getFirstResult();
	}
	
	public List<Product> showAll() {
		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
		List<Product> listProduct = null;
		listProduct = query.getResultList();
		return listProduct;
	}

	
	public List<Product> getPart(int pageNumber, int pageSize, String categoryName, SortParams param) {
		TypedQuery<Product> query = null;
		if (categoryName == null || categoryName.equals("")) {
			query = em.createQuery("SELECT p FROM Product p " + param.getSortParam(), Product.class);
		} else {
			query = em.createQuery("SELECT p FROM Product p WHERE p.category.categoryName =:categoryName " 
									+ param.getSortParam(), Product.class);
			query.setParameter("categoryName", categoryName);
		}
		List<Product> listProduct = null;
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		listProduct = query.getResultList();
		return listProduct;
	}
	
	
	public List<Product> getAllByCategory() {
		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
		List<Product> listProduct = null;
		listProduct = query.getResultList();
		return listProduct;
	}
	
	
	public long getNoOfRecords() {
		Query queryTotal = em.createQuery("SELECT count(p.id) FROM Product p");
		long noOfRecords = (long)queryTotal.getSingleResult();
		return noOfRecords;
	}
	
	
	public long getNoOfRecords(String categoryName) {
		Query queryTotal = em.createQuery("SELECT count(p.id) FROM Product p WHERE p.category.categoryName =:categoryName");
		queryTotal.setParameter("categoryName", categoryName);
		long noOfRecords = (long)queryTotal.getSingleResult();
		return noOfRecords;
	}
	
}
