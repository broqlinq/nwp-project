package raf.nwp.aircompany.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import raf.nwp.aircompany.services.FlightService;

@RestController
@RequestMapping(path = "flight")
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
}
