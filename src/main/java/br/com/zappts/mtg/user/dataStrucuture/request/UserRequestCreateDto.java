package br.com.zappts.mtg.user.dataStrucuture.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

public class UserRequestCreateDto {

    @Email(message = "Email deve ser valido")
    private String email;

    @Size(min = 8)
    @Pattern(message = "weak password", regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$")
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
