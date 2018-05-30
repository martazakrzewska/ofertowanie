package com.mazak.ofertowanie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserRegisterDto {
    private String login;
    private String name;
    private String password;

}
