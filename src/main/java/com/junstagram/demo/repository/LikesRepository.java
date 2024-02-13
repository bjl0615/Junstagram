package com.junstagram.demo.repository;

import com.junstagram.demo.domain.Likes;
import com.junstagram.demo.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    void deleteLikesByPost(Post post);

    @Modifying
    @Query(value = "insert into Likes(post_id, user_id) values(:postId, :userId)", nativeQuery = true)
    void likes(Long postId, Long userId);

    @Modifying
    @Query(value = "delete from Likes l where l.post.id = :postId and l.user.id = :user_id")
    void unLikes(Long postId, Long userId);
}
