package com.eshop.main.orderItems;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.dao.CategoryDao;
import com.eshop.dao.OrderItemsDao;

@Component
public class OrderItemsService {
	@Autowired
	private OrderItemsDao orderItemsDao;
	@Transactional
	public void saveOrderItems(OrderItems orderItem) {
		orderItemsDao.saveOrderItems(orderItem);
	}
	@Transactional
	public void removeOrderItems(int id) {
		orderItemsDao.removeOrderItems(id);
	}
	
	public OrderItems findById(int id) {
		return orderItemsDao.findById(id);
	}
	
	
	public List<OrderItems> showAll() {
		return orderItemsDao.showAll();
	}
	public List<OrderItems> findProductOrders(int id) {
		return orderItemsDao.findProductOrders(id);
	}
}
