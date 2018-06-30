package entidades;

public class Projeto {
	
	private String nome;
	private int numeroDeClasses;
	private int numeroDeLinhas;
	private int numeroDeMetodos;
	private int numeroDeTestes;
	private double complexidadeCiclomatica;
	private double maxComplexidadeCiclomatica;
	
	public Projeto(String nome) {
		this.nome = nome;
		this.numeroDeClasses = 0;
		this.numeroDeLinhas = 0;
		this.numeroDeMetodos = 0;
		this.complexidadeCiclomatica = 0;
		this.maxComplexidadeCiclomatica = 0;
	}

	public int getNumeroDeClasses() {
		return this.numeroDeClasses;
	}

	public int getNumeroDeLinhas() {
		return this.numeroDeLinhas;
	}
	
	public int getNumeroDeMetodos() {
		return this.numeroDeMetodos;
	}
	
	public int getNumeroDeTestes() {
		return this.numeroDeTestes;
	}

	public void setNumeroDeClasses(int numeroDeClasses) {
		this.numeroDeClasses = numeroDeClasses;
	}

	public void setNumeroDeLinhas(int numeroDeClasses) {
		this.numeroDeLinhas = numeroDeClasses;
	}
	
	public void setNumeroDeMetodos(int numeroDeMetodos) {
		this.numeroDeMetodos = numeroDeMetodos;
	}
	
	public void setNumeroDeTestes(int numeroDeTestes) {
		this.numeroDeTestes = numeroDeTestes;
	}

	public String getNome() {
		return this.nome;
	}

	public double getComplexidadeCiclomatica() {
		return complexidadeCiclomatica;
	}

	public void setComplexidadeCiclomatica(double complexidadeCiclomatica) {
		this.complexidadeCiclomatica = complexidadeCiclomatica;
	}
	
	public double getMaxComplexidadeCiclomatica() {
		return maxComplexidadeCiclomatica;
	}

	public void setMaxComplexidadeCiclomatica(double maxComplexidadeCiclomatica) {
		this.maxComplexidadeCiclomatica = maxComplexidadeCiclomatica;		
	}

}
