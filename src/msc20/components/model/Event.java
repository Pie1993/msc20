package msc20.components.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import msc20.components.services.InfoEventi;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne
	private User organiser;

	@Column
	private String structureName;

	@Column
	private String typeOfStructure;

	@Column
	private double latitude;

	@Column
	private double longitude;

	@Column
	private boolean covered;

	@Column
	private double price;

	@Column
	private int numberOfPartecipant;

	@Column
	private double distance;

	@Column
	private LocalDateTime date;

	@Column
	private int limitPartecipant;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> users_role;

	public int getLimitPartecipant() {
		return limitPartecipant;
	}

	public void setLimitPartecipant(int limitPartecipant) {
		this.limitPartecipant = limitPartecipant;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "User_Event", joinColumns = {
			@JoinColumn(name = "fk_event", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "fk_user_event", referencedColumnName = "username") })
	private List<User> partecipants;

	public Event(User organiser, String structureName, String typeOfStructure, double latitude, double longitude,
			boolean covered, double price, LocalDateTime date) {
		super();
		this.organiser = organiser;
		this.structureName = structureName;
		this.typeOfStructure = typeOfStructure;
		this.latitude = latitude;
		this.longitude = longitude;
		this.covered = covered;
		this.price = price;
		this.partecipants = new ArrayList<>();
		this.date = date;
		this.limitPartecipant = InfoEventi.getIstance().getMaxPartecipantOf(typeOfStructure);
		users_role = new HashMap<String, String>();
		//addPartecipant(organiser);

	}

	public void addPartecipant(User user) {
		partecipants.add(user);
		if (user.getRole().equals(""))
			users_role.put(user.getUsername(), getFreeRole());
		else
			users_role.put(user.getUsername(), user.getRole());
		setNumberOfPartecipant(partecipants.size());
	}

	private String getFreeRole() {

		Map<String, Integer> roles = InfoEventi.getIstance().getRolesInfoForEvent(typeOfStructure);
		for (String role : roles.keySet()) {
			int num = 0;
			for (String user : users_role.keySet())
				if (users_role.get(user).equals(role))
					num++;

			if (num < InfoEventi.getIstance().getRoleLimit(typeOfStructure, role))
				return role;
		}

		return null;
	}

	public Event() {
		super();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getOrganiser() {
		return organiser;
	}

	public void setOrganiser(User organiser) {
		this.organiser = organiser;
	}

	public String getStructureName() {
		return structureName;
	}

	public void setStructureName(String structureName) {
		this.structureName = structureName;
	}

	public String getTypeOfStructure() {
		return typeOfStructure;
	}

	public void setTypeOfStructure(String typeOfStructure) {
		this.typeOfStructure = typeOfStructure;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public boolean isCovered() {
		return covered;
	}

	public void setCovered(boolean covered) {
		this.covered = covered;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNumberOfPartecipant() {
		return numberOfPartecipant;
	}

	private void setNumberOfPartecipant(int numberOfPartecipant) {
		this.numberOfPartecipant = numberOfPartecipant;
	}

	public void setDistance(double distance) {
		this.distance = Math.floor(distance * 100) / 100;

	}

	public double getDistance() {
		return distance;
	}

	public List<User> getPartecipants() {
		return partecipants;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setPartecipants(List<User> partecipants) {
		this.partecipants = partecipants;
	}

	public String getTime() {
		return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear() + "  " + date.getHour() + ":"
				+ (date.getMinute() < 10 ? "0" + date.getMinute() : date.getMinute());

	}

	public void removePartecipant(User user) {
		for (int i = 0; i < partecipants.size(); i++)
			if (partecipants.get(i).getUsername().equals(user.getUsername())) {
				partecipants.remove(i);
				users_role.remove(user.getUsername());
				setNumberOfPartecipant(partecipants.size());
				return;
			}

	}

	public int getNumUserForRole(String role) {
		int num = 0;
		for (String username : users_role.keySet())
			if (users_role.get(username).equals(role))
				num++;

		return num;
	}

	public Map<String, String> getUsersRoles() {

		return users_role;
	}

}
