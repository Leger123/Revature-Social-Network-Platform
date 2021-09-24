package com.revature.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.revature.models.User;
import com.revature.models.UserResponse;
import com.revature.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zimi Li
 */

@Service("UserService")
public class UserService {
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    protected static UserResponse format(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getProfileImage(), user.getLastNotification());
    }

    public User createUser(User user) {
        user.setLastNotification(System.currentTimeMillis());
        if (userDao.findUserByUsername(user.getUsername()) == null && userDao.findUserByEmail(user.getEmail()) == null) {
            user.setPassword(BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray()));
            return userDao.save(user);
        }
        return null;
    }

    public User login(User user) {
        User tmp = userDao.findUserByUsername(user.getUsername());
        if (tmp == null)
            return null;
        BCrypt.Result result = BCrypt.verifyer().verify(user.getPassword().toCharArray(), tmp.getPassword());
        return result.verified ? tmp : null;
    }

    public Boolean resetPassword(String email) {
        User user = userDao.findUserByEmail(email);
        if (user != null) {
            user.setPassword(PasswordService.generatePassword());
            PasswordService.sendMail(email, user.getUsername(), user.getPassword());
            user.setPassword(BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray()));
            userDao.save(user);
            return true;
        } else {
            return false;
        }
    }

    public User updateUser(Integer userID, User newUser) {
        User user = userDao.findById(userID).orElse(null);
        if (user == null) return null;
        user.setPassword(BCrypt.withDefaults().hashToString(12, newUser.getPassword().toCharArray()));
        user.setName(newUser.getName());
        user.setProfileImage(newUser.getProfileImage());
        return userDao.save(user);
    }

    public List<UserResponse> getAllUser() {
        return userDao.findAll().stream().map(UserService::format).collect(Collectors.toList());
    }

    public UserResponse getUser(Integer id) {
        User user = userDao.findById(id).orElse(null);
        if (user == null) return null;
        return UserService.format(user);
    }

    public User updateProfileImg(Integer id, String imgUrl) {
        User user = userDao.findById(id).orElse(null);
        if (user == null) return null;
        user.setProfileImage(imgUrl);
        return userDao.save(user);
    }

    public User refreshUser(Integer id) {
        return userDao.findById(id).orElse(null);
    }
}
