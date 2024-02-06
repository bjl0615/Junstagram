package com.junstagram.demo.web.dto.post;

import com.junstagram.demo.domain.Comment;
import com.junstagram.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostInfoDto {

    private Long id;
    private String text;
    private String tag;
    private LocalDateTime createDate;
    private User postUploader;
    private Long likesCount;
    private boolean likeState;
    private boolean uploader;
    private String postImgUrl;
    private List<Comment> comments;

}
