package hu.pindur.backend.exceptionhandling;

import org.springframework.security.core.userdetails.UserDetails;

public class ThereIsNoRoleAssignedToUserException extends RuntimeException {

    private final String username;

    public ThereIsNoRoleAssignedToUserException(UserDetails user) {
        this.username = user.getUsername();
    }

    public String getUsername() {
        return username;
    }
}
