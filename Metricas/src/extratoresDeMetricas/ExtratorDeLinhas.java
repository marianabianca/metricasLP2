package extratoresDeMetricas;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;
import java.util.Set;

import entidades.Projeto;

public class ExtratorDeLinhas implements ExtratorDeMetricas{

	public void extrairMetricas(Map<String, Projeto> projetos, String path) {
		Set<String> nomesProjetos = projetos.keySet();
		
		for (String nomeProjeto : nomesProjetos) {
			Projeto projeto = projetos.get(nomeProjeto);
			this.atualizarNumDeLinhasProjeto(projeto, path);
		}
	}
	
	private void atualizarNumDeLinhasProjeto(Projeto projeto, String path) {
		String nomeProjeto = projeto.getNome();
		String pathProjeto = criarPath(path, nomeProjeto);
		int numeroDeLinhasDoProjeto = pegarNumLinhasProjeto(pathProjeto);
		projeto.setNumeroDeLinhas(numeroDeLinhasDoProjeto);
	}
	
	private String criarPath(String path, String elemento) {
		String retorno = path + File.separator + elemento;
		return retorno;
	}
	
	private int pegarNumLinhasProjeto(String path) {
		int numeroDeLinhasDoProjeto = 0;
		String[] conteudoDoDiretorio = pegarConteudoDoDiretorio(path);
		
		if (conteudoDoDiretorio != null && conteudoDoDiretorio.length > 0) {
			numeroDeLinhasDoProjeto += numeroDeLinhasDoDiretorio(path, conteudoDoDiretorio);
			
			for (String elemento : conteudoDoDiretorio) {
				String auxPath = criarPath(path, elemento);
				
				if (new File(auxPath).isDirectory()) {
					numeroDeLinhasDoProjeto += pegarNumLinhasProjeto(auxPath);
				}
			}
		}
		
		return numeroDeLinhasDoProjeto;
	}
	
	private String[] pegarConteudoDoDiretorio(String pathInicial) {
		File diretorioInicial = new File(pathInicial);
		String[] elementosDiretorio = diretorioInicial.list();
		 
		return elementosDiretorio;
	}
	
	public int numeroDeLinhasDoDiretorio(String diretorio, String[] conteudo) {
		int numLinhas = 0;
		
		for (String elemento : conteudo) {
			if (elemento.toLowerCase().endsWith(".java")) {
				File file = new File(diretorio + File.separator + elemento);
				numLinhas += this.contarNumeroDeLinhasClasse(numLinhas, file);
			}
		}
		
		return numLinhas;
	}
	
	private int contarNumeroDeLinhasClasse(int numeroDeLinhas, File file) {
		try {
			numeroDeLinhas += Files.lines(file.toPath(), StandardCharsets.ISO_8859_1).count();
			return numeroDeLinhas;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return 0;
	}

}
