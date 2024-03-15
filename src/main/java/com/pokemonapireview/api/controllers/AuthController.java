package com.pokemonapireview.api.controllers;

import com.pokemonapireview.api.dto.AuthResponseDTO;
import com.pokemonapireview.api.dto.LoginDto;
import com.pokemonapireview.api.dto.RegisterDto;
import com.pokemonapireview.api.models.Role;
import com.pokemonapireview.api.models.UserEntity;
import com.pokemonapireview.api.repository.RoleRespository;
import com.pokemonapireview.api.repository.UserRespository;
import com.pokemonapireview.api.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRespository userRespository;
    private RoleRespository roleRespository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserRespository userRespository,
                          RoleRespository roleRespository,
                          PasswordEncoder passwordEncoder,
                          JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRespository = userRespository;
        this.roleRespository = roleRespository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }


    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication); // this security context when user login spring security stores it so the user don't keeps loging in
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
    if (userRespository.existsByUsername(registerDto.getUsername())){
        return new ResponseEntity<>("User already taken", HttpStatus.BAD_REQUEST);
    }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleRespository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRespository.save(user);

        return new ResponseEntity<>("Successfully registerd", HttpStatus.CREATED);

    }
}
