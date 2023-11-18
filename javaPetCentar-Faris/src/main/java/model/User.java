package model;

import java.time.LocalDate;

public class User {

	
	private int userId;
	
	private String name, adress, phone, username, password;
	
	private LocalDate date;
	
	private boolean isAdmin;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int userId, String name, String adress, String phone, String username, String password, LocalDate date,
			boolean isAdmin) {
		super();
		this.userId = userId;
		this.name = name;
		this.adress = adress;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.date = date;
		this.isAdmin = isAdmin;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", adress=" + adress + ", phone=" + phone + ", username="
				+ username + ", password=" + password + ", date=" + date + ", isAdmin=" + isAdmin + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
