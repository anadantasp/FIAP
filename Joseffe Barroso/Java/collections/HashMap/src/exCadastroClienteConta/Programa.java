package exCadastroClienteConta;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Programa {

	public static void main(String[] args) throws IOException {

		Scanner scn = new Scanner(System.in);

		HashMap<Integer, Cliente> clientes = new HashMap<Integer, Cliente>();

		int opcao, id, idCliente=0;
		char possuiConta;
		Cliente cli;

		do {
			System.out.printf("---- Sistema de Cadastro de Clientes ----\n\n");
			System.out.printf("Escolha uma opção:\n");
			System.out.printf("1 - Incluir cliente \n" + "2 - Atualizar cliente \n" + "3 - Excluir cliente \n"
					+ "4 - Exibir cliente \n" + "5 - Sair\n\n");
			System.out.printf("Digite a opção desejada: ");
			opcao = scn.nextInt();

			if (opcao == 1) {
				Cliente cliente = new Cliente();

				idCliente++;
				cliente.setId(idCliente);

				System.out.printf("Digite o nome do cliente: ");
				cliente.setNome(scn.next());

				System.out.printf("Digite a idade do cliente: ");
				cliente.setIdade(scn.nextInt());

				System.out.printf("Digite o e-mail do cliente: ");
				cliente.setEmail(scn.next());

				System.out.println("Possui conta? (S/N)");
				possuiConta = scn.next().toUpperCase().charAt(0);

				if (possuiConta == 'S') {

					ContaBancaria conta = new ContaBancaria();

					System.out.println("Agência: ");
					conta.setAgencia(scn.next());
					System.out.println("Nº da conta: ");
					conta.setNumero(scn.next());
					
					conta.setSaldo(0);

					cliente.setConta(conta);
				} else {
					cliente.setConta(null);
				}

				clientes.put(idCliente, cliente);

				System.out.printf("Cliente incluído com sucesso!");
				System.in.read();

			} else if (opcao == 2) {
				clientes.forEach((chave, valor) -> {
					System.out.print("ID: " + chave + "-" + valor.exibirNomeIdade());
				});

				System.out.printf("\nDigite o id do cliente que deseja atualizar: ");
				id = scn.nextInt();
				
				if (clientes.containsKey(id)) {
					cli = clientes.get(id);

					System.out.print("Digite o seu novo nome: ");
					cli.setNome(scn.next());

					System.out.print("Digite a sua nova idade: ");
					cli.setIdade(scn.nextInt());

					System.out.print("Digite o seu novo e-mail: ");
					cli.setEmail(scn.next());

					System.out.printf("Cliente atualizado com sucesso!");
				} else {
					System.out.printf("Cliente não encontrado!");
				}

				System.in.read();

			} else if (opcao == 3) {
				clientes.forEach((chave, valor) -> {
					System.out.print("ID: " + chave + "-" + valor.exibirNomeIdade());
				});

				System.out.printf("\nDigite o id do cliente que deseja remover: ");
				id = scn.nextInt();

				if (clientes.containsKey(id)) {
					clientes.remove(id);

					System.out.printf("Cliente excluído com sucesso!");
				} else {
					System.out.printf("Cliente não encontrado!");
				}

				System.in.read();

			} else if (opcao == 4) {

				clientes.forEach((chave, valor) -> {
					System.out.print("ID: " + chave + "-" + valor.exibirNomeIdade());
					
					if (valor.getConta() != null)
	                	   System.out.println(valor.exibirDadosConta());
				});

				System.in.read();
			}

		} while ((opcao >= 1) && (opcao <= 4));

	}

}
