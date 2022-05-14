package br.com.zappts.mtg.domain.list.controller.errors;

import java.util.HashMap;
import java.util.Map;

public class ListResponseMessages {

    public static final Map<String, String> NONEXISTENT_LIST = new HashMap<String, String>() {{
        put("message", "lista inexistente");
    }};

}
