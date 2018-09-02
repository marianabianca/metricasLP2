package extratoresDeMetricas;

import java.io.File;
import java.util.Map;
import java.util.Set;

import entidades.Projeto;
/**
 * Classe que implementa o Extrator de M�tricas e � repons�vel pela contagem do n�mero
 * de classes de cada projeto analisado.
 */
public class ExtratorDeClasses implements ExtratorDeMetricas {
	
	/**
	 * M�todo que extrai o n�mero de classes dos projetos.
	 * 
	 * @param projetos - Projetos que ser�o medidos
	 * @param path - Caminho at� a pasta dos projetos
	 */
	public void extrairMetricas(Map<String, Projeto> projetos, String path) {
		Set<String> nomesProjetos = projetos.keySet();
		
		for (String nomeProjeto : nomesProjetos) {
			Projeto projeto = projetos.get(nomeProjeto);
			this.atualizarNumDeClassesProjeto(projeto, path);
		}
	}
	
	/**
	 * M�todo que atualiza o n�mero de classes de um projeto a partir do seu path.
	 *
	 * @param projeto - O Projeto que ter� seu n�mero de classes atualizado
	 * @param path - Caminho at� o diret�rio dos projetos
	 */
	private void atualizarNumDeClassesProjeto(Projeto projeto, String path) {
		String nomeProjeto = projeto.getNome();
		String pathProjeto = criarPath(path, nomeProjeto);
		int numDeClasses = pegarNumClassesProjeto(pathProjeto);
		projeto.setNumeroDeClasses(numDeClasses);
	}
	
	/**
	 * M�todo que retorna o caminho at� um determinado projeto a partir da 
	 * concatena��o do caminho at� o diret�rio que cont�m todos os projetos 
	 * e o nome do projeto destino.
	 * 
	 * @param path - Caminho at� o diret�rio dos projetos
	 * @param elemento - Nome do projeto
	 * @return String - Caminho at� o projeto
	 */
	private String criarPath(String path, String elemento) {
		String retorno = path + File.separator + elemento;
		return retorno;
	}
	
	/**
	 * M�todo recursivo para retorno do n�mero de classes de um projeto a partir do seu path.
	 * O m�todo percorre a �rvore de diret�rios de um projeto recursivamente e somando a quantidade
	 * classes de cada diret�rio interno ao total.
	 * 
	 * @param path - Caminho at� o projeto
	 * @return int - N�mero de classes do projeto
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
	 * M�todo que retorna uma lista com o conte�do de um diret�rio a partir do seu path.
	 * 
	 * @param pathInicial - Caminho at� o diret�rio mais externo do projeto
	 * @return String[] - Conte�do do diret�rio
	 */
	private String[] pegarConteudoDoDiretorio(String pathInicial) {
		File diretorioInicial = new File(pathInicial);
		String[] elementosDiretorio = diretorioInicial.list();
		
		return elementosDiretorio;
	}
	
	/**
	 * M�todo que extrai o n�mero de classes de um diret�rio a partir da representa��o
	 * em string do seu conte�do, buscando os arquivos com extens�o .java(classes).
	 * 
	 * @param conteudo - Arranjo com o nomes de cada objeto de um diret�rio
	 * @return int - O n�mero das classes do diret�rio
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