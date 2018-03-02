package regexAssinaturaMetodos;


import org.junit.Assert;
import org.junit.Test;


public class TesteRegexMetodo {

	@Test
	public void test() {
		String assinaturaMetodo1 = "public void test()";
		String assinaturaMetodo2 = "private void atualizarNumDeClassesProjeto(Projeto projeto, String path) {";
		String assinaturaMetodo3 = "private void atualizarNumDeClassesProjeto(Projeto projeto";
		String assinaturaMetodo4 = "void atualizarNumDeClassesProjeto()";
		String assinaturaMetodo5 = "void atualizarNumDeClassesProjeto(";
		String assinaturaMetodo6 = "void atualizarNumDeClassesProjeto() {}";
		String assinaturaMetodo7 = "void atualizarNumDeClassesProjeto() { }";
		String assinaturaMetodo8 = "private void atualizarNumDeClassesProjeto(Projeto projeto, String path) throws Exception {";
		String assinaturaMetodo9 = "private void atualizarNumDeClassesProjeto(Projeto projeto, String path) throws Exception";
		String assinaturaFor = "for(i i: i) {";
		String chamadaDeMetodo = "nome = pegarNome()";
		
		String regex = "[^=]*\\s*[^=]+\\s+[^=]+[(][^=]*[)]?\\s*[{]?\\s*[}]?";
		
		Assert.assertTrue(assinaturaMetodo1.matches(regex));
		Assert.assertTrue(assinaturaMetodo2.matches(regex));
		Assert.assertTrue(assinaturaMetodo3.matches(regex));
		Assert.assertTrue(assinaturaMetodo4.matches(regex));
		Assert.assertTrue(assinaturaMetodo5.matches(regex));
		Assert.assertTrue(assinaturaMetodo6.matches(regex));
		Assert.assertTrue(assinaturaMetodo7.matches(regex));
		Assert.assertTrue(assinaturaMetodo8.matches(regex));
		Assert.assertTrue(assinaturaMetodo9.matches(regex));
		
		Assert.assertFalse(assinaturaFor.matches(regex));
		Assert.assertFalse(chamadaDeMetodo.matches(regex));
	}

}
