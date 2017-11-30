package detector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import entidades.Projeto;

public class DetectorDeOutliers {

	private String detectarOutliersGenerico(Map<String, Projeto> projetos, List<Double> classesPorProjeto) {
		double mediana = this.descobreMediana(classesPorProjeto);
		double desvioAbsolutoMedio = this.getDesvioAbsolutoMedio(classesPorProjeto, mediana);
		
		// se for zero... https://www.ibm.com/support/knowledgecenter/en/SSWLVY_1.0.0/com.ibm.spss.analyticcatalyst.help/analytic_catalyst/modified_z.html
		// If MAD does equal 0. Subtract the median from the score and divide by 1.253314*MeanAD. 1.253314*MeanAD approximately equals the standard deviation: (X-MED)/(1.253314*MeanAD).

		if (desvioAbsolutoMedio <= 0.00001) {
			desvioAbsolutoMedio = 1.253314 * mediana;
		}

		System.out.println("mediana: " + mediana);
		System.out.println("desvio absoluto medio: " + desvioAbsolutoMedio);
		System.out.println();
		
		List<String> outliersPositivos = this.getOutliersPositivos(mediana, desvioAbsolutoMedio, projetos);
		List<String> outliersNegativos = this.getOutliersNegativos(mediana, desvioAbsolutoMedio, projetos);
		
		String retorno = this.imprimeOutliers(outliersPositivos, outliersNegativos);
		
		return retorno;
	}

	public String detectarOutliers(Map<String, Projeto> projetos) {
		String resultado = "CLASSESSS ..... ";
		List<Double> classesPorProjeto = this.classesPorProjeto(projetos);
		resultado += detectarOutliersGenerico(projetos, classesPorProjeto);
		resultado += "\n LINHASSS.....";
		classesPorProjeto = this.linhasPorProjeto(projetos);
		resultado += detectarOutliersGenerico(projetos, classesPorProjeto);
		return resultado;
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

	private List<Double> linhasPorProjeto(Map<String, Projeto> projetos) {
		List<Double> classesPorProjeto = new ArrayList<>();
		
		for (String nomeDoProjeto : projetos.keySet()) {
			Projeto projeto = projetos.get(nomeDoProjeto);
			double numeroDeClassesDoProjeto = projeto.getNumeroDeLinhas();
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
		System.out.println(distanciaDeCadaElementoAMediana);	
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
