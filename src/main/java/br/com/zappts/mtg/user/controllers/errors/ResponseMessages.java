package br.com.zappts.mtg.user.controllers.errors;

import java.util.HashMap;
import java.util.Map;

public class ResponseMessages {

    public static final Map<String, String> EMAILALREADYINUSE = new HashMap<String, String>() {{
        put("message", "Email already exists");
    }};


}
