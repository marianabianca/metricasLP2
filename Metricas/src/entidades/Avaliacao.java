package entidades;

import java.util.List;

public class Avaliacao {
	
	private Outliers outliers;
	private Double mediana;
	private Double desvioAbsolutoMediano;
	
	public Avaliacao() {
		this.outliers = new Outliers();
		this.mediana = null;
		this.desvioAbsolutoMediano = null;
	}
	
	public void setMediana(Double mediana) {
		this.mediana = mediana;
	}
	
	public void setDesvioAbsolutoMediano(Double desvioAbsolutoMediano) {
		this.desvioAbsolutoMediano = desvioAbsolutoMediano;
	}
	
	public String toString() {
		String ls = System.lineSeparator();
		String retorno = "Mediana: " + this.mediana + ls +
				"Desvio Absoluto Mediano: " + this.desvioAbsolutoMediano + ls + ls +
				this.outliers.toString() + ls;
		
		return retorno;
	}
	
	public List<String> getOutliersPositivos() {
		return this.outliers.getOutliersPositivos();
	}
	
	public List<String> getOutliersNegativos() {
		return this.outliers.getOutliersNegativos();
	}

	public void setOutliers(List<String> outliersPositivos, List<String> outliersNegativos) {
		this.outliers.setOutliers(outliersPositivos, outliersNegativos);
	}
}
