package br.com.zappts.mtg.domain.user.controllers.errors;

import java.util.HashMap;
import java.util.Map;

public class UserResponseMessages {

    public static final Map<String, String> EMAIL_ALREADY_IN_USE = new HashMap<String, String>() {{
        put("message", "Email already exists");
    }};

    public static final Map<String, String> INTERNAL_ERROR_IN_AUTH = new HashMap<>() {{
        put("message", "Internal server error, please give a minute");
    }};


}
