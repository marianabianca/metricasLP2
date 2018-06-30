package obtedorDeMetricas;

import java.util.Map;

import entidades.Metrica;
import entidades.Projeto;

public class ObtedorDeComplexidadeCiclomaticaMaxima implements ObtedorDeMetricas {

	@Override
	public Metrica obterMetricas(Map<String, Projeto> projetos) {
		Metrica metrica = this.complexidadeCiclomaticaMaximaPorProjeto(projetos);
		return metrica;
	}

	private Metrica complexidadeCiclomaticaMaximaPorProjeto(Map<String, Projeto> projetos) {
Metrica metrica = new Metrica("Complexidade Ciclomática Máxima");
		
		for (String nomeDoProjeto : projetos.keySet()) {
			Projeto projeto = projetos.get(nomeDoProjeto);
			double maximaComplexidadeCiclomatica = projeto.getMaxComplexidadeCiclomatica();
			
			metrica.adicionarElemento(nomeDoProjeto, maximaComplexidadeCiclomatica);
		}
		
		return metrica;
	}

}
