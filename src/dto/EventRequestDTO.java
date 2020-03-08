package dto;

public class EventRequestDTO {
	
	private String username;
	private String preferredSport;
	private double userLat;
	private double userLong;
	private double cityLat;
	private double cityLong;
	private String city;
	private int hour;
	private int minute;
	private String date;
	private String role;

	public EventRequestDTO() {
	}
	
	
	public EventRequestDTO(String username, String preferredSport, double userLat, double userLong, double cityLat,
			double cityLong,String city,int hour,int minute,String date,String role) {
		super();
		this.username = username;
		this.preferredSport = preferredSport;
		this.userLat = userLat;
		this.userLong = userLong;
		this.cityLat = cityLat;
		this.cityLong = cityLong;
		this.city=city;
		this.hour=hour;
		this.minute=minute;
		this.date=date;
		this.role=role;
	}




	public double getCityLat() {
		return cityLat;
	}


	public void setCityLat(double cityLat) {
		this.cityLat = cityLat;
	}


	public double getCityLong() {
		return cityLong;
	}


	public void setCityLong(double cityLong) {
		this.cityLong = cityLong;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPreferredSport() {
		return preferredSport;
	}

	public void setPreferredSport(String preferredSport) {
		this.preferredSport = preferredSport;
	}

	public double getUserLat() {
		return userLat;
	}

	public void setUserLat(double userLat) {
		this.userLat = userLat;
	}

	public double getUserLong() {
		return userLong;
	}

	public void setUserLong(double userLong) {
		this.userLong = userLong;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
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


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
}
