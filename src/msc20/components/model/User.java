package msc20.components.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

	@Id
	private String username;
	@Column
	private String email;
	@Column
	private String name;
	@Column
	private String surname;

	public User() {
		super();
	}

	public User(String email, String name, String surname, String username) {
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.username= username;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public String getEmail() {
		return email;
	}



	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
