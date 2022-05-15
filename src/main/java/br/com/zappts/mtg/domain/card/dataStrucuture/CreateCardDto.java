package br.com.zappts.mtg.domain.card.dataStrucuture;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class CreateCardDto {

    @NotEmpty(message = "Nome deve ser informado")
    @NotNull(message = "Nome deve ser informado")
    private String name;

    @NotNull(message = "Lista de ser informada")
    @Positive(message = "Identificação da lista deve ser valida")
    private Long list;

    @NotEmpty(message = "Edição deve ser informada")
    @NotNull(message = "Edição deve ser informada")
    private String edition;

    @NotNull(message = "Liguagem nao pode ser vazia")
    @NotEmpty(message = "Liguagem nao pode ser vazia")
    @Pattern(regexp = "inglês|português|japones")
    private String language;

    @NotNull(message = "É necessário informar se a carta é foil")
    private Boolean foil;

    @NotNull(message = "Preço da carta deve ser informado")
    @Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$", message = "Insira uma valor valido")
    private String price;

    public Long getList() {
        return list;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public Boolean getFoil() {
        return foil;
    }

    public String getPrice() {
        return price;
    }

    public String getEdition() {
        return edition;
    }
}
