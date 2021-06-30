package raf.nwp.aircompany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raf.nwp.aircompany.models.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
