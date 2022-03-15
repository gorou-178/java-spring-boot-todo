package com.example.app.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserForm {
    @Size(max = 255)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @NotNull
    private String username;

    @Size(max = 255)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
