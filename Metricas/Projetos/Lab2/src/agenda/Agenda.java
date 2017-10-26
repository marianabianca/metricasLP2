package agenda;
import contato.Contato;
import util.Util;

public class Agenda {

	Contato[] contatos = new Contato[100];
	
	public void cadastrarContato(int posicao, String nome, String sobrenome, String telefone) {
		if (posicao <= 0 || posicao > 100) {
			throw new IllegalArgumentException("POSIÇÃO INVÁLIDA!");
		}
		
		Contato contato = new Contato(nome, sobrenome, telefone);
		
		posicao = posicao - 1;
		contatos[posicao] = contato;
	}

	public String getContato(int posicao) {
		if (posicao <= 0 || posicao > 100) {
			throw new IllegalArgumentException("POSIÇÃO INVÁLIDA!");
		}
		
		posicao = posicao - 1;
		String contato = contatos[posicao].toStringCompleto();
		
		return contato;
	}

	public String listarContatos() {
		String retorno = Util.QUEBRA_DE_LINHA + "";
		
		for (int i = 0; i < contatos.length; i++) {
			if (contatos[i] != null) {
				retorno += i+1 + " - " + contatos[i] + Util.QUEBRA_DE_LINHA;
			}
		}
		
		return retorno;
	}

}
