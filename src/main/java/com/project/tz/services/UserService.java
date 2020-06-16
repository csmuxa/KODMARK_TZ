package com.project.tz.services;

import com.project.tz.dto.CreateUpdateUserDto;
import com.project.tz.dto.GetAllUsersDto;
import com.project.tz.entities.User;

import java.util.List;

public interface UserService {

    List<GetAllUsersDto> getAllUsers();

    User getUser(long id);

    User createUser(CreateUpdateUserDto user);

    User updateUser(long id,CreateUpdateUserDto user);

    void deleteUser(long id);


}

