package raf.nwp.aircompany.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import raf.nwp.aircompany.dtos.UserDto;
import raf.nwp.aircompany.dtos.UserRegisterForm;
import raf.nwp.aircompany.exceptions.ExistingUsernameException;
import raf.nwp.aircompany.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(Mappers::userToDto)
                .toList();
    }

    public Optional<UserDto> findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(Mappers::userToDto);
    }

    public UserDto register(UserRegisterForm registerForm) {
        var user = userRepository.findByUsername(registerForm.username());
        if (user.isPresent())
            throw new ExistingUsernameException(registerForm.username());

        var newUser = Mappers.formToUser(registerForm, passwordEncoder);
        return Mappers.userToDto(userRepository.save(newUser));
    }
}
