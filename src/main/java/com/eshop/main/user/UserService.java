package com.eshop.main.user;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.dao.UserDao;


@Component
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void saveUsers(User users) {
		userDao.saveUsers(users);
	}

	
	@Transactional
	public void removeUsers(int id) {
		userDao.removeUsers(id);
	}

	
	public User findById(int id) {
		return userDao.findById(id);
	}

	
	public List<User> showAll() {
		return userDao.showAll();
	}
	
	
	public List<User> getPart(int pageNumber, int pageSize) {
		return userDao.getPart(pageNumber, pageSize);
	}
	
	
	public int getNoOfPages(int itemsInPage) {
		long noOfRecords = userDao.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / itemsInPage);
		return noOfPages;
	}
	
	
	public long getNoOfRecords() {
		return userDao.getNoOfRecords();
	}
	
	
	public User isValidUser(String username, String password) {
		return userDao.isValidUser(username, password);
	}
	
	
	public boolean isEmailExists(String email) {
		return userDao.isEmailExists(email);
	}
} 
