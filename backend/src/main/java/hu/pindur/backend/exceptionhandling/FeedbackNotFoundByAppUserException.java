package hu.pindur.backend.exceptionhandling;

public class FeedbackNotFoundByAppUserException extends RuntimeException {
    private String username;

    public FeedbackNotFoundByAppUserException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
