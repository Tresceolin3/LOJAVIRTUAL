package dev.ceolin.lojavirtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.ceolin.lojavirtual.model.Acesso;
import dev.ceolin.lojavirtual.repository.AcessoRepository;
import dev.ceolin.lojavirtual.service.AcessoService;

@Controller
@RestController
public class AcessoController {

	@Autowired
	private AcessoService acessoService;

	@Autowired
	private AcessoRepository acessoRepository;
	
	
	@ResponseBody /* Retorno da api */
	@PostMapping(value = "**/salvarAcesso") /* Mapeando url pra receber JSON */
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) {

		Acesso acessoSalvo = acessoService.save(acesso);

		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
	}

	
	@ResponseBody /* Retorno da api */
	@PostMapping(value = "**/deleteAcesso") /* Mapeando url pra receber JSON */
	public ResponseEntity<Acesso> deleteAcesso(@RequestBody Acesso acesso) {

			acessoRepository.deleteById(acesso.getId());

		return new ResponseEntity(HttpStatus.OK);
	}
	
	
}
