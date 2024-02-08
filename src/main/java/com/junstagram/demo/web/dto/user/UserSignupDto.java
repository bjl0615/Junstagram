package com.junstagram.demo.web.dto.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserSignupDto {

    private String email;
    private String password;
    private String phone;
    private String name;

}