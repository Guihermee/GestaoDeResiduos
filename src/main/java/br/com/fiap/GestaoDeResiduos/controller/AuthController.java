package br.com.fiap.GestaoDeResiduos.controller;

import br.com.fiap.GestaoDeResiduos.config.security.TokenService;
import br.com.fiap.GestaoDeResiduos.dto.UsuarioCadastroDto;
import br.com.fiap.GestaoDeResiduos.dto.UsuarioExibicaoDto;
import br.com.fiap.GestaoDeResiduos.dto.UsuarioLoginDto;
import br.com.fiap.GestaoDeResiduos.model.Usuario;
import br.com.fiap.GestaoDeResiduos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login( @RequestBody @Valid UsuarioLoginDto usuarioDto ) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        usuarioDto.email(),
                        usuarioDto.senha());

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto registrarUsuario(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto) {
        UsuarioExibicaoDto usuarioExibicaoDTO = null;
        usuarioExibicaoDTO = usuarioService.saveUsuario(usuarioCadastroDto);
        return usuarioExibicaoDTO;
    }

}


