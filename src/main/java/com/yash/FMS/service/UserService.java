package com.yash.FMS.service;

import com.yash.FMS.dto.UserDTO;
import com.yash.FMS.domain.User;

import java.util.List;


public interface UserService {


    boolean createUser(User user);

    UserDTO authenticateUser(String username, String password);


    UserDTO getUserById(int userId);


    UserDTO getUserByUsername(String username);


    List<UserDTO> getAllUsers();


    boolean updateUser(User user);


    boolean deleteUser(int userId);


}
