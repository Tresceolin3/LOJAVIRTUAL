package dev.ceolin.lojavirtual.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/*Filtro das requisoes que serao capturadas para autenticacao*/
public class JwtApiAutenticacaoFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/* Autenticacao do user */

		try {

			Authentication authentication = new JWTTokenAutenticacaoService()
					.getAuthetication((HttpServletRequest) request, (HttpServletResponse) response);

			/* Processo de autenticacao do spring security */
			SecurityContextHolder.getContext().setAuthentication(authentication);

			chain.doFilter(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Ocorreu um erro no sistema, avise ao administrador: \n" +e.getMessage());
		}
	}

}
