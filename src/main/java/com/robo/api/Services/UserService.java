package com.robo.api.Services;

import com.robo.api.Domain.DTO.UserDto;
import com.robo.api.Domain.Users;

import java.util.List;

public interface UserService {

    Users findById(Integer id);
    List<Users> findAll();
    Users create(UserDto obj);

    Users update(UserDto obj);
    void delete(Integer id);

}
