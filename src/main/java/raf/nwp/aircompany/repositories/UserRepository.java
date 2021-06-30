package raf.nwp.aircompany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raf.nwp.aircompany.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
