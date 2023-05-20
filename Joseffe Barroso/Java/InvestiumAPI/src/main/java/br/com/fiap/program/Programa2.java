package br.com.fiap.program;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import br.com.fiap.bo.CategoriaBo;
import br.com.fiap.bo.ComentarioBo;
import br.com.fiap.bo.PostagemBo;
import br.com.fiap.bo.UsuarioBo;
import br.com.fiap.model.Categoria;
import br.com.fiap.model.Comentario;
import br.com.fiap.model.Postagem;
import br.com.fiap.model.Usuario;

public class Programa2 {

	public static void main(String[] args) throws SQLException, IOException, ParseException {

		Scanner scn = new Scanner(System.in);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		CategoriaBo categoriaBo = new CategoriaBo();
		PostagemBo postagemBo = new PostagemBo();
		ComentarioBo comentarioBo = new ComentarioBo();

		UsuarioBo usuarioBo = new UsuarioBo();

		Postagem postagemAtualizada = new Postagem();

		String email, senha;
		char resposta;
		Usuario usuarioLogado = null;
		ArrayList<Postagem> postagens;
		ArrayList<Comentario> comentarios;

		int opcao;

		do {
			System.out.printf("--------------- BEM-VINDO AO INVESTIUM ---------------\n\n");
			System.out.printf("1 - Fazer login\n" + "2 - Fazer cadastro\n" + "3 - Continuar sem logar\n"
					+ "4 - Esqueci a senha\n" + "5 - Sair do sistema\n");
			System.out.printf("Informe a opção desejada: ");
			opcao = scn.nextInt();

			while (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 5) {
				System.out.printf("\nOpção Inválida!\n");
				System.out.printf("Digite novamente: ");
				opcao = scn.nextInt();
			}

			if (opcao == 1) {
				System.out.printf("\n");
				System.out.printf("Informe o seu e-mail: ");
				email = scn.next();
				System.out.printf("Informe a sua senha: ");
				senha = scn.next();

				usuarioLogado = usuarioBo.getUsuario(email, senha);

				do {
					if (usuarioLogado != null) {
						if (usuarioLogado.getPapel().equals("comum")) {
							System.out.println("Usuário comum");
							System.out.printf("--------------- MENU ---------------\n\n");
							System.out.printf("1 - Acessar Blog\n" + "2 - Acessar empresas\n" + "19 - Sair\n");
							System.out.printf("Digite a opção desejada: ");
							opcao = scn.nextInt();

							while (opcao != 1 && opcao != 2 && opcao != 19) {
								System.out.printf("\nOpção Inválida!\n");
								System.out.printf("Digite novamente: ");
								opcao = scn.nextInt();
							}
							
							if(opcao == 1) {
								postagens = postagemBo.getAll();
								if(postagens.size() > 0) {
									for (Postagem p : postagens) {
										System.out.printf("ID: " + p.getId() + "\tPostagem: " + p.getTitulo() + "\n");
									}
									
									System.out.printf("Informe o ID da postagem que deseja visualizar: ");
									Postagem postagem = postagemBo.getPostagem(scn.nextInt());
									
									if(postagem != null) {
										System.out.printf(
												"\n-------------------------------------------------------------------------------------------\n");
										System.out.printf("\n\nTítulo: " + postagem.getTitulo() + "\n\nConte�do: "
												+ postagem.getConteudo() + "\n\nImagem URL: " + postagem.getImgUrl()
												+ "\n\nData: " + sdf.format(postagem.getDate()) + "\tCategoria: "
												+ postagem.getCategoria() + "\t\t\tLikes: " + postagem.getLikes());
										System.out.printf(
												"\n-------------------------------------------------------------------------------------------\n");
										
										comentarios = comentarioBo.getComentariosPostagem(postagem.getId());
										
										if(comentarios.size() > 0) {
											System.out.printf("Comentários:\n\n");
											for (Comentario c : comentarios) {
												System.out.printf(c.getUsuario().getNome() + ": " + c.getConteudo()
														+ "\t\tData: " + sdf.format(c.getData()) + "\n");
											}
											System.out.printf(
													"\n-------------------------------------------------------------------------------------------\n\n");
										}else {
											System.out.println("Não existe comentários existentes nessa postagem!\n\n");
										}
										
										System.out.printf("Curtiu essa postagem?(S/N): ");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padrão S/N!\n");
											System.out.printf("Digite novamente: ");
											resposta = scn.next().toUpperCase().charAt(0);
										}

										if (resposta == 'S') {
											postagem.setLikes(postagem.getLikes() + 1);
											postagemBo.updateLikes(postagem);
										}
										
										System.out.printf("\nGostaria de deixar um comentario nessa postagem?(S/N): ");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padrão S/N!\n");
											System.out.printf("Digite novamente: ");
											resposta = scn.next().toUpperCase().charAt(0);
										}

										if (resposta == 'S') {
											Comentario novoComentario = new Comentario();

											System.out.printf("Entre com o seu comentário: ");
											novoComentario.setConteudo(scn.next());
											novoComentario.setId(comentarioBo.maiorId() + 1);
											novoComentario.setData(new Date());
											novoComentario.setUsuario(usuarioLogado);
											novoComentario.setPostagem(postagem);

											comentarioBo.insert(novoComentario);
											System.out.println("Comentário realizado com sucesso!\n\n");
										}
										
									}else {
										System.out.println("ID inválido");
									}
								}
							}

						} else if (usuarioLogado.getPapel().equals("admin")) {
							// tinha o do aqui
							System.out.println("Usuário admin");
							System.out.printf("-------------- MENU ADM -------------\n\n");
							System.out.printf("--------------- BLOG ---------------\n");
							System.out.printf("1 - Cadastrar postagem\n" + "2 - Atualizar postagem\n"
									+ "3 - Excluir postagem\n" + "4 - Listar postagens\n" + "5 - Criar categoria\n"
									+ "6 - Atualizar categoria\n" + "7 - Excluir categoria\n"
									+ "8 - Listar categorias\n" + "9 - Listar coment�rios\n"
									+ "10 - Excluir coment�rio\n\n");
							System.out.printf("--------------- EMPRESAS ---------------\n");
							System.out.printf("11 - Cadastrar empresa\n" + "12 - Atulaizar dados da empresa\n"
									+ "13 - Excluir empresa\n" + "14 - Listar empresas\n\n");
							System.out.printf("--------------- USUARIOS ---------------\n");
							System.out.printf("15 - Listar usu�rios\n" + "16 - Editar usu�rio admin\n"
									+ "17 - Excluir usu�rios\n" + "18 - Cadastrar usu�rio administrativo\n");
							System.out.printf("19 - Sair\n");
							System.out.printf("Informe a op��o desejada: ");
							opcao = scn.nextInt();

							if (opcao == 1) {
								Postagem postagem = new Postagem();

								System.out.printf("\n------- CADASTRO DE POSTAGEM --------\n\n");
								System.out.printf("Título da postagem: ");
								postagem.setTitulo(scn.next());
								System.out.printf("Conteúdo da postagem: ");
								postagem.setConteudo(scn.next());
								System.out.printf("URL da imagem: ");
								postagem.setImgUrl(scn.next());
								postagem.setDate(new Date());
								postagem.setId(postagemBo.getMaiorIdPostagem() + 1);

								for (Categoria c : categoriaBo.getAll()) {
									System.out.printf("ID: " + c.getId() + "Categoria: " + c.getDescricao() + "\n");
								}

								System.out.printf("\nInforme o ID da categoria que deseja cadastrar a postagem: ");
								postagem.setCategoria(categoriaBo.getCategoria(scn.nextInt()));

								postagemBo.insert(postagem);
								System.out.printf("Postagem cadastrada com sucesso!\n\n");
							} else if (opcao == 2) {
								for (Postagem p : postagemBo.getAll()) {
									System.out.printf("ID: " + p.getId() + "\tPostagem: " + p.getTitulo()
											+ "\tCategoria: " + p.getCategoria().getDescricao() + "\n");
								}

								System.out.printf("Informe o ID da postagem que deseja atualizar: ");
								postagemAtualizada = postagemBo.getPostagem(scn.nextInt());

								if (postagemAtualizada != null) {

									System.out.printf("Digite o novo t�tulo: ");
									postagemAtualizada.setTitulo(scn.next());
									System.out.printf("Digite o novo conte�do: ");
									postagemAtualizada.setConteudo(scn.next());
									System.out.printf("Digite a nova url da imagem: ");
									postagemAtualizada.setImgUrl(scn.next());

									System.out.printf("Gostaria de atualizar a categoria da postagem: ");
									resposta = scn.next().toUpperCase().charAt(0);

									while (resposta != 'S' && resposta != 'N') {
										System.out.printf("Utilizar padr�o S/N!");
										System.out.printf("Digite novamente: ");
										resposta = scn.next().toUpperCase().charAt(0);
									}

									if (resposta == 'S') {
										for (Categoria c : categoriaBo.getAll()) {
											System.out.printf(
													"ID: " + c.getId() + "Categoria: " + c.getDescricao() + "\n");
										}

										System.out.printf(
												"\nInforme o ID da categoria que deseja cadastrar a postagem: ");
										postagemAtualizada.setCategoria(categoriaBo.getCategoria(scn.nextInt()));
									}

									postagemBo.update(postagemAtualizada);
									System.out.println("Postagem atualizada com sucesso");

								} else {
									System.out.println("Postagem não encontrada");
								}

							} else if (opcao == 3) {
								for (Postagem p : postagemBo.getAll()) {
									System.out.printf("ID: " + p.getId() + "\tPostagem: " + p.getTitulo()
											+ "\tCategoria: " + p.getCategoria() + "\n");
								}

								System.out.printf("Informe o ID da postagem que deseja excluir: ");
								postagemBo.delete(scn.nextInt());
							} else if (opcao == 4) {
								for (Postagem p : postagemBo.getAll()) {
									System.out.printf("ID: " + p.getId() + "\tPostagem: " + p.getTitulo()
											+ "\tCategoria: " + p.getCategoria().getDescricao() + "\n");
								}

								System.out.printf("Deseja ver uma postagem com mais detalhe?(S/N)");
								resposta = scn.next().toUpperCase().charAt(0);

								while (resposta != 'S' && resposta != 'N') {
									System.out.printf("Utilizar padrão S/N!\n");
									System.out.printf("Deseja ver uma postagem com mais detalhe?(S/N)");
									resposta = scn.next().toUpperCase().charAt(0);
								}

								if (resposta == 'S') {
									Postagem p = new Postagem();
									System.out.printf("\nInforme o ID da postagem que deseja visualizar: ");
									p = postagemBo.getPostagem(scn.nextInt());

									if (p != null) {
										System.out.printf(
												"\n-------------------------------------------------------------------------------------------\n");
										System.out.printf("\n\nTítulo: " + p.getTitulo() + "\n\nConteúdo: "
												+ p.getConteudo() + "\n\nImagem URL: " + p.getImgUrl() + "\n\nData: "
												+ sdf.format(p.getDate()) + "\tCategoria: "
												+ p.getCategoria().getDescricao() + "\t\t\tLikes: " + p.getLikes());
										System.out.printf(
												"\n-------------------------------------------------------------------------------------------\n");

									} else {
										System.out.println("ID inválido!");
									}

								}
							} else if (opcao == 5) {
								Categoria categoria = new Categoria();

								categoria.setId(categoriaBo.getMaiorIdCategoria() + 1);
								System.out.println("Digite o nome da categoria");
								categoria.setDescricao(scn.next());

								categoriaBo.insert(categoria);
							} else if (opcao == 6) {
								for (Categoria c : categoriaBo.getAll()) {
									System.out.printf("ID: " + c.getId() + "Categoria: " + c.getDescricao() + "\n");
								}

								System.out.printf("\nInforme o ID da categoria que deseja atualizar: ");
								Categoria categoria = categoriaBo.getCategoria(scn.nextInt());

								if (categoria != null) {
									System.out.println("Digite uma nova categoria: ");
									categoria.setDescricao(scn.next());

									categoriaBo.update(categoria);
								} else {
									System.out.println("ID inválido");
								}

							} else if (opcao == 7) {
								for (Categoria c : categoriaBo.getAll()) {
									System.out.printf("ID: " + c.getId() + "Categoria: " + c.getDescricao() + "\n");
								}

								System.out.printf("\nInforme o ID da categoria que deseja excluir: ");
								categoriaBo.delete(scn.nextInt());
							} else if (opcao == 8) {
								for (Categoria c : categoriaBo.getAll()) {
									System.out.printf("ID: " + c.getId() + "Categoria: " + c.getDescricao() + "\n");
								}
							}
							// TINHA UM WHILE DO DO WHILE AQUI
						} else {
							System.out.println("Usuario não encontrado.");
						}
					}
				} while (opcao != 19);
			}
		} while (opcao <= 4);
	}

}
