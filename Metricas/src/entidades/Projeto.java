package entidades;

public class Projeto {
	
	private String nome;
	private int numeroDeClasses;
	private int numeroDeLinhas;
	
	public Projeto(String nome) {
		this.nome = nome;
		this.numeroDeClasses = 0;
		this.numeroDeLinhas = 0;
	}

	public int getNumeroDeClasses() {
		return this.numeroDeClasses;
	}

	public int getNumeroDeLinhas() {
		return this.numeroDeLinhas;
	}

	public void setNumeroDeClasses(int numeroDeClasses) {
		this.numeroDeClasses = numeroDeClasses;
	}

	public void setNumeroDeLinhas(int numeroDeClasses) {
		this.numeroDeLinhas = numeroDeClasses;
	}

	public String getNome() {
		return this.nome;
	}

}
