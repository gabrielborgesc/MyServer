package com.chat.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chat.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>
{
	public User findByUsername(String username);
}
