package entidades;

public class Projeto {
	
	private String nome;
	private int numeroDeClasses;
	private int numeroDeLinhas;
	private int numeroDeMetodos;
	
	public Projeto(String nome) {
		this.nome = nome;
		this.numeroDeClasses = 0;
		this.numeroDeLinhas = 0;
		this.numeroDeMetodos = 0;
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

	public void setNumeroDeClasses(int numeroDeClasses) {
		this.numeroDeClasses = numeroDeClasses;
	}

	public void setNumeroDeLinhas(int numeroDeClasses) {
		this.numeroDeLinhas = numeroDeClasses;
	}
	
	public void setNumeroDeMetodos(int numeroDeMetodos) {
		this.numeroDeMetodos = numeroDeMetodos;
	}

	public String getNome() {
		return this.nome;
	}

}
