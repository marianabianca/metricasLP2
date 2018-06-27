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
	 * M�todo que extrai a m�trica de complexidade ciclom�tica do projeto
	 * 
	 * @param projetos - Projetos que ser�o medidos
	 * @param path - Caminho at� a pasta dos projetos
	 */
	public void extrairMetricas(Map<String, Projeto> projetos, String path) {
		Set<String> nomesProjetos = projetos.keySet();

		for (String nomeProjeto : nomesProjetos) {
			Projeto projeto = projetos.get(nomeProjeto);
			this.atualizarComplexidadeCiclomatica(projeto, path);
		}
	}

	/**
	 * M�todo que atualiza a complexidade ciclom�tica m�dia de um projeto.
	 * 
	 * @param projeto - Projeto que ter� a sua complexidade atualizada
	 * @param path - Caminho at� diret�rio dos projetos
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
	 * M�todo que busca o caminho do arquivo que cont�m o Java NCSS Report, 
	 * o qual possui a m�trica de complexidade ciclom�tica
	 * 
	 * @param pathProjeto - Caminho at� o diret�rio do projeto
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
	 * M�todo que extrai a m�trica de complexidade ciclom�tica m�dia do arquivo 
	 * 		que cont�m o Java NCSS Report
	 * 
	 * @param pathReport - Caminho do arquivo Java NCSS Report
	 * @return M�trica CCN: Complexidade ciclom�tica m�dia do projeto
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
	 * Cria o caminho at� o arquivo que cont�m o Java NCSS Report.
	 * @param path - Caminho para o diret�rio dos projetos
	 * @param elemento - Nome do projeto do qual se deseja extrair a m�trica
	 * @return Caminho at� o arquivo `javancss-raw-report.xml`
	 */
	private String criarPath(String path, String elemento) {
		String retorno = path + File.separator + elemento + File.separator + "target";
		return retorno;
	}

	/**
	 * REMOVER ISSO AQUI TAMB�M
	 */
	private String[] pegarConteudoDoDiretorio(String pathInicial) {
		File diretorioInicial = new File(pathInicial);
		String[] elementosDiretorio = diretorioInicial.list();

		return elementosDiretorio;
	}

}
