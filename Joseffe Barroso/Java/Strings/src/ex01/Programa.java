package ex01;

import java.util.HashMap;

public class Programa {

	public static void main(String[] args) {
		
		HashMap<Integer, Aluno> alunos = new HashMap<Integer, Aluno>();
		
		String alunosVestibular = "Jose dos Santos,7,Sao Paulo;Sandra Silva,6.5,Sao Jose do Rio Preto;Augusto Soares,8,Sao Paulo;Vanderlei Azevedo,5.65,Santos;Vanessa Ferreira,9,Sao Paulo;Natan Cruz,10,Sao Paulo";
		
		String[] dados = alunosVestibular.split(";");
		
		String[] dadosAluno;
		
		int id = 0;
		
		
		for(String dado: dados) {
			dadosAluno = dado.split(",");
			
			Aluno aluno = new Aluno();
			
			aluno.setNome(dadosAluno[0]);
			aluno.setNota(Double.parseDouble(dadosAluno[1]));
			aluno.setCidade(dadosAluno[2]);
			
			if(aluno.getNota() >= 7) {
				alunos.put(id++, aluno);
			}
		}
		
		alunos.forEach((key, aluno) -> {
			System.out.println("Nome: " + aluno.getNome() +
					"\tNota: " + aluno.getNota() + "\tCidade: " + aluno.getCidade());
		});
		

	}

}
