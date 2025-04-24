package programmers.coffee.domain.user.dto.request;

import lombok.Builder;
import lombok.Setter;
import programmers.coffee.domain.user.domain.Role;
import programmers.coffee.domain.user.domain.User;

@Setter
public class UserSignUpRequest {

    private String name;
    private String email;
    private String password;
    private String zipCode;

    @Builder
    private UserSignUpRequest(String name, String email, String password, String zipCode) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.zipCode = zipCode;
    }

    public User toEntity(Role role, String password){
        return User.builder()
                .name(this.name)
                .email(this.email)
                .password(password)
                .zipCode(this.zipCode)
                .role(role)
                .build();
    }

    public String getPassword() {
        return password;
    }
}