package raf.nwp.aircompany.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.nwp.aircompany.dtos.TicketForm;
import raf.nwp.aircompany.dtos.TicketUpdateForm;
import raf.nwp.aircompany.exceptions.NotFoundException;
import raf.nwp.aircompany.services.TicketService;

import javax.validation.Valid;
import java.time.OffsetDateTime;

@RestController
@RequestMapping(path = "ticket")
@CrossOrigin("*")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping(path = "all")
    public ResponseEntity<?> findAllTickets(@RequestParam(name = "page") int page, @RequestParam(name = "count") int count) {
        return ResponseEntity.ok(ticketService.findAllTickets(page, count));
    }

//    @GetMapping(path = "filter")
//    public ResponseEntity<?> findAllTicketsByCompanyName(
//            @RequestParam(name = "company") String companyName,
//            @RequestParam(name = "page") int page,
//            @RequestParam(name = "count") int count) {
//        return ResponseEntity.ok(ticketService.findTicketsByCompanyName(companyName, page, count));
//    }

    @PostMapping(path = "create")
    public ResponseEntity<?> createTicket(@Valid @RequestBody TicketForm form) {
        try {
            return ResponseEntity.ok(ticketService.createTicket(form));
        } catch (NotFoundException e) {
            return ResponseEntity
                    .notFound()
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e);
        }
    }

    @GetMapping("filter")
    public ResponseEntity<?> filterTickets(
            @RequestParam(name = "origin", required = false) String origin,
            @RequestParam(name = "destination", required = false) String destination,
            @RequestParam(name = "departureDate", required = false) OffsetDateTime departureDate,
            @RequestParam(name = "returnDate", required = false) OffsetDateTime returnDate,
            @RequestParam(name = "company", required = false) String companyName,
            @RequestParam(name = "oneWay", required = false) Boolean oneWay,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "count") int count) {
        return ResponseEntity.ok(ticketService.filterTickets(origin, destination, departureDate, returnDate, companyName, oneWay, page, count));
    }

    @PutMapping
    public ResponseEntity<?> updateTicket(@Valid @RequestBody TicketUpdateForm form) {
        try {
            return ResponseEntity.ok(ticketService.updateTicket(form));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(e);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound()
                    .build();
        }
    }

}
