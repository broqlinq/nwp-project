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
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> authenticate(@Valid @RequestBody UserCredentialsDto credentials) {
        try {
            var token = loginService.generateJwt(credentials);
            return ResponseEntity.ok(token);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid user credentials");
        }
    }

}
