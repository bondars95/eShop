package com.eshop.main.pdf;

public class Orders {
	private String product;
	private String userName;
	private int qty;
	private float price;
	
	public Orders(){}
	
	public Orders(String product,String userName,int qty,float price){
		this.product = product;
		this.userName = userName;
		this.qty = qty;
		this.price = price;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
