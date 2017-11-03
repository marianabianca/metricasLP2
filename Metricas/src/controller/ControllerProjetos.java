package controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import detector.DetectorDeOutliers;
import entidades.Projeto;

public class ControllerProjetos {
	static final String PATH = "Projetos";
	private Map<String, Projeto> projetos;
	private DetectorDeOutliers detectorDeOutliers;
	
	public ControllerProjetos() {
		projetos = new HashMap<>();
		detectorDeOutliers = new DetectorDeOutliers();
		
		this.inicializarSistema();
	}
	
	public String detectarOutliers() {
		String outliers = detectorDeOutliers.detectarOutliers(projetos);
		
		return outliers;
	}

	private void inicializarSistema() {
		this.adicionaProjetosAoController();
		this.atualizaNumDeClassesDosProjetos();
	}

	private void adicionaProjetosAoController() {
		File diretorioInicial = new File(ControllerProjetos.PATH);
		
		String[] nomesProjetos = diretorioInicial.list();
		
		for (String nomeProjeto : nomesProjetos) {
			Projeto projeto = new Projeto(nomeProjeto);
			this.projetos.put(nomeProjeto, projeto);
		}		
	}
	
	private void atualizaNumDeClassesDosProjetos() {
		for (String nomeProjeto : projetos.keySet()) {
			int numeroDeClasses = numeroDeClassesDoProjeto(ControllerProjetos.PATH + File.separator + nomeProjeto);
			
			Projeto projeto = projetos.get(nomeProjeto);
			projeto.setNumeroDeClasse(numeroDeClasses);
		}
	}
	
	private static int numeroDeClassesDoProjeto(String path) {
		int numeroDeClassesDoProjeto = 0;
		String[] conteudoDoDiretorio = pegarConteudoDoDiretorio(path);
		
		if (conteudoDoDiretorio != null && conteudoDoDiretorio.length > 0) {
			numeroDeClassesDoProjeto += numeroDeClassesDoDiretorio(conteudoDoDiretorio);
			
			for (String elemento : conteudoDoDiretorio) {
				String auxPath = path + File.separator + elemento;
				
				if (new File(auxPath).isDirectory()) {
					numeroDeClassesDoProjeto += numeroDeClassesDoProjeto(auxPath);
				}
			}
		}
		
		return numeroDeClassesDoProjeto;
	}
	
	public static int numeroDeClassesDoDiretorio(String[] conteudo) {
		int numClasses = 0;
		
		for (String elemento : conteudo) {
			elemento = elemento.toLowerCase();
			if (elemento.endsWith(".java")) {
				numClasses += 1;
			}
		}
		
		return numClasses;
	}
	
	private static String[] pegarConteudoDoDiretorio(String pathInicial) {
		File diretorioInicial = new File(pathInicial);
		String[] packages = diretorioInicial.list();
		
		return packages;
	}

}
