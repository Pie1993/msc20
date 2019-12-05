package msc20.components.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import msc20.components.model.Credential;
import msc20.components.model.User;


@Repository
public class CredentialsDAO {

	@Autowired
	private DbManager dbManager;

	@Autowired
	private SessionFactory sessionFactory;

	public User checkLogin(String username, String password) {
		final Session session = sessionFactory.openSession();
		Credential cred = null;
		final Query<Credential> query = session
				.createNativeQuery("select * from Credential as c where c.user_username=:u and c.password=:p",
						Credential.class)
				.setParameter("u", username).setParameter("p", password);
		cred = query.uniqueResult();
		session.close();
		return cred == null ? null : cred.getUser();
	}

	public boolean save(Credential credentials) {
		return dbManager.save(credentials);
	}

	public boolean userExist(String username) {
		if (username == null)
			return false;
		final Session session = sessionFactory.openSession();
		final Query<Credential> query = session
				.createNativeQuery("select * from Credential as c where c.user_username=:u", Credential.class)
				.setParameter("u", username);
		final boolean result = query.uniqueResult() != null;
		session.close();
		return result;
	}


}
