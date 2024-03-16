package hu.pindur.backend.exceptionhandling;

public class EmailAlreadyExistException extends RuntimeException {
    private String email;

    public EmailAlreadyExistException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
