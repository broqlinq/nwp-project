package raf.nwp.aircompany.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.nwp.aircompany.dtos.UserCredentialsDto;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "auth")
public class LoginController {

    @PostMapping
    public ResponseEntity<?> authenticate(@Valid @RequestBody UserCredentialsDto credentials) {
        return ResponseEntity.ok(credentials);
    }

}
