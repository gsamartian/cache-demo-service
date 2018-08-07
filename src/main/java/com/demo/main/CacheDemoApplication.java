package com.demo.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.demo.main.domain.User;
import com.demo.main.repository.UserRepository;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class CacheDemoApplication implements CommandLineRunner {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final UserRepository userRepository;

	@Autowired
	private CacheManager cacheManager;
	


	@Autowired
	public CacheDemoApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CacheDemoApplication.class, args);
	}

	@Override
	public void run(String... strings) {

		// Populating embedded database here
		LOG.info("Saving users. Current user count is {}.", userRepository.count());
		User raj = new User("Raj", 2000);
		User sheldon = new User("Sheldon", 29000);
		User penny = new User("Penny", 550);

		userRepository.save(raj);
		userRepository.save(sheldon);
		userRepository.save(penny);

		System.out.println(raj);
		System.out.println(sheldon);
		System.out.println(penny);

		// Load the Cache At Startup
		cacheManager.getCache("users").put(raj.getId(), raj);
		cacheManager.getCache("users").put(sheldon.getId(), sheldon);
		cacheManager.getCache("users").put(penny.getId(), penny);

		LOG.info("Done saving users. Data: {}.", userRepository.findAll());
		
	
		
	}

}
