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
import br.com.fiap.bo.EmpresaBo;
import br.com.fiap.bo.PostagemBo;
import br.com.fiap.bo.UsuarioBo;
import br.com.fiap.model.Categoria;
import br.com.fiap.model.Comentario;
import br.com.fiap.model.Empresa;
import br.com.fiap.model.Governanca;
import br.com.fiap.model.PessoaGovernanca;
import br.com.fiap.model.Postagem;
import br.com.fiap.model.Usuario;

public class Programa {

	public static void main(String[] args) throws SQLException, IOException, ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		Scanner scn = new Scanner(System.in);

		UsuarioBo usuarioBo = new UsuarioBo();
		PostagemBo postagemBo = new PostagemBo();
		ComentarioBo comentarioBo = new ComentarioBo();
		EmpresaBo empresaBo = new EmpresaBo();
		CategoriaBo categoriaBo = new CategoriaBo();

		Usuario usuarioLogado = null, usuario;
		Postagem postagem = null;
		Comentario comentario = null;
		Empresa empresa = null;
		ArrayList<Postagem> postagens;
		ArrayList<Empresa> empresas;
		ArrayList<Governanca> governancas;

		int opcao;
		String email, senha;
		int id, idEmpresa;
		char resposta, empresaJaExistente;

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

				if (usuarioLogado != null) {
					if (usuarioLogado.getPapel().equals("comum")) {
						System.out.printf("--------------- MENU ---------------\n\n");
						System.out.printf("1 - Acessar Blog\n" + "2 - Acessar empresas\n" + "19 - Sair\n");
						System.out.printf("Digite a opção desejada: ");
						opcao = scn.nextInt();

						while (opcao != 1 && opcao != 2 && opcao != 19) {
							System.out.printf("\nOpção Inválida!\n");
							System.out.printf("Digite novamente: ");
							opcao = scn.nextInt();
						}

						if (opcao == 1) {
							postagens = postagemBo.getAll();
							if (postagens.size() > 0) {
								for (Postagem p : postagens) {
									System.out.printf("ID: " + p.getId() + "\tPostagem: " + p.getTitulo() + "\n");
								}

								System.out.printf("Informe o ID da postagem que deseja visualizar: ");
								id = scn.nextInt();

								postagem = postagemBo.getPostagem(id);

								if (postagem != null) {
									System.out.printf(
											"\n-------------------------------------------------------------------------------------------\n");
									System.out.printf("\n\nTítulo: " + postagem.getTitulo() + "\n\nConte�do: "
											+ postagem.getConteudo() + "\n\nImagem URL: " + postagem.getImgUrl()
											+ "\n\nData: " + sdf.format(postagem.getDate()) + "\tCategoria: "
											+ postagem.getCategoria() + "\t\t\tLikes: " + postagem.getLikes());
									System.out.printf(
											"\n-------------------------------------------------------------------------------------------\n");

									if (postagem.getComentarios() != null && postagem.getComentarios().size() > 0) {
										System.out.printf("Comentários:\n\n");
										for (Comentario c : postagem.getComentarios()) {
											System.out.printf(c.getUsuario().getNome() + ": " + c.getConteudo()
													+ "\t\tData: " + sdf.format(c.getData()) + "\n");
										}
										System.out.printf(
												"\n-------------------------------------------------------------------------------------------\n\n");
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
								}
							}
						} else if (opcao == 2) {
							System.out.printf("--------------- MENU ---------------\n\n");
							System.out.printf("1 - Consultar Empresas\n" + "2 - Listar Empresas Salvas\n"
									+ "3 - Editar Empresas Salvas\n" + "19 - Sair\n");
							System.out.printf("Digite a opção desejada: ");
							opcao = scn.nextInt();

							while ((opcao != 1) && (opcao != 2) && (opcao != 3) && (opcao != 19) && (opcao != 15)) {
								System.out.printf("Opção inválida!");
								System.out.printf("Digite novamente: ");
								opcao = scn.nextInt();
							}

							empresas = empresaBo.getAll();

							if (empresas.size() > 0) {
								if (opcao == 1) {
									for (Empresa e : empresas) {
										System.out.printf("ID: " + e.getId() + "\tEmpresa: " + e.getNome() + "\n");
									}

									System.out.printf("\nGostaria de ver alguma empresa com mais detalhes?");
									resposta = scn.next().toUpperCase().charAt(0);

									while (resposta != 'S' && resposta != 'N') {
										System.out.printf("Utilizar padrão S/N!\n");
										System.out.printf("Digite novamente: ");
										resposta = scn.next().toUpperCase().charAt(0);
									}

									if (resposta == 'S') {
										do {
											System.out.printf(
													"Informe o ID da empresa que deseja ver com mais detalhes: ");
											empresa = empresaBo.getEmpresa(scn.nextInt());

											if (empresa != null) {
												System.out.printf(
														"\n-------------------------------------------------------------------------------------------\n\n");
												System.out.printf(
														"ID: " + empresa.getId() + "\tEmpresa: " + empresa.getNome()
																+ "\tSetor: " + empresa.getSetor().getDescricao()
																+ "\tPossui IPO: " + empresa.getAtivoIpo());

												System.out.printf("\n\n--------------- GOVERNAN�A ---------------\n\n");

												for (Governanca g : empresa.getGovernancas()) {
													System.out.printf("Período: " + sdf.format(g.getDtInicio()) + "\t\t"
															+ sdf.format(g.getDtFim()) + "\n\n");

													for (PessoaGovernanca pessoaGovernanca : g.getPessoasGovernanca()) {
														System.out.printf("Nome: " + pessoaGovernanca.getNome()
																+ "\tCargo: " + pessoaGovernanca.getCargo() + "\n");
													}

												}

												/*
												 * 
												 * if (empresa.getBalancos().size() > 0) { System.out
												 * .printf("\n\n--------------- BALAN�OS ---------------\n\n"); for
												 * (Balanco b : empresa.getBalancos()) { System.out.printf("Per�odo: " +
												 * sdf.format(b.getDtInicio()) + "\t\t" + sdf.format(b.getDtFinal()) +
												 * "\n\n"); System.out.printf("\n\nAtivos: \n"); for (Ativo a :
												 * b.getAtivos()) { System.out.printf("ID: " + a.getId() + "\tNome: " +
												 * a.getDescricao() + "\tTipo: " + a.getTipo() + "\tValor: R$" +
												 * a.getValor() + "\n"); }
												 * 
												 * System.out.printf("\n\nPassivos: \n"); for (Passivo p :
												 * b.getPassivos()) { System.out.printf( "ID: " + p.getId() + "\tNome: "
												 * + p.getDescricao() + "\tValor: R$" + p.getValor() + "\n"); }
												 * 
												 * System.out.printf(
												 * "\n-------------------------------------------------------------------------------------------\n"
												 * ); System.out.printf( "Patrim�nio l�quido: R$" +
												 * b.getPatrimonioLiquido()); } } else { System.out.printf(
												 * "\nN�o existe balan�os cadastrados para essa empresa ainda!"); }
												 * 
												 */

												System.out.printf("\n\n--------------- IPO ---------------\n\n");
												System.out.printf("Descrição:" + empresa.getDescricaoIpo()
														+ "\tValor Inicial: " + empresa.getValorInicialIpo());

												System.out.printf(
														"\n\nGostaria de salvar essa empresa na sua lista de favoritos?\n");
												resposta = scn.next().toUpperCase().charAt(0);

												while (resposta != 'S' && resposta != 'N') {
													System.out.printf("Utilizar padrão S/N!\n");
													System.out.printf("Digite novamente: ");
													resposta = scn.next().toUpperCase().charAt(0);
												}

												if (resposta == 'S') {
													Empresa empresaSalva = usuarioBo.getEmpresaSalva(empresa.getId(),
															usuarioLogado.getEmail());

													if (empresaSalva != null) {
														usuarioLogado.addEmpresa(empresaSalva);
														System.out.printf("Empresa adicionada com sucesso!\n\n");
													} else {
														System.out.println(
																"Empresa já consta na sua lista de favoritos!\n\n");
													}
												}

											} else {
												System.out.printf("Empresa não encontrada!\n\n");
											}

											System.out.printf(
													"\n-------------------------------------------------------------------------------------------\n\n");
											System.out.printf("Gostaria de ver outra empresa?");
											resposta = scn.next().toUpperCase().charAt(0);

											while (resposta != 'S' && resposta != 'N') {
												System.out.printf("Utilizar padrão S/N!\n");
												System.out.printf("Digite novamente: ");
												resposta = scn.next().toUpperCase().charAt(0);
											}
										} while (resposta == 'S');
									}

									System.in.read();

								} else if (opcao == 2) {

									System.out.println(
											"---------------------- LISTA DE EMPRESAS SALVAS -----------------------");
									for (Empresa empresaSalva : usuarioBo.getEmpresasSalvas(usuarioLogado.getEmail())) {
										System.out.print("ID: " + empresaSalva.getId() + "Empresa: "
												+ empresaSalva.getNome() + "\n\n");
									}
								} else if (opcao == 3) {
									System.out.println(
											"---------------------- LISTA DE EMPRESAS SALVAS -----------------------");
									for (Empresa empresaSalva : usuarioBo.getEmpresasSalvas(usuarioLogado.getEmail())) {
										System.out.print("ID: " + empresaSalva.getId() + "Empresa: "
												+ empresaSalva.getNome() + "\n\n");
									}

									System.out
											.printf("\nGostaria de excluir alguma empresa da sua lista de favoritos?");
									resposta = scn.next().toUpperCase().charAt(0);

									while (resposta != 'S' && resposta != 'N') {
										System.out.printf("Utilizar padrão S/N!\n");
										System.out.printf("Digite novamente: ");
										resposta = scn.next().toUpperCase().charAt(0);
									}

									if (resposta == 'S') {
										System.out.printf("Informe o ID da empresa que deseja excluir: ");
										idEmpresa = scn.nextInt();
										usuarioBo.ExcluirEmpresaDosSalvos(idEmpresa);
									}
								}
							}

						}
					} else {
						System.out.printf("-------------- MENU ADM -------------\n\n");
						System.out.printf("--------------- BLOG ---------------\n");
						System.out.printf("1 - Cadastrar postagem\n" + "2 - Atualizar postagem\n"
								+ "3 - Excluir postagem\n" + "4 - Listar postagens\n" + "5 - Criar categoria\n"
								+ "6 - Atualizar categoria\n" + "7 - Excluir categoria\n" + "8 - Listar categorias\n"
								+ "9 - Listar coment�rios\n" + "10 - Excluir coment�rio\n\n");
						System.out.printf("--------------- EMPRESAS ---------------\n");
						System.out.printf("11 - Cadastrar empresa\n" + "12 - Atulaizar dados da empresa\n"
								+ "13 - Excluir empresa\n" + "14 - Listar empresas\n\n");
						System.out.printf("--------------- USUARIOS ---------------\n");
						System.out.printf("15 - Listar usu�rios\n" + "16 - Editar usu�rio admin\n"
								+ "17 - Excluir usu�rios\n" + "18 - Cadastrar usu�rio administrativo\n");
						System.out.printf("19 - Sair\n");
						System.out.printf("Informe a op��o desejada: ");
						opcao = scn.nextInt();

						if (opcao == 1) { // CRIAR POSTAGEM
							postagem = new Postagem();

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
								System.out.printf("ID: " + p.getId() + "\tPostagem: " + p.getTitulo() + "\tCategoria: "
										+ p.getCategoria() + "\n");
							}

							System.out.printf("Informe o ID da postagem que deseja atualizar: ");
							postagem = postagemBo.getPostagem(scn.nextInt());

							if (postagem != null) {

								System.out.printf("Digite o novo t�tulo: ");
								postagem.setTitulo(scn.next());
								System.out.printf("Digite o novo conte�do: ");
								postagem.setConteudo(scn.next());
								System.out.printf("Digite a nova url da imagem: ");
								postagem.setImgUrl(scn.next());

								System.out.printf("Gostaria de atualizar a categoria da postagem: ");
								resposta = scn.next().toUpperCase().charAt(0);

								while (resposta != 'S' && resposta != 'N') {
									System.out.printf("Utilizar padr�o S/N!");
									System.out.printf("Digite novamente: ");
									resposta = scn.next().toUpperCase().charAt(0);
								}

								if (resposta == 'S') {
									for (Categoria c : categoriaBo.getAll()) {
										System.out.printf("ID: " + c.getId() + "Categoria: " + c.getDescricao() + "\n");
									}

									System.out.printf("\nInforme o ID da categoria que deseja cadastrar a postagem: ");
									postagem.setCategoria(categoriaBo.getCategoria(scn.nextInt()));
								}

								postagemBo.update(postagem);
								System.out.println("Postagem atualizada com sucesso");

							} else {
								System.out.println("Postagem não encontrada");
							}

						} else if (opcao == 3) {
							for (Postagem p : postagemBo.getAll()) {
								System.out.printf("ID: " + p.getId() + "\tPostagem: " + p.getTitulo() + "\tCategoria: "
										+ p.getCategoria() + "\n");
							}

							System.out.printf("Informe o ID da postagem que deseja excluir: ");
							postagemBo.delete(scn.nextInt());
						} else if (opcao == 4) {
							for (Postagem p : postagemBo.getAll()) {
								System.out.printf("ID: " + p.getId() + "\tPostagem: " + p.getTitulo() + "\tCategoria: "
										+ p.getCategoria() + "\n");
							}

							System.out.printf("Deseja ver uma postagem com mais detalhe?(S/N)");
							resposta = scn.next().toUpperCase().charAt(0);

							while (resposta != 'S' && resposta != 'N') {
								System.out.printf("Utilizar padrão S/N!\n");
								System.out.printf("Deseja ver uma postagem com mais detalhe?(S/N)");
								resposta = scn.next().toUpperCase().charAt(0);
							}
							
							if(resposta == 'S') {
								System.out.printf("\nInforme o ID da postagem que deseja visualizar: ");
								postagem = postagemBo.getPostagem(scn.nextInt());
								
								if(postagem != null) {
									System.out.printf(
											"\n-------------------------------------------------------------------------------------------\n");
									System.out.printf("\n\nTítulo: " + postagem.getTitulo() + "\n\nConteúdo: "
											+ postagem.getConteudo() + "\n\nImagem URL: " + postagem.getImgUrl()
											+ "\n\nData: " + sdf.format(postagem.getDate()) + "\tCategoria: "
											+ postagem.getCategoria() + "\t\t\tLikes: " + postagem.getLikes());
									System.out.printf(
											"\n-------------------------------------------------------------------------------------------\n");

									if (postagem.getComentarios() != null && postagem.getComentarios().size() > 0) {
										System.out.printf("Comentários:\n\n");
										for (Comentario c : postagem.getComentarios()) {
											System.out.printf(c.getUsuario().getNome() + ": " + c.getConteudo()
													+ "\t\tData: " + sdf.format(c.getData()) + "\n");
										}
										System.out.printf(
												"\n-------------------------------------------------------------------------------------------\n\n");
									}
								}else {
									System.out.println("ID inválido!");
								}
								
							}
						}else if(opcao == 5) {
							Categoria categoria = new Categoria();
							
							categoria.setId(categoriaBo.getMaiorIdCategoria() + 1);
							System.out.println("Digite o nome da categoria");
							categoria.setDescricao(scn.next());
							
							categoriaBo.insert(categoria);
						}else if(opcao == 6) {
							for (Categoria c : categoriaBo.getAll()) {
								System.out.printf("ID: " + c.getId() + "Categoria: " + c.getDescricao() + "\n");
							}

							System.out.printf("\nInforme o ID da categoria que deseja atualizar: ");
							Categoria categoria = categoriaBo.getCategoria(scn.nextInt());
							
							if(categoria != null) {
								System.out.println("Digite uma nova categoria: ");
								categoria.setDescricao(scn.next());
								
								categoriaBo.update(categoria);
							}else {
								System.out.println("ID inválido");
							}
							
						}else if(opcao == 7) {
							for (Categoria c : categoriaBo.getAll()) {
								System.out.printf("ID: " + c.getId() + "Categoria: " + c.getDescricao() + "\n");
							}

							System.out.printf("\nInforme o ID da categoria que deseja excluir: ");
							categoriaBo.delete(scn.nextInt());
						}else if(opcao == 8) {
							for (Categoria c : categoriaBo.getAll()) {
								System.out.printf("ID: " + c.getId() + "Categoria: " + c.getDescricao() + "\n");
							}
						}

					} // FIM MENU ADM
				}
			} else if (opcao == 2) {
				usuario = null;

				System.out.printf("Digite seu e-mail: ");
				email = scn.next();

				usuario = usuarioBo.getUsuario(email);

				if (usuario != null) {
					System.out.printf("Usuário já existente!");
				} else {
					usuario = new Usuario();
					usuario.setEmail(email);
					System.out.printf("Digite seu nome: ");
					usuario.setNome(scn.next());
					System.out.printf("Digite sua data de nascimento(DD/MM/YYYY): ");
					usuario.setDtNascimento(sdf.parse(scn.next()));

					usuario.setPapel("comum");

					usuarioBo.insert(usuario);
				}

			} else if (opcao == 3) {
				System.out.printf("--------------- MENU ---------------\n\n");
				System.out.printf("1 - Acessar Blog\n" + "2 - Acessar empresas\n" + "3 - Sair\n");
				System.out.printf("Digite a opção desejada: ");
				opcao = scn.nextInt();

				while (opcao != 1 && opcao != 2 && opcao != 3) {
					System.out.printf("\nOpção Inv�lida!\n");
					System.out.printf("Digite novamente: ");
					opcao = scn.nextInt();
				}

				if (opcao == 1) {
					postagens = postagemBo.getAll();
					if (postagens.size() > 0) {
						for (Postagem p : postagens) {
							System.out.printf("ID: " + p.getId() + "\tPostagem: " + p.getTitulo() + "\n");
						}

						System.out.printf("Informe o ID da postagem que deseja visualizar: ");
						id = scn.nextInt();

						postagem = postagemBo.getPostagem(id);

						if (postagem != null) {
							System.out.printf(
									"\n-------------------------------------------------------------------------------------------\n");
							System.out.printf("\n\nTítulo: " + postagem.getTitulo() + "\n\nConte�do: "
									+ postagem.getConteudo() + "\n\nImagem URL: " + postagem.getImgUrl() + "\n\nData: "
									+ sdf.format(postagem.getDate()) + "\tCategoria: " + postagem.getCategoria()
									+ "\t\t\tLikes: " + postagem.getLikes());
							System.out.printf(
									"\n-------------------------------------------------------------------------------------------\n");

							if (postagem.getComentarios() != null && postagem.getComentarios().size() > 0) {
								System.out.printf("Comentários:\n\n");
								for (Comentario c : postagem.getComentarios()) {
									System.out.printf(c.getUsuario().getNome() + ": " + c.getConteudo() + "\t\tData: "
											+ sdf.format(c.getData()) + "\n");
								}
								System.out.printf(
										"\n-------------------------------------------------------------------------------------------\n\n");

							}

							System.out.printf("Para curtir e comentar é necessário estar logado!\n");
							System.out.printf("Gostaria de realizar o login/cadastro?(S/N)");
							resposta = scn.next().toUpperCase().charAt(0);

							while (resposta != 'S' && resposta != 'N') {
								System.out.printf("Utilizar padr�o S/N!\n");
								System.out.printf("Digite novamente: ");
								resposta = scn.next().toUpperCase().charAt(0);
							}

							if (resposta == 'S') {
								System.out.printf("Já possui conta?(S/N)");
								resposta = scn.next().toUpperCase().charAt(0);

								while (resposta != 'S' && resposta != 'N') {
									System.out.printf("Utilizar padrão S/N!\n");
									System.out.printf("Digite novamente: ");
									resposta = scn.next().toUpperCase().charAt(0);
								}

								if (resposta == 'S') {
									System.out.printf("Informe o seu e-mail: ");
									email = scn.next();
									System.out.printf("Informe a sua senha: ");
									senha = scn.next();

									usuarioLogado = usuarioBo.getUsuario(email, senha);

									if (usuarioLogado != null) {

										System.out.printf("Curtiu essa postagem?(S/N)");
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

										System.out.printf("\nGostaria de deixar um comentario nessa postagem?(S/N)");
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
									} else {
										System.out.printf("Usuário não encontrado");
									}
								} else {

									System.out.printf("Digite seu e-mail: ");
									email = scn.next();

									usuario = usuarioBo.getUsuario(email);

									if (usuario != null) {
										System.out.println("Usuário já existente");
									} else {
										usuario = new Usuario();
										System.out.println("Digite seu nome: ");
										usuario.setNome(scn.next());
										System.out.printf("Digite sua senha: ");
										usuario.setSenha(scn.next());
										System.out.printf("Digite sua data de nascimento(DD/MM/YYYY): ");
										usuario.setDtNascimento(sdf.parse(scn.next()));

										usuario.setPapel("comum");

										usuarioBo.insert(usuario);
										System.out.printf("Usuário cadastrado com sucesso!\n\n");
									}

									System.out.printf("Informe o seu e-mail: ");
									email = scn.next();
									System.out.printf("Informe a sua senha: ");
									senha = scn.next();

									usuarioLogado = usuarioBo.getUsuario(email, senha);

									if (usuarioLogado != null) {

										System.out.printf("Curtiu essa postagem?(S/N)");
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

										System.out.printf("\nGostaria de deixar um comentario nessa postagem?(S/N)");
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
									} else {
										System.out.printf("Usuário não encontrado");
									}

								}
							}
						}
					}
				} else if (opcao == 2) {
					empresas = empresaBo.getAll();

					if (empresas.size() > 0) {

						for (Empresa e : empresas) {
							System.out.printf("ID: " + e.getId() + "\tEmpresa: " + e.getNome() + "\n");
						}

						System.out.printf("\nGostaria de ver alguma empresa com mais detalhes?");
						resposta = scn.next().toUpperCase().charAt(0);

						while (resposta != 'S' && resposta != 'N') {
							System.out.printf("Utilizar padrão S/N!\n");
							System.out.printf("Digite novamente: ");
							resposta = scn.next().toUpperCase().charAt(0);
						}

						if (resposta == 'S') {
							do {
								System.out.printf("Informe o ID da empresa que deseja ver com mais detalhes: ");
								empresa = empresaBo.getEmpresa(scn.nextInt());

								if (empresa != null) {
									System.out.printf(
											"\n-------------------------------------------------------------------------------------------\n\n");
									System.out.printf("ID: " + empresa.getId() + "\tEmpresa: " + empresa.getNome()
											+ "\tSetor: " + empresa.getSetor().getDescricao() + "\tPossui IPO: "
											+ empresa.getAtivoIpo());

									System.out.printf("\n\n--------------- GOVERNAN�A ---------------\n\n");

									for (Governanca g : empresa.getGovernancas()) {
										System.out.printf("Período: " + sdf.format(g.getDtInicio()) + "\t\t"
												+ sdf.format(g.getDtFim()) + "\n\n");

										for (PessoaGovernanca pessoaGovernanca : g.getPessoasGovernanca()) {
											System.out.printf("Nome: " + pessoaGovernanca.getNome() + "\tCargo: "
													+ pessoaGovernanca.getCargo() + "\n");
										}

									}

									/*
									 * 
									 * if (empresa.getBalancos().size() > 0) { System.out
									 * .printf("\n\n--------------- BALAN�OS ---------------\n\n"); for (Balanco b :
									 * empresa.getBalancos()) { System.out.printf("Per�odo: " +
									 * sdf.format(b.getDtInicio()) + "\t\t" + sdf.format(b.getDtFinal()) + "\n\n");
									 * System.out.printf("\n\nAtivos: \n"); for (Ativo a : b.getAtivos()) {
									 * System.out.printf("ID: " + a.getId() + "\tNome: " + a.getDescricao() +
									 * "\tTipo: " + a.getTipo() + "\tValor: R$" + a.getValor() + "\n"); }
									 * 
									 * System.out.printf("\n\nPassivos: \n"); for (Passivo p : b.getPassivos()) {
									 * System.out.printf( "ID: " + p.getId() + "\tNome: " + p.getDescricao() +
									 * "\tValor: R$" + p.getValor() + "\n"); }
									 * 
									 * System.out.printf(
									 * "\n-------------------------------------------------------------------------------------------\n"
									 * ); System.out.printf( "Patrim�nio l�quido: R$" + b.getPatrimonioLiquido()); }
									 * } else { System.out.printf(
									 * "\nN�o existe balan�os cadastrados para essa empresa ainda!"); }
									 * 
									 */

									System.out.printf("\n\n--------------- IPO ---------------\n\n");
									System.out.printf("Descrição:" + empresa.getDescricaoIpo() + "\tValor Inicial: "
											+ empresa.getValorInicialIpo());

									System.out.printf(
											"\n\nPara salvar uma empresa na sua lista de favoritos � necess�rio estar logado!\n");
									System.out.printf("Gostaria de realizar o login/cadastro?(S/N)");
									resposta = scn.next().toUpperCase().charAt(0);

									while (resposta != 'S' && resposta != 'N') {
										System.out.printf("Utilizar padr�o S/N!\n");
										System.out.printf("Digite novamente: ");
										resposta = scn.next().toUpperCase().charAt(0);
									}

									if (resposta == 'S') {
										System.out.printf("Já possui conta?(S/N)");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padrão S/N!\n");
											System.out.printf("Digite novamente: ");
											resposta = scn.next().toUpperCase().charAt(0);
										}

										if (resposta == 'S') {
											System.out.printf("Informe o seu e-mail: ");
											email = scn.next();
											System.out.printf("Informe a sua senha: ");
											senha = scn.next();

											usuarioLogado = usuarioBo.getUsuario(email, senha);

											if (usuarioLogado != null) {
												Empresa empresaSalva = usuarioBo.getEmpresaSalva(empresa.getId(),
														usuarioLogado.getEmail());

												if (empresaSalva != null) {
													usuarioLogado.addEmpresa(empresaSalva);
													System.out.printf("Empresa adicionada com sucesso!\n\n");
												} else {
													System.out.println(
															"Empresa já consta na sua lista de favoritos!\n\n");
												}

											} else {
												System.out.printf("Usuário não encontrado");
											}
										} else {

											System.out.printf("Digite seu e-mail: ");
											email = scn.next();

											usuario = usuarioBo.getUsuario(email);

											if (usuario != null) {
												System.out.println("Usuário já existente");
											} else {
												usuario = new Usuario();
												System.out.println("Digite seu nome: ");
												usuario.setNome(scn.next());
												System.out.printf("Digite sua senha: ");
												usuario.setSenha(scn.next());
												System.out.printf("Digite sua data de nascimento(DD/MM/YYYY): ");
												usuario.setDtNascimento(sdf.parse(scn.next()));

												usuario.setPapel("comum");

												usuarioBo.insert(usuario);
												System.out.printf("Usuário cadastrado com sucesso!\n\n");
											}

											System.out.printf("Informe o seu e-mail: ");
											email = scn.next();
											System.out.printf("Informe a sua senha: ");
											senha = scn.next();

											usuarioLogado = usuarioBo.getUsuario(email, senha);

											if (usuarioLogado != null) {
												Empresa empresaSalva = usuarioBo.getEmpresaSalva(empresa.getId(),
														usuarioLogado.getEmail());

												if (empresaSalva != null) {
													usuarioLogado.addEmpresa(empresaSalva);
													System.out.printf("Empresa adicionada com sucesso!\n\n");
												} else {
													System.out.println(
															"Empresa já consta na sua lista de favoritos!\n\n");
												}
											} else {
												System.out.printf("Usuário não encontrado");
											}

										}
									}

								} else {
									System.out.printf("Empresa não encontrada!\n\n");
								}

								System.out.printf(
										"\n-------------------------------------------------------------------------------------------\n\n");
								System.out.printf("Gostaria de ver outra empresa?");
								resposta = scn.next().toUpperCase().charAt(0);

								while (resposta != 'S' && resposta != 'N') {
									System.out.printf("Utilizar padrão S/N!\n");
									System.out.printf("Digite novamente: ");
									resposta = scn.next().toUpperCase().charAt(0);
								}
							} while (resposta == 'S');
						}

						System.in.read();

					}
				}
			} else if (opcao == 4) {
				System.out.printf("Informe o seu email: ");
				email = scn.next();

				usuario = usuarioBo.getUsuario(email);

				if (usuario != null) {
					System.out.printf("Digite sua nova senha: ");
					usuario.setSenha(scn.next());

					usuarioBo.update(usuario);

					System.out.printf("Senha atualizada com sucesso!\n\n");

				} else {
					System.out.println("Usu�rio n�o cadastrado no sistema!\n\n");
				}

				System.in.read();
			}

		} while (opcao <= 4);
	}

}
