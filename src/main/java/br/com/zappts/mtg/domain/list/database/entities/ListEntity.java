package br.com.zappts.mtg.domain.list.database.entities;

import br.com.zappts.mtg.domain.card.database.entities.CardEntity;
import br.com.zappts.mtg.domain.user.database.entities.UserEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "list_of_cards")
public class ListEntity {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="id_list")
    private List<CardEntity> cards;

    public ListEntity() {

    }

    public ListEntity(String name, UserEntity user) {
        this.user = user;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public List<CardEntity> getCards() {
        return this.cards;
    }

    public void setName(String name) {
        this.name = name;
    }
}
