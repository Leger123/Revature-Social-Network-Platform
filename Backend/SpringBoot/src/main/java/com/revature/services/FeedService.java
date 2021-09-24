package com.revature.services;

import com.revature.models.*;
import com.revature.repository.FeedDao;
import com.revature.repository.NotificationDao;
import com.revature.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zimi Li
 */

@Service("FeedService")
public class FeedService {
    private FeedDao feedDao;
    private UserDao userDao;
    private NotificationDao notificationDao;

    @Autowired
    public FeedService(FeedDao feedDao, UserDao userDao, NotificationDao notificationDao) {
        this.feedDao = feedDao;
        this.userDao = userDao;
        this.notificationDao = notificationDao;
    }

    protected static void format(Feed feed) {
        feed.setLikesResponse(feed.getLikes().size());
        feed.setAuthorResponse(UserService.format(feed.getAuthor()));
        feed.getComments().forEach(comment -> comment.setAuthorResponse(UserService.format(comment.getAuthor())));
    }

    public List<Feed> viewFeed(Integer start, Integer end) {
        List<Feed> feeds = feedDao.findByIdBetweenOrderByIdDesc(start, end);
        feeds.forEach(FeedService::format);
        return feeds;
    }

    public Feed createFeed(Feed feed, Integer userID) {
        User user = userDao.findById(userID).orElse(null);
        if (user == null) return null;
        feed.setAuthor(user);
        feed.setLikes(new HashSet<>());
        return feedDao.save(feed);
    }

    public Boolean likeFeed(Integer feedID, Integer userID) {
        Feed feed = feedDao.findById(feedID).orElse(null);
        if (feed == null)
            return false;
        User user = userDao.findById(userID).orElse(null);
        if (feed.getLikes().contains(user))
            return false;
        feed.getLikes().add(user);
        feedDao.save(feed);

        // create notification
        Notification notification = new Notification(0, "like", System.currentTimeMillis(), user, feed, feedID, UserService.format(user));
        notificationDao.save(notification);
        return true;
    }

    public List<Feed> viewFeedByUserID(Integer userID) {
        User user = userDao.findById(userID).orElse(null);
        if (user == null) return new ArrayList<>();
        List<Feed> feeds = feedDao.findTop10ByAuthorOrderByIdDesc(user);
        feeds.forEach(FeedService::format);
        return feeds;
    }

    public Feed viewFeedByID(Integer id) {
        Feed feed = feedDao.findById(id).orElse(null);
        if (feed != null) FeedService.format(feed);
        return feed;
    }

    public Long getCount() {
        return feedDao.count();
    }
}
