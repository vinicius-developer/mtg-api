package br.com.zappts.mtg.domain.list.controller;

import br.com.zappts.mtg.domain.list.controller.errors.ListResponseMessages;
import br.com.zappts.mtg.domain.list.dataStructure.FormatListDto;
import br.com.zappts.mtg.domain.list.dataStructure.ProjectIdAndName;
import br.com.zappts.mtg.domain.list.dataStructure.ShowListDto;
import br.com.zappts.mtg.domain.list.database.entities.ListEntity;
import br.com.zappts.mtg.domain.list.service.ListService;
import br.com.zappts.mtg.domain.user.database.entities.UserEntity;
import br.com.zappts.mtg.domain.user.service.UserService;
import br.com.zappts.mtg.infra.security.service.TokenService;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

        Long userId = this.tokenService.getUserFromHeader(header);

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

            ShowListDto showListDto = new ShowListDto(listEntity);

            return ResponseEntity.ok(showListDto);

        } catch (InvalidParameterException e) {

            return ResponseEntity.badRequest().body(ListResponseMessages.NONEXISTENT_LIST);

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return ResponseEntity.internalServerError().build();

        }

    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestHeader("Authorization") String header,
                                    @RequestBody FormatListDto formatListDto) {
        try {

            Long userId = this.tokenService.getUserFromHeader(header);

            UserEntity user = this.userService.getUserById(userId);

            this.listService.updateListName(formatListDto, user, id);

            return ResponseEntity.ok().build();

        } catch (InvalidParameterException e) {

            return ResponseEntity.badRequest().body(ListResponseMessages.NONEXISTENT_LIST);

        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();

        }

    }

    @GetMapping("/all")
    public ResponseEntity<?> all(@RequestParam String page) {

        try {

            int pageInt = Integer.parseInt(page);

            Pageable pageable = PageRequest.of(pageInt, 10);

            Page<ProjectIdAndName> listEntity = this.listService.getAllListsByUserId(pageable);

            return ResponseEntity.ok().body(listEntity);

        } catch (NumberFormatException numberFormatException) {

            return ResponseEntity.badRequest().body(ListResponseMessages.INVALID_NUMBER_PAGE);

        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();

        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id,
                                    @RequestHeader("Authorization") String header) {

        try {

            Long userId = this.tokenService.getUserFromHeader(header);

            UserEntity user = this.userService.getUserById(userId);

            this.listService.deleteListFromUserById(user, id);

            return ResponseEntity.ok().build();

        } catch (InvalidParameterException invalidParameterException ) {

            return ResponseEntity.badRequest().body(ListResponseMessages.INVALID_NUMBER_PAGE);

        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();

        }

    }

}
