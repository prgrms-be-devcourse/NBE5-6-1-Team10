package programmers.coffee.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import programmers.coffee.domain.user.domain.Role;
import programmers.coffee.domain.user.domain.User;

@Getter
@Setter
public class UserSignUpRequest {

    @NotBlank(message = "이름은 필수 입력값 입니다.")
    @Pattern(regexp = "^[^\\s]+$", message = "이름은 공백을 포함할 수 없습니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력값 입니다.")
    @Pattern(regexp = "^[^\\s]+$", message = "이메일은 공백을 포함할 수 없습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력값 입니다.")
    @Pattern(regexp = "^[^\\s]+$", message = "비밀번호는 공백을 포함할 수 없습니다.")
    private String password;

    @NotBlank(message = "우편번호는 필수 입력값 입니다.")
    @Pattern(regexp = "^[^\\s]+$", message = "우편번호는 공백을 포함할 수 없습니다.")
    private String zipCode;

    public UserSignUpRequest() {
    }

    @Builder
    private UserSignUpRequest(String name, String email, String password, String zipCode) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.zipCode = zipCode;
    }

    public User toEntity(Role role, String password) {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .password(password)
                .zipCode(this.zipCode)
                .role(role)
                .build();
    }

}