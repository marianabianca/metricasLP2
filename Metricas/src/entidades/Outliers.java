package entidades;

import java.util.ArrayList;
import java.util.List;

public class Outliers {
	
	private List<String> outliersPositivos;
	private List<String> outliersNegativos;
	
	public Outliers() {
		this.outliersPositivos = new ArrayList<>();
		this.outliersNegativos = new ArrayList<>();
	}
	
	public void setOuliersPositivos(List<String> outliersPositivos) {
		this.outliersPositivos = outliersPositivos;
	}
	
	public void setOuliersNegativos(List<String> outliersNegativos) {
		this.outliersNegativos = outliersNegativos;
	}
	
	public String toString() {
		String ls = System.lineSeparator();
		String retorno = "Outliers positivos: " + ls;
		
		for (String outlier : outliersPositivos) {
			retorno += outlier + ls;
		}
		
		retorno += "Outliers negativos: " + ls;
		
		for (String outlier : outliersNegativos) {
			retorno += outlier + ls;
		}
		
		return retorno;		
	}

}
