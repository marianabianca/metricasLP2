package extratoresDeMetricas;

import java.util.Map;

import entidades.Projeto;

public interface ExtratorDeMetricas {
	
	public void extrairMetricas(Map<String, Projeto> projetos, String path);

}
