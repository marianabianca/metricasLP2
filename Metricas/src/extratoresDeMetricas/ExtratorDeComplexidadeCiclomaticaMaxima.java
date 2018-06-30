package extratoresDeMetricas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import entidades.Projeto;

public class ExtratorDeComplexidadeCiclomaticaMaxima implements ExtratorDeMetricas {
	
	private BufferedReader bufferedReader;

	/**
	 * M�todo que extrai a m�trica de complexidade ciclom�tica m�xima do projeto
	 * 
	 * @param projetos - Projetos que ser�o medidos
	 * @param path - Caminho at� a pasta dos projetos
	 */
	public void extrairMetricas(Map<String, Projeto> projetos, String path) {
		Set<String> nomesProjetos = projetos.keySet();

		for (String nomeProjeto : nomesProjetos) {
			Projeto projeto = projetos.get(nomeProjeto);
			this.atualizarMaximaComplexidadeCiclomatica(projeto, path);
		}
	}
	
	/**
	 * M�todo que atualiza a maior complexidade ciclom�tica encontrada em um projeto.
	 * 
	 * @param projeto - Projeto que ter� a sua complexidade atualizada
	 * @param path - Caminho at� diret�rio dos projetos
	 */
	private void atualizarMaximaComplexidadeCiclomatica(Projeto projeto, String path) {
		String nomeProjeto = projeto.getNome();
		String pathReport = criarPath(path, nomeProjeto);
		double maxComplexidadeCiclomatica = pegarMaxComplexidadeCiclomatica(pathReport);
		projeto.setMaxComplexidadeCiclomatica(maxComplexidadeCiclomatica);
	}

	/**
	 * M�todo que pega a maior complexidade ciclom�tica encontrada em um projeto.
	 * @param pathReport - Caminho at� o arquivo que cont�m o Java NCSS Report.
	 * @return Maior complexidade ciclom�tica do projeto.
	 */
	private double pegarMaxComplexidadeCiclomatica(String pathReport) {
		ArrayList<Double> complexidadesCiclomaticas = this.pegarComplexidadesCiclomaticas(pathReport);
		double maiorComplexidade = 0;
		for (Double elemento : complexidadesCiclomaticas) {
			if (elemento > maiorComplexidade) {
				maiorComplexidade = elemento;
			}
		}
		return maiorComplexidade;
	}
	
	/**
	 * M�todo que pega todas as complexidades ciclom�ticas encontradas no Java NCSS Report do projeto.
	 * @param pathReport - Caminho at� o arquivo que cont�m o Java NCSS Report.
	 * @return Array com as complexidades ciclom�ticas dos m�todos do projeto.
	 */
	private ArrayList<Double> pegarComplexidadesCiclomaticas(String pathReport) {
		ArrayList<Double> cnn = new ArrayList<>();
		try {
			File f = new File(pathReport);
			FileReader fr = new FileReader(f);
			String line;
			bufferedReader = new BufferedReader(fr);
			int linhasRestantes = 0;
			while ((line = bufferedReader.readLine()) != null && !line.trim().equals("<function_averages>")) {
				if(linhasRestantes == 1) {
					cnn.add(Double.parseDouble(line.trim().substring(5, 6)));
					linhasRestantes = 0;
				} else if (linhasRestantes > 1) {
					linhasRestantes -= 1;
				}
				if (line.trim().equals("<function>")) {
					linhasRestantes = 3;
				}
			}
			
		} catch (IOException e) {
			e.getCause();
		}
		return cnn;
	}
	
	/**
	 * Cria o caminho at� o arquivo que cont�m o Java NCSS Report.
	 * @param path - Caminho para o diret�rio dos projetos
	 * @param elemento - Nome do projeto do qual se deseja extrair a m�trica
	 * @return Caminho at� o arquivo `javancss-raw-report.xml`
	 */
	private String criarPath(String path, String elemento) {
		String retorno = path + File.separator + elemento + File.separator + "target"
				+ File.separator + "javancss-raw-report.xml";
		return retorno;
	}

}
