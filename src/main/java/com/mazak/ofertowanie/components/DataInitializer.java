package com.mazak.ofertowanie.components;


import com.mazak.ofertowanie.model.Role;
import com.mazak.ofertowanie.model.dto.AppUserRegisterDto;
import com.mazak.ofertowanie.repository.RoleRepository;
import com.mazak.ofertowanie.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private IUserService userService;
    private RoleRepository roleRepository;

    @Autowired
    public DataInitializer(IUserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        initialize();
    }

    private void initialize() {
        Role adminRole = new Role("ADMIN");
        roleRepository.save(adminRole);

        if (!userService.getAppUserWithId(1L).isPresent()) {
            userService.registerUser(new AppUserRegisterDto("admin", "admin", "admin"));
        }
    }
}
