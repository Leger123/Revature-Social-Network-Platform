package com.revature.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.revature.models.JSONResponse;
import com.revature.models.User;
import com.revature.models.UserResponse;
import com.revature.services.UserService;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import org.junit.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Bhavani
 */


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    UserController userController;

    @Mock
    UserService userService;

    @Mock
    HttpServletRequest request;

    @BeforeEach
    void setUp() {
        userController = new UserController(userService);
    }

    @Test
    void registerUserNotNull() {
        //assign
        User user = new User(1, "byelagala", "pass123", "bhavani", "bhavani.yelagala@mail.com", "byelagala");
        JSONResponse expectedResult = new JSONResponse(true, "Register Succeed", user);
        Mockito.when(userService.createUser(user)).thenReturn(user);
        //act
        JSONResponse actualResult = userController.register(user);
        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void registerUserNull() {
        //assign
        JSONResponse expectedResult = new JSONResponse(false, "Register Failed", null);
        Mockito.when(userService.createUser(null)).thenReturn(null);

        //act
        JSONResponse actualResult = this.userController.register(null);

        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void loginSucceed() {
        //assign
        User user = new User(1, "byelagala", "pass123", "bhavani", "bhavani.yelagala@mail.com", "byelagala");
        JSONResponse expectedResult = new JSONResponse(true, "Login Succeed", null);
        request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();

        Mockito.when(userService.login(user)).thenReturn(user);

        //act
        JSONResponse actualResult = this.userController.login(user, request);

        //assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void loginFailed() {
        //assign
        JSONResponse expectedResult = new JSONResponse(false, "Login Failed", null);
        Mockito.when(userService.login(null)).thenReturn(null);
        //assert
        assertEquals(expectedResult, this.userController.login(null, request));
    }

    @Test
    void logout() {
        //assign
        JSONResponse expectedResult = new JSONResponse(true, "Logout Succeed", null);

        request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();
        //act
        JSONResponse actualResult = this.userController.logout(request);

        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void resetPasswordSucceed() {
        //assign
        String email = "abc@ibm.com";
        JSONResponse expectedResult = new JSONResponse(true, "Email has been sent to " + email, null);
        Mockito.when(userService.resetPassword(email)).thenReturn(true);

        //act
        JSONResponse actualResult = this.userController.resetPassword(email);

        //assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void resetPasswordFailed() {
        //assign
        String email = null;
        JSONResponse expectedResult = new JSONResponse(false, "Email does not match our record", null);
        Mockito.when(userService.resetPassword(email)).thenReturn(false);

        //act
        JSONResponse actualResult = this.userController.resetPassword(null);

        //assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void updateUserReturnNotNull() {

        //assign
        User user = new User(1, "byelagala", "pass123", "bhavani", "bhavani.yelagala@mail.com", "byelagala");

        JSONResponse expectedResult = new JSONResponse(true, "User info has been updated", null);

        request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();

        Mockito.when(userService.updateUser(1, user)).thenReturn(user);

        request.getSession().setAttribute("User", user);

        //act
        JSONResponse actualResult = this.userController.update(user, request);

        //assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void updateUserReturnNull() {
        //assign
        User user = new User(1, "byelagala", "pass123", "bhavani", "bhavani.yelagala@mail.com", "byelagala");

        request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();

        JSONResponse expectedResult = new JSONResponse(false, "User info update failed", null);

        Mockito.lenient().when(userService.updateUser(user.getId(), user)).thenReturn(user);

        //act
        JSONResponse actualResult = this.userController.update(user, request);

        //assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void updateProfileImageSuccess() {

        //assign
        User user = new User(1, "byelagala", "pass123", " ", "bhavani.yelagala@mail.com", "byelagala");

        String imgUrl = "https://nathan.com";

        request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();

        JSONResponse expectedResult = new JSONResponse(true, "Profile image has been updated", null);

        Mockito.lenient().when(userService.updateProfileImg(user.getId(), imgUrl)).thenReturn(user);

        request.getSession().setAttribute("User", user);

        //act
        JSONResponse actualResult = this.userController.updateProfileImage(imgUrl, request);

        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void updateProfileImageFailed() {
        //assign
        User user = new User(1, "byelagala", "pass123", "bhavani", "bhavani.yelagala@mail.com", "byelagala");

        String imgUrl = "https://nathan.com";

        request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();

        JSONResponse expectedResult = new JSONResponse(false, "Profile image update failed", null);

        Mockito.lenient().when(userService.updateProfileImg(user.getId(), imgUrl)).thenReturn(user);

        //act
        JSONResponse actualResult = this.userController.updateProfileImage(imgUrl, request);

        //assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getAllUser() {

        UserResponse response = new UserResponse();

        response.setId(1);
        response.setName("Akhila");
        response.setProfileImage("Image");
        response.setLastNotification(9L);

        List<UserResponse> userResponseList = Collections.singletonList(response);

        JSONResponse expectedResult = new JSONResponse(true, "All user", userResponseList);

        Mockito.when(userService.getAllUser()).thenReturn(userResponseList);
        //assert
        assertEquals(expectedResult, this.userController.getAllUser());
    }

    @Test
    void getUserReturnNull() {
        //assign
        Integer id = 1;
        User user = new User(1, "byelagala", "pass123", "bhavani", "bhavani.yelagala@mail.com", "byelagala");
        JSONResponse expectedResult = new JSONResponse(false, "User not Found", null);
        Mockito.when(userService.getUser(user.getId())).thenReturn(null);
        //act
        JSONResponse actualResult = this.userController.getUser(user.getId());
        //assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getUserReturnNotNull() {
        //assign
        UserResponse response = new UserResponse();
        response.setId(1);
        response.setName("Akhila");
        response.setProfileImage("Image");
        response.setLastNotification(9L);

        JSONResponse expectedResult = new JSONResponse(true, "User Found", response);
        Mockito.when(userService.getUser(response.getId())).thenReturn(response);

        //act
        JSONResponse actualResult = this.userController.getUser(response.getId());

        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void checkSession() {

        //assign
        User user = new User(1, "byelagala", "pass123", "bhavani", "bhavani.yelagala@mail.com", "byelagala");

        request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();

        JSONResponse expectedResult = new JSONResponse(true, "Session Found", user);

        Mockito.lenient().when(userService.refreshUser(user.getId())).thenReturn(user);

        request.getSession().setAttribute("User", user);

        //act
        JSONResponse actualResult = this.userController.checkSession(request);

        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void checkSessionIsNull() {
        //assign
        User user = new User(1, "byelagala", "pass123", "bhavani", "bhavani.yelagala@mail.com", "byelagala");


        JSONResponse expectedResult = new JSONResponse(false, "No Session Found", null);

        request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();


        Mockito.lenient().when(userService.refreshUser(user.getId())).thenReturn(null);

        //act
        JSONResponse actualResult = this.userController.checkSession(request);

        //assert
        assertEquals(expectedResult, actualResult);

    }
}