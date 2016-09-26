package com.eshop.main.ordersDetails;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import com.eshop.main.orderItems.OrderItems;
import com.eshop.main.user.User;

@Entity
public class OrdersDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
	private Collection<OrderItems> ordersItemsCollection;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user ;

	private java.sql.Date orderDate;
	private int status;
	
	public OrdersDetails() {
		this.status=1;
	}
	
	
	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public java.sql.Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.sql.Date orderDate) {
		this.orderDate = orderDate;
	}


	public Collection<OrderItems> getOrdersItemsCollection() {
		return ordersItemsCollection;
	}


	public void setOrdersItemsCollection(Collection<OrderItems> ordersItemsCollection) {
		this.ordersItemsCollection = ordersItemsCollection;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + status;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdersDetails other = (OrdersDetails) obj;
		if (id != other.id)
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	
	
}
