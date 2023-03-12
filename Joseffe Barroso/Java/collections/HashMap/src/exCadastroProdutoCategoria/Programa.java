package exCadastroProdutoCategoria;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Programa {

	public static void main(String[] args) throws IOException {
		Scanner scn = new Scanner(System.in);

		int opcao, id, idProduto=0;
		char resposta;
		Produto produtoAtualizado;

		HashMap<Integer, Produto> produtos = new HashMap<Integer, Produto>();

		do {
			System.out.printf("--- SISTEMA DE CADASTRO DE PRODUTO ---\n\n");
			System.out.printf("Escolha uma opção: \n");
			System.out.println("1 - Cadastrar produto\n" + "2 - Atualizar produto\n" + "3 - Excluir produto\n"
					+ "4 - Consultar produtos\n" + "5 - Sair");
			opcao = scn.nextInt();

			if (opcao == 1) {
				Produto produto = new Produto();

				idProduto++;
				produto.setId(idProduto);

				System.out.printf("Digite o nome do produto: ");
				produto.setNome(scn.next());
				System.out.printf("Digite o preço do produto: ");
				produto.setPreco(scn.nextDouble());
				System.out.printf("Digite a quantidade do produto: ");
				produto.setQuantidade(scn.nextDouble());
				;

				System.out.printf("Este produto possui uma categoria? (S/N)");
				resposta = scn.next().toUpperCase().charAt(0);

				if (resposta == 'S') {
					Categoria categoria = new Categoria();

					System.out.printf("Digite o id da categoria: ");
					categoria.setId(scn.nextInt());
					System.out.printf("Digite o nome da categoria: ");
					categoria.setNome(scn.next());

					produto.setCategoria(categoria);
				} else {
					produto.setCategoria(null);
				}

				produtos.put(idProduto, produto);

				System.out.printf("Produto incluído com sucesso!");
				System.in.read();

			} else if (opcao == 2) {
				produtos.forEach((chave, valor) -> {
					System.out.println("ID: " + chave + " - " + valor.exibirNomePreco());
				});

				System.out.printf("\n\nDigite o ID do produto que deseja atualizar: ");
				id = scn.nextInt();

				if (produtos.containsKey(id)) {
					produtoAtualizado = produtos.get(id);

					System.out.printf("Digite o seu novo nome: ");
					produtoAtualizado.setNome(scn.next());
					System.out.printf("Digite o seu novo preço: ");
					produtoAtualizado.setPreco(scn.nextDouble());
					System.out.printf("Digite a sua nova quantidade: ");
					produtoAtualizado.setQuantidade(scn.nextDouble());

					System.out.printf("Produto atualizado com sucesso!");
				} else {
					System.out.printf("Produto não encontrado!");
				}

				System.in.read();

			} else if (opcao == 3) {
				produtos.forEach((chave, valor) -> {
					System.out.println("ID: " + chave + " - " + valor.exibirNomePreco());
				});
				

				System.out.printf("\n\nDigite o ID do produto que deseja excluir: ");
				id = scn.nextInt();

				if (produtos.containsKey(id)) {
					produtos.remove(id);
					System.out.printf("Produto excluído com sucesso!");
				} else {
					System.out.printf("Produto não encontrado!");
				}

				System.in.read();

			} else if (opcao == 4) {

				produtos.forEach((chave, valor) -> {
	                   System.out.println("ID: " + chave + " - " + valor.exibirNomePreco());
	                   
	                   if (valor.getCategoria() != null)
	                	   System.out.println(valor.exibirProdutoCategoria());
	                 });

				System.in.read();
			}
		} while (opcao >= 1 && opcao <= 4);

	}

}
