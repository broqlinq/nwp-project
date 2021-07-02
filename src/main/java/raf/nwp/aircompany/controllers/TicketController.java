package raf.nwp.aircompany.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.nwp.aircompany.dtos.TicketUpdateForm;
import raf.nwp.aircompany.services.TicketService;

@RestController
@RequestMapping(path = "ticket")
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

    @GetMapping("filter")
    public ResponseEntity<?> filterTickets(
            @RequestParam(name = "company", required = false) String companyName,
            @RequestParam(name = "oneWay", required = false) Boolean oneWay,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "count") int count) {
        return ResponseEntity.ok(ticketService.filterTickets(companyName, oneWay, page, count));
    }

    @PutMapping
    public ResponseEntity<?> updateTicket(@RequestBody TicketUpdateForm form) {
        return null;
    }

}
