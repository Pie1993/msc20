package dto;

import java.util.List;

public class EventsContainerDTO {

	public List<EventDTO> getOtherEvents() {
		return otherEvents;
	}

	public void setOtherEvents(List<EventDTO> otherEvents) {
		this.otherEvents = otherEvents;
	}

	public List<EventDTO> getUserEvents() {
		return userEvents;
	}

	public void setUserEvents(List<EventDTO> userEvents) {
		this.userEvents = userEvents;
	}

	public List<EventDTO> getEvents() {
		return events;
	}

	public void setEvents(List<EventDTO> events) {
		this.events = events;
	}

	public void checkDuplicate() {

		boolean trovato = true;
		while (trovato) {
			trovato = false;
			for (int i = 0; i < userEvents.size(); i++)
				for (int j = 0; j < events.size(); j++)
					if (userEvents.get(i).getId() == events.get(j).getId()) {
						events.remove(j);
						trovato = true;
						break;
					}
		}

		trovato = true;
		while (trovato) {
			trovato = false;
			for (int i = 0; i < userEvents.size(); i++)
				for (int j = 0; j < otherEvents.size(); j++)
					if (userEvents.get(i).getId() == otherEvents.get(j).getId()) {
						otherEvents.remove(j);
						trovato = true;
						break;
					}
		}

		trovato = true;
		while (trovato) {
			trovato = false;
			for (int i = 0; i < events.size(); i++)
				for (int j = 0; j < otherEvents.size(); j++)
					if (events.get(i).getId() == otherEvents.get(j).getId()) {
						otherEvents.remove(j);
						trovato = true;
						break;
					}
		}

		trovato = true;
		while (trovato) {
			trovato = false;
			for (int i = 0; i < otherEvents.size(); i++)
				for (int j = 0; j < otherEvents.size(); j++)
					if (i != j)
						if (otherEvents.get(i).getId() == otherEvents.get(j).getId()) {
							otherEvents.remove(j);
							trovato = true;
							break;
						}
		}

	}

	private List<EventDTO> otherEvents;
	private List<EventDTO> userEvents;
	private List<EventDTO> events;

}
