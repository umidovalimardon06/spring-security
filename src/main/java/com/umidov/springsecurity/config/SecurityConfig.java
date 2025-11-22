package com.umidov.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails ali = User.withUsername("ali")
                .password("{noop}ali77")
                .roles("USER")
                .build();
        UserDetails umar = User.withUsername("umar")
                .password("{noop}umar77")
                .roles("ADMIN")
                .build();

        UserDetails vip = User.withUsername("VIP")
                .password("{noop}777")
                .roles("VIP")
                .build();



        return new InMemoryUserDetailsManager(ali, umar, vip);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
        httpSecurity.
                authorizeHttpRequests(auth ->
                        auth.requestMatchers("/home","/deny").permitAll()
                                .requestMatchers("/welcome").authenticated()
                                .requestMatchers("/ali").hasRole("USER")
                                .requestMatchers("/umar").hasAnyRole("ADMIN","VIP")
                                .anyRequest().authenticated()
                )
                .formLogin(form -> form.permitAll())
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling(
                        exception ->
                                exception.accessDeniedPage("/deny"));

        return httpSecurity.build();
    }
}
