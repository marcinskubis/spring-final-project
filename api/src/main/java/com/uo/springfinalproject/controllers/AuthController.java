package com.uo.springfinalproject.controllers;

import com.uo.springfinalproject.DTO.UserDTO;
import com.uo.springfinalproject.services.UserDetailsService;
import com.uo.springfinalproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "*", allowedHeaders = {"Authorization"}, allowCredentials = "true")
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO req){
        UserDTO user = userService.register(req);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO req){
        UserDTO user = userService.login(req);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
