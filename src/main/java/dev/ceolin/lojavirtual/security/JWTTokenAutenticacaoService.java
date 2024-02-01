package dev.ceolin.lojavirtual.security;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//criar  e retornar a autenticacao JWT
@Service
@Component
public class JWTTokenAutenticacaoService {
	
	/*Token de validade de 11 dias*/
	private static final long EXPIRATION_TIME = 959990000;
	
//	/*Chave senha para juntar com JWT*/
	private static final String SECRET = "adoro3bala";
	
	private static final String TOKEN_PREFIX =  "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	/*Gera token ao cliente com JWT*/
	private void addAuthorization(HttpServletResponse response, String username) throws Exception{
		

		String JWT = Jwts.builder()// chama gerador de token
				.setSubject(username)// adiciona user
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //tempo de experiçao
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
				
		String token = TOKEN_PREFIX + " " + JWT;
		
		response.addHeader(HEADER_STRING, token);// resposta para tela e ao cliente, API, APP, uma chamada java
		
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
		
	}
}
