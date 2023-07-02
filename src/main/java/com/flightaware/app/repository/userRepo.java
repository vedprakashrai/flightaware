package com.flightaware.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flightaware.app.model.User;
import com.flightaware.app.model.UserIp;

@Repository
public interface userRepo extends CrudRepository<User,Long> {
	User findByEmailIdIgnoreCase(String emailId);

}