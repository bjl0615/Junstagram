package com.junstagram.demo.web.controller.api;

import com.junstagram.demo.config.auth.PrincipalDetails;
import com.junstagram.demo.service.CommentService;
import com.junstagram.demo.web.dto.CommentUploadDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentUploadDto commentUploadDto,
                                        @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return new ResponseEntity<>(
                commentService.addComment(
                        commentUploadDto.getText(),
                        commentUploadDto.getPostId(),
                        principalDetails.getUser().getId()),
                OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>("댓글 삭제 성공", OK);
    }

}
