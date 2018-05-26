package controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import detector.DetectorDeOutliers;
import entidades.Projeto;
import extratoresDeMetricas.ExtratorDeMetricas;
import extratoresDeMetricas.FactoryExtrator;

public class ControllerProjetos {
	private static final String PATH_PROJETOS = "Projetos";
	private Map<String, Projeto> projetos;
	private DetectorDeOutliers detectorDeOutliers;
	private ExtratorDeMetricas[] extratoresDeMetricas;
	
	public ControllerProjetos() {
		projetos = new HashMap<>();
		detectorDeOutliers = new DetectorDeOutliers();
		
		FactoryExtrator factoryExtrator = new FactoryExtrator();
		extratoresDeMetricas = factoryExtrator.getExtratores();
		
		this.inicializarSistema();
	}
	
	private void inicializarSistema() {
		this.adicionarProjetosAoController();
		this.extrairMetricas();
	}
	
	private void adicionarProjetosAoController() {
		String[] nomesProjetos = this.pegarNomesProjetos();
		
		for (String nomeProjeto : nomesProjetos) {
			Projeto projeto = new Projeto(nomeProjeto);
			this.projetos.put(nomeProjeto, projeto);
		}
	}
	
	private String[] pegarNomesProjetos() {
		File diretorioInicial = new File(PATH_PROJETOS);
		String[] nomesProjetos = diretorioInicial.list();
		
		return nomesProjetos;
	}
	
	private void extrairMetricas() {
		for (ExtratorDeMetricas extrator: extratoresDeMetricas) {
			extrator.extrairMetricas(projetos, PATH_PROJETOS);
		}
	}
	
	public String detectarOutliers() {
		String outliers = detectorDeOutliers.imprimeOutliers(projetos);
		
		return outliers;
	}
}
