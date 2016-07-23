package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import app.repo.UserRepository;

@Service
public class PersistentDataService implements ApplicationRunner {

	@Autowired
	private UserRepository repository;

	public void printUserCount() {
		long count = repository.count();
		System.out.println("THE USER COUNT IS: " + count);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.print("From the run method: ");
		printUserCount();
	}
}
