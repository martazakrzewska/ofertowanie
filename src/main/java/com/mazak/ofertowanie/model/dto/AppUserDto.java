package com.mazak.ofertowanie.model.dto;

import com.mazak.ofertowanie.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {
    private Long id;
    private String login;
    private String name;

    public static AppUserDto create(AppUser user) {
        return new AppUserDto(
                user.getId(),
                user.getLogin(),
                user.getName());
    }
}
