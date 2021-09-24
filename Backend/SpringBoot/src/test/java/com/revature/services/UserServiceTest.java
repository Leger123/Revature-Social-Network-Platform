package com.revature.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.revature.models.User;
import com.revature.models.UserResponse;
import com.revature.repository.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zimi Li
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    UserService userService;
    User user;

    @Mock
    UserDao userDao;

    @BeforeEach
    void setup() {
        userService = new UserService(userDao);
        user = new User(1, "user1", "pass1", "name", "zimi.li@revature.net", null, null,null, null);
    }

    @Test
    void createUserNotNull() {
        Mockito.when(userDao.findUserByUsername(user.getUsername())).thenReturn(null);
        Mockito.when(userDao.save(Mockito.any(User.class))).thenReturn(user);

        User actual = userService.createUser(user);

        assertEquals(user, actual);
        Mockito.verify(userDao, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    void createUserNull() {
        Mockito.when(userDao.findUserByUsername(user.getUsername())).thenReturn(user);
        assertNull(userService.createUser(user));
    }

    @Test
    void loginSucceed() {
        User encrypt = new User(1, "user1", "pass1", "name", "email", null, null, null, null);
        encrypt.setPassword(BCrypt.withDefaults().hashToString(12, encrypt.getPassword().toCharArray()));
        Mockito.when(userDao.findUserByUsername(user.getUsername())).thenReturn(encrypt);
        assertEquals(encrypt, userService.login(user));
    }

    @Test
    void loginFailed() {
        User encrypt = new User(1, "user1", "pass", "name", "email", null, null, null, null);
        encrypt.setPassword(BCrypt.withDefaults().hashToString(12, encrypt.getPassword().toCharArray()));
        Mockito.when(userDao.findUserByUsername(user.getUsername())).thenReturn(encrypt);
        assertNull(userService.login(user));
    }

    @Test
    void resetPasswordSucceed() {
        Mockito.when(userDao.findUserByEmail(user.getEmail())).thenReturn(user);
        assertTrue(userService.resetPassword(user.getEmail()));
    }

    @Test
    void resetPasswordFailed() {
        Mockito.when(userDao.findUserByEmail(user.getEmail())).thenReturn(null);
        assertFalse(userService.resetPassword(user.getEmail()));
    }

    @Test
    void updateUser() {
        Mockito.when(userDao.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(userDao.save(Mockito.any(User.class))).thenReturn(user);
        assertEquals(user, userService.updateUser(user.getId(), user));
    }

    @Test
    void getAllUser() {
        List<User> users = new ArrayList<>();
        users.add(user);
        Mockito.when(userDao.findAll()).thenReturn(users);

        List<UserResponse> expected = new ArrayList<>();
        expected.add(new UserResponse(user.getId(), user.getName(), user.getProfileImage(), user.getLastNotification()));
        assertEquals(expected, userService.getAllUser());
    }

    @Test
    void getUserNull() {
        Integer id = 1;
        Mockito.when(userDao.findById(id)).thenReturn(Optional.empty());
        assertNull(userService.getUser(id));
    }

    @Test
    void getUserNotNull() {
        Integer id = 1;
        Mockito.when(userDao.findById(id)).thenReturn(Optional.ofNullable(user));
        UserResponse expected = new UserResponse(user.getId(), user.getName(), user.getProfileImage(), user.getLastNotification());
        assertEquals(expected, userService.getUser(id));
    }
}