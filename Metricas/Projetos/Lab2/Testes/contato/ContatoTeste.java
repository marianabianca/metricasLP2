package contato;

import org.junit.Test;

import contato.Contato;

public class ContatoTeste {

	@Test(expected = NullPointerException.class)
	public void contatoNomeNulo() {
		new Contato(null, "a", "a");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void contatoNomeVazio() {
		new Contato("        ", "a", "a");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void contatoSobrenomeNulo() {
		new Contato("a", null, "a");
	}

	@Test(expected = IllegalArgumentException.class)
	public void contatoSobrenomeVazio() {
		new Contato("a", "         ", "a");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void contatoTelefoneNlo() {
		new Contato("a", "a", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void contatoTelefoneVazio() {
		new Contato("a", "a", "         ");
	}
	
	@Test
	public void contatoSucesso() {
		new Contato("a", "a", "a");
	}
}
