package extratoresDeMetricas;

import java.io.File;
import java.util.Map;
import java.util.Set;

import entidades.Projeto;

public class ExtratorDeClasses implements ExtratorDeMetricas {
	
	public void extrairMetricas(Map<String, Projeto> projetos, String path) {
		Set<String> nomesProjetos = projetos.keySet();
		
		for (String nomeProjeto : nomesProjetos) {
			Projeto projeto = projetos.get(nomeProjeto);
			this.atualizarNumDeClassesProjeto(projeto, path);
		}
	}
	
	private void atualizarNumDeClassesProjeto(Projeto projeto, String path) {
		String nomeProjeto = projeto.getNome();
		String pathProjeto = criarPath(path, nomeProjeto);
		int numDeClasses = pegarNumClassesProjeto(pathProjeto);
		projeto.setNumeroDeClasses(numDeClasses);
	}
	
	private String criarPath(String path, String elemento) {
		String retorno = path + File.separator + elemento;
		return retorno;
	}
	
	private int pegarNumClassesProjeto(String path) {
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
	
	private String[] pegarConteudoDoDiretorio(String pathInicial) {
		File diretorioInicial = new File(pathInicial);
		String[] elementosDiretorio = diretorioInicial.list();
		
		return elementosDiretorio;
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
}