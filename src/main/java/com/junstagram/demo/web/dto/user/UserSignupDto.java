package com.junstagram.demo.web.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserSignupDto {

    @Size(min = 2 , max = 100 , message = "이메일은 2글자 이상, 100글자 이하로 작성해 주세요")
    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @Size(min = 8 , message = "비밀번호는 8자 이상입니다.")
    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message = "비밀번호는 영문 대, 소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 이상의 비밀번호여야 합니다.")
    private String password;

    @NotBlank(message = "전화번호를 입력해 주세요.")
    @Pattern(regexp = "^[0-9]+$", message = "전화 번호는 숫자로만 입력해 주세요.")
    private String phone;

    @Size(min = 1, max = 30, message = "이름은 1글자 이상, 30글자 이내로 작성해 주세요.")
    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;

}
