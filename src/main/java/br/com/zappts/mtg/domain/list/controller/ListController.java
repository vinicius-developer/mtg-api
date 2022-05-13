package br.com.zappts.mtg.domain.list.controller;

import br.com.zappts.mtg.domain.list.dataStructure.CreateListDto;
import br.com.zappts.mtg.domain.list.service.ListService;
import br.com.zappts.mtg.infra.security.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/list")
public class ListController {

    private final ListService listService;

    private final TokenService tokenService;

    public ListController(ListService listService, TokenService tokenService) {
        this.listService = listService;
        this.tokenService = tokenService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestBody CreateListDto createListDto,
            @RequestHeader("Authentication") String header
    ) {

        String token = this.tokenService.restoreToken(header);

        Long userId = this.tokenService.getUserId(token);

        createListDto.setOwner(userId);

        this.listService.


    }

}
