package obtedorDeMetricas;

import java.util.Map;

import entidades.Metrica;
import entidades.Projeto;

public class ObtedorDeTestes implements ObtedorDeMetricas {

	public Metrica obterMetricas(Map<String, Projeto> projetos) {
		Metrica metrica = this.testesPorProjeto(projetos);
		return metrica;
	}
	
	private Metrica testesPorProjeto(Map<String, Projeto> projetos) {
		Metrica metrica = new Metrica("Testes");
		
		for (String nomeDoProjeto : projetos.keySet()) {
			Projeto projeto = projetos.get(nomeDoProjeto);
			double numeroDeTestesDoProjeto = projeto.getNumeroDeTestes();
			metrica.adicionarElemento(nomeDoProjeto, numeroDeTestesDoProjeto);
		}
		
		return metrica;
	}

}
