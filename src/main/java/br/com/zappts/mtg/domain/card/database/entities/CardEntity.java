package br.com.zappts.mtg.domain.card.database.entities;

import br.com.zappts.mtg.domain.list.database.entities.ListEntity;

import javax.persistence.*;

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

    @Column(nullable = false, name = "language_card")
    private String language;

    @Column(nullable = false)
    private Boolean isFoil;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private Integer amount;

    public CardEntity() {
    }

    public CardEntity(ListEntity list,
                      String name,
                      String edition,
                      String language,
                      Boolean isFoil,
                      String price,
                      Integer amount) {
        this.list = list;
        this.name = name;
        this.edition = edition;
        this.language = language;
        this.isFoil = isFoil;
        this.price = price;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEdition() {
        return edition;
    }

    public String getLanguage() {
        return language;
    }

    public ListEntity getList() {
        return list;
    }

    public Boolean getIsFoil() {
        return isFoil;
    }

    public String getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setList(ListEntity list) {
        this.list = list;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setIsFoil(Boolean isFoil) {
        this.isFoil = isFoil;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


}
