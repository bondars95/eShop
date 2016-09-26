package com.eshop.main.ordersDetails;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.dao.OrdersDetailsDao;
import com.eshop.main.orderItems.OrderItems;
import com.eshop.main.orderItems.OrderItemsService;

@Component
public class OrdersDetailsService {
	@Autowired
	private OrdersDetailsDao ordersDetailsyDao;
	@Autowired
	private OrderItemsService orderItemsService;

	@Transactional
	public void saveOrdersDetails(OrdersDetails ordersDetails) {
		ordersDetailsyDao.saveOrdersDetails(ordersDetails);
	}
	
	@Transactional
	public void removeOrdersDetails(int id) {
		ordersDetailsyDao.removeOrdersDetails(id);
	}

	public OrdersDetails findById(int id) {
		return ordersDetailsyDao.findById(id);
	}

	public List<OrdersDetails> showAll() {
		return ordersDetailsyDao.showAll();
	}
	
	public List<OrdersDetails> getSome(int pageNumber, int pageSize) {
		return ordersDetailsyDao.getSome(pageNumber, pageSize);
	}
	
	public long getNoOfRecords() {
		return ordersDetailsyDao.getNoOfRecords();
	}
	
	public OrdersDetails findProductOrders(OrderItems orderItems) {
		return ordersDetailsyDao.findProductOrders(orderItems);
	}
	
	public List<OrdersDetails> findUserOrders(int id) {
		return ordersDetailsyDao.findUserOrders(id);
	}
	
	public List<OrdersDetails> getOrderDetailsByProductId(int productId) {
		List<OrderItems> listOfItems = orderItemsService.findProductOrders(productId);
		List<OrdersDetails> listOfOrders = showAll();
		
		List<OrdersDetails> list = new ArrayList<>();
		
//		listOfOrders.stream().filter(od -> od.getOrdersItemsCollection().contains(listOfItems.stream()))
//			.forEach(od -> System.out.println(od.getId()));
		
		for (OrdersDetails od : listOfOrders) {
			for (OrderItems oi : listOfItems) {
				if (od.getOrdersItemsCollection().contains(oi)) {
					list.add(od);
				}
			}
		}
		return list;
	}
}

