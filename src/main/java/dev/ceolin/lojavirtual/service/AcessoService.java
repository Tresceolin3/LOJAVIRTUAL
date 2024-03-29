package dev.ceolin.lojavirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ceolin.lojavirtual.model.Acesso;
import dev.ceolin.lojavirtual.repository.AcessoRepository;

@Service
public class AcessoService {
	@Autowired	
	private AcessoRepository acessoRepository;
		
	public Acesso save(Acesso acesso) {
		return acessoRepository.save(acesso);
	}
	
	
}
