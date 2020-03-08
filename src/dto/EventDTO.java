package dto;

import java.util.Map;

public class EventDTO {


	private int id;


	private String organiser;
	
	private String structureName;
	
	private String typeOfStructure;
	
	
	private double latitude;
	
	
	private double longitude;

	
	private boolean covered;
	
	 
	private double price;
	
	 
	private int numberOfPartecipant;
	
	private double distance;
	
	private String date;
	
	private int limitPartecipant;
	
	private Map<String,String>users_roles;

	public EventDTO(String organiser, String structureName, String typeOfStructure, double latitude,
			double longitude, boolean covered, double price, int numberOfPartecipant, int id, double distance,String date, int limitPartecipant,Map<String,String>users_roles) {
		super();
		this.organiser = organiser;
		this.structureName = structureName;
		this.typeOfStructure = typeOfStructure;
		this.latitude = latitude;
		this.longitude = longitude;
		this.covered = covered;
		this.price = price;
		this.numberOfPartecipant = numberOfPartecipant;
		this.id=id;
		this.distance=distance;
		this.date=date;
		this.limitPartecipant=limitPartecipant;
		this.users_roles=users_roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrganiser() {
		return organiser;
	}

	public void setOrganiser(String organiser) {
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

	public void setNumberOfPartecipant(int numberOfPartecipant) {
		this.numberOfPartecipant = numberOfPartecipant;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getLimitPartecipant() {
		return limitPartecipant;
	}

	public void setLimitPartecipant(int limitPartecipant) {
		this.limitPartecipant = limitPartecipant;
	}

	public Map<String,String> getUsers_roles() {
		return users_roles;
	}

	public void setUsers_roles(Map<String,String> users_roles) {
		this.users_roles = users_roles;
	}
}
