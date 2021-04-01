package com.example.demo.controller;


import com.example.demo.Exceptions.InvalidOperationException;
import com.example.demo.bag.WebResponse;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.model.User;
import com.example.demo.registration.UserRegister;
import com.example.demo.repository.OpenService;
import com.example.demo.repository.UserService;
import com.example.demo.token.CreateTokenRequest;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "User")
@RestController
public class UserAuthenticationController extends OpenController{

    private final AuthenticationManager authenticationManager ;
    private UserService userService;
    private final OpenService openService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserAuthenticationController(AuthenticationManager authenticationManager, UserService userService, OpenService openService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.openService = openService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @RequestMapping(value = "/auth/sign-up", method = RequestMethod.POST)
    public ResponseEntity<String> register(@Valid @RequestBody UserRegister userRegister){
        if (openService.registerUser(userRegister)){
            return new ResponseEntity(HttpStatus.OK);
        }

        throw new InvalidOperationException("Cannot Register User");
    }

    @PostMapping("/auth/token")
    public ResponseEntity signin(@Valid @RequestBody CreateTokenRequest data, HttpServletRequest request) {
        User user = (User) userService.loadUserByUsername(data.getEmail());

        System.out.println(user.getPassword());
        System.out.println(data.getPassword());
        List<String>ana=new ArrayList<>();
        ana.add(user.getROLE());
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),data.getPassword(),getGrantedAuthorities(ana) );
        authenticationManager.authenticate(authentication);
        String token = jwtTokenProvider.createToken(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return WebResponse
                .create()
                .add("token",token)
                .compactWeb();
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

}
