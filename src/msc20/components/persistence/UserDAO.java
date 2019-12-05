package msc20.components.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import msc20.components.model.User;


@Repository
public class UserDAO {
	@Autowired
	private DbManager dbManager;


	/**
	 * method used for save a new User
	 * 
	 * @param user
	 * @return the save result status
	 */
	public boolean save(User user) {
		return dbManager.save(user.getClass().cast(user));
	}

	/**
	 * method used for update a user
	 * 
	 * @param user
	 * @return the update result status
	 */

	public boolean update(User user) {
		return dbManager.update(user.getClass().cast(user));
	}

	public boolean remove(User user) {
		return dbManager.delete(user.getClass().cast(user));
	}




}
