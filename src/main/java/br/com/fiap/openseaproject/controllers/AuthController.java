package br.com.fiap.openseaproject.controllers;

import br.com.fiap.openseaproject.dto.SignupRequest;
import br.com.fiap.openseaproject.dto.SignupResponse;
import br.com.fiap.openseaproject.models.User;
import br.com.fiap.openseaproject.repositories.UserRepository;
import br.com.fiap.openseaproject.security.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;

    private final WebSecurityConfigurerAdapter webSecurityConfigurerAdapter;

    @Autowired
    public AuthController(UserRepository userRepository, WebSecurityConfigurerAdapter webSecurityConfigurerAdapter) {
        this.userRepository = userRepository;
        this.webSecurityConfigurerAdapter = webSecurityConfigurerAdapter;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody @Validated SignupRequest signupRequest) {
        User user = new User(
            null,
            signupRequest.username(),
            webSecurityConfigurerAdapter.passwordEncoder().encode(signupRequest.password()),
            signupRequest.email()
        );

        userRepository.save(user);

        return ResponseEntity
            .created(URI.create(String.format("/users/%d", user.getId())))
            .body(SignupResponse.fromUser(user));
    }
}
