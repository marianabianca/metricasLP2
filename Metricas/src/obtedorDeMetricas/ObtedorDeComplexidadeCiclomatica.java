package obtedorDeMetricas;

import java.util.Map;

import entidades.Metrica;
import entidades.Projeto;

public class ObtedorDeComplexidadeCiclomatica implements ObtedorDeMetricas {
	
	public Metrica obterMetricas(Map<String, Projeto> projetos) {
		Metrica metrica = this.complexidadeCiclomaticaPorProjeto(projetos);
		return metrica;
	}
	
	private Metrica complexidadeCiclomaticaPorProjeto(Map<String, Projeto> projetos) {
		Metrica metrica = new Metrica("Complexidade Ciclomática");
		
		for (String nomeDoProjeto : projetos.keySet()) {
			Projeto projeto = projetos.get(nomeDoProjeto);
			double complexidadeCiclomatica = projeto.getComplexidadeCiclomatica();
			
			metrica.adicionarElemento(nomeDoProjeto, complexidadeCiclomatica);
		}
		
		return metrica;
	}

}
