package com.ejb.ejbdemo.beans.service;

import com.ejb.ejbdemo.entity.User;
import jakarta.ejb.Local;
import java.util.List;

@Local
public interface UserService {

  void createUser(User user);

  void updateUser(String id, User user);

  void deleteUser(String id);

  User findById(String id);

  List<User> findAll();
}
