package br.com.zappts.mtg.domain.list.dataStructure;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateListDto {

    private Long owner;

    @NotNull(message = "Nome da lista deve ser informado")
    @NotEmpty(message = "Nome da lista deve ser informado")
    private String name;

    public Long getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }
}
