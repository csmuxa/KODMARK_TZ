package com.project.tz.controllers;

import com.project.tz.dto.CreateUpdateUserDto;
import com.project.tz.dto.GetAllUsersDto;
import com.project.tz.entities.User;
import com.project.tz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


    @RestController
    @RequestMapping("/user")
    public class UserController {

        @Autowired
        UserService userService;

        @GetMapping
        public List<GetAllUsersDto> getAllUsers() {
            List<GetAllUsersDto> allUsers = userService.getAllUsers();

            return allUsers;
        }

        @GetMapping("{id}")
        public User getUser(@PathVariable("id") long id) {
            User user = userService.getUser(id);

            return user;
        }

        @PostMapping()
        public User createUser(@Valid @RequestBody  CreateUpdateUserDto user) {
            User creatingUser = userService.createUser(user);

            return creatingUser;
        }

        @PutMapping("{id}")
        public User updateUser(@PathVariable("id") long id,@Valid @RequestBody CreateUpdateUserDto user) {
            User updatingUser = userService.updateUser(id, user);

            return updatingUser;
        }

        @DeleteMapping("{id}")
        public ResponseEntity<String> deleteUser(@PathVariable("id")long id) {
            userService.deleteUser(id);

            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
        }
}
