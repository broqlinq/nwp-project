package raf.nwp.aircompany.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import raf.nwp.aircompany.models.Company;
import raf.nwp.aircompany.models.Ticket;

import java.time.OffsetDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findAllByCompany(Company company);

    void deleteAllByCompany(Company company);

    @Query(value = "SELECT t FROM Ticket t WHERE t.company.name = :companyName")
    Page<Ticket> findTicketsByCompanyName(@Param(value = "companyName") String companyName, Pageable pageable);

    @Query(value = "SELECT t FROM Ticket t " +
            "WHERE (:companyName IS NULL OR t.company.name = :companyName) " +
            "AND (:oneWay IS NULL OR (:oneWay = TRUE AND t.returnDate IS NULL) OR (:oneWay = FALSE AND t.returnDate IS NOT NULL))")
    Page<Ticket> findTicketsByCompanyNameAAndOneWay(@Param(value = "companyName") String companyName, @Param(value = "oneWay") Boolean oneWay, Pageable pageable);

    @Query(value = "SELECT t from Ticket t " +
            "WHERE (:origin IS NULL OR t.flight.origin.name = :origin) " +
            "AND (:destination IS NULL OR t.flight.destination.name = :destination) " +
            "AND (:departureDate IS NULL OR t.departureDate >= :departureDate) " +
            "AND (:returnDate IS NULL OR t.returnDate <= :returnDate) " +
            "AND (:company IS NULL OR t.company.name = :company) " +
            "AND (:oneWay IS NULL OR (:oneWay = TRUE AND t.returnDate IS NULL) OR (:oneWay = FALSE AND t.returnDate IS NOT NULL))")
    Page<Ticket> filterTickets(
            @Param(value = "origin") String origin,
            @Param(value = "destination") String destination,
            @Param(value = "departureDate") OffsetDateTime departureDate,
            @Param(value = "returnDate") OffsetDateTime returnDate,
            @Param(value = "company") String companyName,
            @Param(value = "oneWay") Boolean oneWay,
            Pageable pageable);
}
