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
	 * Método que extrai a métrica de complexidade ciclomática máxima do projeto
	 * 
	 * @param projetos - Projetos que serão medidos
	 * @param path - Caminho até a pasta dos projetos
	 */
	public void extrairMetricas(Map<String, Projeto> projetos, String path) {
		Set<String> nomesProjetos = projetos.keySet();

		for (String nomeProjeto : nomesProjetos) {
			Projeto projeto = projetos.get(nomeProjeto);
			this.atualizarMaximaComplexidadeCiclomatica(projeto, path);
		}
	}
	
	/**
	 * Método que atualiza a maior complexidade ciclomática encontrada em um projeto.
	 * 
	 * @param projeto - Projeto que terá a sua complexidade atualizada
	 * @param path - Caminho até diretório dos projetos
	 */
	private void atualizarMaximaComplexidadeCiclomatica(Projeto projeto, String path) {
		String nomeProjeto = projeto.getNome();
		String pathReport = criarPath(path, nomeProjeto);
		double maxComplexidadeCiclomatica = pegarMaxComplexidadeCiclomatica(pathReport);
		projeto.setMaxComplexidadeCiclomatica(maxComplexidadeCiclomatica);
	}

	/**
	 * Método que pega a maior complexidade ciclomática encontrada em um projeto.
	 * @param pathReport - Caminho até o arquivo que contém o Java NCSS Report.
	 * @return Maior complexidade ciclomática do projeto.
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
	 * Método que pega todas as complexidades ciclomáticas encontradas no Java NCSS Report do projeto.
	 * @param pathReport - Caminho até o arquivo que contém o Java NCSS Report.
	 * @return Array com as complexidades ciclomáticas dos métodos do projeto.
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
