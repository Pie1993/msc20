package dto;

import java.util.List;

public class UserDTO {

	public UserDTO() {
	}

	public UserDTO(String username, String name, String surname, String email, String password, boolean company,
			String sport, String city, int hour, int minute, String role) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.company = company;
		this.password = password;
		this.sport = sport;
		this.city = city;
		this.hour = hour;
		this.minute = minute;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getCompany() {
		return company;
	}

	public void setCompany(boolean company) {
		this.company = company;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private String role;
	private int minute;
	private int hour;
	private String city;
	private String username;
	private String name;
	private String surname;
	private String email;
	private boolean company;
	private String password;
	private String sport;

}
