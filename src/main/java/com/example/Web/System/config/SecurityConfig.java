package com.example.Web.System.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration

public class SecurityConfig  {
/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/test/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("adminpassword"))
                .roles("ADMIN")
                .build();
        return (UserDetailsService) new MapReactiveUserDetailsService(user);
    }
*/

    @Bean

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
