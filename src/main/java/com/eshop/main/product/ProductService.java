package com.eshop.main.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.dao.ProductDao;
import com.eshop.main.SortParams;
import com.eshop.trash.Pagination;


@Component
public class ProductService {

	@Autowired
	private ProductDao productsDao;
	
	@Transactional
	public void saveProduct(Product product) {
		productsDao.saveProduct(product);
	}

	@Transactional
	public void removeProduct(int id) {
		productsDao.removeProduct(id);
	}

	public List<Product> showAll() {
		return productsDao.showAll();
	}
	
	public Product findById(int id) {
		return productsDao.findById(id);
	}

	
	public List<Product> getPart(int pageNumber, int pageSize, String categoryName, SortParams param) {
		return productsDao.getPart(pageNumber, pageSize, categoryName, param);
	}
	
	
	public int getNoOfPages(int itemsInPage, String categoryName) {
		long noOfRecords;
		if (categoryName == null || categoryName.equals("")) {
			noOfRecords = productsDao.getNoOfRecords();
		} else {
			noOfRecords = productsDao.getNoOfRecords(categoryName);
		}
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / itemsInPage);
		return noOfPages;
	}
	
}
