package programmers.coffee.domain.user.domain;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_USER("기본 회원");

    private final String description;

    Role(String description) {
        this.description = description;
    }
}
