package com.junstagram.demo.web.dto.user;

import com.junstagram.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDto {

    private boolean loginUser;
    private boolean follow;
    private User user;
    private int postCount;
    private int userFollowerCount;
    private int userFollowingCount;

}
