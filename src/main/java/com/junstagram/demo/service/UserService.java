package com.junstagram.demo.service;

import com.junstagram.demo.domain.User;
import com.junstagram.demo.handler.exception.CustomValidationException;
import com.junstagram.demo.repository.UserRepository;
import com.junstagram.demo.web.dto.UserSignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User join(UserSignupDto userDto) {

        if(userRepository.findByUserEmail(userDto.getEmail()) != null) {
            throw new CustomValidationException("이미 존재하는 메일입니다.");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return userRepository.save(User.builder()
                .name(userDto.getName())
                .password(encoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .title(null)
                .website(null)
                .profileImgUrl(null)
                .build());
    }
}
