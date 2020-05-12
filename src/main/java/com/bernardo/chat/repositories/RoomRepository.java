package com.bernardo.chat.repositories;

import com.bernardo.chat.domain.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {
  public Room findByName(String roomName);
}
