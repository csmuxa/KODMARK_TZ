package com.project.tz.services;

import com.project.tz.dto.CreateUpdateUserDto;
import com.project.tz.dto.GetAllUsersDto;
import com.project.tz.entities.Role;
import com.project.tz.entities.User;
import com.project.tz.repositories.RoleRepository;
import com.project.tz.repositories.UserRepository;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @PostConstruct
    void initDBWithRoles() {
        Role role1 = new Role();
        role1.setRoleName("ADMIN");
        Role role2 = new Role();
        role2.setRoleName("OPERATOR");
        Role role3 = new Role();
        role3.setRoleName("STATISTIC");

        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
    }


    @Override
    public List<GetAllUsersDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<GetAllUsersDto> userDtos = new ArrayList<>();
        for (User user : users) {
            GetAllUsersDto userDto = new GetAllUsersDto();
            BeanUtils.copyProperties(user, userDto);
            userDtos.add(userDto);
        }

        return userDtos;
    }

    @Override
    public User getUser(long id) {
        User user = userRepository.findById(id);

        if (user == null)
            throw new RuntimeException("User not found");

        return user;
    }

    @Override
    public User createUser(CreateUpdateUserDto user) {
        User gettingUser = userRepository.findByUsername(user.
                getUsername());
        if (gettingUser != null)
            throw new RuntimeException("User already exists");



        User creatingUser = new User();
        BeanUtils.copyProperties(user, creatingUser);


        Set<Role> roles = new HashSet<>();


        if (user.getIds() != null) {
            for (Integer id : user.getIds()) {
                Role role = roleRepository.findById(id);
                if (role != null)
                    roles.add(role);
                else throw new RuntimeException("One or more roles not found");
            }
        }
        else throw new RuntimeException("Roles haven't chosen");
        creatingUser.setRoles(roles);
        User returningUser = userRepository.save(creatingUser);
        return returningUser;
    }

    @Override
    public User updateUser(long id, CreateUpdateUserDto user) {
        User persistingUser = userRepository.findById(id);
        if (persistingUser == null)
            throw new RuntimeException("Couldn't update,user not found");
        BeanUtils.copyProperties(user, persistingUser, "id");
        Set<Role> roles = new HashSet<>();
        if (user.getIds() != null) {
            for (Integer ids : user.getIds()) {
                Role role = roleRepository.findById(ids);
                if (role != null)
                    roles.add(role);
                else throw new RuntimeException("One or more roles not found");
            }
        }
        else throw new RuntimeException("Roles haven't chosen");

        persistingUser.setRoles(roles);
        User returningUser = userRepository.save(persistingUser);
        return returningUser;
    }

    @Override
    public void deleteUser(long id) {
        User deletingUser = userRepository.findById(id);
        if (deletingUser == null)
            throw new RuntimeException("Couldn't delete,user not found");
        userRepository.deleteById(id);
    }
}
