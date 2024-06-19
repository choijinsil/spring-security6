package com.example.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHierarchy roleHierarchy(){ // role 계층 설정
        return RoleHierarchyImpl.withDefaultRolePrefix()
            .role("ADMIN").implies("STAFF")
            .role("STAFF").implies("USER")
            .role("USER").implies("GUEST")
            .build();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> auth
            .requestMatchers("/","/login", "/loginProc","/join","/joinProc","/h2-console/**").permitAll()
            .requestMatchers(PathRequest.toH2Console()).permitAll()
            .requestMatchers("/admin").hasRole("ADMIN")
            .anyRequest().authenticated()
        );

        http
            .formLogin((auth) -> auth
                .loginPage("/login")
                .loginProcessingUrl("/loginProc")
                .permitAll()
            );

        // h2 웹콘솔은 iframe으로 화면을 구성한다. 그래서 X-Frame-Options헤더의 내용에 따라 iframe에서의 요청을 허용할것인지 판단.
        http
            .csrf((auth) -> auth.disable()) // 단순 api서버로 사용자 세션을 stateless로 관리하기 때문에 csrf enable 설정을 하지 않아도 된다.
            .headers(headers -> headers.frameOptions(FrameOptionsConfig::sameOrigin));

        http
                .sessionManagement((auth) -> auth
                .maximumSessions(1) // 하나의 아이디에 대한 다중 로그인 허용 갯수
                .maxSessionsPreventsLogin(true) // 다중로그인 갯수를 초과했을 경우 처리방법
                );

        http
            .sessionManagement((auth) -> auth.sessionFixation().changeSessionId());

        http
            .logout((auth) -> auth.logoutUrl("/logout").logoutSuccessUrl("/"));

        return http.build();
    }
}
