package extratoresDeMetricas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import entidades.Projeto;

public class ExtratorDeComplexidadeCiclomatica implements ExtratorDeMetricas {

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
		String pathReport = criarPath(path, nomeProjeto);
		double complexidadeCiclomatica = pegarComplexidadeCiclomatica(pathReport);
		projeto.setComplexidadeCiclomatica(complexidadeCiclomatica);
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
			int linhasRestantes = 0;
			while ((line = bufferedReader.readLine()) != null) {
				if (linhasRestantes == 1) {
					ccn = line.trim().substring(5,9);
					break;
				}else if (linhasRestantes > 1) {
					linhasRestantes -= 1;
				}
				if (line.trim().equals("<function_averages>")) {
					linhasRestantes = 2;
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
		String retorno = path + File.separator + elemento + File.separator + "target"
				+ File.separator + "javancss-raw-report.xml";
		return retorno;
	}
}
