package raf.nwp.aircompany.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import raf.nwp.aircompany.dtos.UserCredentialsDto;
import raf.nwp.aircompany.dtos.UserJwtResponse;
import raf.nwp.aircompany.exceptions.InvalidCredentialsException;
import raf.nwp.aircompany.models.User;
import raf.nwp.aircompany.repositories.UserRepository;
import raf.nwp.aircompany.security.JwtManager;

import javax.validation.Valid;

@Service
public class LoginService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtManager jwtManager;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtManager jwtManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtManager = jwtManager;
    }

    public UserJwtResponse generateJwt(@Valid UserCredentialsDto userCredentials) {
        return userRepository.findByUsername(userCredentials.username())
                .filter(user -> passwordEncoder.matches(userCredentials.password(), user.getPassword()))
                .map(this::generate)
                .orElseThrow(() -> new InvalidCredentialsException(userCredentials));
    }

    private UserJwtResponse generate(User user) {
        var token = jwtManager.generateToken(user);
        return new UserJwtResponse(token, user.getUsername(), user.getType());
    }

}
