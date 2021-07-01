package raf.nwp.aircompany.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.nwp.aircompany.dtos.UserRegisterForm;
import raf.nwp.aircompany.exceptions.ExistingUsernameException;
import raf.nwp.aircompany.services.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        var allUsers = userService.findAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping(path = "get")
    public ResponseEntity<?> getUserByUsername(@RequestParam(name = "username") String username) {
        var user = userService.findUserByUsername(username);
        return ResponseEntity.of(user);
    }

    @PostMapping(path = "register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterForm registerForm) {
        try {
            return ResponseEntity.ok(userService.register(registerForm));
        } catch (ExistingUsernameException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
