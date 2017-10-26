package contato;

public class Contato {
	private String nome;
	private String sobrenome;
	private String telefone;
	
	public Contato(String nome, String sobrenome, String telefone) {
		if (nome == null) {
			throw new NullPointerException("Nome nulo.");
		}
		if (nome.trim().equals("")) {
			throw new IllegalArgumentException("Nome vazio.");
		}
		if (sobrenome == null){
			throw new IllegalArgumentException("Sobrenome nulo.");
		}
		if (sobrenome.trim().equals("")) {
			throw new IllegalArgumentException("Sobrenome vazio.");
		}
		if (telefone == null) {
			throw new IllegalArgumentException("Telefone nulo.");
		}
		if (telefone.trim().equals("")) {
			throw new IllegalArgumentException("Telefone vazio.");
		}
		
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone = telefone;
	}
	
	public String toStringCompleto() {
		String retorno = this.nome + " " + this.sobrenome + " - " + this.telefone;
		return retorno;
	}
	
	@Override
	public String toString() {
		String retorno = this.nome + " " + this.sobrenome;
		return retorno;
	}
}
