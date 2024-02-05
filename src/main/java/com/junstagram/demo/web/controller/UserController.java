package com.junstagram.demo.web.controller;

import com.junstagram.demo.service.UserService;
import com.junstagram.demo.web.dto.UserSignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserSignupDto userDto) {
        userService.join(userDto);
        return "redirect:/login";
    }

}
