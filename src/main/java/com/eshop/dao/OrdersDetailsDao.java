package com.eshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.main.orderItems.OrderItems;
import com.eshop.main.ordersDetails.OrdersDetails;
import com.eshop.main.product.Product;

@Repository
public class OrdersDetailsDao {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void saveOrdersDetails(OrdersDetails ordersDetails) {
		if (ordersDetails.getId() == 0) {
			em.persist(ordersDetails);
		} else {
			em.merge(ordersDetails);
		}
	}
	@Transactional
	public void removeOrdersDetails(int id) {
		OrdersDetails ordersDetails = em.find(OrdersDetails.class, id);
		if (ordersDetails != null) {
			em.remove(ordersDetails);
		}
	}

	public OrdersDetails findById(int id) {
		OrdersDetails ordersDetails = null;
		ordersDetails = em.find(OrdersDetails.class, id);
		return ordersDetails;
	}

	public List<OrdersDetails> showAll() {
		TypedQuery<OrdersDetails> query = em.createQuery("SELECT c FROM OrdersDetails c Order by c.status DESC", OrdersDetails.class);
		List<OrdersDetails> listOrdersDetails = null;
		listOrdersDetails = query.getResultList();
		return listOrdersDetails;
	}
	
	public List<OrdersDetails> getSome(int pageNumber, int pageSize) {
		TypedQuery<OrdersDetails> query = em.createQuery("SELECT o FROM OrdersDetails o", OrdersDetails.class);
		List<OrdersDetails> ordersList = null;
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		ordersList = query.getResultList();
		return ordersList;
	}
	
	
	public long getNoOfRecords() {
		Query queryTotal = em.createQuery("SELECT count(o.id) FROM OrdersDetails o");
		long noOfRecords = (long)queryTotal.getSingleResult();
		return noOfRecords;
	}
	
	
	public OrdersDetails findProductOrders(OrderItems orderItems) {
		TypedQuery<OrdersDetails> query = em.createQuery("SELECT od FROM OrdersDetails od WHERE :orderItems member of od.ordersItemsCollection ", OrdersDetails.class);
	    OrdersDetails listOrdersDetails = null;
		query.setParameter("orderItems", orderItems);
		listOrdersDetails = query.getSingleResult();
		return listOrdersDetails;
	}
	
	public List<OrdersDetails> findUserOrders(int id) {
		TypedQuery<OrdersDetails> query = em.createQuery("SELECT  od FROM OrdersDetails od WHERE od.user.id="+id, OrdersDetails.class);
		List<OrdersDetails> listUserOrders = null;
		listUserOrders = query.getResultList();
		return listUserOrders;
	}
}
