package br.com.zappts.mtg.domain.list.dataStructure;

import br.com.zappts.mtg.domain.card.database.entities.CardEntity;
import br.com.zappts.mtg.domain.list.database.entities.ListEntity;

import java.util.ArrayList;
import java.util.List;

public class ShowListDto {

    private String ownerName;

    private String listName;

    private final List<CardToListDto> cards = new ArrayList<>();

    public ShowListDto(ListEntity list) {
        this.ownerName = list.getUser().getUsername();
        this.listName = list.getName();
        this.addCards(list.getCards());
    }

    private void addCards(List<CardEntity> cards) {
        cards.forEach(card -> {
            this.cards.add(new CardToListDto(card));
        });
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getListName() {
        return listName;
    }

    public List<CardToListDto> getCards() {
        return cards;
    }

}
