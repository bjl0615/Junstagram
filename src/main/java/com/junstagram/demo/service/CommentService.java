package com.junstagram.demo.service;

import com.junstagram.demo.domain.Comment;
import com.junstagram.demo.domain.Post;
import com.junstagram.demo.domain.User;
import com.junstagram.demo.handler.exception.CustomApiException;
import com.junstagram.demo.repository.CommentRepository;
import com.junstagram.demo.repository.PostRepository;
import com.junstagram.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public Comment addComment(String text, Long postId , Long sessionId){
        Post post = postRepository.findById(postId).get();
        User user = userRepository.findById(sessionId).orElseThrow(() -> {
            return new CustomApiException("유저 아이디를 찾을 수 없습니다.");
        });

        Comment comment = Comment.builder()
                .text(text)
                .post(post)
                .user(user)
                .build();

        return commentRepository.save(comment);
    }

    @Transactional
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
