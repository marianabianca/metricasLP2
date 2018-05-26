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
	
	/**
	 * Inicializa o sistema.
	 */
	private void inicializarSistema() {
		this.adicionarProjetosAoController();
		this.extrairMetricas();
	}
	
	/**
	 * Adiciona todos os projetos presentes no diretório
	 * de projetos ao Controller.
	 */
	private void adicionarProjetosAoController() {
		String[] nomesProjetos = this.pegarNomesProjetos();
		
		for (String nomeProjeto : nomesProjetos) {
			Projeto projeto = new Projeto(nomeProjeto);
			this.projetos.put(nomeProjeto, projeto);
		}
	}
	
	/**
	 * Extrai os nomes de todos os projetos do diretório.
	 * @return Array com os nomes de todos os projetos
	 */
	private String[] pegarNomesProjetos() {
		File diretorioInicial = new File(PATH_PROJETOS);
		String[] nomesProjetos = diretorioInicial.list();
		
		return nomesProjetos;
	}
	
	/**
	 * Executa os extratores de métricas para todos os projetos
	 * existentes.
	 */
	private void extrairMetricas() {
		for (ExtratorDeMetricas extrator: extratoresDeMetricas) {
			extrator.extrairMetricas(projetos, PATH_PROJETOS);
		}
	}
	
	/**
	 * Detecta os outliers presentes na lista de projetos.
	 * @return String final que apresenta todos os outliers encontrados.
	 */
	public String detectarOutliers() {
		String outliers = detectorDeOutliers.imprimeOutliers(projetos);
		
		return outliers;
	}
}