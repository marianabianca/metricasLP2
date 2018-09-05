package detector;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import entidades.Metrica;
import entidades.Projeto;

public class GeradorCsv {
	
	public void gerarOutliersCsv(Map<String, Projeto> projetos, List<Metrica> metricas) throws IOException {		
		for (Metrica metrica : metricas) {
			FileWriter metricaCsv = criaArquivoCsv(metrica.getNome());
			StringBuilder sb = this.criaStringBuilderParaCsv();
			
			for (String nomeDoProjeto : projetos.keySet()) {
				Projeto projeto = projetos.get(nomeDoProjeto);
				this.adicionaDadosDoProjeto(projeto, metrica, sb);
			}
			metricaCsv.write(sb.toString());
			metricaCsv.close();
		}
	}
	
	private FileWriter criaArquivoCsv(String nomeMetrica) throws IOException {
		nomeMetrica = nomeMetrica.toLowerCase();
		FileWriter metricaCsv = new FileWriter(nomeMetrica + ".csv");
		return metricaCsv;
	}
	
	private StringBuilder criaStringBuilderParaCsv() {
		StringBuilder sb = new StringBuilder();
		sb.append("Projeto,Metrica,Outlier Positivo,Outlier Negativo\n");
		return sb;
	}
	
	private StringBuilder adicionaDadosDoProjeto(Projeto projeto, Metrica metrica, StringBuilder sb) {
		String nomeDaMetrica = metrica.getNome();
		String nomeDoProjeto = projeto.getNome();
		int metricaDoProjeto = this.getMetricaProjeto(projeto, nomeDaMetrica);
		boolean outlierPositivo = metrica.getOutliersPositivos().contains(nomeDoProjeto);
		boolean outlierNegativo = metrica.getOutliersNegativos().contains(nomeDoProjeto);
		sb.append(nomeDoProjeto + "," + metricaDoProjeto + "," + outlierPositivo + "," + outlierNegativo + "\n");
		return sb;
	}
	
	private int getMetricaProjeto(Projeto projeto, String nome) {
		if (nome.equals("Classes")) return projeto.getNumeroDeClasses();
		if (nome.equals("Linhas")) return projeto.getNumeroDeLinhas();
		if (nome.equals("Metodos")) return projeto.getNumeroDeMetodos();
		if (nome.equals("Testes")) return projeto.getNumeroDeTestes();
		return 0;
	}

}
