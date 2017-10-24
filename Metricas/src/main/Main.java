package main;

import java.io.File;

public class Main {
	
	public static void main(String[] args) {
		int numClasses = numeroTotalDeClasses("src");
		
		System.out.println("O projeto contem " + numClasses + " classes");
	}
	
	
	/**
	 * Retorna o numero total de classes de um projeto
	 * 
	 * @param path path de onde comeca a busca por classes
	 * @return o numero total de classes de um projeto
	 */
	public static int numeroTotalDeClasses(String path) {
		int numClasses = 0;
		String[] conteudo = encontrarConteudo(path);
		
		if (conteudo != null && conteudo.length > 0) {
			numClasses += numeroDeClasses(conteudo);
			
			for (String elemento : conteudo) {
				String auxPath = path + File.separator + elemento;
				
				if (new File(auxPath).isDirectory()) {
					numClasses += numeroTotalDeClasses(auxPath);
				}
			}
		}
		
		return numClasses;
	}
	
	/**
	 * Encontra o conteudo de um diretorio
	 * 
	 * @param pathInicial path do diretorio
	 * @return String[] com o nome de cada arquivo e/ou diretorio
	 */
	public static String[] encontrarConteudo(String pathInicial) {
		File diretorioInicial = new File(pathInicial);
		String[] packages = diretorioInicial.list();
		
		return packages;
	}
	
	
	/**
	 * Diz o número de classes java que um diretorio contem
	 * 
	 * @param conteudo String[] com o nome de cada arquivo e/ou diretorio contido naquele diretorio
	 * @return numero de classes do diretorio
	 */
	public static int numeroDeClasses(String[] conteudo) {
		int numClasses = 0;
		
		for (String elemento : conteudo) {
			if (elemento.contains(".java")) {
				numClasses += 1;
			}
		}
		
		return numClasses;
	}

}
