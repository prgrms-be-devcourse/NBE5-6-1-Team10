package programmers.coffee.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
// post 요청이 허용될 수 있도록 명시적 추가
import org.springframework.http.HttpMethod;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers("/", "/users/login", "/users/login-process", "/users/signup", "/orders", "/orders/result/").permitAll()
                                // upload 에 있는 이미지 파일 권한 접근 허용을 위해 "/upload/**" 추가
                                .requestMatchers("/css/**", "/assets/**", "/js/**","/upload/**").permitAll()
                                // items경로에 대한 get,post 요청 모두 허용하게 지정
                                .requestMatchers("/items/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/items/new").permitAll()

                                .anyRequest().authenticated()
                );

        http.csrf((auth) -> auth.disable());

        http
                .formLogin(auth -> auth
                        .loginProcessingUrl("/users/login-process")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/users")
                        .permitAll()
                );

        return http.build();
    }
}