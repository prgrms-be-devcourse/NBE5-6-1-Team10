package programmers.coffee.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import programmers.coffee.domain.user.domain.User;
import programmers.coffee.domain.user.dto.request.UserSignUpRequest;
import programmers.coffee.domain.user.exception.SignUpValidationException;
import programmers.coffee.domain.user.repository.UserRepository;

import static programmers.coffee.domain.user.domain.Role.*;
import static programmers.coffee.global.exception.Message.*;
import static programmers.coffee.global.exception.Reason.*;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void signup(UserSignUpRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = request.toEntity(ROLE_USER, encodedPassword);

        userRepository.findByEmail(request.getEmail())
                        .ifPresent(findUser -> {
                            throw new SignUpValidationException(USER_EMAIL, USER_DUPLICATED_EMAIL);
                        });

        userRepository.save(user);
    }
}
