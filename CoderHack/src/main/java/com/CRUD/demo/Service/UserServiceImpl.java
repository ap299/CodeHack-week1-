package com.CRUD.demo.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import com.CRUD.demo.Repository.UserRepository;
import com.CRUD.demo.entity.User;

import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
    private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	@Override
    public User addUser(User user) {
        // Set score
        int score = user.getScore();
        if (score >= 1 && score <= 30) {
            user.setBadges(Collections.singleton("Code Ninja"));
        } else if (score > 30 && score <= 60) {
            user.setBadges(Collections.singleton("Code Champ"));
        } else if (score > 60 && score <= 100) {
            user.setBadges(Collections.singleton("Code Master"));
        }
        
        // Save user
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User registerUser(User user) {
        user.setScore(0);
        user.setBadges(Set.of());
        return userRepository.save(user);
    }

    public User updateUserScore(String userId, int newScore) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setScore(newScore);
            // Update badges based on the new score
            Set<String> newBadges = calculateBadges(newScore);
            user.setBadges(newBadges);
            userRepository.save(user);
        }
        return user;
    }

    private Set<String> calculateBadges(int score) {
        Set<String> badges = new HashSet<>();
        if (score >= 1 && score <= 30) {
            badges.add("Code Ninja");
        } else if (score > 30 && score <= 60) {
            badges.add("Code Champ");
        } else if (score > 60 && score <= 100) {
            badges.add("Code Master");
        }
        return badges;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
    
    @Override
    public List<User> getAllUsersSortedByScore() {
        List<User> users = userRepository.findAll();
        
        // Sort users by score in descending order
        Collections.sort(users, Comparator.comparingInt(User::getScore).reversed());
        
        return users;
    }

}
