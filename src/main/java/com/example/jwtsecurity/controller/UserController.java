package com.example.jwtsecurity.controller;

import com.example.jwtsecurity.dto.AuthRequest;
import com.example.jwtsecurity.entity.User;
import com.example.jwtsecurity.service.JwtService;
import com.example.jwtsecurity.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public UserController(JwtService jwtService, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }



    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        System.out.println("helliiiii");
        userService.addUser(user);
        return "New user added";
    }

    @GetMapping("/home")
    public ResponseEntity<?> home(){
        System.out.println("helliiiii");
        return new ResponseEntity<>("Hello there!!",HttpStatus.BAD_REQUEST);

    }
    @GetMapping("/getUsers")
    public ResponseEntity<?> fetchAllUsers(){
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUsername());
        }else{
            throw new UsernameNotFoundException("Invalid User Request!!");
        }


    }
}
