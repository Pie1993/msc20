package msc20.components.services;

import msc20.components.model.*;
import msc20.components.persistence.CredentialsDAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import javax.annotation.PostConstruct;


@Service
public class PostConstructService {


	@Autowired
	private CredentialsDAO credentialsDAO;


	@PostConstruct
	public void initDatabase() {
					
		User u1 = new User("mail@mail.it", "nome", "cognome", "ciccio");
			
		credentialsDAO.save(new Credential(u1, "password"));

	}
}
