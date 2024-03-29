package program;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Endereco;
import model.Pessoa;
import service.ViaCepService;

public class Teste {
	public static void main(String[] args) throws IOException {

		Scanner scn = new Scanner(System.in);

		ArrayList<Pessoa> pessoas = new ArrayList<>();

		int opcao, id, indexPessoa;
		char resposta, possuiConta;
		Pessoa recebePessoa;
		String cep;

		do {
			System.out.printf("---- Sistema de Cadastro de Clientes ----\n\n");
			System.out.printf("Escolha uma op��o:\n");
			System.out.printf("1 - Incluir cliente \n" + "2 - Atualizar cliente \n" + "3 - Excluir cliente \n"
					+ "4 - Exibir cliente \n" + "5 - Sair\n\n");
			System.out.printf("Digite a op��o desejada: ");
			opcao = scn.nextInt();

			if (opcao == 1) {
				Pessoa pessoa = new Pessoa();

				if (pessoas.size() > 0) {
					pessoa.setId(pessoas.get(pessoas.size() - 1).getId() + 1);
				} else {
					pessoa.setId(1);
				}

				System.out.printf("Digite o seu nome: ");
				pessoa.setNome(scn.next());

				System.out.printf("Digite o seu e-mail: ");
				pessoa.setEmail(scn.next());

				System.out.printf("Digite o seu cep: ");
				cep = scn.next();

				ViaCepService viacepservice = new ViaCepService();

				try {
					Endereco endereco = viacepservice.getEndereco(cep);

					System.out.println(endereco.imprimirEndereco());
					System.out.println("Este � o seu endere�o?(S/N)");
					resposta = scn.next().toUpperCase().charAt(0);

					while (resposta != 'S' && resposta != 'N') {
						System.out.println("Resposta inv�lida! Digite novamente(S/N): ");
						resposta = scn.next().toUpperCase().charAt(0);
					}

					if (resposta == 'S') {
						System.out.println("Informe o n�mero do logradouro: ");
						endereco.setNumero(scn.nextInt());

						System.out.println("Possui complemento?(S/N)");
						resposta = scn.next().toUpperCase().charAt(0);

						while (resposta != 'S' && resposta != 'N') {
							System.out.println("Resposta inv�lida! Digite novamente(S/N): ");
							resposta = scn.next().toUpperCase().charAt(0);
						}

						if (resposta == 'S') {
							System.out.println("Informe o complemento: ");
							endereco.setComplementoCasa(scn.next());
						}

						pessoa.setEndereco(endereco);

					} else {
						break;
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

				pessoas.add(pessoa);

				System.out.printf("Pessoa cadastrada com sucesso!");
				System.in.read();

			} else if (opcao == 2) {
				for (Pessoa p : pessoas) {
					System.out.printf(p.imprimirPessoa());
				}

				System.out.printf("\nDigite o id da pessoa que deseja atualizar: ");
				id = scn.nextInt();

				indexPessoa = -1;
				for (Pessoa p : pessoas) {
					if (p.getId() == id) {
						indexPessoa = pessoas.indexOf(p);
						break;
					}
				}

				if (indexPessoa != -1) {
					recebePessoa = pessoas.get(indexPessoa);

					System.out.print("Digite o seu novo nome: ");
					recebePessoa.setNome(scn.next());

					System.out.print("Digite o seu novo e-mail: ");
					recebePessoa.setEmail(scn.next());

					System.out.printf("Digite o seu novo cep: ");
					cep = scn.next();

					ViaCepService viacepservice = new ViaCepService();

					try {
						Endereco endereco = viacepservice.getEndereco(cep);

						System.out.println(endereco.imprimirEndereco());

						System.out.println("Informe o n�mero do logradouro: ");
						endereco.setNumero(scn.nextInt());

						System.out.println("Possui complemento?(S/N)");
						resposta = scn.next().toUpperCase().charAt(0);

						while (resposta != 'S' && resposta != 'N') {
							System.out.println("Resposta inv�lida! Digite novamente(S/N): ");
							resposta = scn.next().toUpperCase().charAt(0);
						}

						if (resposta == 'S') {
							System.out.println("Informe o complemento: ");
							endereco.setComplementoCasa(scn.next());
						}

						recebePessoa.setEndereco(endereco);

						System.out.printf("Cliente atualizado com sucesso!");

					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					System.out.printf("Pessoa n�o encontrada!");
				}

				System.in.read();

			} else if (opcao == 3) {
				for (Pessoa p : pessoas) {
					System.out.printf(p.imprimirPessoa());
				}

				System.out.printf("\nDigite o id do cliente que deseja remover: ");
				id = scn.nextInt();

				indexPessoa = -1;
				for (Pessoa p : pessoas) {
					if (p.getId() == id) {
						indexPessoa = pessoas.indexOf(p);
						break;
					}
				}

				if (indexPessoa != -1) {
					pessoas.remove(indexPessoa);

					System.out.printf("Cliente exclu�do com sucesso!");
				} else {
					System.out.printf("Cliente n�o encontrado!");
				}

				System.in.read();

			} else if (opcao == 4) {

				for (Pessoa p : pessoas) {
					System.out.printf(p.imprimirPessoa());

					System.in.read();
				}

			}

		} while ((opcao >= 1) && (opcao <= 4));

	}

}
