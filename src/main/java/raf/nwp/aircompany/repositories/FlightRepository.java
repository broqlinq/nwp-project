package raf.nwp.aircompany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raf.nwp.aircompany.models.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
