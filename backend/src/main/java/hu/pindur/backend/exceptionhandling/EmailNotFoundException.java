package hu.pindur.backend.exceptionhandling;

public class EmailNotFoundException extends RuntimeException {

    private final String errorMessage;

    public EmailNotFoundException(String emailNotFound) {
        this.errorMessage = emailNotFound;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
