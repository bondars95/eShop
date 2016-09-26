package com.eshop.main.product;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import com.eshop.main.category.Category;
import com.eshop.main.orderItems.OrderItems;
import com.eshop.main.ordersDetails.OrdersDetails;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	@NotEmpty(message = "Product name can not be empty")
	private String productName;
	@NotEmpty(message = "Description name can not be empty")
	private String description;
	@NotEmpty(message = "Description name can not be empty")
	private String country;
    @Digits(integer=5,fraction=2, message = "Try not that big")
	private double price;
	private int rest;
	private String imgPath;
	@Transient
	private int basketQty;

	public int getBasketQty() {
		return basketQty;
	}

	public void setBasketQty(int basketQty) {
		this.basketQty = basketQty;
	}

	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private Collection<OrderItems> ordersDetailsCollection;

	public Product() {
		this.basketQty = 1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getRest() {
		return rest;
	}

	public void setRest(int rest) {
		this.rest = rest;
	}

	
	public Collection<OrderItems> getOrdersDetailsCollection() {
		return ordersDetailsCollection;
	}

	public void setOrdersDetailsCollection(Collection<OrderItems> ordersDetailsCollection) {
		this.ordersDetailsCollection = ordersDetailsCollection;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", category=" + category + ", productName=" + productName + ", description="
				+ description + ", country=" + country + ", price=" + price + ", rest=" + rest
				+ ", ordersDetailsCollection=" + ordersDetailsCollection + "]";
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

}
