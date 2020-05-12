package com.bernardo.chat.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bernardo.chat.domain.Type;
import com.bernardo.chat.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest 
{
	@Autowired
	private UserRepository userRepository;

	//@Test
	public void findAllTest()
	{
		Iterable<User> users = userRepository.findAll();
		for (User user : users) {
			System.out.println(user.getUsername());
		}
		
	}
	
	//@Test
	public void findById()
	{
		Optional<User> optional = userRepository.findById(1);
		
		User user = optional.get();
		
		System.out.println(user.getUsername());
		
	}
	
	//@Test
	public void findByUsername()
	{
		User user = userRepository.findByUsername("gabriel");
		
		//System.out.println(user.getPassword());
	}
	
	
	//@Test
		public void insert(User newuser) {
				
			User user = userRepository.findByUsername(newuser.getUsername());
			if(user==null) {
			userRepository.save(newuser);
		}
		}
	
	//@Test
		public void delete(String username) {
			User user = userRepository.findByUsername(username);
			if(user!=null) {
			userRepository.deleteById(user.getId());
			}
		}
		
		public void updateEmail(String username, String newEmail) {
			User user = userRepository.findByUsername(username);
			if(user!=null) {
				user.setEmail(newEmail);
				userRepository.save(user);
				
			}
		}
		
		public void updatePassword(String username, String newPassword) {
			User user = userRepository.findByUsername(username);
			if(user!=null) {
				user.setPassword(newPassword);
				userRepository.save(user);
				
			}
		}
	
	@Test
	public void test() {
		User newuser = new User();
		newuser.setUsername("werneck");
		//newuser.setId(2);
		newuser.setEmail("wern@gmail.com");
		newuser.setPassword("abcde");
		Type type = Type.REGULAR;
		newuser.setType(type);
		
		updatePassword("gabriel", "12345");
		
	}
	
}
