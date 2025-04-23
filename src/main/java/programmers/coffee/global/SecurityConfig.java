package programmers.coffee.global;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers("/", "/login", "/login-process", "/join", "/join-process").permitAll()
                                .requestMatchers("/css/**", "/assets/**", "/js/**").permitAll()
                                .anyRequest().authenticated()
                );

        http.csrf((auth) -> auth.disable());

//        http
//                .formLogin((auth) -> auth.loginPage("/login")
//                        .loginProcessingUrl("/login-process")
//                        .permitAll()
//                );

        return http.build();
    }
}
