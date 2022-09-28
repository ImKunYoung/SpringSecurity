package com.example.springsecurity.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        /*TODO-ISSUE 모든 URL에서 Security 풀림*/
        httpSecurity.authorizeRequests().antMatchers("/sample/admin").denyAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        // 사용자 계정은 user1
        authenticationManagerBuilder.inMemoryAuthentication().withUser("user1")
                .password("$2a$10$pvtnrZLWPHqGZ/7xF5FxEO29x.UgF6lV21L16NtVfxuUtQzMQG9Nu") // 1111 패스워드 인코딩 결과
                .roles("USER");
    }

}
