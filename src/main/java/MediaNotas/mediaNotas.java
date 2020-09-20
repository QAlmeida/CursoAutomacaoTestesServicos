package MediaNotas;

public class mediaNotas {
	int nota1;
	int nota2;

	public mediaNotas(int nota1, int nota2) {
		this.nota1 = nota1;
		this.nota2 = nota2;

	}

	public String getResultado() {
		int soma = nota1 + nota2;
		String resultado = "Aprovado";
		if (soma < 12) {
			resultado = "Reprovado";
		} 
		return resultado;
	}
	
	

}
