package com.demo.main.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.main.domain.User;
import com.demo.main.service.UserService;

@RestController
@RequestMapping
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Cacheable(value = "members", key = "#id")
	@GetMapping("/members/{id}")
	public User getMembers(@PathVariable long id) {
		LOG.info("Getting member with ID {}.", id);
		return userService.getUserById(id);
	}
	
	
	@Cacheable(value = "users", key = "#id")
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable long id) {
		LOG.info("Getting user with ID {}.", id);
		return userService.getUserById(id);
	}

}
