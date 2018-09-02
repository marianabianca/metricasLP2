package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Metrica {
	
	private String nome;
	private Map<String, Double> elementos;
	private Avaliacao avaliacao;
	
	public Metrica(String nome) {
		this.nome = nome;
		this.elementos = new HashMap<>();
		this.avaliacao = new Avaliacao();
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
	
	public void setMediana(Double mediana) {
		this.avaliacao.setMediana(mediana);
	}
	
	public void setDesvioAbsolutoMediano(Double desvioAbsolutoMediano) {
		this.avaliacao.setDesvioAbsolutoMediano(desvioAbsolutoMediano);
	}
	
	public String toString() {
		String retorno = "Metrica: " + this.nome + System.lineSeparator() + this.avaliacao.toString();
		return retorno;
	}
	
	public String toStringCsv() {
		String resultado = this.nome + "," + this.avaliacao.toStringCsv();
		return resultado;
	}

	public void setOutliers(List<String> outliersPositivos, List<String> outliersNegativos) {
		this.avaliacao.setOutliers(outliersPositivos, outliersNegativos);
	}

}
