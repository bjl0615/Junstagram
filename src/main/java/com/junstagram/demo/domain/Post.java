package com.junstagram.demo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String tag;
    private String text;

    @Transient //  JPA 구현체는 해당 필드를 데이터베이스 테이블의 열로 매핑하지 않음
    private Long likeCount;

    @Transient
    private boolean likeState;

    private LocalDateTime createDate;

    private String postImageUrl;

    @PrePersist // @PrePersist 애노테이션이 지정된 메서드는 엔티티 객체가 데이터베이스에 저장되기 전에 수행해야 하는 작업을 정의
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    @Builder
    public Post(User user , String tag , String text , Long likeCount , String postImgUrl) {
        this.user = user;
        this.tag = tag;
        this.text = text;
        this.likeCount = likeCount;
        this.postImageUrl = postImgUrl;
    }




}
