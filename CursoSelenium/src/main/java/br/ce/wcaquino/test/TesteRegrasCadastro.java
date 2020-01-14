package br.ce.wcaquino.test;

import static br.ce.wcaquino.core.DriverFactory.getDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.page.CampoTreinamentoPage;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro extends BaseTest {

	private DSL dsl;
	private CampoTreinamentoPage page;

	@Parameter
	public String nome;
	@Parameter(value = 1)
	public String sobreNome;
	@Parameter(value = 2)
	public String sexo;
	@Parameter(value = 3)
	public List<String> comidas;
	@Parameter(value = 4)
	public String[] esportes;
	@Parameter(value = 5)
	public String msg;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CampoTreinamentoPage();
	}

	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] { { "", "", "", Arrays.asList(), new String[] {}, "Nome eh obrigatorio" },
				{ "Gustavo", "", "", Arrays.asList(), new String[] {}, "Sobrenome eh obrigatorio" },
				{ "Gustavo", "Alves", "", Arrays.asList(), new String[] {}, "Sexo eh obrigatorio" },
				{ "Gustavo", "Alves", "Masculino", Arrays.asList("Carne", "Vegetariana"), new String[] {},
						"Tem certeza que voce eh vegetariano?" },
				{ "Gustavo", "Alves", "Masculino", Arrays.asList("Carne"),
						new String[] { "Karate", "O que eh esporte?" }, "Voce faz esporte ou nao?" } });
	}

	@Test
	public void deveValidarRegras() {
		page.setNome(nome);
		page.setSobreNome(sobreNome);
		if (sexo.equals("Masculino")) {
			page.setSexoMasculino();
		} else if (sexo.equals("Feminino")) {
			page.setSexoFeminino();
		}
		if (comidas.contains("Carne"))
			page.setComidaCarne();
		if (comidas.contains("Pizza"))
			page.setComidaPizza();
		if (comidas.contains("Vegetariana"))
			page.setComidaVegetariana();
		page.setEsporte(esportes);
		page.cadastrar();
		Assert.assertEquals(msg, dsl.alertaObterTextoEAceita());
	}
}
