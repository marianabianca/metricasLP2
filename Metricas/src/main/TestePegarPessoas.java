package main;

import java.io.File;
import java.util.HashMap;

public class TestePegarPessoas {
	
	private static HashMap<String, Integer> classesAlunos = new HashMap<>(); 
	
	public static void main(String[] args) {
		String pathname = "Projetos";
		
		adicionaAlunos(pathname);
		
		NumDeClassesAlunos(pathname);
		
		for (String aluno : classesAlunos.keySet()) {
			System.out.println(aluno + ": " + classesAlunos.get(aluno));
		}
	}

	/**
	 * Esse método atualiza o numero de classes de cada aluni no HashMa classesAlunos
	 * 
	 * @param pathname path para a pasta que se encontram os projetos
	 */
	private static void NumDeClassesAlunos(String pathname) {
		for (String aluno : classesAlunos.keySet()) {
			int numClasses = numeroTotalDeClasses(pathname + File.separator + aluno);
			
			classesAlunos.replace(aluno, numClasses);			
		}
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

	/**
	 * Esse método adiciona os nomes dos alunos ao HashMap classesAlunos
	 * @param pathname o path para a pasta onde se encontram os projetos dos alunos
	 */
	private static void adicionaAlunos(String pathname) {
		File diretorioInicial = new File(pathname);
		
		String[] alunos = diretorioInicial.list();
		
		for (String aluno : alunos) {
			classesAlunos.put(aluno, 0);
		}
	}

//	private static FilenameFilter criaFiltro() {
//		FilenameFilter classFilter = new FilenameFilter() {
//			
//			@Override
//			public boolean accept(File dir, String name) {
//				name = name.toLowerCase();
//				if (name.endsWith(".java")) {
//					return true;
//				} else {
//					return false;
//				}
//			}
//		};
//		
//		return classFilter;
//	}

}
