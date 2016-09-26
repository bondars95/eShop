package com.eshop.main.user;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.eshop.main.ordersDetails.OrdersDetails;

@Entity(name="Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty(message = "First name can not be empty")
	private String fName;
	@NotEmpty(message = "Last name can not be empty")
	private String lName;
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "email is uncorrect")
	private String email;
	@NotNull(message = "Birh can not be empy")
	private java.sql.Date birthdate;
	@NotEmpty(message = "City can not be empty")	
	private String city;
	@NotEmpty(message = "Sex can not be empty")
	private String sex;
	@NotEmpty(message = "Phone can not be empty")
	private String phone;
	private Integer role;
	@OneToMany(mappedBy = "user")
	private Collection<OrdersDetails> ordersDetailsCollection;
	@NotEmpty(message = "Password can not be empty")
	private String password;
	
	public User() {
	};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public java.sql.Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(java.sql.Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<OrdersDetails> getOrdersDetailsCollection() {
		return ordersDetailsCollection;
	}

	public void setOrdersDetailsCollection(Collection<OrdersDetails> ordersDetailsCollection) {
		this.ordersDetailsCollection = ordersDetailsCollection;

	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", birthdate="
				+ birthdate + ", city=" + city + ", sex=" + sex + ", phone=" + phone + ", role=" + role
				+ ", ordersDetailsCollection=" + ordersDetailsCollection + ", password=" + password + "]";
	}
}
