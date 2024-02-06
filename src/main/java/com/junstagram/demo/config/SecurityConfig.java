package com.junstagram.demo.config;

import com.junstagram.demo.config.auth.PrincipalDetailsService;
import com.junstagram.demo.config.oauth.Oauth2DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final PrincipalDetailsService principalDetailService;
    private final Oauth2DetailService oauth2DetailService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/" , "/login" , "/signup" , "/style/**" , "/js/**" , "/img/**").permitAll()
                .anyRequest().authenticated())
                .formLogin((formLogin) -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/loginForm")
                        .defaultSuccessUrl("/"))
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true))
                .oauth2Login((oauthLogin) -> oauthLogin
                        .loginPage("/login")
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                .userService(oauth2DetailService)));

        return http.build();
    }
}
