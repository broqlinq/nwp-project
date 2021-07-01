package raf.nwp.aircompany.exceptions;

public class ExistingUsernameException extends RuntimeException {

    public ExistingUsernameException(String username) {
        super("User with username `" + username + "` already exists");
    }
}
