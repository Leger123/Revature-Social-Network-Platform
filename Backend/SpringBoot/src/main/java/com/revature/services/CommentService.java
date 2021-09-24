package com.revature.services;

import com.revature.models.Comment;
import com.revature.models.Feed;
import com.revature.models.Notification;
import com.revature.models.User;
import com.revature.repository.CommentDao;
import com.revature.repository.FeedDao;
import com.revature.repository.NotificationDao;
import com.revature.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Zimi Li
 */
@Service("CommentService")
public class CommentService {
    private CommentDao commentDao;
    private FeedDao feedDao;
    private UserDao userDao;
    private NotificationDao notificationDao;

    @Autowired
    public CommentService(CommentDao commentDao, FeedDao feedDao, UserDao userDao, NotificationDao notificationDao) {
        this.commentDao = commentDao;
        this.feedDao = feedDao;
        this.userDao = userDao;
        this.notificationDao = notificationDao;
    }

    public Boolean createComment(Integer feedID, Integer userID, Comment comment) {
        Feed feed = feedDao.findById(feedID).orElse(null);
        if (feed == null) return false;
        comment.setBelongTo(feed);
        User user = userDao.findById(userID).orElse(null);
        if (user == null) return false;
        comment.setAuthor(user);
        commentDao.save(comment);

        // create notification
        Notification notification = new Notification(0, "comment", System.currentTimeMillis(), user, feed, feedID, UserService.format(user));
        notificationDao.save(notification);
        return true;
    }
}
