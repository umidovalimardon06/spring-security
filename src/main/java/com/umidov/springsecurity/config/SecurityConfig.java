package com.umidov.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
        httpSecurity
                .authorizeHttpRequests(
                        request ->
                                 request.requestMatchers("/", "/home").permitAll()
                                        .requestMatchers("/news").authenticated()
                                        .requestMatchers("/admin_panel").hasRole("ADMIN")
                                        .anyRequest().authenticated())

                .httpBasic(Customizer.withDefaults())
                .logout(LogoutConfigurer::permitAll);

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123").roles("ADMIN")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("123")
                .build();

        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.createUser(admin);
        manager.createUser(user);

        return (manager);
    }
}
