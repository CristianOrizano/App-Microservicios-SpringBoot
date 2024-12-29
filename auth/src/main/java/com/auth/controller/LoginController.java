package com.auth.controller;

import com.auth.dto.usuario.LoginDto;
import com.auth.shared.security.jwt.JWTAuthResonseDTO;
import com.auth.shared.security.jwt.JwtTokenProvider;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirements(value = {})
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/login")
@Tag(name = "Login")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping()
    public ResponseEntity<JWTAuthResonseDTO> authenticateUser(@RequestBody LoginDto loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        //obtenemos el token del jwtTokenProvider
        JWTAuthResonseDTO token = jwtTokenProvider.getSecurity(authentication);
        return ResponseEntity.ok(token);
    }


}
