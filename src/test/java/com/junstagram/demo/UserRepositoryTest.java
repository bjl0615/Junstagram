package com.junstagram.demo;

import com.junstagram.demo.domain.User;
import com.junstagram.demo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager em;

    @Test
    public void save() throws Exception {
        User user = User.builder()
                .name("userA")
                .password("1234")
                .email("abc@gmail.com")
                .phone("010-1234-5678")
                .title(null)
                .website(null)
                .profileImgUrl(null)
                .build();

        userRepository.save(user);
        em.flush();
        em.clear();

        assertThat(user.getName()).isEqualTo(userRepository.findOne(user.getId()).getName());
    }
}
