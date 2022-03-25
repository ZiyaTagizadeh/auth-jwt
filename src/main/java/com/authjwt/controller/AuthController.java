package com.authjwt.controller;


import com.authjwt.authServices.TokenManager;
import com.authjwt.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.html.HTMLTableRowElement;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;


    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginUser loginUser){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUserName(),loginUser.getPassword()));
            return ResponseEntity.ok(tokenManager.generateToken(loginUser.getUserName()));

        }
        catch(Exception e){
            throw  e;
        }

}

}
