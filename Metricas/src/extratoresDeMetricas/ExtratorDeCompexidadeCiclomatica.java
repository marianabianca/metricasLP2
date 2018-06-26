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

	public void extrairMetricas(Map<String, Projeto> projetos, String path) {
		Set<String> nomesProjetos = projetos.keySet();

		for (String nomeProjeto : nomesProjetos) {
			Projeto projeto = projetos.get(nomeProjeto);
			this.atualizarComplexidadeCiclomatica(projeto, path);
		}
	}

	private void atualizarComplexidadeCiclomatica(Projeto projeto, String path) {
		String nomeProjeto = projeto.getNome();
		String pathProjeto = criarPath(path, nomeProjeto);
		String pathReport = buscaJavancss(pathProjeto);
		double complexidadeCiclomatica = pegarComplexidadeCiclomatica(pathReport);
		projeto.setComplexidadeCiclomatica(complexidadeCiclomatica);
	}

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

	private String criarPath(String path, String elemento) {
		String retorno = path + File.separator + elemento + File.separator + "target";
		return retorno;
	}

	private String[] pegarConteudoDoDiretorio(String pathInicial) {
		File diretorioInicial = new File(pathInicial);
		String[] elementosDiretorio = diretorioInicial.list();

		return elementosDiretorio;
	}

}
