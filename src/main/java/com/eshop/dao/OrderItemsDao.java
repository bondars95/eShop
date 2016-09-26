package com.eshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.eshop.main.category.Category;
import com.eshop.main.orderItems.OrderItems;

@Repository
public class OrderItemsDao {
	@PersistenceContext
	private EntityManager em;
	
	
	public void saveOrderItems(OrderItems orderItem) {
		if (orderItem.getId() == 0) {
			em.persist(orderItem);
		} else {
			em.merge(orderItem);
		}
	}

	public void removeOrderItems(int id) {
		OrderItems orderItem = em.find(OrderItems.class, id);
		if (orderItem != null) {
			em.remove(orderItem);
		}
	}
	
	public OrderItems findById(int id) {
		OrderItems orderItem = null;
		orderItem = em.find(OrderItems.class, id);
		return orderItem;
	}
	
	
	public List<OrderItems> showAll() {
		TypedQuery<OrderItems> query = em.createQuery("SELECT c FROM OrderItems c", OrderItems.class);
		List<OrderItems> listOrderItems = null;
		listOrderItems = query.getResultList();
		return listOrderItems;
	}
	
	public List<OrderItems> findProductOrders(int id) {
		TypedQuery<OrderItems> query = em.createQuery("SELECT oi FROM OrderItems oi WHERE  oi.product.id="+id, OrderItems.class);
		List<OrderItems> listOrderItems = null;
		listOrderItems = query.getResultList();
		return listOrderItems;
	}
}
