package app;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.pojo.User;
import app.service.UserService;

public class UserRepoTest  {

	private UserService repository = new UserService();
	private User user;

	@Before
	public void setUp() {
		repository.startOperation();
		
		user = new User("firstname", "lastname");
		user = repository.save(user);
		
		repository.commit();
	}

	@After
	public void tearDown(){
		repository.startOperation();
		
		repository.delete(user);
		repository.commit();
		
		assertTrue(repository.count() == 0);
		repository.close();
	}
	
	@Test
	public void findUserById() {
		User fetchedUser = repository.find(user.getId());
		assertNotNull(fetchedUser);
		assertTrue(fetchedUser.getFirstName().compareTo("firstname")==0);
		assertTrue(fetchedUser.getLastName().compareTo("lastname")==0);
	}
	
	@Test
	public void saveAndFindUserByLastName() {
		List<User> fetchedUsers = repository.findByLastName(user.getLastName());
		assertTrue(fetchedUsers.size() > 0);
		User firstFetchedUser = fetchedUsers.get(0);
		assertNotNull(firstFetchedUser);
		assertTrue(firstFetchedUser.getFirstName().compareTo("firstname")==0);
		assertTrue(firstFetchedUser.getLastName().compareTo("lastname")==0);
	}
	
}
