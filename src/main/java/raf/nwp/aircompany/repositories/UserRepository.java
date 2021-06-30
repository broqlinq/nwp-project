package raf.nwp.aircompany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raf.nwp.aircompany.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
