package msc20.components.model;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

	@Id
	private String username;
	@Column
	private String email;
	@Column
	private String name;
	@Column
	private String surname;
	@Column
	private String city;
	@Column
	private boolean company;
	@Column
	private String sport;
	@Column
	private int hour;
	@Column
	private int minute;
	@Column
	private String role;

	@ElementCollection(fetch = FetchType.LAZY)
	private List<Pair<Double, Double>> pastCountries;

	@ManyToMany(mappedBy = "partecipants", fetch = FetchType.EAGER)
	private List<Event> events;

	public User() {
		super();
	}

	public User(String email, String name, String surname, String username, boolean company) {
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.company = company;
		this.events = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		this.hour = c.get(Calendar.HOUR_OF_DAY);
		this.minute = c.get(Calendar.MINUTE);
		this.sport = new String();
		this.city = new String();
		this.role = new String();
		this.pastCountries = new ArrayList<>();
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean getCompany() {
		return company;
	}

	public void setCompany(boolean company) {
		this.company = company;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city, double cityLat, double cityLong) {
		this.city = city;
		if (cityLat != 1000 && cityLong != 1000)
			for (Pair<Double, Double> country : pastCountries) {
				if (country.getFirst() == cityLat && country.getSecond() == cityLong)
					return;
			}

		pastCountries.add(new Pair<Double, Double>(cityLat, cityLong));
		if (pastCountries.size() > 10)
			pastCountries.remove(0);
	}

	public void remove(Event event2) {
		for (int i = 0; i < events.size(); i++)
			if (events.get(i).getId() == event2.getId()) {
				events.remove(i);
				return;
			}
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Pair<Double, Double>> getPastCountries() {
		return pastCountries;
	}

	public void setPastCountries(List<Pair<Double, Double>> pastCountries) {
		this.pastCountries = pastCountries;
	}

}
