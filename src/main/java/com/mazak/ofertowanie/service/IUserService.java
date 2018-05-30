package com.mazak.ofertowanie.service;

import com.mazak.ofertowanie.model.AppUser;
import com.mazak.ofertowanie.model.dto.AppUserDto;
import com.mazak.ofertowanie.model.dto.AppUserRegisterDto;
import com.mazak.ofertowanie.model.dto.LoginDto;

import java.util.Optional;

public interface IUserService {
     Optional<AppUser> getUserWithLoginAndPassword(LoginDto dto);

     Optional<AppUser> getAppUserWithId(Long identifier);

     Optional<AppUserDto> registerUser(AppUserRegisterDto user);

}
