package com.junstagram.demo.repository;

import com.junstagram.demo.domain.Comment;
import com.junstagram.demo.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    void deleteCommentByPost(Post post);
}