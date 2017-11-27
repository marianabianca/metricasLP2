package detector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import entidades.Projeto;

public class DetectorDeOutliers {

	public String detectarOutliers(Map<String, Projeto> projetos) {
		List<Double> classesPorProjeto = this.classesPorProjeto(projetos);
		
		double mediana = this.descobreMediana(classesPorProjeto);
		double desvioAbsolutoMedio = this.getDesvioAbsolutoMedio(classesPorProjeto, mediana);
		
		System.out.println("mediana: " + mediana);
		System.out.println("desvio absoluto medio: " + desvioAbsolutoMedio);
		System.out.println();
		
		List<String> outliersPositivos = this.getOutliersPositivos(mediana, desvioAbsolutoMedio, projetos);
		List<String> outliersNegativos = this.getOutliersNegativos(mediana, desvioAbsolutoMedio, projetos);
		
		String retorno = this.imprimeOutliers(outliersPositivos, outliersNegativos);
		
		return retorno;
	}

	private String imprimeOutliers(List<String> outliersPositivos, List<String> outliersNegativos) {
		String lineSeparator = System.lineSeparator();
		String retorno = "Outliers positivos: " + lineSeparator;
		
		for (String outlier : outliersPositivos) {
			retorno += outlier + lineSeparator;
		}
		
		retorno += lineSeparator + "Outliers negativos: " + lineSeparator;
		
		for (String outlier : outliersNegativos) {
			retorno += outlier + lineSeparator;
		}
		
		return retorno;
	}

	private List<String> getOutliersPositivos(double mediana, double desvioAbsolutoMedio,
			Map<String, Projeto> projetos) {
		
		List<String> outliers = new ArrayList<>();
		
		for (String nomeDoProjeto : projetos.keySet()) {
			Projeto projeto = projetos.get(nomeDoProjeto);
			if (this.ehOutlierPositivo(projeto, mediana, desvioAbsolutoMedio)) {
				outliers.add(nomeDoProjeto);
			}
		}

		return outliers;
	}
	
	private List<String> getOutliersNegativos(double mediana, double desvioAbsolutoMedio,
			Map<String, Projeto> projetos) {
		
		List<String> outliers = new ArrayList<>();
		
		for (String nomeDoProjeto : projetos.keySet()) {
			Projeto projeto = projetos.get(nomeDoProjeto);
			if (this.ehOutlierNegativo(projeto, mediana, desvioAbsolutoMedio)) {
				outliers.add(nomeDoProjeto);
			}
		}
		
		return outliers;
	}

	private boolean ehOutlierPositivo(Projeto projeto, double mediana, double desvioAbsolutoMedio) {
		int numeroDeClassesDoProjeto = projeto.getNumeroDeClasses();
		
		double zScore = (0.6745 * (numeroDeClassesDoProjeto - mediana)) / desvioAbsolutoMedio;
		
		return zScore > 3.5;
	}
	
	private boolean ehOutlierNegativo(Projeto projeto, double mediana, double desvioAbsolutoMedio) {
		int numeroDeClassesDoProjeto = projeto.getNumeroDeClasses();
		
		double zScore = (0.6745 * (numeroDeClassesDoProjeto - mediana)) / desvioAbsolutoMedio;
		
		return zScore < -3.5;
	}

	private List<Double> classesPorProjeto(Map<String, Projeto> projetos) {
		List<Double> classesPorProjeto = new ArrayList<>();
		
		for (String nomeDoProjeto : projetos.keySet()) {
			Projeto projeto = projetos.get(nomeDoProjeto);
			double numeroDeClassesDoProjeto = projeto.getNumeroDeClasses();
			classesPorProjeto.add(numeroDeClassesDoProjeto);
		}
		
		return classesPorProjeto;
	}

	private double getDesvioAbsolutoMedio(List<Double> classesPorProjeto, double mediana) {
		List<Double> distanciaDeCadaElementoAMediana = new ArrayList<>();
		
		double desvioAbsolutoMedio = 0;
		
		for (Double numeroDeClassesProjeto : classesPorProjeto) {
			double distancia = Math.abs(numeroDeClassesProjeto - mediana);
			distanciaDeCadaElementoAMediana.add(distancia);
		}
		
		Collections.sort(distanciaDeCadaElementoAMediana);
		
		desvioAbsolutoMedio = descobreMediana(distanciaDeCadaElementoAMediana);
		
		return desvioAbsolutoMedio;
	}

	private double descobreMediana(List<Double> lista) {
		double mediana;
		
		List<Double> listaCopia = new ArrayList<>(lista);
		
		Collections.sort(listaCopia);
		
		int numeroDeElementos = listaCopia.size();
		int centro = numeroDeElementos / 2;
		
		if (numeroDeElementos % 2 == 0) {
			mediana = (listaCopia.get(centro) + listaCopia.get(centro - 1)) / 2;
		} else {
			mediana = listaCopia.get(centro);
		}
		
		return mediana;
	}

}
