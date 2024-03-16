package hu.pindur.backend.exceptionhandling;

public class NoFeedbackAllowedException extends RuntimeException {
    private String exceptionMessage;

    public NoFeedbackAllowedException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
