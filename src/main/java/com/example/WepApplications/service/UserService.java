package com.example.WepApplications.service;
import com.example.WepApplications.dto.UserDto;

import com.example.WepApplications.model.User;



import java.util.List;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
}