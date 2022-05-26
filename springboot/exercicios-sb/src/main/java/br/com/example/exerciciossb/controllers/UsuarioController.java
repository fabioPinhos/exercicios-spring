package br.com.example.exerciciossb.controllers;

import br.com.example.exerciciossb.model.entities.Usuario;
import br.com.example.exerciciossb.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    public UsuarioServiceImpl usuarioService;

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody Usuario usuario){

        String senhaCriptografada = passwordEncoder().encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        return usuarioService.salvar(usuario);
    }
}
