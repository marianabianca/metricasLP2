package extratoresDeMetricas;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entidades.Projeto;

public class ExtratorDeMetodos implements ExtratorDeMetricas{

	public void extrairMetricas(Map<String, Projeto> projetos, String path) {
		Set<String> nomesProjetos = projetos.keySet();
		
		for (String nomeProjeto : nomesProjetos) {
			Projeto projeto = projetos.get(nomeProjeto);
			this.atualizarNumDeMetodosProjeto(projeto, path);
		}
	}
	
	private void atualizarNumDeMetodosProjeto(Projeto projeto, String path) {
		String nomeProjeto = projeto.getNome();
		String pathProjeto = criarPath(path, nomeProjeto);
		int numeroDeMetodosDoProjeto = pegarNumTestesProjeto(pathProjeto);
		projeto.setNumeroDeMetodos(numeroDeMetodosDoProjeto);
	}
	
	private String criarPath(String path, String elemento) {
		String retorno = path + File.separator + elemento;
		return retorno;
	}
	
	private int pegarNumTestesProjeto(String path) {
		int numeroDeTestesDoProjeto = 0;
		String[] conteudoDoDiretorio = pegarConteudoDoDiretorio(path);
		
		if (conteudoDoDiretorio != null && conteudoDoDiretorio.length > 0) {
			numeroDeTestesDoProjeto += numeroDeTestesDoDiretorio(path, conteudoDoDiretorio);
			
			for (String elemento : conteudoDoDiretorio) {
				String auxPath = criarPath(path, elemento);
				
				if (new File(auxPath).isDirectory()) {
					numeroDeTestesDoProjeto += pegarNumTestesProjeto(auxPath);
				}
			}
		}
		
		return numeroDeTestesDoProjeto;
	}
	
	private String[] pegarConteudoDoDiretorio(String pathInicial) {
		File diretorioInicial = new File(pathInicial);
		String[] elementosDiretorio = diretorioInicial.list();
		 
		return elementosDiretorio;
	}
	
	public int numeroDeTestesDoDiretorio(String diretorio, String[] conteudo) {
		int numTestes = 0;
		
		for (String elemento : conteudo) {
			if (elemento.toLowerCase().endsWith(".java")) {
				File file = new File(diretorio + File.separator + elemento);
				numTestes += this.contarNumeroDeTestesClasse(0, file);
			}
		}
		
		return numTestes;
	}
	
	private int contarNumeroDeTestesClasse(int numeroDeTestes, File file) {
		List<String> linhas = new ArrayList<>();
		String regex = "[^=]*\\s*[^=]+\\s+[^=]+[(][^=]*[)]?\\s*[{]?\\s*[}]?";
		
		try {
			linhas = Files.readAllLines(file.toPath(),  StandardCharsets.ISO_8859_1);
			
			for (String linha: linhas) {
				if (linha.matches(regex)) {
					numeroDeTestes += 1;
				}
			}
			
			return numeroDeTestes;			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return 0;
	}

}
