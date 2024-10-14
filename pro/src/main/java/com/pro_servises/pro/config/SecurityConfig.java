package com.pro_servises.pro.config;

import com.pro_servises.pro.serviceImp.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.pro_servises.pro.enums.Role.PROVIDER;
import static com.pro_servises.pro.enums.Role.USER;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(expressionInterceptUrlRegistry ->
                        expressionInterceptUrlRegistry
                                .requestMatchers("/signin").permitAll()
                                .requestMatchers("/signup").permitAll()
                                .requestMatchers("/api/article/**").permitAll()
                                .requestMatchers("/api/article/get_article/**").permitAll()
                                .requestMatchers("/api/article/get_articles").permitAll()

                                .requestMatchers("/api/contact/**").permitAll()

                                .requestMatchers(POST,"/api/enterprise/**").hasRole("PROVIDER")
                                .requestMatchers("/api/enterprise/**").permitAll()

                                .requestMatchers("/api/order/**").permitAll()

                                .requestMatchers(POST,"/api/product/**").hasRole("PROVIDER")
                                .requestMatchers("/api/product/**").permitAll()

                                .requestMatchers("/api/rating/**").hasRole("USER")
                                .requestMatchers(GET,"/api/rating/**").permitAll()
                                .requestMatchers(GET,"/api/rating/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin.disable())
                .addFilterBefore(new JwtAuthorizationFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}
