package dev.ceolin.lojavirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ceolin.lojavirtual.repository.UsuarioRepository;

@Service
public class PessoaUserService {
		
		@Autowired
		private UsuarioRepository usuarioRepository;
}
