package br.com.example.exerciciossb.service.impl;

import br.com.example.exerciciossb.model.entities.Usuario;
import br.com.example.exerciciossb.model.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Transactional
    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

//        if (!userName.equals("cicrano")) {
//            throw new UsernameNotFoundException("Usuario nao encontrado na base.");
//        }

        Usuario usuario = usuarioRepository
                .findByLogin(userName)
                .orElseThrow(()-> new UsernameNotFoundException("Usuário nao encontrado na base."));

        String[] roles =  usuario.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
//                .username("cicrano")
//                .password(passwordEncoder().encode("123"))
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

}
