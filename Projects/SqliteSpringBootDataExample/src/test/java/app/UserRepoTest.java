package app;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import app.pojo.User;
import app.repo.UserRepository;

@Transactional
public class UserRepoTest extends AbstSpringBootTest {

	@Autowired 
	UserRepository repository;
	
	User user;

	@Before
	public void setUp() {
		user = new User("firstname", "lastname");
	}

	@Test
	public void saveAndFindUserById() {
		user = repository.save(user);
		User fetchedUser = repository.findOne(user.getId());
		assertNotNull(fetchedUser);
		assertTrue(fetchedUser.getFirstName().compareTo("firstname")==0);
		assertTrue(fetchedUser.getLastName().compareTo("lastname")==0);
	}
	
	@Test
	public void saveAndFindUserByLastName() {
		user = repository.save(user);
		List<User> fetchedUsers = repository.findByLastName(user.getLastName());
		assertTrue(fetchedUsers.size() > 0);
		User firstFetchedUser = fetchedUsers.get(0);
		assertNotNull(firstFetchedUser);
		assertTrue(firstFetchedUser.getFirstName().compareTo("firstname")==0);
		assertTrue(firstFetchedUser.getLastName().compareTo("lastname")==0);
	}
	
}
