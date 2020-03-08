package msc20.components.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.EventDTO;
import dto.EventRequestDTO;
import dto.EventsContainerDTO;
import dto.JoinDeleteEventRequestDTO;
import msc20.components.model.Event;
import msc20.components.model.Pair;
import msc20.components.model.User;
import msc20.components.persistence.CredentialsDAO;
import msc20.components.persistence.EventsDAO;
import msc20.components.persistence.UserDAO;

@Service
public class EventsService {

	@Autowired
	private EventsDAO eventsDAO;

	private final double NO_VALUE = 1000;

	@Autowired
	private CredentialsDAO credentialsDAO;

	@Autowired
	private UserDAO userDAO;

	public EventsContainerDTO getEvents(EventRequestDTO eventRequestDTO) {
		User user = credentialsDAO.getUser(eventRequestDTO.getUsername());
		user.setCity(eventRequestDTO.getCity(), eventRequestDTO.getCityLat(), eventRequestDTO.getCityLong());
		user.setSport(eventRequestDTO.getPreferredSport());
		user.setMinute(eventRequestDTO.getMinute());
		user.setHour(eventRequestDTO.getHour());
		user.setRole(eventRequestDTO.getRole());
		userDAO.update(user);

		System.out.println(user.getEvents().size());

		String d[] = eventRequestDTO.getDate().split("/");
		int day = Integer.valueOf(d[0]);
		int month = Integer.valueOf(d[1]);
		int year = Integer.valueOf(d[2]);

		EventsContainerDTO eventsContainerDTO = new EventsContainerDTO();

		// FILTRO EVENTUALI DUPLICATI DOVUTI ALLA PRESENZA DI LIST INVECE DI SET NELLA
		// CLASSE USER
		HashSet<Event> hash = new HashSet<>();
		for (Event e : user.getEvents())
			hash.add(e);
		List<Event> user_events = new ArrayList<>();
		for (Event e : hash)
			user_events.add(e);

		// LISTA EVENTI UTENTE
		List<EventDTO> eventsDTO = computeList(user_events, eventRequestDTO);
		eventsContainerDTO.setUserEvents(eventsDTO);

		// LISTA EVENTI DELLA RICERCA
		// QUERY
		List<Event> events = new ArrayList<Event>();
		if (eventRequestDTO.getUserLat() != NO_VALUE && eventRequestDTO.getUserLong() != NO_VALUE)
			events.addAll(eventsDAO.getEvents(eventRequestDTO.getUserLat(), eventRequestDTO.getUserLong(), user, year,
					month, day));
		if (eventRequestDTO.getCityLat() != NO_VALUE && eventRequestDTO.getCityLong() != NO_VALUE)
			events.addAll(eventsDAO.getEvents(eventRequestDTO.getCityLat(), eventRequestDTO.getCityLong(), user, year,
					month, day));

		// FILTRO EVENTUALI DUPLICATI DOVUTI ALLA PRESENZA DI LIST INVECE DI SET NELLA
		// CLASSE USER
		hash = new HashSet<>();
		for (Event e : events)
			hash.add(e);
		List<Event> search_events = new ArrayList<>();
		for (Event e : hash)
			search_events.add(e);

		eventsDTO = computeList(search_events, eventRequestDTO);
		eventsContainerDTO.setEvents(eventsDTO);

		// LISTA EVENTI CORRELATI IN BASE ALLE RICERCHE PASSATE
		events = new ArrayList<Event>();
		getEventsForRelatedEvents(events, user, eventRequestDTO, year, month, day);
		eventsDTO = computeList(events, eventRequestDTO);
		eventsContainerDTO.setOtherEvents(eventsDTO);

		// CONTROLLO EVENTUALI DUPLICATI
		eventsContainerDTO.checkDuplicate();

		return eventsContainerDTO;
	}

	private void getEventsForRelatedEvents(List<Event> events, User user, EventRequestDTO eventRequestDTO, int year,
			int month, int day) {
		for (Pair<Double, Double> latlong : user.getPastCountries()) {
			events.addAll(eventsDAO.getEvents(latlong.getFirst(), latlong.getSecond(), user, year, month, day));
		}

	}

	public void joinDeleteEvent(JoinDeleteEventRequestDTO joinDeleteEventRequestDTO) {
		User user = credentialsDAO.getUser(joinDeleteEventRequestDTO.getUsername());
		Event event2 = eventsDAO.getEvents(joinDeleteEventRequestDTO.getEvent_key());
		for (Event event : user.getEvents())
			if (event.getId() == joinDeleteEventRequestDTO.getEvent_key()) {
				user.remove(event2);
				event2.removePartecipant(user);
				userDAO.update(user);
				eventsDAO.update(event2);
				return;
			}

		user.getEvents().add(event2);
		event2.addPartecipant(user);
		userDAO.update(user);
		eventsDAO.update(event2);

	}

	private List<EventDTO> computeList(List<Event> events, EventRequestDTO event) {

		// DISTANZA
		for (Event e : events) {
			if (event.getUserLat() != NO_VALUE && event.getUserLong() != NO_VALUE)
				e.setDistance(eventsDAO.distance(event.getUserLat(), e.getLatitude(), event.getUserLong(),
						e.getLongitude(), 0, 0));
			else if (event.getCityLat() != NO_VALUE && event.getCityLong() != NO_VALUE)
				e.setDistance(eventsDAO.distance(event.getCityLat(), e.getLatitude(), event.getCityLong(),
						e.getLongitude(), 0, 0));
		}

		// RIEMPI
		List<EventDTO> eventsDTO = new ArrayList<EventDTO>();
		for (Event e : events) {
			eventsDTO.add(new EventDTO(e.getOrganiser().getUsername(), e.getStructureName(), e.getTypeOfStructure(),
					e.getLatitude(), e.getLongitude(), e.isCovered(), e.getPrice(), e.getNumberOfPartecipant(),
					e.getId(), e.getDistance(), e.getTime(), e.getLimitPartecipant(), e.getUsersRoles()));
		}
		return eventsDTO;
	}

}
