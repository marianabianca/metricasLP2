package entidades;

public class Projeto {
	
	String nome;
	int numeroDeClasses;
	
	public Projeto(String nome) {
		this.nome = nome;
		this.numeroDeClasses = 0;
	}
	
	public void setNumeroDeClasse(int numeroDeClasses) {
		this.numeroDeClasses = numeroDeClasses;
	}

	public int getNumeroDeClasses() {
		return this.numeroDeClasses;
	}

}
