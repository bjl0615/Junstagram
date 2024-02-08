package com.junstagram.demo.service;

import com.junstagram.demo.handler.exception.CustomApiException;
import com.junstagram.demo.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikesRepository likesRepository;

    @Transactional
    public void likes(Long postId , Long sessionId) {
        try{
            likesRepository.likes(postId , sessionId);
        }catch (Exception e) {
            throw new CustomApiException("이미 좋아요를 누르셨습니다.");
        }
    }

    @Transactional
    public void unlikes(Long postId , Long sessionId) {
        likesRepository.unLikes(postId , sessionId);
    }

}
