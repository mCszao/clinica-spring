package med.clinica.spring.api.domain.user.controller;

import jakarta.validation.Valid;
import med.clinica.spring.api.domain.user.dto.UserDTO;
import med.clinica.spring.api.domain.user.repository.UserEntity;
import med.clinica.spring.api.infra.security.DTO.TokenJWT;
import med.clinica.spring.api.infra.security.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity signIn(@RequestBody @Valid UserDTO dto){
        var authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));
        var token = tokenService.generateToken((UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJWT(token));
    }


}
