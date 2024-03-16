package hu.pindur.backend.exceptionhandling;

public class CartIsNotFoundException extends RuntimeException {
    private final String username;

    public CartIsNotFoundException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}