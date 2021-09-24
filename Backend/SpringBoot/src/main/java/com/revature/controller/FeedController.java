package com.revature.controller;

import com.revature.config.Configuration;
import com.revature.models.Feed;
import com.revature.models.JSONResponse;
import com.revature.models.User;
import com.revature.services.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zimi Li
 */
@RestController("FeedController")
@RequestMapping("api")
@CrossOrigin(value = {"http://localhost:4200", "http://18.119.105.113:8080", "http://18.119.105.113:80"}, allowCredentials = "true")
public class FeedController {
    private FeedService feedService;

    @Autowired
    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @GetMapping("feed/offset/{offset}")
    public JSONResponse viewFeed(@PathVariable Integer offset) {
        return new JSONResponse(true, "Retrieved Feed", feedService.viewFeed(offset, offset + Configuration.MAXIMUM_FEED_DISPLAYED));
    }

    @GetMapping("feed/user/{id}")
    public JSONResponse viewFeedByUserID(@PathVariable("id") Integer userID) {
        return new JSONResponse(true, "Retrieved Feed", feedService.viewFeedByUserID(userID));
    }

    @GetMapping("feed/{id}")
    public JSONResponse viewFeedByID(@PathVariable("id") Integer id) {
        Feed feed = feedService.viewFeedByID(id);
        if (feed == null)
            return new JSONResponse(false, "Feed Not Found", null);
        else
            return new JSONResponse(true, "Retrieved Feed", feed);
    }

    @PostMapping("feed")
    public JSONResponse createFeed(@RequestBody Feed feed, HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("User");
        if (user == null)
            return new JSONResponse(false, "Session Not Found", null);
        feed = feedService.createFeed(feed, user.getId());
        if (feed == null)
            return new JSONResponse(false, "Created Failed", null);
        else
            return new JSONResponse(true, "Created Succeed", null);
    }

    @PatchMapping("feed/{id}")
    public JSONResponse likeFeed(@PathVariable Integer id, HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("User");
        if (user == null)
            return new JSONResponse(false, "Session Not Found", null);
        if (feedService.likeFeed(id, user.getId()))
            return new JSONResponse(true, "Like Succeed", null);
        else
            return new JSONResponse(false, "Like Failed", null);
    }

    @GetMapping("feed/count")
    public JSONResponse getFeedCount() {
        return new JSONResponse(true, "Feed Count",feedService.getCount());
    }

}
