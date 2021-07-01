package raf.nwp.aircompany.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import raf.nwp.aircompany.dtos.UserDto;
import raf.nwp.aircompany.dtos.UserRegisterForm;
import raf.nwp.aircompany.models.User;

import java.util.List;

public class Mappers {

    public static UserDto userToDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getType());
    }

    public static User formToUser(UserRegisterForm registerForm, PasswordEncoder passwordEncoder) {
        return new User(
                null,
                registerForm.username(),
                passwordEncoder.encode(registerForm.password()),
                registerForm.type(),
                (registerForm.type() == User.Type.REGULAR) ? List.of() : null);
    }
}
