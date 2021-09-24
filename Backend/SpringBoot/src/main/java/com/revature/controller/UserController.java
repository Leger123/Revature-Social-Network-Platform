package com.revature.controller;

import com.revature.models.JSONResponse;
import com.revature.models.User;
import com.revature.models.UserResponse;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zimi Li
 */

@RestController("UserController")
@RequestMapping("api")
@CrossOrigin(value = {"http://localhost:4200", "http://18.119.105.113:8080", "http://18.119.105.113:80"}, allowCredentials = "true")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping("user")
    public JSONResponse register(@RequestBody User user) {
        user = userService.createUser(user);
        if (user != null)
            return new JSONResponse(true, "Register Succeed", user);
        else
            return new JSONResponse(false, "Register Failed", null);
    }

    @PostMapping("login")
    public JSONResponse login(@RequestBody User user, HttpServletRequest req) {
        user = userService.login(user);
        if (user != null) {
            req.getSession().setAttribute("User", user);
            return new JSONResponse(true, "Login Succeed", null);
        } else {
            return new JSONResponse(false, "Login Failed", null);
        }
    }

    @GetMapping("logout")
    public JSONResponse logout(HttpServletRequest req) {
        req.getSession().invalidate();
        return new JSONResponse(true, "Logout Succeed", null);
    }

    @PatchMapping("user/password/{email}")
    public JSONResponse resetPassword(@PathVariable String email) {
        if (userService.resetPassword(email))
            return new JSONResponse(true, "Email has been sent to " + email, null);
        else
            return new JSONResponse(false, "Email does not match our record", null);
    }

    @PutMapping("user")
    public JSONResponse update(@RequestBody User user, HttpServletRequest req) {
        User sessionUser = (User) req.getSession().getAttribute("User");
        if (sessionUser != null) {
            user = userService.updateUser(sessionUser.getId(), user);
            if (user != null) {
                req.getSession().setAttribute("User", user);
                return new JSONResponse(true, "User info has been updated", null);
            }
        }
        return new JSONResponse(false, "User info update failed", null);
    }

    @PatchMapping("user/profile-img")
    public JSONResponse updateProfileImage(@RequestBody String imgUrl, HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("User");
        if (user != null) {
            user = userService.updateProfileImg(user.getId(), imgUrl);
            if (user != null) {
                req.getSession().setAttribute("User", user);
                return new JSONResponse(true, "Profile image has been updated", null);
            }
        }
        return new JSONResponse(false, "Profile image update failed", null);
    }

    @GetMapping("user")
    public JSONResponse getAllUser() {

        return new JSONResponse(true, "All user", this.userService.getAllUser());
    }

    @GetMapping("user/{id}")
    public JSONResponse getUser(@PathVariable Integer id) {
        UserResponse user = userService.getUser(id);
        if (user != null)
            return new JSONResponse(true, "User Found", user);
        else
            return new JSONResponse(false, "User not Found", null);
    }

    @GetMapping("check")
    public JSONResponse checkSession(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("User");
        if (user == null)
            return new JSONResponse(false, "No Session Found", null);
        user = userService.refreshUser(user.getId());
        req.getSession().setAttribute("User", user);
        return new JSONResponse(true, "Session Found", user);
    }
}
