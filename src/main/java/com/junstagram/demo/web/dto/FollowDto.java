package com.junstagram.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FollowDto {

    private Long id;
    private String name;
    private String profileImgUrl;
    private int followState;
    private int loginUser;



}
