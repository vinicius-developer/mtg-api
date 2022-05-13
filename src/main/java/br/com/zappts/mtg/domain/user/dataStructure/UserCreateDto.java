package br.com.zappts.mtg.domain.user.dataStructure;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UserCreateDto {

    @Email(message = "Email deve ser valido")
    private String email;

    @Pattern(message = "Weak password", regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$")
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
