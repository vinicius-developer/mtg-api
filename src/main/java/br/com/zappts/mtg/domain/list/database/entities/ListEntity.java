package br.com.zappts.mtg.domain.list.database.entities;

import br.com.zappts.mtg.domain.user.database.entities.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "list_of_cards")
public class ListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private UserEntity user;

    @Column(nullable = false)
    private String name;

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

}
