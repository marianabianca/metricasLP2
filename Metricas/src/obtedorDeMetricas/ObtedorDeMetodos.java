package obtedorDeMetricas;

import java.util.Map;

import entidades.Metrica;
import entidades.Projeto;

public class ObtedorDeMetodos implements ObtedorDeMetricas {

	public Metrica obterMetricas(Map<String, Projeto> projetos) {
		Metrica metrica = this.metodosPorProjeto(projetos);
		return metrica;
	}
	
	private Metrica metodosPorProjeto(Map<String, Projeto> projetos) {
		Metrica metrica = new Metrica("Metodos");
		
		for (String nomeDoProjeto : projetos.keySet()) {
			Projeto projeto = projetos.get(nomeDoProjeto);
			double numeroDeMetodosDoProjeto = projeto.getNumeroDeMetodos();
			
			metrica.adicionarElemento(nomeDoProjeto, numeroDeMetodosDoProjeto);
		}
		
		return metrica;
	}

}
