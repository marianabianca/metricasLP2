package agenda;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import agenda.Agenda;
import util.Util;


public class AgendaTeste {
	
	private Agenda agenda;
	
	@Before
	public void setup() {
		this.agenda = new Agenda();
	}

	@Test
	public void cadastrarContatoPosicao0() {
		try {
			agenda.cadastrarContato(0, "a", "a", "a");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("POSIÇÃO INVÁLIDA!", e.getMessage());
		}
	}
	
	@Test
	public void cadastrarContatoPosicao101() {
		try {
			agenda.cadastrarContato(101, "a", "a", "a");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("POSIÇÃO INVÁLIDA!", e.getMessage());
		}
	}
	
	@Test
	public void cadastrarContatoSucesso() {
		// cadastra contato
		agenda.cadastrarContato(1, "a", "a", "a");
		
		// pega contato
		Assert.assertEquals("a a - a", agenda.getContato(1));
		
		// lista contatos
		String mensagem = Util.QUEBRA_DE_LINHA + "1 - a a" + Util.QUEBRA_DE_LINHA;
		Assert.assertEquals(mensagem, agenda.listarContatos());
	}
	
	@Test
	public void getContatoPosicao0() {
		try {
			agenda.getContato(0);
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("POSIÇÃO INVÁLIDA!", e.getMessage());
		}
	}
	
	@Test
	public void getContatoPosicao101() {
		try {
			agenda.getContato(101);
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("POSIÇÃO INVÁLIDA!", e.getMessage());
		}
	}
}
