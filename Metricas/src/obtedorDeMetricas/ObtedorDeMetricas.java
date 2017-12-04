package obtedorDeMetricas;

import java.util.Map;

import entidades.Metrica;
import entidades.Projeto;

public interface ObtedorDeMetricas {
	
	public Metrica obterMetricas(Map<String, Projeto> projetos);
	
}
