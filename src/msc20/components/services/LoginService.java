package msc20.components.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import msc20.components.model.User;
import msc20.components.persistence.CredentialsDAO;

@Service
public class LoginService {

	@Autowired
	private CredentialsDAO credentialsDAO;

	public User login(String username, String password) {
		return credentialsDAO.checkLogin(username, password);
	}

}
