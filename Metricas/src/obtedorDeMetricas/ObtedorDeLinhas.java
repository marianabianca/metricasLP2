package obtedorDeMetricas;

import java.util.Map;

import entidades.Metrica;
import entidades.Projeto;

public class ObtedorDeLinhas implements ObtedorDeMetricas {

	public Metrica obterMetricas(Map<String, Projeto> projetos) {
		Metrica metrica = this.linhasPorProjeto(projetos);
		return metrica;
	}
	
	private Metrica linhasPorProjeto(Map<String, Projeto> projetos) {
		Metrica metrica = new Metrica("Linhas");
		
		for (String nomeDoProjeto : projetos.keySet()) {
			Projeto projeto = projetos.get(nomeDoProjeto);
			double numeroDeLinhasDoProjeto = projeto.getNumeroDeLinhas();
			
			metrica.adicionarElemento(nomeDoProjeto, numeroDeLinhasDoProjeto);
		}
		
		return metrica;
	}

}
