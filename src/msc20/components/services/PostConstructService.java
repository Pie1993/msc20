package msc20.components.services;

import msc20.components.model.*;
import msc20.components.persistence.CredentialsDAO;
import msc20.components.persistence.EventsDAO;
import msc20.components.persistence.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;

@Service
public class PostConstructService {

	@Autowired
	private CredentialsDAO credentialsDAO;
	@Autowired
	private EventsDAO eventsDAO;
	@Autowired
	private UserDAO userDAO;

	@PostConstruct
	public void initDatabase() {

		User ciccio = new User("mail@mail.it", "nome", "cognome", "ciccio", false);
		credentialsDAO.save(new Credential(ciccio, "password"));
		//ciccio.setSport("Calcio");

		User u2 = new User("mail@mail.it", "giovanni", "cognome", "gio", false);
		u2.setSport("Calcio");
		u2.setRole("POR");

		credentialsDAO.save(new Credential(u2, "password"));

		User u3 = new User("mail@mail.it", "Alessandro", "cognome", "alex", false);
		credentialsDAO.save(new Credential(u3, "password"));

		User u4 = new User("mail@mail.it", "Davide", "cognome", "davide", false);
		credentialsDAO.save(new Credential(u4, "password"));

		User u5 = new User("mail@mail.it", "nome", "cognome", "giuseppe", false);
		u5.setSport("Calcio");
		u5.setRole("POR");
		credentialsDAO.save(new Credential(u5, "password"));
		
		// 3 MARZO

		LocalDateTime time = LocalDateTime.of(2020, 03, 03, 19, 00);

		Event e1 = new Event(u3, "Grimoli", "Calciotto", 39.359139, 16.241652, false, 1, time);
		time = LocalDateTime.of(2020, 03, 03, 15, 00);

		Event e2 = new Event(ciccio, "Grimoli", "Calcetto", 39.359205, 16.241232, false, 1, time);
		time = LocalDateTime.of(2020, 03, 03, 22, 03);

		Event e4 = new Event(u5, "wimbledon", "Tennis", 39.310753, 16.230005, false, 3, time);
		Event calcio = new Event(u5, "San Vito", "Calcio", 39.310363, 16.230675, false, 3, time);
		Event calcettoBisignano = new Event(u5, "Campetto del Salvatore", "Calcetto", 39.516447, 16.281693, false, 3,
				time);
		Event calcioBisignano = new Event(u5, "Campetto di Santa Croce", "Calcio", 39.516447, 16.281693, false, 5,
				time);
		Event calcettoTrebisacce = new Event(u5, "Centro Sportivo Seventeen", "Calcetto", 39.871613, 16.533943, false,
				5, time);

		eventsDAO.save(calcettoTrebisacce);
		eventsDAO.save(calcioBisignano);
		eventsDAO.save(calcettoBisignano);
		eventsDAO.save(calcio);
		eventsDAO.save(e1);
		eventsDAO.save(e2);
		eventsDAO.save(e4);

		//updatePartecipant(ciccio, calcio);
		updatePartecipant(u2, calcio);
		//updatePartecipant(ciccio, e1);
		//updatePartecipant(ciccio, e2);
		
		
		
		// 4 MARZO

		time = LocalDateTime.of(2020, 03, 04, 19, 00);

		e1 = new Event(u3, "Grimoli", "Calciotto", 39.359139, 16.241652, false, 1, time);
		time = LocalDateTime.of(2020, 03, 04, 15, 00);

		e2 = new Event(ciccio, "Grimoli", "Calcetto", 39.359205, 16.241232, false, 1, time);
		time = LocalDateTime.of(2020, 03, 04, 22, 03);

		e4 = new Event(u5, "wimbledon", "Tennis", 39.310753, 16.230005, false, 3, time);
		calcio = new Event(u5, "San Vito", "Calcio", 39.310363, 16.230675, false, 3, time);
		calcettoBisignano = new Event(u5, "Campetto del Salvatore", "Calcetto", 39.516447, 16.281693, false, 3, time);
		calcioBisignano = new Event(u5, "Campetto di Santa Croce", "Calcio", 39.516447, 16.281693, false, 5, time);
		calcettoTrebisacce = new Event(u5, "Centro Sportivo Seventeen", "Calcetto", 39.871613, 16.533943, false, 5,
				time);

		eventsDAO.save(calcettoTrebisacce);
		eventsDAO.save(calcioBisignano);
		eventsDAO.save(calcettoBisignano);
		eventsDAO.save(calcio);
		eventsDAO.save(e1);
		eventsDAO.save(e2);
		eventsDAO.save(e4);

		
		
		
		
		// 5 MARZO
		
		
		time = LocalDateTime.of(2020, 03, 05, 19, 00);

		e1 = new Event(u3, "Grimoli", "Calciotto", 39.359139, 16.241652, false, 1, time);
		time = LocalDateTime.of(2020, 03, 05, 15, 00);

		e2 = new Event(ciccio, "Grimoli", "Calcetto", 39.359205, 16.241232, false, 1, time);
		time = LocalDateTime.of(2020, 03, 05, 22, 03);

		e4 = new Event(u5, "wimbledon", "Tennis", 39.310753, 16.230005, false, 3, time);
		calcio = new Event(u5, "San Vito", "Calcio", 39.310363, 16.230675, false, 3, time);
		calcettoBisignano = new Event(u5, "Campetto del Salvatore", "Calcetto", 39.516447, 16.281693, false, 3, time);
		calcioBisignano = new Event(u5, "Campetto di Santa Croce", "Calcio", 39.516447, 16.281693, false, 5, time);
		calcettoTrebisacce = new Event(u5, "Centro Sportivo Seventeen", "Calcetto", 39.871613, 16.533943, false, 5,
				time);

		eventsDAO.save(calcettoTrebisacce);
		eventsDAO.save(calcioBisignano);
		eventsDAO.save(calcettoBisignano);
		eventsDAO.save(calcio);
		eventsDAO.save(e1);
		eventsDAO.save(e2);
		eventsDAO.save(e4);
		
		
		
	}

	private void updatePartecipant(User user, Event event) {
		user.getEvents().add(event);
		event.addPartecipant(user);
		userDAO.update(user);
		eventsDAO.update(event);

	}
}
