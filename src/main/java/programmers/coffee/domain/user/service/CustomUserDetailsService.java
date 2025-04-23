package programmers.coffee.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import programmers.coffee.domain.user.domain.CustomUserDetails;
import programmers.coffee.domain.user.domain.User;
import programmers.coffee.domain.user.repository.UserRepository;
import programmers.coffee.global.exception.NotFoundException;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("일치 하는 회원 이메일이 없습니다."));

        return new CustomUserDetails(user);
    }
}
