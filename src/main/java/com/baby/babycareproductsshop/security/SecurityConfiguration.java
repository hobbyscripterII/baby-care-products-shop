package com.baby.babycareproductsshop.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(http -> http.disable())
                .formLogin(formLogin -> formLogin.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(author -> author.requestMatchers(
                                        "/api/user/sign-in",
                                        "/api/user/sign-up",
                                        "/api/user/sign-up/**",
                                        "/api/user/refresh-token",
                                        "/api/product",
                                        "/api/product/search",
                                        "/api/product/main",
                                        "/api/product/pop-new-product",
                                        "/api/product/{iproduct}",
                                        "/error",
                                        "/err",
                                        "/",
                                        "/index.html",
                                        "/static/**",
                                        "/fimg/**",
                                        "/css/**",
                                        "/swagger.html",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/assets/**", // *
                                        "/login",
                                        "/signUp",
                                        "/commu/read/**"
                                ).permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/board").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/board/{iboard}").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(except -> {
                    except.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                            .accessDeniedHandler(new JwtAccessDeniedHandler());
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
