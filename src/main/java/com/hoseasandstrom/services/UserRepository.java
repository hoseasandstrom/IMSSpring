package com.hoseasandstrom.services;

import com.hoseasandstrom.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hoseasandstrom on 10/19/16.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByUsername(String username);
}
