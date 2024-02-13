package com.junstagram.demo.web.controller;

import com.junstagram.demo.config.auth.PrincipalDetails;
import com.junstagram.demo.service.UserService;
import com.junstagram.demo.web.dto.user.UserProfileDto;
import com.junstagram.demo.web.dto.user.UserSignupDto;
import com.junstagram.demo.web.dto.user.UserUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public String profile(Model model, @RequestParam Long id , @AuthenticationPrincipal PrincipalDetails principalDetails) {
        UserProfileDto userProfileDto = userService.getUserProfileDto(id , principalDetails.getUser().getId());
        model.addAttribute("userProfileDto" , userProfileDto);
        return "user/profile";
    }

    @GetMapping("/update")
    public String update() {
        return "user/update";
    }


    @PostMapping("/update")
    public String updateUser(@Valid UserUpdateDto userUpdateDto ,
                             @RequestParam("profileImgUrl")MultipartFile multipartFile,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal PrincipalDetails principalDetails) {
        userService.update(userUpdateDto, multipartFile , principalDetails);
        redirectAttributes.addAttribute("id" , principalDetails.getUser().getId());
        return "redirect:/user/profile";
    }
}
