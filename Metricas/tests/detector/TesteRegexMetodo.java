package detector;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import entidades.Projeto;

public class TesteRegexMetodo {

	@Test
	public void test() {
		String nome = "public void test";
		String nome2 = "void test()";
		String nome3 = "public void test()";
		String nome4 = "private void atualizarNumDeClassesProjeto(Projeto projeto, String path) {";
		String nome5 = "for(i i: i) {";
		String parenteses1 = "(";
		String parenteses2 = ")";
		String parenteses3 = "()";
		Assert.assertTrue(nome.matches(".*\\s+.*\\s+.*"));
		Assert.assertTrue(parenteses1.matches("\\("));
		Assert.assertTrue(parenteses2.matches("[)]"));
		Assert.assertTrue(parenteses3.matches("[(].*[)]"));
		Assert.assertTrue(nome2.matches(".*\\s*.+\\s+.+[(].*[)]"));
		Assert.assertTrue(nome3.matches(".*\\s*.+\\s+.+[(].*[)]\\s*[{]?"));
		Assert.assertTrue(nome4.matches(".*\\s*.+\\s+.+[(].*[)]\\s*[{]?"));
		Assert.assertTrue(!nome5.matches(".*\\s*.+\\s+.+[(].*[)]\\s*[{]?"));
	}

}
