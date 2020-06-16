package com.project.tz.services;

import com.project.tz.dto.CreateUpdateUserDto;
import com.project.tz.entities.Role;
import com.project.tz.entities.User;
import com.project.tz.repositories.RoleRepository;
import com.project.tz.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl();


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void checkIfCreatedUserNotNullIfEverythingOk() {
        CreateUpdateUserDto createUpdateUserDto = new CreateUpdateUserDto();
        createUpdateUserDto.setIds(new ArrayList<>(Arrays.asList(1, 2, 3)));
        Mockito.when(userRepository.save(any())).thenReturn(new User());
        Mockito.when(roleRepository.findById(anyLong())).thenReturn(new Role());
        User user = userService.createUser(createUpdateUserDto);
        assertThat(user).isNotNull();
    }

    @Test
    public void checkIfCreatingUserThrowsRuntimeExceptionWhenRolesEmpty() {
        CreateUpdateUserDto createUpdateUserDto = new CreateUpdateUserDto();
        Mockito.when(userRepository.save(any())).thenReturn(new User());
        Mockito.when(roleRepository.findById(anyLong())).thenReturn(new Role());
        assertThrows(RuntimeException.class, () -> userService.createUser(createUpdateUserDto));

    }

    @Test
    public void checkIfCreatingUserThrowsRuntimeExceptionWhenRoleNotFound() {
        CreateUpdateUserDto createUpdateUserDto = new CreateUpdateUserDto();
        createUpdateUserDto.setIds(new ArrayList<>(Arrays.asList(1, 2, 3)));
        Mockito.when(userRepository.save(any())).thenReturn(new User());
        Mockito.when(roleRepository.findById(anyLong())).thenReturn(null);
        assertThrows(RuntimeException.class, () -> userService.createUser(createUpdateUserDto));

    }

    @Test
    public void checkIfUpdatedUserNotNullIfEverythingOk() {
        CreateUpdateUserDto createUpdateUserDto = new CreateUpdateUserDto();
        createUpdateUserDto.setIds(new ArrayList<>(Arrays.asList(1, 2, 3)));
        Mockito.when(userRepository.findById(anyLong())).thenReturn(new User());
        Mockito.when(userRepository.save(any())).thenReturn(new User());
        Mockito.when(roleRepository.findById(anyLong())).thenReturn(new Role());
        User user = userService.updateUser(1, createUpdateUserDto);
        assertThat(user).isNotNull();
    }

    @Test
    public void checkIfUpdatingUserThrowsRuntimeExceptionWhenRolesEmpty() {
        CreateUpdateUserDto createUpdateUserDto = new CreateUpdateUserDto();
        Mockito.when(userRepository.findById(anyLong())).thenReturn(new User());
        Mockito.when(userRepository.save(any())).thenReturn(new User());
        Mockito.when(roleRepository.findById(anyLong())).thenReturn(new Role());
        assertThrows(RuntimeException.class, () -> userService.createUser(createUpdateUserDto));

    }

    @Test
    public void checkIfUpdatingUserThrowsRuntimeExceptionWhenRoleNotFound() {
        CreateUpdateUserDto createUpdateUserDto = new CreateUpdateUserDto();
        Mockito.when(userRepository.findById(anyLong())).thenReturn(new User());
        Mockito.when(userRepository.save(any())).thenReturn(new User());
        Mockito.when(roleRepository.findById(anyLong())).thenReturn(null);
        assertThrows(RuntimeException.class, () -> userService.createUser(createUpdateUserDto));
    }

    @Test
    public void checkIfUserNotFoundWhenDeleteThrowRuntimeException() {
        Mockito.when(userRepository.findById(anyLong())).thenReturn(null);
        assertThrows(RuntimeException.class, () -> userService.deleteUser(2));
        Mockito.verify(userRepository, Mockito.times(0)).deleteById(anyLong());
    }


    @Test
    public void checkIfDeletedUserDoesNotThrowExceptionIfEverythingOk() {
        Mockito.when(userRepository.findById(anyLong())).thenReturn(new User());
        assertDoesNotThrow(() -> userService.deleteUser(2));
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(anyLong());
    }

}
