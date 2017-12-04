package detector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import entidades.Metrica;
import entidades.Projeto;
import obtedorDeMetricas.FactoryObtedorDeMetricas;
import obtedorDeMetricas.ObtedorDeMetricas;

public class DetectorDeOutliers {
	
	private static final String LS = System.lineSeparator();
	ObtedorDeMetricas[] obtedoresDeMetricas;
	
	public DetectorDeOutliers() {
		FactoryObtedorDeMetricas factoryObtedorDeMetricas = new FactoryObtedorDeMetricas();
		obtedoresDeMetricas = factoryObtedorDeMetricas.getObtedoresDeMetricas();
	}
	
	public String imprimeOutliers(Map<String, Projeto> projetos) {
		String resultado = "";
		List<Metrica> metricas = this.detectarOutliers(projetos);
		
		for (Metrica metrica : metricas) {
			resultado += metrica.toString() + LS +
					"------------------------------" + LS + LS;
		}
		
		return resultado;
	}
	
	private List<Metrica> detectarOutliers(Map<String, Projeto> projetos) {
		List<Metrica> metricas = new ArrayList<>();
		
		for (ObtedorDeMetricas obtedorDeMetricas : obtedoresDeMetricas) {
			Metrica metrica = obtedorDeMetricas.obterMetricas(projetos);
			List<Double> metricaValores = metrica.getValoresElementos();
			
			Double mediana = this.calculaMediana(metricaValores);
			Double desvioAbsolutoMediano = this.getDesvioAbsolutoMediano(metricaValores, mediana);
			
			this.descobreOutliers(metrica, mediana, desvioAbsolutoMediano);
			
			metricas.add(metrica);
		}
		
		return metricas;
	}
	
	private void descobreOutliers(Metrica metrica, Double mediana, Double desvioAbsolutoMediano) {
		Map<String, Double> elementos = metrica.getElementos();
		
		List<String> outliersPositivos = new ArrayList<>();
		List<String> outliersNegativos = new ArrayList<>();
		
		for (String nomeDoProjeto : elementos.keySet()) {
			Double elemento = elementos.get(nomeDoProjeto);
			
			if (this.ehOutlierPositivo(elemento, mediana, desvioAbsolutoMediano)) {
				outliersPositivos.add(nomeDoProjeto);
			}
			if (this.ehOutlierNegativo(elemento, mediana, desvioAbsolutoMediano)) {
				outliersNegativos.add(nomeDoProjeto);
			}
		}
		
		metrica.setOutliersPositivos(outliersPositivos);
		metrica.setOutliersNegativos(outliersNegativos);
	}
	
	private double calculaMediana(List<Double> elementos) {
		double mediana;

		List<Double> listaCopia = new ArrayList<>(elementos);
		
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
	
	private double getDesvioAbsolutoMediano(List<Double> elementosPorProjeto, double mediana) {
		double desvioAbsolutoMediano = 0;
		
		List<Double> distanciaDeCadaElementoAMediana = this.calculaDistanciaDeCadaElementoAMediana(elementosPorProjeto, mediana);
		
		desvioAbsolutoMediano = calculaMediana(distanciaDeCadaElementoAMediana);
		
		if (desvioAbsolutoMediano <= 0.00001) {
			desvioAbsolutoMediano = 1.253314 * mediana;
		}
		
		return desvioAbsolutoMediano;
	}

	private List<Double> calculaDistanciaDeCadaElementoAMediana(List<Double> elementosPorProjeto, double mediana) {
		List<Double> distanciaDeCadaElementoAMediana = new ArrayList<>();
		
		for (Double elemento : elementosPorProjeto) {
			double distancia = Math.abs(elemento - mediana);
			distanciaDeCadaElementoAMediana.add(distancia);
		}
		
		return distanciaDeCadaElementoAMediana;
	}
	
	private boolean ehOutlierPositivo(Double elemento, double mediana, double desvioAbsolutoMediano) {		
		double zScore = (0.6745 * (elemento - mediana)) / desvioAbsolutoMediano;
		
		return zScore > 3.5;
	}
	
	private boolean ehOutlierNegativo(Double elemento, double mediana, double desvioAbsolutoMedio) {		
		double zScore = (0.6745 * (elemento - mediana)) / desvioAbsolutoMedio;
		
		return zScore < -3.5;
	}
}
