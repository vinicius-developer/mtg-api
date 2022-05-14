package br.com.zappts.mtg.domain.list.dataStructure;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FormatListDto {

    @NotNull(message = "Nome da lista deve ser informado")
    @NotEmpty(message = "Nome da lista deve ser informado")
    private String name;

    public String getName() {
        return name;
    }

}
