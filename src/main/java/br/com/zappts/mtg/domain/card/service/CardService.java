package br.com.zappts.mtg.domain.card.service;

import br.com.zappts.mtg.domain.card.dataStrucuture.CreateCardDto;
import br.com.zappts.mtg.domain.card.dataStrucuture.UpdateCardDto;
import br.com.zappts.mtg.domain.card.database.entities.CardEntity;
import br.com.zappts.mtg.domain.card.database.respository.CardRepository;
import br.com.zappts.mtg.domain.list.database.entities.ListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.security.InvalidParameterException;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardEntity addCardToList(CreateCardDto createCardDto, ListEntity list) {

        Optional<CardEntity> optionalCard = this.cardRepository.findByListAndNameAndEditionAndLanguageAndIsFoilAndPrice(
                list,
                createCardDto.getName(),
                createCardDto.getEdition(),
                createCardDto.getLanguage(),
                createCardDto.getFoil(),
                createCardDto.getPrice()
        );

        if(optionalCard.isEmpty()) {

            return this.createNewCard(createCardDto, list);

        }

        return addAmountOfCard(optionalCard.get());
    }

    public void deleteCardToList(CardEntity card, Integer quantity) {

        if(card.getAmount() <= quantity) {
            this.deleteAllCardsToList(card);

            return;
        }

        this.decreaseAmountCards(card, quantity);

    }

    public void updateCard(CardEntity card, UpdateCardDto newData) {
        card.setName(newData.getName());
        card.setEdition(newData.getEdition());
        card.setLanguage(newData.getLanguage());
        card.setIsFoil(newData.getFoil());
        card.setPrice(newData.getPrice());

        this.cardRepository.save(card);
    }

    public CardEntity getCardFromId(Long id) {
        Optional<CardEntity> optionalCard = this.cardRepository.findById(id);

        if(optionalCard.isEmpty()) {
            throw new InvalidParameterException("Carta mencionada não existe");
        }

        return optionalCard.get();
    }

    private void decreaseAmountCards(CardEntity card, Integer quantity) {
        card.setAmount(card.getAmount() - quantity);

        this.cardRepository.save(card);
    }

    private void deleteAllCardsToList(CardEntity card) {

        this.cardRepository.delete(card);

    }

    private CardEntity addAmountOfCard(CardEntity card) {
        card.setAmount(card.getAmount() + 1);

        return this.cardRepository.save(card);
    }

    private CardEntity createNewCard(CreateCardDto createCardDto, ListEntity list) {

        CardEntity card = new CardEntity();

        card.setLanguage(createCardDto.getLanguage());
        card.setEdition(createCardDto.getEdition());
        card.setPrice(createCardDto.getPrice());
        card.setIsFoil(createCardDto.getFoil());
        card.setName(createCardDto.getName());
        card.setList(list);
        card.setAmount(1);

        return this.cardRepository.save(card);

    }


}
