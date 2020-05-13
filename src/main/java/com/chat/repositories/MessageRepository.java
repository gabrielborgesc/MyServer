package com.chat.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chat.domain.Message;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
  public List<Message> findAllByRoomId(Integer roomId); // busca por todas as mensagens da sala
  /// deveríamos restingir por data

  public List<Message> findAllByUserId(Integer userId);

  public List<Message> findAllByUserIdAndRoomId(Integer userId, Integer roomId);

  public List<Message> findAllByRoomIdOrderByCreatedDate(Integer roomId);
}
