package controller;

import java.io.File;
import java.io.IOException;
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
		
		try {
			this.inicializarSistema();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inicializa o sistema.
	 * @throws IOException 
	 */
	private void inicializarSistema() throws IOException {
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
	 * @throws IOException 
	 */
	private void extrairMetricas() {
		for (ExtratorDeMetricas extrator: extratoresDeMetricas) {
			extrator.extrairMetricas(projetos, PATH_PROJETOS);
		}
	}
	
	/**
	 * Detecta os outliers presentes na lista de projetos.
	 * @return String final que apresenta todos os outliers encontrados.
	 * @throws IOException 
	 */
	public String detectarOutliers() throws IOException {
		String outliers = detectorDeOutliers.imprimeOutliers(projetos);
		detectorDeOutliers.gerarOutliersCsv(projetos);
		
		return outliers;
	}
}