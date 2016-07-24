package app.repo;

import java.util.List;
import java.util.UUID;

import javax.persistence.Query;

import app.pojo.User;

/**
 * Repository class to house custom queries specific to the User entity
 */
public class UserRepository extends GenericDao<User, UUID>{

    public UserRepository() {
		super(User.class, UUID.class);
	}

	public List<User> findByLastName(String lastName){
        Query query = session.createQuery("select u FROM User u where u.lastName= :lastName");
        query.setParameter("lastName", lastName);
        List<User> users = query.getResultList();
        return users;
	}
	
}