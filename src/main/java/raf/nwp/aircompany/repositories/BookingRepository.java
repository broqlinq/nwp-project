package raf.nwp.aircompany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raf.nwp.aircompany.models.Booking;
import raf.nwp.aircompany.models.Ticket;
import raf.nwp.aircompany.models.User;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByUser(User user);

    Optional<Booking> findByUserAndTicket(User user, Ticket ticket);
}
