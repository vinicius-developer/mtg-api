package br.com.zappts.mtg.domain.list.controller;

import br.com.zappts.mtg.domain.list.controller.errors.ListResponseMessages;
import br.com.zappts.mtg.domain.list.dataStructure.FormatListDto;
import br.com.zappts.mtg.domain.list.dataStructure.ShowListDto;
import br.com.zappts.mtg.domain.list.database.entities.ListEntity;
import br.com.zappts.mtg.domain.list.service.ListService;
import br.com.zappts.mtg.domain.user.database.entities.UserEntity;
import br.com.zappts.mtg.domain.user.service.UserService;
import br.com.zappts.mtg.infra.security.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidParameterException;

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
            @RequestBody FormatListDto createListDto,
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

    @GetMapping("/find/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {

        try {

            ListEntity listEntity = this.listService.findListById(id);

            ShowListDto showListDto  = new ShowListDto(listEntity);

            return ResponseEntity.ok(showListDto);

        } catch (InvalidParameterException e) {

            return ResponseEntity.badRequest().body(ListResponseMessages.NONEXISTENT_LIST);

        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();

        }

    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestHeader("Authorization") String header,
                                    @RequestBody FormatListDto formatListDto) {
        try {

            String token = this.tokenService.restoreToken(header);

            Long userId = this.tokenService.getUserId(token);

            UserEntity user = this.userService.getUserById(userId);

            this.listService.updateListName(id, formatListDto, user);

            return ResponseEntity.ok().build();

        } catch (InvalidParameterException e) {

            return ResponseEntity.badRequest().body(ListResponseMessages.NONEXISTENT_LIST);

        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();

        }


    }


}
