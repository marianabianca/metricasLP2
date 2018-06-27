package extratoresDeMetricas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import entidades.Projeto;

public class ExtratorDeCompexidadeCiclomatica implements ExtratorDeMetricas {

	private BufferedReader bufferedReader;

	/**
	 * Método que extrai a métrica de complexidade ciclomática do projeto
	 * 
	 * @param projetos - Projetos que serão medidos
	 * @param path - Caminho até a pasta dos projetos
	 */
	public void extrairMetricas(Map<String, Projeto> projetos, String path) {
		Set<String> nomesProjetos = projetos.keySet();

		for (String nomeProjeto : nomesProjetos) {
			Projeto projeto = projetos.get(nomeProjeto);
			this.atualizarComplexidadeCiclomatica(projeto, path);
		}
	}

	/**
	 * Método que atualiza a complexidade ciclomática média de um projeto.
	 * 
	 * @param projeto - Projeto que terá a sua complexidade atualizada
	 * @param path - Caminho até diretório dos projetos
	 */
	private void atualizarComplexidadeCiclomatica(Projeto projeto, String path) {
		String nomeProjeto = projeto.getNome();
		String pathProjeto = criarPath(path, nomeProjeto);
		String pathReport = buscaJavancss(pathProjeto);
		double complexidadeCiclomatica = pegarComplexidadeCiclomatica(pathReport);
		projeto.setComplexidadeCiclomatica(complexidadeCiclomatica);
	}

	/**
	 * REMOVER ISSO AQUI
	 * Método que busca o caminho do arquivo que contém o Java NCSS Report, 
	 * o qual possui a métrica de complexidade ciclomática
	 * 
	 * @param pathProjeto - Caminho até o diretório do projeto
	 * @return
	 */
	private String buscaJavancss(String pathProjeto) {
		String[] list = pegarConteudoDoDiretorio(pathProjeto);
		String pathReport = pathProjeto;
		for (String elemento : list) {
			if (elemento.equals("javancss-raw-report.xml")) {
				pathReport += File.separator + elemento;
			}
		}
		return pathReport;
	}
	
	/**
	 * Método que extrai a métrica de complexidade ciclomática média do arquivo 
	 * 		que contém o Java NCSS Report
	 * 
	 * @param pathReport - Caminho do arquivo Java NCSS Report
	 * @return Métrica CCN: Complexidade ciclomática média do projeto
	 */
	private double pegarComplexidadeCiclomatica(String pathReport) {
		String ccn = "";
		try {
			File f = new File(pathReport);
			FileReader fr = new FileReader(f);
			String line;
			bufferedReader = new BufferedReader(fr);
			int medias = 0;
			while ((line = bufferedReader.readLine()) != null) {
				if (medias == 1) {
					ccn = line.trim().substring(5,9);
					break;
				}else if (medias > 1) {
					medias -= 1;
				}
				if (line.trim().equals("<function_averages>")) {
					medias = 2;
				}
			}
		} catch (IOException ex) {
			ex.getCause();
		}
		
		double c = Double.parseDouble(ccn);
		return c;
	}

	/**
	 * Cria o caminho até o arquivo que contém o Java NCSS Report.
	 * @param path - Caminho para o diretório dos projetos
	 * @param elemento - Nome do projeto do qual se deseja extrair a métrica
	 * @return Caminho até o arquivo `javancss-raw-report.xml`
	 */
	private String criarPath(String path, String elemento) {
		String retorno = path + File.separator + elemento + File.separator + "target";
		return retorno;
	}

	/**
	 * REMOVER ISSO AQUI TAMBÉM
	 */
	private String[] pegarConteudoDoDiretorio(String pathInicial) {
		File diretorioInicial = new File(pathInicial);
		String[] elementosDiretorio = diretorioInicial.list();

		return elementosDiretorio;
	}

}
