package br.com.zappts.mtg.domain.user.controllers.errors;

import java.util.HashMap;
import java.util.Map;

public class UserResponseMessages {

    public static final Map<String, String> EMAIL_ERROR = new HashMap<>() {{
        put("message", "e-mail invalido");
    }};

    public static final Map<String, String> USERNAME_ERROR = new HashMap<>() {{
        put("message", "username invalido");
    }};

    public static final Map<String, String> UNAUTHORIZED = new HashMap<String, String>() {{
        put("error", "nao autorizado");
    }};


}
