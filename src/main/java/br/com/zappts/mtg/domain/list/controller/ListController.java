package br.com.zappts.mtg.domain.list.controller;

import br.com.zappts.mtg.domain.list.dataStructure.CreateListDto;
import br.com.zappts.mtg.domain.list.database.entities.ListEntity;
import br.com.zappts.mtg.domain.list.service.ListService;
import br.com.zappts.mtg.domain.user.database.entities.UserEntity;
import br.com.zappts.mtg.domain.user.service.UserService;
import br.com.zappts.mtg.infra.security.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/list")
public class ListController {

    private final ListService listService;

    private final TokenService tokenService;

    private final UserService userService;

    public ListController(ListService listService,
                          TokenService tokenService,
                          UserService userService) {
        this.userService = userService;
        this.listService = listService;
        this.tokenService = tokenService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestBody CreateListDto createListDto,
            @RequestHeader("Authorization") String header
    ) throws URISyntaxException {

        String token = this.tokenService.restoreToken(header);

        Long userId = this.tokenService.getUserId(token);

        UserEntity user = this.userService.getUserById(userId);

        Long idList = this.listService.create(createListDto, user)
                .getId();

        if(idList != null) {
            return ResponseEntity.created(
                    new URI("/list/find/" + idList)
            ).build();
        }

        return ResponseEntity.internalServerError().build();

    }





}
