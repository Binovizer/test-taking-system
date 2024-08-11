package org.ad.service;

import org.ad.model.User;
import org.ad.model.UserRole;
import org.ad.util.IdGenerationUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type UserService
 *
 * @author nadeem Date : 10/08/24
 */
public class UserServiceImpl implements UserService {
    private final Map<String, User> userMap;

    public UserServiceImpl() {
        userMap = new HashMap<>();
    }

    @Override
    public User addUser(User user) {
        // Add validation based on unique identities if applicable
        String id = IdGenerationUtil.generateId();
        user.setId(id);
        userMap.put(id, user);
        System.out.println("User created: " + user);
        return userMap.get(id);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public User getUserById(String id) {
        return userMap.get(id);
    }

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.addUser(
                User.builder()
                        .firstName("Alice")
                        .lastName("Doe")
                        .email("alice.doe@email.com")
                        .role(UserRole.valueOf("student".toUpperCase()))
                        .build());
        List<User> allUsers = userService.getAllUsers();
        System.out.println(allUsers);
    }
}
