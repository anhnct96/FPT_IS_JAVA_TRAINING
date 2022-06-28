package fis.ra.criminalmanagementsystem.controller;

import fis.ra.criminalmanagementsystem.auth.JwtTokenProvider;
import fis.ra.criminalmanagementsystem.dto.LoginRequest;
import fis.ra.criminalmanagementsystem.dto.LoginResponse;
import fis.ra.criminalmanagementsystem.repository.authorization.DetectiveDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken((DetectiveDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }
}
