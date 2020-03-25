package com.codecool.secustream.controller;

import com.codecool.secustream.security.JwtRequestFilter;
import com.codecool.secustream.security.JwtUtil;
import com.codecool.secustream.security.UserCredentials;
import com.codecool.secustream.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody UserCredentials secuUser, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                secuUser.getUsername(),
                secuUser.getPassword()
        ));
        String jwtToken = jwtUtil.generateToken(authentication);
        addTokenToCookie(response, jwtToken);
        return ResponseEntity.ok().body(secuUser.getUsername());
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<String> signup(@RequestBody UserCredentials secuUser) {
        userService.register(secuUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(secuUser.getUsername());
    }

    private void addTokenToCookie(HttpServletResponse response, String token) {
        ResponseCookie cookie = ResponseCookie.from(JwtRequestFilter.TOKEN, token)
                .domain("localhost") // should be parameterized
                .sameSite("Strict")
//                .secure(true)
                .httpOnly(true)
                .path("/")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

}
