package br.com.example.exerciciossb.security.jwt;

import br.com.example.exerciciossb.ExerciciosSbApplication;
import br.com.example.exerciciossb.model.entities.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JWTService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chava-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario){

        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(Long.valueOf(expiracao));
        Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts
                .builder()
                .setSubject(usuario.getLogin())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }

    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValido(String token){
        try {

            Date expiration = obterClaims(token).getExpiration();
            LocalDateTime localDateTime = expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(localDateTime);

        } catch (Exception e){
            return false;
        }
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException{
        return (String)  obterClaims(token).getSubject();
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(ExerciciosSbApplication.class);
        JWTService service = context.getBean(JWTService.class);

        Usuario usuario = new Usuario();
        usuario.setLogin("fulano");

        String token = service.gerarToken(usuario);
        System.out.println(token);

        boolean isTokenValido = service.tokenValido(token);
        System.out.println(isTokenValido);

        System.out.println(service.obterLoginUsuario(token));
    }

}
