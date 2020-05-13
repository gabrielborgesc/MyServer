package com.chat.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.domain.Message;
import com.chat.domain.Room;
import com.chat.domain.User;
import com.chat.dto.AddUserToRoomCommand;
import com.chat.dto.CreateRoomCommand;
import com.chat.dto.DeleteRoomCommand;
import com.chat.dto.RemoveUserFromRoomCommand;
import com.chat.dto.RoomCommand;
import com.chat.dto.UserCommand;
import com.chat.repositories.RoomRepository;

@Service
public class RoomService {

  private RoomRepository roomRepository;
  private MessageService messageService;
  private UserService userService;

  @Autowired
  public RoomService(
      RoomRepository roomRepository, MessageService messageService, UserService userService) {
    this.roomRepository = roomRepository;
    this.messageService = messageService;
    this.userService = userService;
  }

  public boolean create(CreateRoomCommand command) {
    Room room = this.findByName(command.getRoomName());

    if (room == null) {
      this.save(new Room(command.getRoomName()));
      return true;
    }
    return false;
  }

  public boolean delete(DeleteRoomCommand command) {
    Room room = this.findByName(command.getRoomName());
    if (room == null)
      return false;
    for ( User user : room.getUsers() ) {
    	RemoveUserFromRoomCommand newCommand = new RemoveUserFromRoomCommand();
    	newCommand.setRoomName(room.getName());
    	newCommand.setUsername(user.getUsername());
    	this.removeUser(newCommand);
    }
    this.deleteById(room.getId());
    return true;
  }

  public List<Message> getMessages(Integer roomId) {
    return this.messageService.findAllByRoomIdOrderByCreatedDate(roomId);
  }

  public Boolean addUser(AddUserToRoomCommand command) {
    User user = this.userService.findByUsername(command.getUsername());
    Room room = this.findByName(command.getRoomName());

    if (user == null || room == null) return false;

    user.getRooms().add(room);
    room.getUsers().add(user);

    this.userService.save(user);
    this.save(room);
    return true;
  }

  public Boolean removeUser(RemoveUserFromRoomCommand command) {
    User user = this.userService.findByUsername(command.getUsername());
    Room room = this.findByName(command.getRoomName());

    if (user == null || room == null) return false;
    
    else if (!(user.getRooms().contains(room) || room.getUsers().contains(user))) return false;

    user.getRooms().remove(room);
    room.getUsers().remove(user);

    this.userService.save(user);
    this.save(room);
    return true;
  }

  private void deleteById(Integer id) {
    this.roomRepository.deleteById(id);
  }

  private Room findByName(String name) {
    return this.roomRepository.findByName(name);
  }

  private Room save(Room room) {
    return this.roomRepository.save(room);
  }
  
  public List<RoomCommand> findAll() {
	  List<RoomCommand> result = new ArrayList<>();
	  for(Room room : roomRepository.findAll())
		  result.add(convert(room));
    return result;
  }

  private RoomCommand convert(Room room) {
	RoomCommand newRoom = new RoomCommand();
	newRoom.setRoomName(room.getName());
	return newRoom;
  }
}
