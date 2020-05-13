package com.chat.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.beans.SessionBean;
import com.chat.domain.Room;
import com.chat.domain.User;
import com.chat.dto.ChatListCommand;
import com.chat.dto.ChatSelectionCommand;
import com.chat.repositories.RoomRepository;

@Service
public class ChatSelectorService {
	private SessionBean sessionBean;
	private RoomRepository roomRepository;
	
	@Autowired
	public ChatSelectorService(SessionBean sessionBean, RoomRepository roomRepository) {
		this.sessionBean = sessionBean;
		this.roomRepository = roomRepository;
	}
	
	public List<ChatListCommand> chats() {
		sessionBean.refresh();
		
		if(!validateRooms(sessionBean.getCurrentUser()))
			return null;
		
		List<ChatListCommand> chats = new ArrayList<ChatListCommand>();
		
		for(Room room : sessionBean.getCurrentUser().getRooms())
		{
			chats.add(convert(room));
		}
		
		
		return chats;
	}
	
	public boolean selectChat(ChatSelectionCommand chatSelectionCommand) {
		Room selectedRoom = roomRepository.findById(chatSelectionCommand.getChatId()).get();
		
		if(selectedRoom == null)
			return false;
		
		sessionBean.enterRoom(selectedRoom);
		return true;
	}
	

	private ChatListCommand convert(Room room) {
		return new ChatListCommand(room.getId(), room.getName(), "oioioi");
	}

	private boolean validateRooms(User currentUser) {
		if(currentUser == null || currentUser.getRooms() == null)
			return false;
		return true;
	}
	
}
