package programmers.coffee.global.exception;

public class BusinessException extends RuntimeException {

    private Reason reason;
    private Message message;

    public BusinessException(Reason reason, Message message) {
        super(message.getMessage());
        this.reason = reason;
    }
}
