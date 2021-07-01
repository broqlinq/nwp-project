package raf.nwp.aircompany.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record UserCredentialsDto(
        @NotNull(message = "Username cannot be null")
        @Length(min = 4, max = 32, message = "Username must be 6-32 characters long")
        String username,

        @NotNull(message = "Password cannot be null")
        @Length(min = 6, max = 32, message = "Password length must be 6-32 characters long")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,32}$", message = "Password must only uppercase and lowercase english letters, and at least one digit")
        String password
) {}
