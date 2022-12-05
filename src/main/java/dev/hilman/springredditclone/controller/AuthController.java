package dev.hilman.springredditclone.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.hilman.springredditclone.dto.AuthenticationResponse;
import dev.hilman.springredditclone.dto.LoginRequest;
import dev.hilman.springredditclone.dto.RefreshTokenRequest;
import dev.hilman.springredditclone.dto.RegisterRequest;
import dev.hilman.springredditclone.services.AuthService;
import dev.hilman.springredditclone.services.RefreshTokenService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public String signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return "User Registration Succesfully, Please Check Your Email For Activated";
    }

    @GetMapping("/accountVerification/{token}")
    @ResponseStatus(HttpStatus.OK)
    public String verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return "Account Activated Succesfully";
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
       return authService.login(loginRequest);
       
    }
    
    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public String logout (@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return "Refresh Token Deleted Successfully!!";
    }
}
