package msc20.components.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import msc20.components.model.Event;
import msc20.components.model.User;
import msc20.components.services.InfoEventi;

@Repository
public class EventsDAO {

	private static final double KM = 10;

	@Autowired
	private DbManager dbManager;

	@Autowired
	private SessionFactory sessionFactory;

	public List<Event> getEvents(double userLat, double userLong, User user, int year, int month, int day) {
		List<Event> events = new ArrayList<Event>();

		final Session session = sessionFactory.openSession();

		final Query<Event> query;

		if (user.getSport().equals(""))
			query = session.createQuery("from Event as e", Event.class);
		else
			query = session.createQuery("from Event as e where e.typeOfStructure=:sport", Event.class)
					.setParameter("sport", user.getSport());

		for (Event event : query.getResultList()) {
			double dis = distance(userLat, event.getLatitude(), userLong, event.getLongitude(), 0, 0);
			if (checkConditions(dis, event, user, year, month, day)) {
				event.setDistance(dis);
				events.add(event);
			}
		}

		session.close();
		return events;
	}

	private boolean checkConditions(double dis, Event event, User user, int year, int month, int day) {

		if (dis > KM)
			return false;

		if (!user.getRole().equals("") && event.getNumUserForRole(user.getRole()) >= InfoEventi.getIstance()
				.getRoleLimit(event.getTypeOfStructure(), user.getRole()))
			return false;

		if (!(((event.getDate().getHour() > user.getHour())
				|| (event.getDate().getHour() == user.getHour() && event.getDate().getMinute() >= user.getMinute()))
				&& event.getDate().getYear() == year && event.getDate().getMonthValue() == month
				&& event.getDate().getDayOfMonth() == day))
			return false;

		return true;
	}

	public double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		double height = el1 - el2;

		distance = Math.pow(distance, 2) + Math.pow(height, 2);

		return (Math.sqrt(distance) / 1000);
	}

	public boolean save(Event event) {
		return dbManager.save(event);
	}

	public Event getEvents(int id) {

		final Session session = sessionFactory.openSession();

		final Query<Event> query;

		query = session.createQuery("from Event as e where e.id=:id", Event.class).setParameter("id", id);

		Event event = query.uniqueResult();
		for (User user : event.getPartecipants())
			user.getName();

		session.close();

		return event;
	}

	public boolean update(Event event) {
		return dbManager.update(event);

	}

	public List<Event> getAllEvents() {
		List<Event> events = new ArrayList<Event>();

		final Session session = sessionFactory.openSession();
		final Query<Event> query = session.createQuery("from Event", Event.class);

		events = query.getResultList();
		session.close();
		return events;

	}

}
