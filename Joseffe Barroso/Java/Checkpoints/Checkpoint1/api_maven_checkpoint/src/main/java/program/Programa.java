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
		int idFilme, idComentario, novoComentario=0;
		String nome;
		String comentario;

		FilmesService fs = new FilmesService();
		HashMap<Integer, Filme> filmes = fs.getFilmes();

		do {

			System.out.println("---------- Lista dos filmes em cartaz ----------\n\n");
			for (Integer f : filmes.keySet()) {
				System.out.println(filmes.get(f).getId() + " - " + filmes.get(f).getNome());
			}

			System.out.printf("\n---------- MENU ----------\n\n");
			System.out.printf("1 - Ver detalhes do filme" + "\n2 - Fazer um comentário do filme"
					+ "\n3 - Excluir um comentário" + "\n4 - Sair\n\n");
			
			System.out.println("Digite uma opcao: ");
			opcao = scn.nextInt();

			if (opcao == 1) {

				System.out.printf("Digite o ID do filme: ");
				idFilme = scn.nextInt();
				
				for(Integer f: filmes.keySet()) {
					if(idFilme == filmes.get(f).getId()) {
						System.out.println("Filme: " + filmes.get(f).getNome() 
								+"\nSinopse: " + filmes.get(f).getSinopse());
						
						if(filmes.get(f).getComentarios().size() != 0) {
							System.out.println("\nCOMENTÁRIOS: ");
							filmes.get(f).exibirComentarioCompleto();
						}else {
							System.out.println("Este filme ainda não possui comentários.");
						}
						break;
					}
				}
			}else if(opcao == 2) {
				System.out.printf("Digite o ID do filme: ");
				idFilme = scn.nextInt();
				System.out.println("Digite o seu nome: ");
				nome = scn.next();
				System.out.println("Digite o seu comentario: ");
				comentario = scn.next();
				
				novoComentario++;
				for(Integer f: filmes.keySet()) {
					if(idFilme == filmes.get(f).getId()) {
						filmes.get(f).addComentario(novoComentario,nome, comentario);
						System.out.println("Comentário adicionado com sucesso!");
						break;
					}
				}
				
			}else if(opcao == 3) {
				System.out.printf("Digite o ID do filme: ");
				idFilme = scn.nextInt();
				
				for(Integer f: filmes.keySet()) {
					if(idFilme == filmes.get(f).getId()) {
						System.out.println("Filme: " + filmes.get(f).getNome());
						
						if(filmes.get(f).getComentarios() != null) {
							System.out.println("COMENTÁRIOS: ");
							filmes.get(f).exibirComentarioCompleto();
							
							System.out.println("Digite o ID do comentário que gostaria de excluir: ");
							idComentario = scn.nextInt();
							
							filmes.get(f).deletarComentario(idComentario);
						}else {
							System.out.println("Filme sem comentários!");
						}
						break;
					}
				}
			}

		} while (opcao < 4);

	}

}
