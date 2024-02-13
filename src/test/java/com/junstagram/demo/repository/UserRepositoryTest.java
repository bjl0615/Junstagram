package com.junstagram.demo.repository;

import com.junstagram.demo.domain.User;
import com.junstagram.demo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
//@Rollback(value = false)
class UserRepositoryTest {

    @Autowired UserRepository userRepository;
    @Autowired EntityManager em;

    @Test
    public void save() throws Exception {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //given
        User user = User.builder()
                .name("userA")
                .password(encoder.encode("1234"))
                .email("abc@email.com")
                .phone("010-1234-5678")
                .title(null)
                .website(null)
                .profileImgUrl(null)
                .build();

        System.out.println(user.getName());

        //when
        userRepository.save(user);
        em.flush();
        em.clear();

        assertThat(user.getName()).isEqualTo(userRepository.findById(user.getId()).get().getName());

        assertThat(user.getEmail()).isEqualTo(userRepository.findById(user.getId()).get().getEmail());
    }

}
