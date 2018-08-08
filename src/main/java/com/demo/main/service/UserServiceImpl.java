package com.demo.main.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.main.domain.User;
import com.demo.main.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAllUsers() {
		LOG.debug("Fetching All Persons");
		return userRepository.findAll();
	}

	@Override
	public User getUserById(long id) {
		LOG.debug("Getting user with ID {}.", id);
		return userRepository.findOne(id);
	}

	@Override
	public User updateUserById(long id, User user) {
		LOG.debug("Updating user with ID {}.", id);
		userRepository.save(user);
		return user;
	}

	@Override
	public void deleteUserById(long id) {
		LOG.debug("deleting person with id {}", id);
		userRepository.delete(id);
	}

}
