package raf.nwp.aircompany.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import raf.nwp.aircompany.dtos.UserCredentialsDto;
import raf.nwp.aircompany.exceptions.InvalidCredentialsException;
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

    public String generateJwt(@Valid UserCredentialsDto userCredentials) {
        return userRepository.findByUsername(userCredentials.username())
                .filter(user -> passwordEncoder.matches(userCredentials.password(), user.getPassword()))
                .map(jwtManager::generateToken)
                .orElseThrow(() -> new InvalidCredentialsException(userCredentials));
    }

}
