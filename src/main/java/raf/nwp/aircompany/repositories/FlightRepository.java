package raf.nwp.aircompany.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import raf.nwp.aircompany.models.Flight;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(value = "SELECT c FROM Flight c " +
            "WHERE (:origin IS NULL OR c.origin.name = :origin)" +
            "AND (:destination IS NULL OR c.destination.name = :destination)")
    List<Flight> findFlightsByOriginAndDestination(@Param(value = "origin") String origin, @Param(value = "destination") String destination);

    @Query(value = "SELECT c FROM Flight c " +
            "WHERE (:origin IS NULL OR c.origin.name = :origin)" +
            "AND (:destination IS NULL OR c.destination.name = :destination)")
    Page<Flight> findFlightsByOriginAndDestination(@Param(value = "origin") String origin, @Param(value = "destination") String destination, Pageable pageable);
}
