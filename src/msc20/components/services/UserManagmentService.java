package msc20.components.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import msc20.components.model.Credential;
import msc20.components.model.User;
import msc20.components.persistence.CredentialsDAO;

@Service
public class UserManagmentService {

	@Autowired
	private CredentialsDAO credentialsDAO;

	public boolean existUser(String username) {
		return credentialsDAO.userExist(username);

	}

	public void createUser(String username, String email, String name, String surname, String password) {
		User user = new User(email, name, surname, username);
		Credential credential = new Credential(user, password);
		credentialsDAO.save(credential);

	}

}
