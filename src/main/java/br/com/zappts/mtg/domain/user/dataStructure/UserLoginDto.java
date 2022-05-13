package br.com.zappts.mtg.domain.user.dataStructure;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserLoginDto {

    @NotNull(message = "E-mail deve ser informado")
    @NotEmpty(message = "E-mail deve ser informado")
    private String email;

    @NotNull(message = "Senha deve ser informada")
    @NotEmpty(message = "Senha deve ser informada")
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken covert() {
        return new UsernamePasswordAuthenticationToken(
                this.email,
                this.password
        );
    }
}
