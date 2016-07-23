package app.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.pojo.User;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByLastName(String lastName);
}