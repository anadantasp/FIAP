package program;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.http.client.ClientProtocolException;

import model.Filme;
import service.FilmesService;

public class Programa {

	public static void main(String[] args) throws ClientProtocolException, IOException {

		Scanner scn = new Scanner(System.in);

		int opcao = 0;
		int idFilme, idComentario;
		String nome;
		String comentario;

		FilmesService fs = new FilmesService();
		HashMap<Integer, Filme> filmes = fs.getFilmes();

		do {

			System.out.printf("\n---------- Lista dos filmes em cartaz ----------\n\n");
			for (Integer filme : filmes.keySet()) {
				System.out.println(filmes.get(filme).getId() + " - " + filmes.get(filme).getNome());
			}

			System.out.printf("\n---------- MENU ----------\n\n");
			System.out.printf("1 - Ver detalhes do filme" + "\n2 - Fazer um comentário do filme"
					+ "\n3 - Excluir um comentário" + "\n4 - Sair\n\n");
			
			System.out.printf("Digite uma opcao: ");
			opcao = scn.nextInt();

			if (opcao == 1) {

				System.out.printf("Digite o ID do filme: ");
				idFilme = scn.nextInt();
				
				for(Integer filme: filmes.keySet()) {
					if(idFilme == filmes.get(filme).getId()) {
						System.out.printf("Filme: " + filmes.get(filme).getNome() 
								+"\nSinopse: " + filmes.get(filme).getSinopse());
						
						if(filmes.get(filme).getComentarios().size() != 0) {
							System.out.printf("\nCOMENTÁRIOS: ");
							filmes.get(filme).exibirComentario();
						}else {
							System.out.printf("\nEste filme ainda não possui comentários.\n\n");
						}
						break;
					}
				}
			}else if(opcao == 2) {
				System.out.printf("Digite o ID do filme: ");
				idFilme = scn.nextInt();
				System.out.printf("Digite o seu nome: ");
				nome = scn.next();
				System.out.printf("Digite o seu comentario: ");
				comentario = scn.next();

				for(Integer filme: filmes.keySet()) {
					if(idFilme == filmes.get(filme).getId()) {
						filmes.get(filme).addComentario(nome, comentario);
						System.out.printf("\nComentário adicionado com sucesso!\n");
						break;
					}
				}
				
			}else if(opcao == 3) {
				System.out.printf("Digite o ID do filme: ");
				idFilme = scn.nextInt();
				
				for(Integer filme: filmes.keySet()) {
					if(idFilme == filmes.get(filme).getId()) {
						System.out.printf("Filme: " + filmes.get(filme).getNome());
						
						if(filmes.get(filme).getComentarios() != null) {
							System.out.printf("\nCOMENTÁRIOS: ");
							filmes.get(filme).exibirComentario();;
							
							System.out.printf("\nDigite o ID do comentário que gostaria de excluir: ");
							idComentario = scn.nextInt();
							
							filmes.get(filme).deletarComentario(idComentario);
						}else {
							System.out.printf("\nFilme sem comentários!");
						}
						break;
					}
				}
			}

		} while (opcao < 4);

	}

}
