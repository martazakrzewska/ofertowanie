package com.mazak.ofertowanie.service;

import com.mazak.ofertowanie.exception.UserIsInvalidException;
import com.mazak.ofertowanie.exception.UserWithThatLoginExistsException;
import com.mazak.ofertowanie.model.AppUser;
import com.mazak.ofertowanie.model.dto.AppUserDto;
import com.mazak.ofertowanie.model.dto.AppUserRegisterDto;
import com.mazak.ofertowanie.model.dto.LoginDto;
import com.mazak.ofertowanie.repository.RoleRepository;
import com.mazak.ofertowanie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Optional<AppUserDto> registerUser(AppUserRegisterDto user) {
        if (isInvalidUser(user)) {
            throw new UserIsInvalidException();
        }
        if (isLoginRegistered(user.getLogin())) {
            throw new UserWithThatLoginExistsException();
        }
//        HashSet<Role> set = new HashSet<Role>();
//        set.add(roleRepository.findById(1L).get());

        AppUser userToRegister = new AppUser(user.getLogin(), user.getPassword(), user.getName());

        userToRegister.setPassword(bCryptPasswordEncoder.encode(userToRegister.getPassword()));
        userToRegister = userRepository.save(userToRegister);
        return Optional.ofNullable(AppUserDto.create(userToRegister));
    }
//
    private boolean isInvalidUser(AppUserRegisterDto user) {
        return user.getLogin() == null || user.getLogin().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty();
    }

    private boolean isLoginRegistered(String login) {
        return userRepository.findByLogin(login).isPresent();
    }

    @Override
    public Optional<AppUser> getAppUserWithId(Long identifier) {
        return userRepository.findById(identifier);
    }


    @Override
    public Optional<AppUser> getUserWithLoginAndPassword(LoginDto dto) {
        // z bazy danych wyciągam użytkownika o loginie
        Optional<AppUser> userFromDB = userRepository.findByLogin(dto.getLogin());
        if (userFromDB.isPresent()) { // jeśli taki istnieje
            AppUser user = userFromDB.get(); // sprawdzam jego hasło (niżej)
            if (bCryptPasswordEncoder.matches(dto.getPassword(), user.getPassword())) {
                return userFromDB; // jeśli hasło się zgadza, to zwracam użytkownika
            }
        }
        return Optional.empty();
    }

}
