package com.ejb.ejbdemo.beans.service;

import com.ejb.ejbdemo.beans.repository.UserRepository;
import com.ejb.ejbdemo.entity.User;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
@Local(UserService.class)
public class UserServiceImpl implements UserService {

  @Inject
  private UserRepository repository;

  @Override
  public void createUser(User user) {

    repository.save(user);
  }

  @Override
  public void updateUser(String id, User user) {

  }

  @Override
  public void deleteUser(String id) {
    if (id == null || id.trim().isEmpty()) {
      throw new IllegalArgumentException("User ID cannot be empty");
    }

    // Find the user by ID
    User user = findById(id);

    if (user == null) {
      throw new IllegalArgumentException("User not found with ID: " + id);
    }

    try {
      repository.delete(user);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public User findById(String id) {

    return repository.findById(id);
  }

  @Override
  public List<User> findAll() {
    return repository.findAll();
  }
}
