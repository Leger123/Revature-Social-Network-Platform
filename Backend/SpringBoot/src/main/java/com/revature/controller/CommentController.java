package com.revature.controller;

import com.revature.models.Comment;
import com.revature.models.JSONResponse;
import com.revature.models.User;
import com.revature.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Zimi Li
 */
@RestController("CommentController")
@RequestMapping("api")
@CrossOrigin(value = {"http://localhost:4200", "http://18.119.105.113:8080", "http://18.119.105.113:80"}, allowCredentials = "true")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("comment/{id}")
    public JSONResponse commentFeed(@PathVariable Integer id, @RequestBody Comment comment, HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("User");
        if (user == null)
            return new JSONResponse(false, "Session Not Found", null);
        if (commentService.createComment(id, user.getId(), comment))
            return new JSONResponse(true, "Comment Succeed", null);
        else
            return new JSONResponse(false, "No Feed Found", null);
    }
}
