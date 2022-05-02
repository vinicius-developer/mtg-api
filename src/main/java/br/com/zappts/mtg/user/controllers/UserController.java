package br.com.zappts.mtg.user.controllers;


import br.com.zappts.mtg.user.service.UserService;
import br.com.zappts.mtg.user.dataStrucuture.request.UserRequestCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserRequestCreateDto userRequestCreateDto) {

        if(!this.userService.emailAlreadyExists(userRequestCreateDto)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        return new ResponseEntity(HttpStatus.CREATED);

    }

}
