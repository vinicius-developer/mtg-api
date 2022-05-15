package br.com.zappts.mtg.domain.card.controller.erros;

import java.util.HashMap;
import java.util.Map;

public class CardResponseMessages {

    public static final Map<String, String> NONEXISTENT_CARD = new HashMap<>() {{
        put("message", "carta inexistente");
    }};


}
