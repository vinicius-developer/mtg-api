package br.com.zappts.mtg.domain.card.dataStrucuture;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class UpdateCardDto {

    @NotEmpty(message = "Nome deve ser informado")
    @NotNull(message = "Nome deve ser informado")
    private String name;

    @NotEmpty(message = "Edição deve ser informada")
    @NotNull(message = "Edição deve ser informada")
    private String edition;

    @NotNull(message = "Liguagem nao pode ser vazia")
    @NotEmpty(message = "Liguagem nao pode ser vazia")
    @Pattern(regexp = "inglês|português|japones")
    private String language;

    @NotNull(message = "É necessário informar se a carta é foil")
    private Boolean foil;

    @Positive(message = "preço da carta invalido")
    @NotNull(message = "preço da carta deve ser informado")
    private String price;

    public String getName() {
        return name;
    }

    public String getEdition() {
        return edition;
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
}
