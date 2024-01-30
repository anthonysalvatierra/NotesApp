package com.note.app.NotesApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/reg/enter")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/")
                .permitAll()
                .requestMatchers("/entry/register")
                .permitAll()
                .requestMatchers("/reg/save")
                .permitAll()
                .requestMatchers("css/bootstrap.min.css")
                .permitAll()
                .requestMatchers("index-style.css")
                .permitAll()
                .requestMatchers("images/index-wallpaper.jpg")
                .permitAll()
                .requestMatchers("/images/**")
                .permitAll()
                .requestMatchers("dashboard-style.css")
                .permitAll()
                .requestMatchers("js/bootstrap.min.js")
                .permitAll()
                .requestMatchers("https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js")
                .permitAll()
                .anyRequest()
                .authenticated();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
