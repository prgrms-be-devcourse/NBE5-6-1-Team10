package programmers.coffee.global.exception;

import lombok.Getter;

@Getter
public enum Message {
    USER_DUPLICATED_EMAIL("중복된 이메일이 있습니다");

    private final String message;

    Message(String message) {
        this.message = message;
    }
}
