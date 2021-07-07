package raf.nwp.aircompany.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.nwp.aircompany.dtos.FlightDto;
import raf.nwp.aircompany.exceptions.NotFoundException;
import raf.nwp.aircompany.services.FlightService;

@RestController
@RequestMapping(path = "flight")
@CrossOrigin("*")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping(path = "all")
    public ResponseEntity<?> getAllFlights() {
        return ResponseEntity.ok(flightService.findAllFlights());
    }

    @GetMapping(path = "filter")
    public ResponseEntity<?> filterFlightsByOriginAndDestination(
            @RequestParam(name = "origin", required = false) String origin,
            @RequestParam(name = "destination", required = false) String destination) {
        return ResponseEntity.ok(flightService.findAllFlightsBy(origin, destination));
    }

    @PostMapping
    public ResponseEntity<?> createFlight(@RequestBody FlightDto flightDto) {
        try {
            return ResponseEntity.ok(flightService.createFlight(flightDto));
        } catch (NotFoundException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> fetchFlights(@RequestParam(value = "page") int page, @RequestParam(value = "count") int count) {
        return ResponseEntity.ok(flightService.findFlights(page, count));
    }

    @GetMapping(path = "fetch")
    public ResponseEntity<?> fetchFlightsByOriginAndDestination(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "count") int count,
            @RequestParam(name = "origin", required = false) String origin,
            @RequestParam(name = "destination", required = false) String destination) {
        return ResponseEntity.ok(flightService.findFlightsByOriginAndDestination(page, count, origin, destination));
    }
}
