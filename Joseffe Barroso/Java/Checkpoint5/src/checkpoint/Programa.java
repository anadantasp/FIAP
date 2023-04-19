package checkpoint;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Programa {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		char resposta = 'S';
		int quantidadeProdutosPedido;

		String line = "";
		double total = 0;
		double totalPedido = 0;
		String nomeCliente = "";

		String path = "C:\\arquivoscheckpoint\\pedidos.txt";
		String arquivoTotalPedidos = "C:\\arquivoscheckpoint\\total_pedidos.txt";

		System.out.println("---------- CADASTRO DE PEDIDOS ----------");
		do {
			Cliente cliente = new Cliente();

			System.out.printf("Digite o nome do cliente: ");
			cliente.setNome(scn.next());
			System.out.printf("Quantos produtos deseja comprar?");
			quantidadeProdutosPedido = scn.nextInt();

			for (int i = 0; i < quantidadeProdutosPedido; i++) {
				Produto produto = new Produto();

				System.out.printf("Digite o nome do produto: ");
				produto.setNome(scn.next());
				System.out.printf("Digite quantidade desejada: ");
				produto.setQuantidade(scn.nextDouble());
				System.out.printf("Digite o preço unitário do produto: ");
				produto.setPrecoUnitario(scn.nextDouble());

				cliente.addProdutos(produto);
			}

			clientes.add(cliente);

			System.out.println("Deseja cadastrar um novo pedido?");
			resposta = scn.next().toUpperCase().charAt(0);

		} while (resposta == 'S');

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
			for (Cliente c : clientes) {

				bw.write(c.getNome());
				bw.newLine();

				if (c.getProdutos() != null) {
					for (Produto p : c.getProdutos()) {
						bw.write(p.getNome() + "," + Double.toString(p.getQuantidade()) + ","
								+ Double.toString(p.getPrecoUnitario()));
						bw.newLine();
					}
				}

				bw.newLine();
				bw.newLine();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedReader br = new BufferedReader(new FileReader(path));

				BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTotalPedidos, true))) {

			line = br.readLine();

			while (line != null) {

				if (line.contains(",")) {
					while(line.contains(",")) {
						String[] dados = line.split(",");
						total = Double.parseDouble(dados[1]) * Double.parseDouble(dados[2]);
						totalPedido += total;
						line = br.readLine();
					}
					bw.write(nomeCliente + " - R$" + String.format("%.2f", totalPedido));
					bw.newLine();
					line = br.readLine();
					totalPedido = 0;
					
				}else {
					nomeCliente = line;
					line = br.readLine();
				}
			}
		} catch (IOException e) {
			System.out.println("Erro ao escrever no arquivo - " + e.getMessage());
		}

	}

}
