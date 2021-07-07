package raf.nwp.aircompany.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raf.nwp.aircompany.services.CityService;

@RestController
@RequestMapping(path = "city")
@CrossOrigin("*")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(path = "all")
    public ResponseEntity<?> getAllCities() {
        return ResponseEntity.ok(cityService.findAllCities());
    }
}
