package com.bernardo.chat.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bernardo.chat.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>
{
	public User findByUsername(String username);
}
