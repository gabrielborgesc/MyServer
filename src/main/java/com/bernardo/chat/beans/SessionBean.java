package com.bernardo.chat.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.bernardo.chat.domain.Room;
import com.bernardo.chat.domain.Type;
import com.bernardo.chat.domain.User;
import com.bernardo.chat.repositories.UserRepository;

@Component
@SessionScope
public class SessionBean 
{
	private User sessionUser;
	
	private Room currentRoom;
	
	private UserRepository userRepository;
	
	@Autowired
	public SessionBean(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	public void login(User user)
	{
		this.sessionUser = user;
	}
	
	public void logout()
	{
		this.sessionUser = null;
	}
	
	public boolean isLogged()
	{
		return sessionUser != null;
	}
	
	public String getUsername()
	{
		return sessionUser == null ? null : sessionUser.getUsername();
	}
	
	public void refresh()
	{
		if(sessionUser == null)
			return;
		this.sessionUser = userRepository.findById(sessionUser.getId()).get();
	}
	
	public Type getLoggedRole()
	{
		if(sessionUser == null)
			return null;
		return sessionUser.getType();
	}
	
	public void enterRoom(Room room) {
		this.currentRoom = room;
	}
	
	public Room getCurrentRoom() {
		return this.currentRoom;
	}
	
	public User getCurrentUser() {
		return this.sessionUser;
	}
	
}
