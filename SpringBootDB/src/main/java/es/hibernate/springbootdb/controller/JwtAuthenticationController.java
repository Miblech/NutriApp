package es.hibernate.springbootdb.controller;

import es.hibernate.springbootdb.entity.JwtRequest;
import es.hibernate.springbootdb.entity.User;
import es.hibernate.springbootdb.security.JwtTokenUtil;
import es.hibernate.springbootdb.service.JwtUserDetailsService;
import es.hibernate.springbootdb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public String createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        User user = userService.getUserByUsernameOrEmail(authenticationRequest.getUsername());
        if (user == null || !userService.validatePassword(authenticationRequest.getPassword(), user)) {
            throw new Exception("INVALID_CREDENTIALS");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        return jwtTokenUtil.generateToken(userDetails);
    }
}