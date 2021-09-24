package com.revature.repository;

import com.revature.models.Notification;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Zimi Li
 */
@Repository("NotificationDao")
@Transactional
public interface NotificationDao extends JpaRepository<Notification, Integer> {
    List<Notification> findTop10ByFeedAuthorOrderByTimestampDesc(User user);
    List<Notification> findByFeedAuthorOrderByTimestampDesc(User user);
}
