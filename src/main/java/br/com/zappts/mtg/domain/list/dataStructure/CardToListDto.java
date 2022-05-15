package br.com.zappts.mtg.domain.list.dataStructure;

import br.com.zappts.mtg.domain.card.database.entities.CardEntity;

import java.math.BigDecimal;

public class CardToListDto {
    private Long id;

    private String name;

    private String edition;

    private String language;

    private Boolean isFoil;

    private String price;

    private Integer amount;

    public CardToListDto(CardEntity card) {
        this.id = card.getId();
        this.name = card.getName();
        this.edition = card.getEdition();
        this.language = card.getLanguage();
        this.isFoil = card.getIsFoil();
        this.price = card.getPrice();
        this.amount = card.getAmount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getFoil() {
        return isFoil;
    }

    public void setFoil(Boolean foil) {
        isFoil = foil;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
