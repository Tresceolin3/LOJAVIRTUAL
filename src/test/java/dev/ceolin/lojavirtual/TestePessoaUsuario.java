package dev.ceolin.lojavirtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import dev.ceolin.lojavirtual.model.PessoaJuridica;
import dev.ceolin.lojavirtual.repository.PessoaRepository;
import dev.ceolin.lojavirtual.service.PessoaUserService;
import junit.framework.TestCase;

@Profile("teste")
@SpringBootTest(classes = LojavirtualApplication.class)
public class TestePessoaUsuario extends TestCase {

	@Autowired
	private PessoaUserService pessoaUserService;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Test
	public void testCadPessoa() {

		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setCnpj("12399000190");
		pessoaJuridica.setNome("Gabriel");
		pessoaJuridica.setEmail("gabriel@gmail.com");
		pessoaJuridica.setTelefone("67912391293");
		pessoaJuridica.setInscEstadual("1212312312312");
		pessoaJuridica.setInscMunicipal("12312897391273");
		pessoaJuridica.setNomeFantasia("MonkeyFruta");
		pessoaJuridica.setRazaoSocial("1231231223");

		pessoaRepository.save(pessoaJuridica);

		/*
		 * PessoaFisica pessoaFisica = new PessoaFisica();
		 * 
		 * pessoaFisica.setCpf("098391293"); pessoaFisica.setNome("Gabriel");
		 * pessoaFisica.setEmail("gabriel@gmail.com");
		 * pessoaFisica.setTelefone("67912391293");
		 * 
		 * pessoaFisica.setEmpresa(pessoaFisica);
		 * 
		 */

	}

}
