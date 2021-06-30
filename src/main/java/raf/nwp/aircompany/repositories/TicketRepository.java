package raf.nwp.aircompany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raf.nwp.aircompany.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
