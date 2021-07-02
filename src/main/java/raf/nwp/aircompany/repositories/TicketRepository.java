package raf.nwp.aircompany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import raf.nwp.aircompany.models.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT t FROM tickets_2 t WHERE t.company.name = :companyName")
    List<Ticket> findTicketsByCompanyName(@Param(value = "companyName") String companyName);

//    @Query(value = "SELECT t FROM Ticket t WHERE (:oneWay IS NULL OR IF())")
//    List<Ticket> findTicketsByOneWay(@Param(value = "oneWay") Boolean oneWay);
}
