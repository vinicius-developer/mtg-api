package br.com.zappts.mtg.domain.user.controllers.errors;

import java.util.HashMap;
import java.util.Map;

public class UserResponseMessages {

    public static final Map<String, String> EMAIL_ALREADY_IN_USE = new HashMap<String, String>() {{
        put("message", "Nao foi possivel cadastrar");
    }};

    public static final Map<String, String> UNAUTHORIZED = new HashMap<>() {{
        put("error", "Unauthorized");
    }};


}
