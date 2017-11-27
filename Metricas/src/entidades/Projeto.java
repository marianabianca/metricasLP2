package entidades;

public class Projeto {
	
	String nome;
	int numeroDeClasses;
	
	public Projeto(String nome) {
		this.nome = nome;
		this.numeroDeClasses = 0;
	}

	public int getNumeroDeClasses() {
		return this.numeroDeClasses;
	}

	public void atualizarNumeroDeClasses(int numeroDeClasses) {
		this.numeroDeClasses = numeroDeClasses;
	}

}
