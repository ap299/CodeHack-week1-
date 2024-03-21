package com.CRUD.demo.Service;

import java.util.List;

import com.CRUD.demo.entity.User;

public interface UserService {

    List<User> getAllUsers();
    
    List<User> getAllUsersSortedByScore();
    
    User addUser(User user);


    User getUserById(String userId);

    User registerUser(User user);

    User updateUserScore(String userId, int newScore);
    

    void deleteUser(String userId);
}
