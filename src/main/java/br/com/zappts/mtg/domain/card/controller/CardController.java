package br.com.zappts.mtg.domain.card.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @PostMapping("/create")
    public ResponseEntity<?> create() {
        return ResponseEntity.ok().build();
    }

}
