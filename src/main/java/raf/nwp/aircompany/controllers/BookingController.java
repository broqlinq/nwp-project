package raf.nwp.aircompany.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.nwp.aircompany.dtos.BookingRequest;
import raf.nwp.aircompany.exceptions.NotFoundException;
import raf.nwp.aircompany.services.BookingService;

import java.util.List;

@RestController
@RequestMapping(path = "booking")
@CrossOrigin("*")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<?> getBookingsForUser(@RequestParam(name = "username") String username) {
        try {
            var bookings = bookingService.findBookingsByUser(username);
            return ResponseEntity.ok(bookings);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "create")
    public ResponseEntity<?> createBookingForUser(@RequestBody BookingRequest request) {
        try {
            return ResponseEntity.ok(bookingService.createBookingForUser(request.username(), request.ticketId(), request.count()));
        } catch (NotFoundException e) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PostMapping(path = "buy")
    public ResponseEntity<?> simulateBuyTickets(@RequestBody List<Long> ids) {
        try {
            var bookings = bookingService.simulateBuyTickets(ids);
            return ResponseEntity.ok(bookings);
        } catch (IllegalStateException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBooking(@RequestParam(name = "id") Long id) {
        try {
            return ResponseEntity.ok(bookingService.deleteBooking(id));
        } catch (NotFoundException e) {
            return ResponseEntity
                    .notFound()
                    .build();
        } catch (IllegalStateException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
