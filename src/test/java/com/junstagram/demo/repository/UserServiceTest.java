package com.junstagram.demo.repository;

import com.junstagram.demo.domain.User;
import com.junstagram.demo.service.UserService;
import com.junstagram.demo.web.dto.user.UserSignupDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user = User.builder()
                .name("userA")
                .password(encoder.encode("test1234@"))
                .email("test@gmail.com")
                .phone("0123456789")
                .title(null)
                .website(null)
                .profileImgUrl(null)
                .build();
    }

    public UserSignupDto createDto(User user) {
        return UserSignupDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .name(user.getName())
                .build();
    }

    @Test
    public void join() throws Exception {
        UserSignupDto userDto = createDto(user);
        User savedUser = userService.join(userDto);

        assertThat(user.getEmail()).isEqualTo(savedUser.getEmail());
        assertThat(user.getName()).isEqualTo(savedUser.getName());
    }
}
