package raf.nwp.aircompany.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import raf.nwp.aircompany.models.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT t FROM Ticket t WHERE t.company.name = :companyName")
    Page<Ticket> findTicketsByCompanyName(@Param(value = "companyName") String companyName, Pageable pageable);

    //    @Query(value = "SELECT t FROM Ticket t WHERE (:oneWay IS NULL OR IF())")
//    List<Ticket> findTicketsByOneWay(@Param(value = "oneWay") Boolean oneWay);
    @Query(value = "SELECT t FROM Ticket t " +
            "WHERE (:companyName IS NULL OR t.company.name = :companyName) " +
            "AND (:oneWay IS NULL OR (:oneWay = TRUE AND t.returnDate IS NULL) OR (:oneWay = FALSE AND t.returnDate IS NOT NULL))")
    Page<Ticket> findTicketsByCompanyNameAAndOneWay(@Param(value = "companyName") String companyName, @Param(value = "oneWay") Boolean oneWay, Pageable pageable);
}
