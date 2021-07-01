package raf.nwp.aircompany.exceptions;

import raf.nwp.aircompany.dtos.UserCredentialsDto;

public class InvalidCredentialsException extends RuntimeException {

    private final UserCredentialsDto userCredentials;

    public InvalidCredentialsException(UserCredentialsDto userCredentials) {
        super("Invalid credentials");
        this.userCredentials = userCredentials;
    }

    public UserCredentialsDto getUserCredentials() {
        return userCredentials;
    }
}
