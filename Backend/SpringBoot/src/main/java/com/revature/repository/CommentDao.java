package com.revature.repository;

import com.revature.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author Zimi Li
 */
@Repository("CommentDao")
@Transactional
public interface CommentDao extends JpaRepository<Comment, Integer> {
}
