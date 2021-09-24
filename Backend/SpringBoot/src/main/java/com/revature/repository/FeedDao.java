package com.revature.repository;

import com.revature.models.Feed;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Zimi Li
 */
@Repository("FeedDao")
@Transactional
public interface FeedDao extends JpaRepository<Feed, Integer> {
    List<Feed> findByIdBetweenOrderByIdDesc(Integer start, Integer end);
    List<Feed> findTop10ByAuthorOrderByIdDesc(User user);
}
