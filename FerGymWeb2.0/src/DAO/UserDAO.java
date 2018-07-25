package DAO;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import model.User;

public class UserDAO {
	
	/**
	 * Function to find the user with has this credentials
	 * @param session hibernate session
	 * @param email user email
	 * @param password user password
	 * @return user
	 */
	public User getUserByLogin(Session session, String email, String password)
	{
		Query query = session.createQuery("FROM User WHERE email=:email AND password=:password");
		query.setParameter("email", email);
		query.setParameter("password", password);
		
		@SuppressWarnings("unchecked")
		List<Object> list = query.getResultList();
		User user = (User) list.get(0);
		return user;
	}
}
