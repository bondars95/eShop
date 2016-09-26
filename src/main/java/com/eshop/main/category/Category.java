package com.eshop.main.category;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.eshop.main.product.Product;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Integer parentId;
	private String categoryName;
	@OneToMany(mappedBy="category")
	private Collection<Product> productsCollection;
	@Transient
	private List<Category> sub;
	@Transient
	private Category parentCategory;
	
	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public List<Category> getSub() {
		return sub;
	}

	public void setSub(List<Category> sub) {
		this.sub = sub;
	}

	public Collection<Product> getProductsCollection() {
		return productsCollection;
	}

	public void setProductsCollection(Collection<Product> productsCollection) {
		this.productsCollection = productsCollection;
	}

	public Category() {
	};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return categoryName;
	}
	

}
