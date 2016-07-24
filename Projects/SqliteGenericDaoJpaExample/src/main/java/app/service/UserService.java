package app.service;

import app.repo.UserRepository;

/**
 * Service class to house any additional business logic on top of UserRepository functionality 
 */
public class UserService extends UserRepository{

	public void printUserCount() {
		long count = this.count();
		System.out.println("THE USER COUNT IS: " + count);
	}
}
