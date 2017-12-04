package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Metrica {
	
	private String nome;
	private Map<String, Double> elementos;
	private Outliers outliers;
	
	public Metrica(String nome) {
		this.nome = nome;
		this.elementos = new HashMap<>();
		this.outliers = new Outliers();
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void adicionarElemento(String nomeDoProjeto, Double elemento) {
		if (!elementos.containsKey(nomeDoProjeto)) {
			elementos.put(nomeDoProjeto, elemento);
		} else {
			elemento += elementos.get(nomeDoProjeto);
			elementos.put(nomeDoProjeto, elemento);
		}
	}

	public List<Double> getValoresElementos() {
		List<Double> valoresElementos = new ArrayList<Double>(elementos.values());
		return valoresElementos;
	}
	
	public Map<String, Double> getElementos(){
		return this.elementos;
	}
	
	public void setOutliersPositivos(List<String> outliersPositivos) {
		this.outliers.setOuliersPositivos(outliersPositivos);
	}
	
	public void setOutliersNegativos(List<String> outliersNegativos) {
		this.outliers.setOuliersNegativos(outliersNegativos);
	}
	
	public String toString() {
		String retorno = "Metrica: " + this.nome + System.lineSeparator() + this.outliers.toString();
		return retorno;
	}

}
