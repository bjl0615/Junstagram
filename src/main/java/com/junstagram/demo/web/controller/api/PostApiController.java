package com.junstagram.demo.web.controller.api;

import com.junstagram.demo.config.auth.PrincipalDetails;
import com.junstagram.demo.service.LikeService;
import com.junstagram.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostApiController {

    private final PostService postService;
    private final LikeService likeService;

    @GetMapping("/{postId}")
    public ResponseEntity<?> postInfo(@PathVariable Long postId , @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return new ResponseEntity<>(postService.getPostInfoDto(postId , principalDetails.getUser().getId()) , OK);
    }

    @PostMapping("/{postId}/likes")
    public ResponseEntity<?> likes(@PathVariable Long postId , @AuthenticationPrincipal PrincipalDetails principalDetails) {
        likeService.likes(postId, principalDetails.getUser().getId());
        return new ResponseEntity<>("좋아요 성공", OK);
    }

    @DeleteMapping("/{postId}/likes")
    public ResponseEntity<?> unlikes(@PathVariable Long postId , @AuthenticationPrincipal PrincipalDetails principalDetails) {
        likeService.unlikes(postId , principalDetails.getUser().getId());
        return new ResponseEntity<>("좋아요 취소 성공" , OK);
    }

    @GetMapping
    public ResponseEntity<?> mainStory(@AuthenticationPrincipal PrincipalDetails principalDetails , @PageableDefault(size = 3)Pageable pageable){
        return new ResponseEntity<>(postService.getPost(principalDetails.getUser().getId(), pageable) , OK);
    }

    @GetMapping("/tag")
    public ResponseEntity<?> searchTag(@RequestParam String tag , @AuthenticationPrincipal PrincipalDetails principalDetails, @PageableDefault(size = 3) Pageable pageable) {
        return new ResponseEntity<>(postService.getTagPost(tag , principalDetails.getUser().getId() , pageable) , OK);
    }

    @GetMapping("/popular")
    public ResponseEntity<?> getPopularPost() {
        return new ResponseEntity<>(postService.getPopularPost(), OK);
    }

}
