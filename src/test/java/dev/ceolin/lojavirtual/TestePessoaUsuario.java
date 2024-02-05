package dev.ceolin.lojavirtual;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import dev.ceolin.lojavirtual.controller.PessoaController;
import dev.ceolin.lojavirtual.model.PessoaJuridica;
import junit.framework.TestCase;

@Profile("teste")
@SpringBootTest(classes = LojavirtualApplication.class)
public class TestePessoaUsuario extends TestCase {
	
	@Autowired
	private PessoaController pessoaController;
	

	@Test
	public void testCadPessoa() throws ExceptionDevJava{

		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setCnpj(" "+ Calendar.getInstance().getTimeInMillis());
		pessoaJuridica.setNome("Gabriel");
		pessoaJuridica.setEmail("testesLevisk@gmail.com");
		pessoaJuridica.setTelefone("67912391293");
		pessoaJuridica.setInscEstadual("1212312312312");
		pessoaJuridica.setInscMunicipal("12312897391273");
		pessoaJuridica.setNomeFantasia("MonkeyFruta");
		pessoaJuridica.setRazaoSocial("1231231223");

		pessoaController.salvarPj(pessoaJuridica);

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
		//System.out.println(new BCryptPasswordEncoder().encode("123"));

	}

}
