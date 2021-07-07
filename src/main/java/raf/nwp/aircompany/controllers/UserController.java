package raf.nwp.aircompany.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.nwp.aircompany.dtos.UserRegisterForm;
import raf.nwp.aircompany.exceptions.ExistingUsernameException;
import raf.nwp.aircompany.exceptions.NotFoundException;
import raf.nwp.aircompany.services.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "all")
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
        System.out.println(registerForm);
        try {
            return ResponseEntity.ok(userService.registerUser(registerForm));
        } catch (ExistingUsernameException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserRegisterForm registerForm) {
        return ResponseEntity.ok(userService.updateUser(registerForm));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam(name = "id") Long id) {
        try {
            return ResponseEntity.ok(userService.deleteDelete(id));
        } catch (NotFoundException e) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
