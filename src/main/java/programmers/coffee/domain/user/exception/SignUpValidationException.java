package programmers.coffee.domain.user.exception;

import programmers.coffee.global.exception.BusinessException;
import programmers.coffee.global.exception.Message;
import programmers.coffee.global.exception.Reason;

public class SignUpValidationException extends BusinessException {

    public SignUpValidationException(Reason reason, Message message) {
        super(reason, message);
    }
}