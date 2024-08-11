package org.ad.service;

import org.ad.model.User;

import java.util.List;

/**
 * The type UserService
 *
 * @author nadeem Date : 10/08/24
 */
public interface UserService {
    User addUser(User user);

    List<User> getAllUsers();

    User getUserById(String id);
}
