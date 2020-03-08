package msc20.components.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.EventDTO;
import dto.UserDTO;
import msc20.components.model.Event;
import msc20.components.model.User;
import msc20.components.persistence.CredentialsDAO;

@Service
public class LoginService {

	@Autowired
	private CredentialsDAO credentialsDAO;

	public UserDTO login(String username, String password) {

		User user = credentialsDAO.checkLogin(username, password);
		if (user == null)
			return null;

		UserDTO userDTO = new UserDTO(user.getUsername(), user.getName(), user.getSurname(), user.getEmail(),
				"NO_PASSWORD", user.getCompany(), user.getSport(), user.getCity(), user.getHour(), user.getMinute(),
				user.getRole());

//		List<EventDTO> eventsDTO = new ArrayList<EventDTO>();
//		for (Event e : user.getEvents()) {
//			eventsDTO.add(new EventDTO(e.getOrganiser().getUsername(), e.getStructureName(), e.getTypeOfStructure(),
//					e.getLatitude(), e.getLongitude(), e.isCovered(), e.getPrice(), e.getNumberOfPartecipant(),
//					e.getId(), e.getDistance(), e.getTime(), e.getLimitPartecipant(),e.getUsersRoles()));
//		}

//		userDTO.setEvents(eventsDTO);
		return userDTO;
	}

}
