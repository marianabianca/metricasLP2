package extratoresDeMetricas;

import java.io.File;
import java.util.Map;
import java.util.Set;

import entidades.Projeto;
/**
 * Classe que implementa o Extrator de Métricas e é reponsável pela contagem do número
 * de classes de cada projeto analisado.
 */
public class ExtratorDeClasses implements ExtratorDeMetricas {
	
	/**
	 * Método que extrai o número de classes dos projetos.
	 * 
	 * @param projetos - Projetos que serão medidos
	 * @param path - Caminho até a pasta dos projetos
	 */
	public void extrairMetricas(Map<String, Projeto> projetos, String path) {
		Set<String> nomesProjetos = projetos.keySet();
		
		for (String nomeProjeto : nomesProjetos) {
			Projeto projeto = projetos.get(nomeProjeto);
			this.atualizarNumDeClassesProjeto(projeto, path);
		}
	}
	
	/**
	 * Método que atualiza o número de classes de um projeto a partir do seu path.
	 *
	 * @param projeto - O Projeto que terá seu número de classes atualizado
	 * @param path - Caminho até o diretório dos projetos
	 */
	private void atualizarNumDeClassesProjeto(Projeto projeto, String path) {
		String nomeProjeto = projeto.getNome();
		String pathProjeto = criarPath(path, nomeProjeto);
		int numDeClasses = pegarNumClassesProjeto(pathProjeto);
		projeto.setNumeroDeClasses(numDeClasses);
	}
	
	/**
	 * Método que retorna o caminho até um determinado projeto a partir da 
	 * concatenação do caminho até o diretório que contém todos os projetos 
	 * e o nome do projeto destino.
	 * 
	 * @param path - Caminho até o diretório dos projetos
	 * @param elemento - Nome do projeto
	 * @return String - Caminho até o projeto
	 */
	private String criarPath(String path, String elemento) {
		String retorno = path + File.separator + elemento;
		return retorno;
	}
	
	/**
	 * Método recursivo para retorno do número de classes de um projeto a partir do seu path.
	 * O método percorre a árvore de diretórios de um projeto recursivamente e somando a quantidade
	 * classes de cada diretório interno ao total.
	 * 
	 * @param path - Caminho até o projeto
	 * @return int - Número de classes do projeto
	 */
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
	
	/**
	 * Método que retorna uma lista com o conteúdo de um diretório a partir do seu path.
	 * 
	 * @param pathInicial - Caminho até o diretório mais externo do projeto
	 * @return String[] - Conteúdo do diretório
	 */
	private String[] pegarConteudoDoDiretorio(String pathInicial) {
		File diretorioInicial = new File(pathInicial);
		String[] elementosDiretorio = diretorioInicial.list();
		
		return elementosDiretorio;
	}
	
	/**
	 * Método que extrai o número de classes de um diretório a partir da representação
	 * em string do seu conteúdo, buscando os arquivos com extensão .java(classes).
	 * 
	 * @param conteudo - Arranjo com o nomes de cada objeto de um diretório
	 * @return int - O número das classes do diretório
	 */
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