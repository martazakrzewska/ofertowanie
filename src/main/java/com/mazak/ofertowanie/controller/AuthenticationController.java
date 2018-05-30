package com.mazak.ofertowanie.controller;

import com.mazak.ofertowanie.model.AppUser;
import com.mazak.ofertowanie.model.Role;
import com.mazak.ofertowanie.model.dto.AppUserDto;
import com.mazak.ofertowanie.model.dto.AppUserRegisterDto;
import com.mazak.ofertowanie.model.dto.AuthenticationDto;
import com.mazak.ofertowanie.model.dto.LoginDto;
import com.mazak.ofertowanie.response.ResponseFactory;
import com.mazak.ofertowanie.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mazak.ofertowanie.config.JWTFilter.SECRET;


@RestController
@RequestMapping (path = "/auth/")
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private UserService userService;


    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody AppUserRegisterDto dto){
        Optional<AppUserDto> user = userService.registerUser(dto);
        if(user.isPresent()){
            return ResponseFactory.created((user.get()));
        }
        return ResponseFactory.badRequest();

    }

    @RequestMapping(path = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity authenticate(@RequestBody LoginDto dto){
        Optional<AppUser> userOptional = userService.getUserWithLoginAndPassword(dto);
        if (userOptional.isPresent()){
            AppUser user = userOptional.get();

            // haslo
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            String token = Jwts.builder()
                    .setSubject(user.getLogin())
                    .setIssuedAt(new Date())
                    .claim("roles", translateRoles(user.getRoles()))
                    .signWith(signatureAlgorithm, signingKey)
                    .compact();
            return ResponseFactory.ok(new AuthenticationDto(user.getLogin(), user.getId(), token));
        }
        return ResponseFactory.badRequest();
    }

    private Set<String> translateRoles(Set<Role> roles) {
        return roles.stream().map(role -> role.getName()).collect(Collectors.toSet());
    }
}
