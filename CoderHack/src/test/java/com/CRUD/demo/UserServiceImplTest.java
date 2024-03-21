package com.CRUD.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.CRUD.demo.Repository.UserRepository;
import com.CRUD.demo.Service.UserServiceImpl;
import com.CRUD.demo.entity.User;
import com.CRUD.demo.Service.UserService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetAllUsers() {
        // Mock data
        List<User> users = new ArrayList<>();
        users.add(new User("1", "user1", 50, null));
        users.add(new User("2", "user2", 70, null));

        // Mock behavior
        when(userRepository.findAll()).thenReturn(users);

        // Call service method
        List<User> retrievedUsers = userService.getAllUsers();

        // Verify
        assertEquals(users.size(), retrievedUsers.size());
        assertEquals(users.get(0).getUsername(), retrievedUsers.get(0).getUsername());
        assertEquals(users.get(1).getUsername(), retrievedUsers.get(1).getUsername());

        // Verify that the repository method was called
        verify(userRepository, times(1)).findAll();
    }
    // working
    @Test
    public void testUpdateUserScore() {
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserServiceImpl(userRepository);
        
        User user = new User("1", "TestUser", 50, Set.of("Code Ninja"));
        
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        
        userService.updateUserScore("1", 70);
        
        verify(userRepository, times(1)).save(user);
        
        assertEquals(70, user.getScore());
        
        Set<String> expectedBadges = new HashSet<>();
        expectedBadges.add("Code Master");
        assertEquals(expectedBadges, user.getBadges());
    }
    
    
    @Test
    public void testDeleteUser() {
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserServiceImpl(userRepository);
        
        String userId = "1";
        
        userService.deleteUser(userId);
        
        verify(userRepository, times(1)).deleteById(userId);
    }
}
