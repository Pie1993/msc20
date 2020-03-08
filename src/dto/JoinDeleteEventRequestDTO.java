package dto;

public class JoinDeleteEventRequestDTO {

	private int event_key;


	private String username;

	public JoinDeleteEventRequestDTO() {
		super();
	}

	public JoinDeleteEventRequestDTO(int event_key, String username) {
		super();
		this.event_key = event_key;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getEvent_key() {
		return event_key;
	}

	public void setEvent_key(int event_key) {
		this.event_key = event_key;
	}



}
