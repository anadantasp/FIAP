package program;

import java.sql.SQLException;
import java.util.Scanner;

import dao.CategoriaDao;

public class Programa {
	public static void main(String[] args) throws SQLException {
		CategoriaDao catDao = new CategoriaDao();

		Scanner scn = new Scanner(System.in);

		int opcao;
		String email, senha;

		do {
			System.out.printf("--------------- BEM-VINDO AO INVESTIUM ---------------\n\n");
			System.out.printf("1 - Fazer login\n" + "2 - Fazer cadastro\n" + "3 - Continuar sem logar\n"
					+ "4 - Esqueci a senha\n" + "5 - Sair do sistema\n");
			System.out.printf("Informe a opção desejada: ");
			opcao = scn.nextInt();
			
			while (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 5) {
				System.out.printf("\nOpção Inv�lida!\n");
				System.out.printf("Digite novamente: ");
				opcao = scn.nextInt();
			}
			
			if (opcao == 1) {

				System.out.printf("\n");
				System.out.printf("Informe o seu e-mail: ");
				email = scn.next();
				System.out.printf("Informe a sua senha: ");
				senha = scn.next();
			}else if(opcao == 2) {
				
			}
			

		} while (opcao <= 4);

	}
}
