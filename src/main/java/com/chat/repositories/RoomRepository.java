package com.chat.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chat.domain.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {
  public Room findByName(String roomName);
}
