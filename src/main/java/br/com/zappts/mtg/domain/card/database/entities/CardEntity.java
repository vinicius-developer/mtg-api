package br.com.zappts.mtg.domain.card.database.entities;

import br.com.zappts.mtg.domain.card.dataStrucuture.CardLanguage;
import br.com.zappts.mtg.domain.list.database.entities.ListEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cards")
public class CardEntity {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_list")
    private ListEntity list;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String edition;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "language_card")
    private CardLanguage languageCard;

    @Column(nullable = false)
    private Boolean isFoil;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer amount;

    public CardEntity() {
    }

    public CardEntity(ListEntity list,
                      String name,
                      String edition,
                      CardLanguage languageCard,
                      Boolean isFoil,
                      BigDecimal price,
                      Integer amount) {
        this.list = list;
        this.name = name;
        this.edition = edition;
        this.languageCard = languageCard;
        this.isFoil = isFoil;
        this.price = price;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public ListEntity getListEntity() {
        return list;
    }

    public String getName() {
        return name;
    }

    public String getEdition() {
        return edition;
    }

    public CardLanguage getLanguageCard() {
        return languageCard;
    }

    public ListEntity getList() {
        return list;
    }

    public Boolean getIsFoil() {
        return isFoil;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setListEntity(ListEntity list) {
        this.list = list;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setLanguageCard(CardLanguage languageCard) {
        this.languageCard = languageCard;
    }

    public void setIsFoil(Boolean isFoil) {
        this.isFoil = isFoil;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
