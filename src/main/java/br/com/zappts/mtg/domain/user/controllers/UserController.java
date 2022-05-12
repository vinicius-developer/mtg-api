package br.com.zappts.mtg.domain.user.controllers;


import br.com.zappts.mtg.domain.user.controllers.dto.UserCreateDto;
import br.com.zappts.mtg.domain.user.controllers.dto.UserLoginDto;
import br.com.zappts.mtg.domain.user.controllers.errors.UserResponseMessages;
import br.com.zappts.mtg.infra.security.service.TokenService;
import br.com.zappts.mtg.domain.user.controllers.dto.TokenDto;
import br.com.zappts.mtg.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid UserCreateDto userRequestCreateDto) {

        if(this.userService.emailAlreadyExists(userRequestCreateDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(UserResponseMessages.EMAIL_ALREADY_IN_USE);
        }

        this.userService.create(userRequestCreateDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        UsernamePasswordAuthenticationToken loginData = userLoginDto.covert();

        try {
            Authentication authentication = this.authenticationManager.authenticate(loginData);

            String token = this.tokenService.generateToken(authentication);

            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.internalServerError()
                    .body(UserResponseMessages.INTERNAL_ERROR_IN_AUTH);
        }



    }






}
