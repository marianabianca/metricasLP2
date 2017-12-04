package obtedorDeMetricas;

import java.util.Map;

import entidades.Metrica;
import entidades.Projeto;

public class ObtedorDeClasses implements ObtedorDeMetricas {

	public Metrica obterMetricas(Map<String, Projeto> projetos) {
		Metrica metrica = this.classesPorProjeto(projetos);
		return metrica;
	}
	
	private Metrica classesPorProjeto(Map<String, Projeto> projetos) {
		Metrica metrica = new Metrica("Classes");
		
		for (String nomeDoProjeto : projetos.keySet()) {
			Projeto projeto = projetos.get(nomeDoProjeto);
			double numeroDeClassesDoProjeto = projeto.getNumeroDeClasses();
			
			metrica.adicionarElemento(nomeDoProjeto, numeroDeClassesDoProjeto);
		}
		
		return metrica;
	}

}
