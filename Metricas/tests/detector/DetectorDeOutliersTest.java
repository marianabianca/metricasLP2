package detector;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidades.Projeto;

public class DetectorDeOutliersTest {

	private static final String LS = System.lineSeparator();
	DetectorDeOutliers detector;
	
	@Before
	public void setup() {
		detector = new DetectorDeOutliers();
	}
	
	@Test
	public void semVariacaoTest() {
		Map<String, Projeto> projetos = new HashMap<>();
		
		for (int i = 0; i <= 20; i++) {
			String nomeProjeto = Integer.toString(i);
			Projeto projeto = new Projeto(nomeProjeto);
			projeto.setNumeroDeClasses(5);
			projeto.setNumeroDeLinhas(5);
			projetos.put(nomeProjeto, projeto);
		}		
		
		String outliers = detector.imprimeOutliers(projetos);
		String mensagem = "Metrica: Classes" + LS +
				"Mediana: 5.0" + LS +
				"Desvio Absoluto Mediano: 6.26657" + LS + LS +
				"Outliers positivos: " + LS +
				"Outliers negativos: " + LS + LS + LS +
				"------------------------------" + LS + LS;
		mensagem += "Metrica: Linhas" + LS +
				"Mediana: 5.0" + LS +
				"Desvio Absoluto Mediano: 6.26657" + LS + LS +
				"Outliers positivos: " + LS +
				"Outliers negativos: " + LS + LS + LS +
				"------------------------------" + LS + LS;
				
		Assert.assertEquals(mensagem, outliers);
	}
	
	@Test
	public void comOutlierPosivitoTest() {
		Map<String, Projeto> projetos = new HashMap<>();
		
		for (int i = 0; i <= 20; i++) {
			String nomeProjeto = Integer.toString(i);
			Projeto projeto = new Projeto(nomeProjeto);
			projeto.setNumeroDeClasses(2);
			projeto.setNumeroDeLinhas(2);
			projetos.put(nomeProjeto, projeto);
		}
		
		Projeto projeto = new Projeto("outlier positivo");
		projeto.setNumeroDeClasses(16);
		projeto.setNumeroDeLinhas(16);
		projetos.put("outlier positivo", projeto);
		
		String outliers = detector.imprimeOutliers(projetos);
		String mensagem = "Metrica: Classes" + LS +
				"Mediana: 2.0" + LS +
				"Desvio Absoluto Mediano: 2.506628" + LS + LS +
				"Outliers positivos: " + LS +
				"outlier positivo" + LS +
				"Outliers negativos: " + LS + LS + LS +
				"------------------------------" + LS + LS;
		mensagem += "Metrica: Linhas" + LS +
				"Mediana: 2.0" + LS +
				"Desvio Absoluto Mediano: 2.506628" + LS + LS +
				"Outliers positivos: " + LS +
				"outlier positivo" + LS +
				"Outliers negativos: " + LS + LS + LS +
				"------------------------------" + LS + LS;
		
		Assert.assertEquals(mensagem, outliers);
	}
	
	@Test
	public void comOutlierNegativoTest() {
		Map<String, Projeto> projetos = new HashMap<>();
		
		for (int i = 0; i <= 20; i++) {
			String nomeProjeto = Integer.toString(i);
			Projeto projeto = new Projeto(nomeProjeto);
			projeto.setNumeroDeClasses(2);
			projeto.setNumeroDeLinhas(2);
			projetos.put(nomeProjeto, projeto);
		}
		
		Projeto projeto = new Projeto("outlier negativo");
		projeto.setNumeroDeClasses(-12); // Nao faz sentido
		projeto.setNumeroDeLinhas(-12); // Nao faz sentido
		projetos.put("outlier negativo", projeto);
		
		String outliers = detector.imprimeOutliers(projetos);
		String mensagem = "Metrica: Classes" + LS +
				"Mediana: 2.0" + LS +
				"Desvio Absoluto Mediano: 2.506628" + LS + LS +
				"Outliers positivos: " + LS +
				"Outliers negativos: " + LS +
				"outlier negativo" + LS + 
				LS + LS +
				"------------------------------" + LS + LS;
		mensagem += "Metrica: Linhas" + LS +
				"Mediana: 2.0" + LS +
				"Desvio Absoluto Mediano: 2.506628" + LS + LS +
				"Outliers positivos: " + LS +
				"Outliers negativos: " + LS +
				"outlier negativo" + LS + 
				LS + LS +
				"------------------------------" + LS + LS;

		Assert.assertEquals(mensagem, outliers);
	}
	
}
