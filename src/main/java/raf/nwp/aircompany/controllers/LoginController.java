package raf.nwp.aircompany.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.nwp.aircompany.dtos.UserCredentialsDto;
import raf.nwp.aircompany.exceptions.InvalidCredentialsException;
import raf.nwp.aircompany.services.LoginService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "auth")
@CrossOrigin("*")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> authenticate(@Valid @RequestBody UserCredentialsDto credentials) {
        try {
            var userData = loginService.generateJwt(credentials);
            return ResponseEntity.ok(userData);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid user credentials");
        }
    }

}
