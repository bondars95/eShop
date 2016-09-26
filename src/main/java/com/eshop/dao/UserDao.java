package com.eshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import com.eshop.main.product.Product;
import com.eshop.main.user.User;


@Repository
public class UserDao {

	@PersistenceContext
	private EntityManager em;

	
	public void saveUsers(User users) {
		if (users.getId() == 0) {
			em.persist(users);
		} else {
			em.merge(users);
		}
	}

	
	public void removeUsers(int id) {
		User users = em.find(User.class, id);
		if (users != null) {
			em.remove(users);
		}
	}

	
	public User findById(int id) {
		User user = null;
		user = em.find(User.class, id);
		return user;
	}

	
	public List<User> showAll() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM Users u", User.class);
		List<User> listUsers = null;
		listUsers = query.getResultList();
		return listUsers;
	}
	
	
	public List<User> getPart(int pageNumber, int pageSize) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM Users u", User.class);
	
		List<User> listOfUsers = null;
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		listOfUsers = query.getResultList();
		return listOfUsers;
	}
	
	
	public long getNoOfRecords() {
		Query queryTotal = em.createQuery("SELECT count(u.id) FROM Users u");
		long noOfRecords = (long)queryTotal.getSingleResult();
		return noOfRecords;
	}

	
	public User isValidUser(String username, String password) {
		System.out.println("username = " + username + " password = " + password);
		TypedQuery<User> query = em.createQuery(
				"SELECT u FROM Users u WHERE u.email=:email and u.password=:password and u.role>0", User.class);
		User user = null;
		try {
		user = query.setParameter("email", username)
				   .setParameter("password", password).getSingleResult(); 
		} catch (Exception e) { 
			e.printStackTrace();
			user = new User();
		}
			return user;
		}
	
	
	public boolean isEmailExists(String email) {
		Query query = em.createQuery("SELECT count(u.id) FROM Users u WHERE u.email=:email");
		long count = (long)query.setParameter("email", email).getSingleResult();
		return count > 0 ? true : false;
		}

}
