package raf.nwp.aircompany.services;

import org.springframework.stereotype.Service;
import raf.nwp.aircompany.dtos.UserDto;
import raf.nwp.aircompany.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
