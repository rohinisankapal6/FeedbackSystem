package com.yash.FMS.dao;

import com.yash.FMS.domain.Category;
import com.yash.FMS.domain.Feedback;
import com.yash.FMS.domain.User;

import java.util.List;


public interface UserDAO {


    boolean createUser(User user);


    User getUserById(int userId);


    User getUserByUsername(String username);


    List<User> getAllUsers();


    boolean updateUser(User user);


    boolean deleteUser(int userId);
}
