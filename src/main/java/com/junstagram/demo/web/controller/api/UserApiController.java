package com.junstagram.demo.web.controller.api;

import com.junstagram.demo.config.auth.PrincipalDetails;
import com.junstagram.demo.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    private final FollowService followService;

    @GetMapping("/{profileId}/follower")
    public ResponseEntity<?> getFollower(@PathVariable Long profileId , @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return new ResponseEntity<>(followService.getFollow(profileId , principalDetails.getUser().getId()) , OK);
    }

    @GetMapping("/{profileId}/following")
    public ResponseEntity<?> getFollowing(@PathVariable Long profileId ,
                                          @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return new ResponseEntity<>(followService.getFollowing(profileId , principalDetails.getUser().getId()) , OK);
    }

}
