package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import detector.DetectorDeOutliers;
import entidades.Projeto;

public class ControllerProjetos {
	private static final String SEPARATOR = File.separator;
	static final String PATH_PROJETOS = "Projetos";
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
		this.adicionarProjetosAoController();
		this.atualizarNumDeClassesDosProjetos();
	}
	
	private String[] pegarNomesProjetos() {
		File diretorioInicial = new File(PATH_PROJETOS);
		String[] nomesProjetos = diretorioInicial.list();
		
		return nomesProjetos;
	}

	private void adicionarProjetosAoController() {
		String[] nomesProjetos = this.pegarNomesProjetos();
		
		for (String nomeProjeto : nomesProjetos) {
			Projeto projeto = new Projeto(nomeProjeto);
			this.projetos.put(nomeProjeto, projeto);
		}
	}
	
	private void atualizarNumDeClassesDosProjetos() {
		Set<String> nomesProjetos = projetos.keySet();
		
		for (String nomeProjeto : nomesProjetos) {
			this.atualizarNumDeClassesProjeto(nomeProjeto);
		}
	}
	
	private void atualizarNumDeClassesProjeto(String nomeProjeto) {
		String pathProjeto = gerarPathDoProjeto(nomeProjeto);
		int numDeClasses = pegarNumClassesProjeto(pathProjeto);
		int numDeLinhas = pegarNumLinhasProjeto(pathProjeto);
		Projeto projeto = projetos.get(nomeProjeto);
		projeto.atualizarNumeroDeClasses(numDeClasses);
		projeto.atualizarNumeroDeLinhas(numDeLinhas);
	}

	private String gerarPathDoProjeto(String nomeProjeto) {
		String pathProjeto = PATH_PROJETOS + SEPARATOR + nomeProjeto;
		return pathProjeto;
	}

	private static int pegarNumClassesProjeto(String path) {
		int numeroDeClassesDoProjeto = 0;
		String[] conteudoDoDiretorio = pegarConteudoDoDiretorio(path);
		
		if (conteudoDoDiretorio != null && conteudoDoDiretorio.length > 0) {
			numeroDeClassesDoProjeto += numeroDeClassesDoDiretorio(conteudoDoDiretorio);
			
			for (String elemento : conteudoDoDiretorio) {
				String auxPath = criarPath(path, elemento);
				
				if (new File(auxPath).isDirectory()) {
					numeroDeClassesDoProjeto += pegarNumClassesProjeto(auxPath);
				}
			}
		}
		
		return numeroDeClassesDoProjeto;
	}

	private static int pegarNumLinhasProjeto(String path) {
		int numeroDeClassesDoProjeto = 0;
		String[] conteudoDoDiretorio = pegarConteudoDoDiretorio(path);
		
		if (conteudoDoDiretorio != null && conteudoDoDiretorio.length > 0) {
			numeroDeClassesDoProjeto += numeroDeLinhasDoDiretorio(path, conteudoDoDiretorio);
			
			for (String elemento : conteudoDoDiretorio) {
				String auxPath = criarPath(path, elemento);
				
				if (new File(auxPath).isDirectory()) {
					numeroDeClassesDoProjeto += pegarNumLinhasProjeto(auxPath);
				}
			}
		}
		
		return numeroDeClassesDoProjeto;
	}
	
	public static int numeroDeLinhasDoDiretorio(String diretorio, String[] conteudo) {
		int numClasses = 0;
		
		for (String elemento : conteudo) {
			if (elemento.toLowerCase().endsWith(".java")) {
				try {
					numClasses += Files.lines(new File(diretorio + File.separator + elemento).toPath()).count();
				} catch (IOException ioe) {
					//throw new RuntimeException(ioe);
					ioe.printStackTrace();
					return 0;
				}
			}
		}
		
		return numClasses;
	}
	
	private static String criarPath(String path, String elemento) {
		String retorno = path + SEPARATOR + elemento;
		return retorno;
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
		String[] elementosDiretorio = diretorioInicial.list();
		
		return elementosDiretorio;
	}

}
