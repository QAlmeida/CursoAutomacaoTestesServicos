package Exemplos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import MediaNotas.mediaNotas;

public class MediaNotasTest {
	//duas notas, receber o resultado
	//media menor que 6, reprovador
	//media maior ou igual a 6, aprovado
	
	@Test
	public void testReprovado() {
		int nota1 = 5;
		int nota2 = 5;
		String resultado = "";
		mediaNotas mediaNotas = new mediaNotas(nota1, nota2);
		resultado = mediaNotas.getResultado();
		assertEquals("Reprovado", resultado);
	}
	
	@Test
	public void testAprovado() {
//		int nota1 = 5;
//		int nota2 = 5;
		String resultado = "";
		mediaNotas mediaNotas = new mediaNotas(6, 6);
		resultado = mediaNotas.getResultado();
		assertEquals("Aprovado", resultado);
	}

}
