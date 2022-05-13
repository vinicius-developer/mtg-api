package br.com.zappts.mtg.domain.list.database.entities;

import br.com.zappts.mtg.domain.list.dataStructure.CreateListDto;
import br.com.zappts.mtg.domain.user.database.entities.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "list_of_cards")
public class ListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;

    @Column(nullable = false)
    private String name;

    public ListEntity() {

    }

    public ListEntity(CreateListDto createListDto) {
        this.user = createListDto.getOwner();
        this.name = createListDto.getName();
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
