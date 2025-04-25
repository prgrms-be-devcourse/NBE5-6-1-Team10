package programmers.coffee.global.exception;

import lombok.Getter;

@Getter
public enum Reason {
    USER_EMAIL("회원 이메일");

    private final String reason;

    Reason(String reason) {
        this.reason = reason;
    }
}