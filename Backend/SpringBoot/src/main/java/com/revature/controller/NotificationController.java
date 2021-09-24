package com.revature.controller;

import com.revature.models.JSONResponse;
import com.revature.models.Notification;
import com.revature.models.User;
import com.revature.services.NotificationService;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Zimi Li
 */
@RestController("NotificationController")
@RequestMapping("api")
@CrossOrigin(value = {"http://localhost:4200", "http://18.119.105.113:8080", "http://18.119.105.113:80"}, allowCredentials = "true")
public class NotificationController {
    private NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("notification-preview")
    public JSONResponse getPreviewNotification(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("User");
        if (user == null)
            return new JSONResponse(false, "Session not found", null);
        List<Notification> notifications = notificationService.getTop10NotificationByUserID(user.getId());
        if (notifications == null)
            return new JSONResponse(false, "User ID not found", null);
        else
            return new JSONResponse(true, "retrieved notification", notifications);
    }

    @GetMapping("notification")
    public JSONResponse getAllNotification(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("User");
        if (user == null)
            return new JSONResponse(false, "Session not found", null);
        List<Notification> notifications = notificationService.getNotificationByUserID(user.getId());
        if (notifications == null)
            return new JSONResponse(false, "User ID not found", null);
        else
            return new JSONResponse(true, "retrieved notification", notifications);
    }
}
