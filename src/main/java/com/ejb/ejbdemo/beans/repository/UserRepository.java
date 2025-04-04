package com.ejb.ejbdemo.beans.repository;

import com.ejb.ejbdemo.entity.User;
import jakarta.ejb.Local;
import java.util.List;

@Local
public interface UserRepository {
  User save(User user);

  void update(User user);

  void delete(User user);

  User findById(String id);

  List<User> findAll();

  boolean existsByEmail(String email);

  boolean existsByUsername(String username);
}
