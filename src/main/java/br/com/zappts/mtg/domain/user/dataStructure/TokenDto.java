package br.com.zappts.mtg.domain.user.dataStructure;

public class TokenDto {

    private final String token;

    private final String type;
    public TokenDto(String token, String bearer) {
        this.token = token;
        this.type = bearer;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

}
