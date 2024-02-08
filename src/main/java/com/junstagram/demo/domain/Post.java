package com.junstagram.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"posts"})
    private User user;

    private String tag;
    private String text;

    @Transient //  JPA 구현체는 해당 필드를 데이터베이스 테이블의 열로 매핑하지 않음
    private Long likeCount;

    @Transient
    private boolean likesState;

    private LocalDateTime createDate;

    private String postImageUrl;

    @OneToMany(mappedBy = "post")
    @JsonIgnoreProperties({"posts"})
    private List<Likes> likes;

    @OrderBy("id")
    @OneToMany(mappedBy = "post")
    @JsonIgnoreProperties({"posts"})
    private List<Comment> comments;

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

    public void updateLikesState(boolean likesState) {
        this.likesState = likesState;
    }

    public void update(String tag , String text) {
        this.tag = tag;
        this.text = text;
    }

    public void updateLikesCount(Long likesCount) {
        this.likeCount = likesCount;
    }

    public void makePost(Long id){
        this.id = id;
    }



}
