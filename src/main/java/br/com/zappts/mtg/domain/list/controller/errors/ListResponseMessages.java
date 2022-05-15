package br.com.zappts.mtg.domain.list.controller.errors;

import java.util.HashMap;
import java.util.Map;

public class ListResponseMessages {

    public static final Map<String, String> NONEXISTENT_LIST = new HashMap<>() {{
        put("message", "lista inexistente");
    }};

    public static final Map<String, String>  INVALID_NUMBER_PAGE = new HashMap<>() {{
        put("message", "pagina invalida");
    }};

}
