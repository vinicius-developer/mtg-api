package br.com.zappts.mtg.domain.card.controller;


import br.com.zappts.mtg.domain.card.controller.erros.CardResponseMessages;
import br.com.zappts.mtg.domain.card.controller.exception.InvalidListAccessException;
import br.com.zappts.mtg.domain.card.dataStrucuture.CreateCardDto;
import br.com.zappts.mtg.domain.card.dataStrucuture.UpdateCardDto;
import br.com.zappts.mtg.domain.card.database.entities.CardEntity;
import br.com.zappts.mtg.domain.card.service.CardService;
import br.com.zappts.mtg.domain.list.controller.errors.ListResponseMessages;
import br.com.zappts.mtg.domain.list.database.entities.ListEntity;
import br.com.zappts.mtg.domain.list.service.ListService;
import br.com.zappts.mtg.domain.user.database.entities.UserEntity;
import br.com.zappts.mtg.domain.user.service.UserService;
import br.com.zappts.mtg.infra.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidParameterException;

@RestController
@RequestMapping("/card")
@Validated
public class CardController {

    private final TokenService tokenService;

    private final UserService userService;

    private final CardService cardService;

    private final ListService listService;
    @Autowired
    public CardController(CardService cardService,
                          TokenService tokenService,
                          UserService userService,
                          ListService listService) {

        this.cardService = cardService;
        this.userService = userService;
        this.tokenService = tokenService;
        this.listService = listService;

    }

    @PostMapping("/insert")
    public ResponseEntity<?> create(@Valid @RequestBody CreateCardDto createCardDto,
                                    @RequestHeader("Authorization") String header) throws URISyntaxException {

        Long idUser = this.tokenService.getUserFromHeader(header);

        UserEntity user = this.userService.getUserById(idUser);

        Long listId = createCardDto.getList();
        System.out.println("asdfasdfasdfasdfasdfasdf");

        if(!this.listService.userIsListOwner(user, listId)) {
            return ResponseEntity.badRequest()
                    .body(ListResponseMessages.NONEXISTENT_LIST);
        }

        ListEntity listEntity = this.listService.findListById(listId);

        CardEntity card = this.cardService.addCardToList(createCardDto, listEntity);

        return ResponseEntity.created(new URI("card/find/" + card.getId())).build();

    }

    @DeleteMapping("/delete/{id}")
    @Validated
    public ResponseEntity<?> remove(@PathVariable Long id,
                                    @RequestHeader("Authorization") String header,
                                    @Valid
                                        @RequestParam(required = false, name = "quantity", defaultValue = "1")
                                        @Pattern(regexp = "[(1-9)]\\w+")
                                        String quantity
    ) {

        try {
            Long idUser = this.tokenService.getUserFromHeader(header);

            UserEntity user = this.userService.getUserById(idUser);

            CardEntity card = this.cardService.getCardFromId(id);

            if(!this.listService.userIsListOwner(user, card.getList().getId())) {
                return ResponseEntity.badRequest()
                        .body(ListResponseMessages.NONEXISTENT_LIST);
            }

            this.cardService.deleteCardToList(card,
                    Integer.parseInt(quantity));

            return ResponseEntity.ok().build();
        } catch (InvalidParameterException invalidParameterException) {

            return ResponseEntity.badRequest().body(CardResponseMessages.NONEXISTENT_CARD);

        } catch (InvalidListAccessException invalidListAccessException) {

            return ResponseEntity.badRequest().body(ListResponseMessages.NONEXISTENT_LIST);

        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();

        }

    }

    @PutMapping("/update/{id}")
    @Validated
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestHeader("Authorization") String header,
                                    @Validated @RequestBody UpdateCardDto updateCardDto) {


        try {
            Long idUser = this.tokenService.getUserFromHeader(header);

            UserEntity user = this.userService.getUserById(idUser);

            CardEntity card = this.cardService.getCardFromId(id);

            if(!this.listService.userIsListOwner(user, card.getList().getId())) {
                return ResponseEntity.badRequest()
                        .body(ListResponseMessages.NONEXISTENT_LIST);
            }

            this.cardService.updateCard(card, updateCardDto);

            return ResponseEntity.ok().build();
        } catch (InvalidParameterException invalidParameterException) {
            return ResponseEntity.badRequest().body(CardResponseMessages.NONEXISTENT_CARD);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }



}
