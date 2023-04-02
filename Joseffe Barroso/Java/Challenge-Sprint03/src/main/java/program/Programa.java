package program;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import model.Ativo;
import model.Balanco;
import model.Categoria;
import model.Comentario;
import model.Empresa;
import model.Governanca;
import model.Ipo;
import model.Passivo;
import model.Postagem;
import model.Usuario;
import model.Valores;
import service.FeriadoService;
import service.TaxasService;

public class Programa {

public static void main(String[] args) throws ParseException, IOException {
		
		/*
		 * Programa j� possui dois usu�rios administradores cadastrados:
		 * 
		 * email:ana@email.com.br
		 * senha: senha123
		 * 
		 * email: diego@email.com.br
		 * senha: senha@123
		 */

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Scanner scn = new Scanner(System.in);

		ArrayList<Usuario> usuarios = new ArrayList<>();
		ArrayList<Postagem> postagens = new ArrayList<>();
		ArrayList<Categoria> categorias = new ArrayList<>();

		ArrayList<Empresa> empresas = new ArrayList<>();
		ArrayList<Ipo> ipos = new ArrayList<>();

		int opcao;
		String email, senha;
		char emailJaExistente;
		int indexUsuarioLogado, indexUsuario, indexPostagemGeral, indexPostagemUsuario, indexPostagemCat,
				indexCategoria, indexComentario, indexComentarioUsuario;
		int idUsuarioLogado;
		Usuario usuarioLogado, usu;
		int id, idPostagem;
		Postagem post, postUsuario;
		char resposta, categoriaExistente;
		String novaCategoria, atuCategoria, categoriaAntiga;
		Categoria cat, catAtu;
		int indexEmpresa;
		int idEmpresa;
		Empresa emp, empUsuario;
		int idGovernanca, indexGovernanca;
		Governanca gov;
		int idValor, indexValor;
		Valores var;
		int idBalanco, indexBalanco;
		Balanco bal;
		int idEmpresaJaExistente, indexEmpresaJaExistente, indexEmpresaUsuario;
		int count;
		String nome;
		char empresaJaExistente;
		String nomeCategoria;
		char categoriaComPostagens;
		
		//APIs
		FeriadoService feriadoService = new FeriadoService();
		HashMap<String, String> feriados = feriadoService.getFeriados();
		TaxasService taxasService = new TaxasService();
		HashMap<String, Double> taxas = taxasService.getTaxas();

		Usuario admin1 = new Usuario(1, "Ana", "ana@email.com.br", "senha123", sdf.parse("29/07/1996"), "admin");
		Usuario admin2 = new Usuario(2, "Diego", "diego@email.com.br", "senha@123", sdf.parse("09/02/1994"), "admin");

		usuarios.add(admin1);
		usuarios.add(admin2);

		do {
			System.out.printf("--------------- BEM-VINDO AO INVESTIUM ---------------\n\n");
			System.out.printf("1 - Fazer login\n" + "2 - Fazer cadastro\n" + "3 - Continuar sem logar\n"
					+ "4 - Esqueci a senha\n" + "5 - Sair do sistema\n");
			System.out.printf("Informe a op��o desejada: ");
			opcao = scn.nextInt();

			while (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 5) {
				System.out.printf("\nOp��o Inv�lida!\n");
				System.out.printf("Digite novamente: ");
				opcao = scn.nextInt();
			}

			if (opcao == 1) {

				System.out.printf("\n");
				System.out.printf("Informe o seu e-mail: ");
				email = scn.next();
				System.out.printf("Informe a sua senha: ");
				senha = scn.next();

				indexUsuarioLogado = -1;
				for (Usuario u : usuarios) {
					if ((u.getEmail().equals(email)) && (u.getSenha().equals(senha))) {
						System.out.printf("\nUsu�rio logado com sucesso!\n\n");
						indexUsuarioLogado = usuarios.indexOf(u);
						break;
					}
				}

				do {

					if (indexUsuarioLogado != -1) {
						usuarioLogado = usuarios.get(indexUsuarioLogado);

						if (usuarioLogado.getPapel().equals("comum")) {
							System.out.printf("--------------- MENU ---------------\n\n");
							System.out.printf("1 - Acessar Blog\n" + "2 - Acessar empresas\n" + "19 - Sair\n");
							System.out.printf("Digite a op��o desejada: ");
							opcao = scn.nextInt();

							while (opcao != 1 && opcao != 2 && opcao != 19) {
								System.out.printf("\nOp��o Inv�lida!\n");
								System.out.printf("Digite novamente: ");
								opcao = scn.nextInt();
							}

							if (opcao == 1) {
								System.out.println("Valores di�rios das taxas:\n");
								taxas.forEach((key, value) -> {
						            System.out.println(key + ": " + value + "%");
						          });
								
								if (postagens.size() > 0) {
									for (Postagem p : postagens) {
										System.out.printf("ID: " + p.getId() + "\tPostagem: " + p.getTitulo() + "\n");
									}

									System.out.printf("Informe o ID da postagem que deseja visualizar: ");
									id = scn.nextInt();

									indexPostagemGeral = -1;
									for (Postagem p : postagens) {
										if (p.getId() == id) {
											indexPostagemGeral = postagens.indexOf(p);
										}
									}

									if (indexPostagemGeral != -1) {
										post = postagens.get(indexPostagemGeral);
										System.out.printf(
												"\n-------------------------------------------------------------------------------------------\n");
										System.out.printf("\n\nT�tulo: " + post.getTitulo() + "\t\t\tCriada por: "
												+ post.getUsuario().getNome() + "\n\nConte�do: " + post.getConteudo()
												+ "\n\nImagem URL: " + post.getImgUrl() + "\n\nData: "
												+ sdf.format(post.getDate()) + "\tCategoria: " + post.getCategoria()
												+ "\t\t\tLikes: " + post.getLikes());
										System.out.printf(
												"\n-------------------------------------------------------------------------------------------\n");

										if (post.getComentarios() != null && post.getComentarios().size() > 0) {
											System.out.printf("Coment�rios:\n\n");
											for (Comentario c : post.getComentarios()) {
												System.out.printf(c.getUsuario().getNome() + ": " + c.getConteudo()
														+ "\t\tData: " + sdf.format(c.getData()) + "\n");
											}
											System.out.printf(
													"\n-------------------------------------------------------------------------------------------\n\n");
										}

										System.out.printf("Curtiu essa postagem?(S/N): ");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!\n");
											System.out.printf("Digite novamente: ");
											resposta = scn.next().toUpperCase().charAt(0);
										}

										if (resposta == 'S') {
											post.setLikes(post.getLikes() + 1);
										}

										System.out.printf("\nGostaria de deixar um comentario nessa postagem?(S/N): ");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!\n");
											System.out.printf("Digite novamente: ");
											resposta = scn.next().toUpperCase().charAt(0);
										}

										if (resposta == 'S') {
											Comentario comentario = new Comentario();

											if (post.getComentarios().size() > 0) {
												comentario.setId(post.getComentarios()
														.get(post.getComentarios().size() - 1).getId() + 1);
											} else {
												comentario.setId(1);
											}

											System.out.printf("Entre com o seu coment�rio: ");
											comentario.setConteudo(scn.next());
											comentario.setData(new Date());
											comentario.setUsuario(usuarioLogado);

											post.addComentario(comentario);
											System.out.println("Coment�rio realizado com sucesso!\n\n");
										}

									} else {
										System.out.printf("Postagem n�o encontrada\n\n");
									}
								} else {
									System.out.printf(
											"Infelizmente n�o temos postagens no blog! Nos desculpe pelo transtorno!\n\n");
								}

								System.in.read();

							} else if (opcao == 2) {

								System.out.printf("--------------- MENU ---------------\n\n");
								System.out.printf("1 - Consultar Empresas\n" + "2 - Consultar IPOs\n"
										+ "3 - Listar Empresas Salvas\n" + "4 - Editar Empresas Salvas\n"
										+ "19 - Sair\n");
								System.out.printf("Digite a op��o desejada: ");
								opcao = scn.nextInt();

								while ((opcao != 1) && (opcao != 2) && (opcao != 3) && (opcao != 4) && (opcao != 19)
										&& (opcao != 15)) {
									System.out.printf("Op��o inv�lida!");
									System.out.printf("Digite novamente: ");
									opcao = scn.nextInt();
								}

								if (empresas.size() > 0) {
									if (opcao == 1) {
										count = 0;
										for (Empresa e : empresas) {
											if (e.getGovernancas().size() > 0 && e.getValores().size() > 0
													&& e.getBalancos().size() > 0) {
												System.out.printf(
														"ID: " + e.getId() + "\tEmpresa: " + e.getNome() + "\n");
												count++;
											}
										}

										if (count > 0) {
											System.out.printf("\nGostaria de ver alguma empresa com mais detalhes?");
											resposta = scn.next().toUpperCase().charAt(0);

											while (resposta != 'S' && resposta != 'N') {
												System.out.printf("Utilizar padr�o S/N!\n");
												System.out.printf("Digite novamente: ");
												resposta = scn.next().toUpperCase().charAt(0);
											}

											if (resposta == 'S') {
												do {
													System.out.printf(
															"Informe o ID da empresa que deseja ver com mais detalhes: ");
													idEmpresa = scn.nextInt();

													indexEmpresa = -1;
													for (Empresa p : empresas) {
														if (p.getId() == idEmpresa) {
															indexEmpresa = empresas.indexOf(p);
														}
													}

													if (indexEmpresa != -1) {
														emp = empresas.get(indexEmpresa);

														if (emp.getGovernancas().size() > 0
																&& emp.getValores().size() > 0
																&& emp.getBalancos().size() > 0) {
															System.out.printf(
																	"\n-------------------------------------------------------------------------------------------\n\n");
															System.out.printf("ID: " + emp.getId() + "\tEmpresa: "
																	+ emp.getNome() + "\tSetor: " + emp.getSetor()
																	+ "\tPossui IPO: " + emp.getAtivoIpo());

															if (emp.getGovernancas().size() > 0) {
																System.out.printf(
																		"\n\n--------------- GOVERNAN�A ---------------\n\n");
																for (Governanca c : emp.getGovernancas()) {
																	System.out.printf("Per�do: "
																			+ sdf.format(c.getDtInicio()) + "\t\t"
																			+ sdf.format(c.getDtFim()) + "\n\n");
																	System.out.printf("Presidentes: ");
																	for (String s : c.getPresidente()) {
																		System.out.printf(s + " |\t");
																	}

																	System.out.printf("\nDiretoria: ");
																	for (String s : c.getDiretoria()) {
																		System.out.printf(s + " |\t");
																	}

																	System.out.printf("\nComit�: ");
																	for (String s : c.getComites()) {
																		System.out.printf(s + " |\t");
																	}

																	System.out.printf("\nConselho Fiscal: ");
																	for (String s : c.getConselhoFiscal()) {
																		System.out.printf(s + " |\t");
																	}

																	System.out.printf("\nConselho Consultivo: ");
																	for (String s : c.getConselhoConsultivo()) {
																		System.out.printf(s + " |\t");
																	}

																	System.out.printf("\nConselho Administrativo: ");
																	for (String s : c.getConselhoAdministrativo()) {
																		System.out.printf(s + " |\t");
																	}

																	System.out.printf("\nAssembleia Geral: ");
																	for (String s : c.getAssembleiaGeral()) {
																		System.out.printf(s + " |\t");
																	}
																}
															} else {
																System.out.printf(
																		"\nN�o existe uma governan�a cadastrada para essa empresa ainda!");
															}

															if (emp.getValores().size() > 0) {
																System.out.printf(
																		"\n\n--------------- VALORES ---------------\n\n");
																for (Valores v : emp.getValores()) {
																	System.out.printf(
																			"Valor: " + v.getNome() + "\nDescri��o: "
																					+ v.getDescricao() + "\n\n");
																}
															} else {
																System.out.printf(
																		"\nN�o existe valores cadastrados para essa empresa ainda!");
															}

															if (emp.getBalancos().size() > 0) {
																System.out.printf(
																		"\n\n--------------- BALAN�OS ---------------\n\n");
																for (Balanco b : emp.getBalancos()) {
																	System.out.printf("Per�odo: "
																			+ sdf.format(b.getDtInicio()) + "\t\t"
																			+ sdf.format(b.getDtFinal()) + "\n\n");
																	System.out.printf("\n\nAtivos: \n");
																	for (Ativo a : b.getAtivos()) {
																		System.out.printf("ID: " + a.getId()
																				+ "\tNome: " + a.getDescricao()
																				+ "\tTipo: " + a.getTipo()
																				+ "\tValor: R$" + a.getValor() + "\n");
																	}

																	System.out.printf("\n\nPassivos: \n");
																	for (Passivo p : b.getPassivos()) {
																		System.out.printf("ID: " + p.getId()
																				+ "\tNome: " + p.getDescricao()
																				+ "\tValor: R$" + p.getValor() + "\n");
																	}

																	System.out.printf(
																			"\n-------------------------------------------------------------------------------------------\n");
																	System.out.printf("Patrim�nio l�quido: R$"
																			+ b.getPatrimonioLiquido());
																}
															} else {
																System.out.printf(
																		"\nN�o existe balan�os cadastrados para essa empresa ainda!");
															}

															if (emp.getAtivoIpo()) {
																System.out.printf(
																		"\n\n--------------- IPO ---------------\n\n");
																System.out.printf(
																		"Descri��o:" + emp.getIpo().getDescricao()
																				+ "\tValor Inicial: "
																				+ emp.getIpo().getValorInicial());
															}

															System.out.printf(
																	"\n\nGostaria de salvar essa empresa na sua lista de favoritos?\n");
															resposta = scn.next().toUpperCase().charAt(0);

															while (resposta != 'S' && resposta != 'N') {
																System.out.printf("Utilizar padr�o S/N!\n");
																System.out.printf("Digite novamente: ");
																resposta = scn.next().toUpperCase().charAt(0);
															}

															empresaJaExistente = 'N';
															if (resposta == 'S') {
																for (Empresa e : usuarioLogado.getEmpresas()) {
																	if (e.getNome() == emp.getNome()) {
																		empresaJaExistente = 'S';
																		break;
																	}
																}

																if (empresaJaExistente == 'N') {
																	usuarioLogado.addEmpresa(emp);
																	System.out.printf(
																			"Empresa adicionada com sucesso!\n\n");
																} else {
																	System.out.println(
																			"Empresa j� consta na sua lista de favoritos!\n\n");
																}
															}
														} else {
															System.out.printf("\nEmpresa n�o encontrada!\n\n");
														}

													} else {
														System.out.printf("Empresa n�o encontrada!\n\n");
													}

													System.out.printf(
															"\n-------------------------------------------------------------------------------------------\n\n");
													System.out.printf("Gostaria de ver outra empresa?");
													resposta = scn.next().toUpperCase().charAt(0);

													while (resposta != 'S' && resposta != 'N') {
														System.out.printf("Utilizar padr�o S/N!\n");
														System.out.printf("Digite novamente: ");
														resposta = scn.next().toUpperCase().charAt(0);
													}
												} while (resposta == 'S');
											}
										} else {
											System.out.printf(
													"Infelizmente no momento n�o possu�mos empresas cadastradas!\n\n");
										}

										System.in.read();

									} else if (opcao == 2) {
										
										System.out.printf("\n--------------- ATEN��O ---------------\n\n");
										System.out.printf("Segue as datas em que a bolsa n�o opera: \n");
										
										feriados.forEach((chave, valor) -> {
								            System.out.println(chave + ": " + valor);
										});     

										count = 0;
										for (Empresa e : empresas) {
											if (e.getGovernancas().size() > 0 && e.getValores().size() > 0
													&& e.getBalancos().size() > 0 && e.getAtivoIpo()) {
												System.out.printf(
														"ID: " + e.getId() + "\tEmpresa: " + e.getNome() + "\n");
												count++;
											}
										}

										if (count > 0) {
											System.out.printf(
													"\nEssas s�o as empresas que possuem IPOs ativos no momento. Gostaria de ver alguma com mais detalhes? ");
											resposta = scn.next().toUpperCase().charAt(0);

											while (resposta != 'S' && resposta != 'N') {
												System.out.printf("Utilizar padr�o S/N!\n");
												System.out.printf("Digite novamente: ");
												resposta = scn.next().toUpperCase().charAt(0);
											}

											if (resposta == 'S') {
												do {
													System.out.printf(
															"Informe o ID da empresa que deseja ver com mais detalhes: ");
													idEmpresa = scn.nextInt();

													indexEmpresa = -1;
													for (Empresa p : empresas) {
														if (p.getId() == idEmpresa) {
															indexEmpresa = empresas.indexOf(p);
														}
													}

													if (indexEmpresa != -1) {
														emp = empresas.get(indexEmpresa);

														if (emp.getGovernancas().size() > 0
																&& emp.getValores().size() > 0
																&& emp.getBalancos().size() > 0 && emp.getAtivoIpo()) {
															System.out.printf(
																	"\n-------------------------------------------------------------------------------------------\n\n");
															System.out.printf("ID: " + emp.getId() + "\tEmpresa: "
																	+ emp.getNome() + "\tSetor: " + emp.getSetor()
																	+ "\tPossui IPO: " + emp.getAtivoIpo());

															if (emp.getGovernancas().size() > 0) {
																System.out.printf(
																		"\n\n--------------- GOVERNAN�A ---------------\n\n");
																for (Governanca c : emp.getGovernancas()) {
																	System.out.printf("Per�do: "
																			+ sdf.format(c.getDtInicio()) + "\t\t"
																			+ sdf.format(c.getDtFim()) + "\n\n");
																	System.out.printf("Presidentes: ");
																	for (String s : c.getPresidente()) {
																		System.out.printf(s + " |\t");
																	}

																	System.out.printf("\nDiretoria: ");
																	for (String s : c.getDiretoria()) {
																		System.out.printf(s + " |\t");
																	}

																	System.out.printf("\nComit�: ");
																	for (String s : c.getComites()) {
																		System.out.printf(s + " |\t");
																	}

																	System.out.printf("\nConselho Fiscal: ");
																	for (String s : c.getConselhoFiscal()) {
																		System.out.printf(s + " |\t");
																	}

																	System.out.printf("\nConselho Consultivo: ");
																	for (String s : c.getConselhoConsultivo()) {
																		System.out.printf(s + " |\t");
																	}

																	System.out.printf("\nConselho Administrativo: ");
																	for (String s : c.getConselhoAdministrativo()) {
																		System.out.printf(s + " |\t");
																	}

																	System.out.printf("\nAssembleia Geral: ");
																	for (String s : c.getAssembleiaGeral()) {
																		System.out.printf(s + " |\t");
																	}
																}
															} else {
																System.out.printf(
																		"\nN�o existe uma governan�a cadastrada para essa empresa ainda!");
															}

															if (emp.getValores().size() > 0) {
																System.out.printf(
																		"\n\n--------------- VALORES ---------------\n\n");
																for (Valores v : emp.getValores()) {
																	System.out.printf(
																			"Valor: " + v.getNome() + "\nDescri��o: "
																					+ v.getDescricao() + "\n\n");
																}
															} else {
																System.out.printf(
																		"\nN�o existe valores cadastrados para essa empresa ainda!");
															}

															if (emp.getBalancos().size() > 0) {
																System.out.printf(
																		"\n\n--------------- BALAN�OS ---------------\n\n");
																for (Balanco b : emp.getBalancos()) {
																	System.out.printf("Per�odo: "
																			+ sdf.format(b.getDtInicio()) + "\t\t"
																			+ sdf.format(b.getDtFinal()) + "\n\n");
																	System.out.printf("\n\nAtivos: \n");
																	for (Ativo a : b.getAtivos()) {
																		System.out.printf("ID: " + a.getId()
																				+ "\tNome: " + a.getDescricao()
																				+ "\tTipo: " + a.getTipo()
																				+ "\tValor: R$" + a.getValor() + "\n");
																	}

																	System.out.printf("\n\nPassivos: \n");
																	for (Passivo p : b.getPassivos()) {
																		System.out.printf("ID: " + p.getId()
																				+ "\tNome: " + p.getDescricao()
																				+ "\tValor: R$" + p.getValor() + "\n");
																	}

																	System.out.printf(
																			"\n-------------------------------------------------------------------------------------------\n");
																	System.out.printf("Patrim�nio l�quido: R$"
																			+ b.getPatrimonioLiquido());
																}
															} else {
																System.out.printf(
																		"\nN�o existe balan�os cadastrados para essa empresa ainda!");
															}

															if (emp.getAtivoIpo()) {
																System.out.printf(
																		"\n\n--------------- IPO ---------------\n\n");
																System.out.printf(
																		"Descri��o:" + emp.getIpo().getDescricao()
																				+ "\tValor Inicial: "
																				+ emp.getIpo().getValorInicial());
															}

															System.out.printf(
																	"\n\nGostaria de salvar essa empresa na sua lista de favoritos?\n");
															resposta = scn.next().toUpperCase().charAt(0);

															while (resposta != 'S' && resposta != 'N') {
																System.out.printf("Utilizar padr�o S/N!\n");
																System.out.printf("Digite novamente: ");
																resposta = scn.next().toUpperCase().charAt(0);
															}

															empresaJaExistente = 'N';
															if (resposta == 'S') {
																for (Empresa e : usuarioLogado.getEmpresas()) {
																	if (e.getNome() == emp.getNome()) {
																		empresaJaExistente = 'S';
																		break;
																	}
																}

																if (empresaJaExistente == 'N') {
																	usuarioLogado.addEmpresa(emp);
																	System.out.printf(
																			"Empresa adicionada com sucesso!\n\n");
																} else {
																	System.out.println(
																			"Empresa j� consta na sua lista de favoritos!\n\n");
																}
															}
														} else {
															System.out.printf("ID incorreto!");
														}

													} else {
														System.out.printf("Empresa n�o encontrada!\n\n");
													}

													System.out.printf("\n\nGostaria de ver outra empresa?");
													resposta = scn.next().toUpperCase().charAt(0);

													while (resposta != 'S' && resposta != 'N') {
														System.out.printf("Utilizar padr�o S/N!\n");
														System.out.printf("Digite novamente: ");
														resposta = scn.next().toUpperCase().charAt(0);
													}
												} while (resposta == 'S');
											}
										} else {
											System.out.printf("N�o encontramos empresas com IPOs abertos!\n\n");
										}

										System.in.read();
									} else if (opcao == 3) {
										if (usuarioLogado.getEmpresas().size() > 0) {
											for (Empresa e : usuarioLogado.getEmpresas()) {
												System.out.printf(
														"ID: " + e.getId() + "\tEmpresa: " + e.getNome() + "\n");

											}

											System.out.printf("\nGostaria de ver alguma empresa com mais detalhes?");
											resposta = scn.next().toUpperCase().charAt(0);

											while (resposta != 'S' && resposta != 'N') {
												System.out.printf("Utilizar padr�o S/N!\n");
												System.out.printf("Digite novamente: ");
												resposta = scn.next().toUpperCase().charAt(0);
											}

											if (resposta == 'S') {
												do {
													System.out.printf(
															"Informe o ID da empresa que deseja ver com mais detalhes: ");
													idEmpresa = scn.nextInt();

													indexEmpresa = -1;
													for (Empresa p : usuarioLogado.getEmpresas()) {
														if (p.getId() == idEmpresa) {
															indexEmpresa = empresas.indexOf(p);
														}
													}

													if (indexEmpresa != -1) {
														emp = empresas.get(indexEmpresa);
														System.out.printf(
																"\n-------------------------------------------------------------------------------------------\n\n");
														System.out.printf("ID: " + emp.getId() + "\tEmpresa: "
																+ emp.getNome() + "\tSetor: " + emp.getSetor()
																+ "\tPossui IPO: " + emp.getAtivoIpo());

														if (emp.getGovernancas().size() > 0) {
															System.out.printf(
																	"\n\n--------------- GOVERNAN�A ---------------\n\n");
															for (Governanca c : emp.getGovernancas()) {
																System.out.printf("Per�do: "
																		+ sdf.format(c.getDtInicio()) + "\t\t"
																		+ sdf.format(c.getDtFim()) + "\n\n");
																System.out.printf("Presidentes: ");
																for (String s : c.getPresidente()) {
																	System.out.printf(s + " |\t");
																}

																System.out.printf("\nDiretoria: ");
																for (String s : c.getDiretoria()) {
																	System.out.printf(s + " |\t");
																}

																System.out.printf("\nComit�: ");
																for (String s : c.getComites()) {
																	System.out.printf(s + " |\t");
																}

																System.out.printf("\nConselho Fiscal: ");
																for (String s : c.getConselhoFiscal()) {
																	System.out.printf(s + " |\t");
																}

																System.out.printf("\nConselho Consultivo: ");
																for (String s : c.getConselhoConsultivo()) {
																	System.out.printf(s + " |\t");
																}

																System.out.printf("\nConselho Administrativo: ");
																for (String s : c.getConselhoAdministrativo()) {
																	System.out.printf(s + " |\t");
																}

																System.out.printf("\nAssembleia Geral: ");
																for (String s : c.getAssembleiaGeral()) {
																	System.out.printf(s + " |\t");
																}
															}
														} else {
															System.out.printf(
																	"\nN�o existe uma governan�a cadastrada para essa empresa ainda!");
														}

														if (emp.getValores().size() > 0) {
															System.out.printf(
																	"\n\n--------------- VALORES ---------------\n\n");
															for (Valores v : emp.getValores()) {
																System.out.printf("Valor: " + v.getNome()
																		+ "\nDescri��o: " + v.getDescricao() + "\n\n");
															}
														} else {
															System.out.printf(
																	"\nN�o existe valores cadastrados para essa empresa ainda!");
														}

														if (emp.getBalancos().size() > 0) {
															System.out.printf(
																	"\n\n--------------- BALAN�OS ---------------\n\n");
															for (Balanco b : emp.getBalancos()) {
																System.out.printf("Per�odo: "
																		+ sdf.format(b.getDtInicio()) + "\t\t"
																		+ sdf.format(b.getDtFinal()) + "\n\n");
																System.out.printf("\n\nAtivos: \n");
																for (Ativo a : b.getAtivos()) {
																	System.out.printf("ID: " + a.getId() + "\tNome: "
																			+ a.getDescricao() + "\tTipo: "
																			+ a.getTipo() + "\tValor: R$" + a.getValor()
																			+ "\n");
																}

																System.out.printf("\n\nPassivos: \n");
																for (Passivo p : b.getPassivos()) {
																	System.out.printf("ID: " + p.getId() + "\tNome: "
																			+ p.getDescricao() + "\tValor: R$"
																			+ p.getValor() + "\n");
																}

																System.out.printf(
																		"\n-------------------------------------------------------------------------------------------\n");
																System.out.printf("Patrim�nio l�quido: R$"
																		+ b.getPatrimonioLiquido());
															}
														} else {
															System.out.printf(
																	"\nN�o existe balan�os cadastrados para essa empresa ainda!");
														}

														if (emp.getAtivoIpo()) {
															System.out.printf(
																	"\n\n--------------- IPO ---------------\n\n");
															System.out.printf("Descri��o:" + emp.getIpo().getDescricao()
																	+ "\tValor Inicial: "
																	+ emp.getIpo().getValorInicial());
														}

													} else {
														System.out.printf("Empresa n�o encontrada!\n\n");
													}

													System.out.printf("\n\nGostaria de ver outra empresa?");
													resposta = scn.next().toUpperCase().charAt(0);

													while (resposta != 'S' && resposta != 'N') {
														System.out.printf("Utilizar padr�o S/N!\n");
														System.out.printf("Digite novamente");
														resposta = scn.next().toUpperCase().charAt(0);
													}
												} while (resposta == 'S');
											}
										} else {
											System.out.printf("Voc� n�o possui nenhuma empresa salva!\n\n");
										}

										System.in.read();

									} else if (opcao == 4) {
										for (Empresa e : usuarioLogado.getEmpresas()) {
											System.out.printf("ID: " + e.getId() + "\tEmpresa: " + e.getNome() + "\n");

										}

										System.out.printf(
												"\nGostaria de excluir alguma empresa da sua lista de favoritos?");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!\n");
											System.out.printf("Digite novamente: ");
											resposta = scn.next().toUpperCase().charAt(0);
										}

										if (resposta == 'S') {
											System.out.printf("Informe o ID da empresa que deseja excluir: ");
											idEmpresa = scn.nextInt();

											indexEmpresa = -1;
											for (Empresa p : usuarioLogado.getEmpresas()) {
												if (p.getId() == idEmpresa) {
													indexEmpresa = usuarioLogado.getEmpresas().indexOf(p);
												}
											}

											if (indexEmpresa != -1) {
												usuarioLogado.getEmpresas().remove(indexEmpresa);
												System.out.printf("Empresa removida com sucesso!\n\n");
											} else {
												System.out.printf("Empresa n�o encontrada!\n\n");
											}
										}

										System.in.read();
									}

								} else {
									System.out.println("Ainda n�o possu�mos empresas cadastradas\n\n");
								}

							}
						} else if (usuarioLogado.getPapel().equals("admin")) { // MENU ADM
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

							if (opcao == 1) { // CRIAR POSTAGEM
								Postagem postagem = new Postagem();

								if (categorias.size() > 0) {
									if (postagens.size() > 0) {
										postagem.setId(postagens.get(postagens.size() - 1).getId() + 1);
									} else {
										postagem.setId(1);
									}

									System.out.printf("\n------- CADASTRO DE POSTAGEM --------\n\n");
									System.out.printf("T�tulo da postagem: ");
									postagem.setTitulo(scn.next());
									System.out.printf("Conte�do da postagem: ");
									postagem.setConteudo(scn.next());
									System.out.printf("URL da imagem: ");
									postagem.setImgUrl(scn.next());
									postagem.setDate(new Date());
									postagem.setUsuario(usuarioLogado);

									do {
										System.out
												.printf("\nEscolha uma das categorias v�lidas (ITEM OBRIGAT�RIO)\n\n");
										for (Categoria c : categorias) {
											System.out.printf(
													"ID: " + c.getId() + "\tCategoria: " + c.getDescricao() + "\n");
										}

										System.out.printf(
												"\nInforme o ID da categoria que deseja cadastrar a postagem: ");
										id = scn.nextInt();

										indexCategoria = -1;
										for (Categoria c : categorias) {
											if (c.getId() == id) {
												indexCategoria = categorias.indexOf(c);
											}
										}

									} while (indexCategoria == -1);

									cat = categorias.get(indexCategoria);

									cat.addPostagem(postagem);
									// System.out.printf("Postagem adicionada com sucesso - CATEGORIA\n");

									postagem.setCategoria(cat);

									postagens.add(postagem);
									System.out.printf("Postagem cadastrada com sucesso!\n\n");

									if (postagem.getUsuario() == usuarioLogado) {
										usuarioLogado.addPostagem(postagem);
									}

									
								} else {
									System.out.println("� obrigat�rio que toda postagem tenha uma categoria!"
											+ " Mas n�o encontramos nenhuma categoria cadastrada. Cadastre uma categoria antes de cadastrar uma postagem!\n\n");
								}

								System.in.read();

							} else if (opcao == 2) { // ATUALIZAR POSTAGEM
								if (postagens.size() > 0) {
									for (Postagem p : postagens) {
										System.out.printf("ID: " + p.getId() + "\tPostagem: " + p.getTitulo()
												+ "\tCategoria: " + p.getCategoria() + "\n");
									}

									System.out.printf("Informe o ID da postagem que deseja atualizar: ");
									idPostagem = scn.nextInt();

									indexPostagemGeral = -1;
									for (Postagem p : postagens) {
										if (p.getId() == idPostagem) {
											indexPostagemGeral = postagens.indexOf(p);
										}
									}

									if (indexPostagemGeral != -1) {
										post = postagens.get(indexPostagemGeral);

										usu = post.getUsuario();
										categoriaAntiga = post.getCategoria();

										System.out.printf("Digite o novo t�tulo: ");
										post.setTitulo(scn.next());
										System.out.printf("Digite o novo conte�do: ");
										post.setConteudo(scn.next());
										System.out.printf("Digite a nova url da imagem: ");
										post.setImgUrl(scn.next());

										System.out.printf("Gostaria de atualizar a categoria da postagem: ");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!");
											System.out.printf("Digite novamente: ");
											resposta = scn.next().toUpperCase().charAt(0);
										}

										if (resposta == 'S') {
											indexCategoria = -1;
											do {
												System.out.printf(
														"Escolha uma das categorias v�lidas! (ITEM OBRIGAT�RIO)\n\n");
												for (Categoria c : categorias) {
													System.out.printf("ID: " + c.getId() + "\tCategoria: "
															+ c.getDescricao() + "\n");
												}

												System.out.printf(
														"Informe o ID da categoria que deseja cadastrar a postagem: ");
												id = scn.nextInt();

												for (Categoria c : categorias) {
													if (c.getId() == id) {
														indexCategoria = categorias.indexOf(c);
													}
												}

											} while (indexCategoria == -1);

											cat = categorias.get(indexCategoria);

											post.setCategoria(cat);

											for (Categoria c : categorias) {
												if (c.getDescricao() == categoriaAntiga) {
													indexCategoria = categorias.indexOf(c);
												}
											}

											catAtu = categorias.get(indexCategoria);

											indexPostagemCat = -1;
											for (Postagem p : catAtu.getPostagens()) {
												if (p.getId() == idPostagem) {
													indexPostagemCat = catAtu.getPostagens().indexOf(p);
												}
											}

											catAtu.getPostagens().remove(indexPostagemCat);

											cat.addPostagem(post);
										}

										indexPostagemUsuario = -1;
										for (Postagem p : usu.getPostagens()) {
											if (p.getId() == idPostagem) {
												indexPostagemUsuario = usu.getPostagens().indexOf(p);
												break;
											}
										}

										nomeCategoria = post.getCategoria();
										if (indexPostagemUsuario != -1) {
											postUsuario = usu.getPostagens().get(indexPostagemUsuario);
											postUsuario.setTitulo(post.getTitulo());
											postUsuario.setConteudo(post.getConteudo());
											postUsuario.setImgUrl(post.getImgUrl());
											postUsuario.setCategoria(nomeCategoria);

										}

										postUsuario = usu.getPostagens().get(indexPostagemUsuario);

										
									} else {
										System.out.printf("Postagem n�o encontrada\n\n");
									}
								} else {
									System.out.printf("N�o temos postagens cadastradas para atualizar!\n\n");
								}

								System.in.read();

							} else if (opcao == 3) { // EXCLUIR POSTAGEM
								if (postagens.size() > 0) {
									for (Postagem p : postagens) {
										System.out.printf("ID: " + p.getId() + "\tPostagem: " + p.getTitulo() + "\n");
									}

									System.out.printf("Informe o ID da postagem que deseja excluir: ");
									id = scn.nextInt();

									indexPostagemGeral = -1;
									for (Postagem p : postagens) {
										if (p.getId() == id) {
											indexPostagemGeral = postagens.indexOf(p);
										}
									}

									if (indexPostagemGeral != -1) {
										post = postagens.get(indexPostagemGeral);

										usu = post.getUsuario();
										nome = post.getCategoria();

										indexPostagemUsuario = -1;
										for (Postagem p : usu.getPostagens()) {
											if (p.getId() == id) {
												indexPostagemUsuario = usu.getPostagens().indexOf(p);
											}
										}

										indexCategoria = -1;
										for (Categoria c : categorias) {
											if (c.getDescricao() == nome) {
												indexCategoria = categorias.indexOf(c);
											}
										}

										cat = categorias.get(indexCategoria);

										indexPostagemCat = -1;
										for (Postagem p : cat.getPostagens()) {
											if (p.getId() == id) {
												indexPostagemCat = cat.getPostagens().indexOf(p);
											}
										}

										usu.getPostagens().remove(indexPostagemUsuario);
										cat.getPostagens().remove(indexPostagemCat);
										postagens.remove(indexPostagemGeral);
										System.out.printf("Postagem exclu�da com sucesso\n\n");

									} else {
										System.out.printf("Postagem n�o encontrada\n\n");
									}
								} else {
									System.out.printf("N�o temos postagens cadastradas para serem exclu�das!\n\n");
								}

								System.in.read();

							} else if (opcao == 4) { // LISTAR POSTAGENS
								if (postagens.size() > 0) {
									for (Postagem p : postagens) {
										System.out.printf("ID: " + p.getId() + "\tPostagem: " + p.getTitulo() + "\n");
									}

									System.out.printf("Deseja ver uma postagem com mais detalhe?(S/N)");
									resposta = scn.next().toUpperCase().charAt(0);

									while (resposta != 'S' && resposta != 'N') {
										System.out.printf("Utilizar padr�o S/N!\n");
										System.out.printf("Deseja ver uma postagem com mais detalhe?(S/N)");
										resposta = scn.next().toUpperCase().charAt(0);
									}

									if (resposta == 'S') {
										System.out.printf("Informe o ID da postagem que deseja visualizar: ");
										id = scn.nextInt();

										indexPostagemGeral = -1;
										for (Postagem p : postagens) {
											if (p.getId() == id) {
												indexPostagemGeral = postagens.indexOf(p);
											}
										}

										if (indexPostagemGeral != -1) {
											post = postagens.get(indexPostagemGeral);
											System.out.printf(
													"\n-------------------------------------------------------------------------------------------\n");
											System.out.printf("\n\nT�tulo: " + post.getTitulo() + "\t\t\tCriada por: "
													+ post.getUsuario().getNome() + "\n\nConte�do: "
													+ post.getConteudo() + "\n\nImagem URL: " + post.getImgUrl()
													+ "\n\nData: " + sdf.format(post.getDate()) + "\tCategoria: "
													+ post.getCategoria() + "\t\t\tLikes: " + post.getLikes());
											System.out.printf(
													"\n-------------------------------------------------------------------------------------------\n");

										} else {
											System.out.printf("Postagem n�o encontrada\n\n");
										}
									}
								} else {
									System.out.printf("N�o temos postagens cadastradas no momento!\n\n");
								}

								System.in.read();

							} else if (opcao == 5) {
								Categoria categoria = new Categoria();

								if (categorias.size() > 0) {
									categoria.setId(categorias.get(categorias.size() - 1).getId() + 1);
								} else {
									categoria.setId(1);
								}

								System.out.println("Digite o nome da categoria");
								novaCategoria = scn.next();

								categoriaExistente = 'N';
								for (Categoria c : categorias) {
									if (c.getDescricao().equals(novaCategoria)) {
										categoriaExistente = 'S';
										break;
									}
								}

								if (categoriaExistente == 'N') {
									categoria.setDescricao(novaCategoria);
									categorias.add(categoria);
									System.out.printf("Categoria criada com sucesso!\n\n");
								} else {
									System.out.printf("Categoria j� existente\n\n");
								}

								System.in.read();

							} else if (opcao == 6) {
								if (categorias.size() > 0) {
									for (Categoria c : categorias) {
										System.out.println("ID: " + c.getId() + "\tCategoria: " + c.getDescricao());
									}

									System.out.printf("Informe o ID da categoria que deseja atualizar: ");
									id = scn.nextInt();

									indexCategoria = -1;
									for (Categoria c : categorias) {
										if (c.getId() == id) {
											indexCategoria = categorias.indexOf(c);
											break;
										}
									}

									if (indexCategoria != -1) {
										cat = categorias.get(indexCategoria);

										System.out.printf("Digite o novo nome da categoria: ");
										atuCategoria = scn.next();

										categoriaExistente = 'N';
										for (Categoria c : categorias) {
											if (c.getDescricao().equals(atuCategoria)) {
												categoriaExistente = 'S';
												break;
											}
										}

										if (categoriaExistente == 'N') {
											// atualizar postagens de postagens e usuario
											indexPostagemGeral = -1;
											for (Postagem p : postagens) {
												if (p.getCategoria() == cat.getDescricao()) {
													indexPostagemGeral = postagens.indexOf(p);
												}
											}

											post = postagens.get(indexPostagemGeral);

											usu = post.getUsuario();

											indexPostagemUsuario = -1;
											for (Postagem p : usu.getPostagens()) {
												if (p.getId() == id) {
													indexPostagemUsuario = usu.getPostagens().indexOf(p);
												}
											}

											postUsuario = usu.getPostagens().get(indexPostagemUsuario);

											cat.setDescricao(atuCategoria);
											post.setCategoria(cat);
											postUsuario.setCategoria(cat);
											System.out.printf("Categoria atualizada com sucesso!\n\n");

										} else {
											System.out.printf("Categoria j� existente\n\n");
										}

									} else {
										System.out.printf("Categoria n�o encontrada!\n\n");
									}
								} else {
									System.out.printf("N�o temos categorias cadastradas para serem atualizadas!\n\n");
								}

								System.in.read();

							} else if (opcao == 7) {
								if (categorias.size() > 0) {
									for (Categoria c : categorias) {
										System.out.println("ID: " + c.getId() + "\tCategoria: " + c.getDescricao());
									}

									System.out.printf("Informe o ID da categoria que deseja excluir: ");
									id = scn.nextInt();

									indexCategoria = -1;
									for (Categoria c : categorias) {
										if (c.getId() == id) {
											indexCategoria = categorias.indexOf(c);
											break;
										}
									}

									if (indexCategoria != -1) {
										cat = categorias.get(indexCategoria);

										categoriaComPostagens = 'N';
										for (Postagem p : postagens) {
											if (p.getCategoria() == cat.getDescricao()) {
												categoriaComPostagens = 'S';
											}
										}

										if (categoriaComPostagens == 'N') {
											categorias.remove(indexCategoria);
											System.out.printf("Categoria exclu�da com sucesso!\n\n");
										} else {
											System.out.printf(
													"Essa categoria possui postagens associadas � ela! Por favor, atualize as postagens com outra categoria pra que essa possa ser removida\n\n");
										}

									} else {
										System.out.printf("Categoria n�o encontrada\n\n");
									}
								} else {
									System.out.printf("N�o temos categorias cadastradas para serem exclu�das!\n\n");
								}

								System.in.read();

							} else if (opcao == 8) {
								if (categorias.size() > 0) {
									System.out.printf("");
									for (Categoria c : categorias) {
										System.out.println("ID: " + c.getId() + "\tCategoria: " + c.getDescricao());
									}

									System.out.printf("Gostaria de ver as postagens associadas � alguma categoria?");
									resposta = scn.next().toUpperCase().charAt(0);

									while (resposta != 'S' && resposta != 'N') {
										System.out.printf("Utilizar padr�o S/N!\n");
										System.out
												.printf("Gostaria de ver as postagens associadas � alguma categoria?");
										resposta = scn.next().toUpperCase().charAt(0);
									}

									if (resposta == 'S') {

										System.out.printf(
												"Informe o ID da categoria que deseja visualizar as postagens relacionadas: ");
										id = scn.nextInt();

										indexCategoria = -1;
										for (Categoria c : categorias) {
											if (c.getId() == id) {
												indexCategoria = categorias.indexOf(c);
											}
										}

										if (indexCategoria != -1) {
											cat = categorias.get(indexCategoria);

											if (cat.getPostagens().size() > 0) {
												for (Postagem p : cat.getPostagens()) {
													System.out
															.printf("ID: " + p.getId() + "\tPostagem: " + p.getTitulo()
																	+ "\tData: " + sdf.format(p.getDate()) + "\n");
												}
											} else {
												System.out.printf(
														"N�o existe postagens relacionadas a essa categoria!\n\n");
											}

										} else {
											System.out.printf("Categoria n�o encontrada!\n\n");
										}
									}
								} else {
									System.out.printf("N�o temos categorias cadastradas!\n\n");
								}

								System.in.read();

							} else if (opcao == 9) {
								if (postagens.size() > 0) {
									do {
										for (Postagem p : postagens) {
											System.out
													.printf("ID: " + p.getId() + "\tPostagem: " + p.getTitulo() + "\n");
										}

										System.out.printf(
												"Informe o ID da postagem que deseja visualizar os coment�rios: ");
										id = scn.nextInt();

										indexPostagemGeral = -1;
										for (Postagem p : postagens) {
											if (p.getId() == id) {
												indexPostagemGeral = postagens.indexOf(p);
											}
										}

										if (indexPostagemGeral != -1) {
											post = postagens.get(indexPostagemGeral);

											if (post.getComentarios().size() > 0) {
												for (Comentario c : post.getComentarios()) {
													System.out.printf(c.exibirComentario());
												}
											} else {
												System.out.printf("Essa postagem ainda n�o possui coment�rios!\n\n");
											}
										} else {
											System.out.printf("Postagem n�o encontrada!\n\n");
										}

										System.out.print("Deseja ver os coment�rios de outra postagem?");
										resposta = scn.next().toUpperCase().charAt(0);

									} while (resposta != 'N');
								} else {
									System.out.printf("N�o temos postagens cadastradas no momento!\n\n");
								}

								System.in.read();
							} else if (opcao == 10) {
								if (postagens.size() > 0) {
									for (Postagem p : postagens) {
										System.out.printf("ID: " + p.getId() + "\tPostagem: " + p.getTitulo() + "\n");
									}

									System.out.printf("Informe o ID da postagem que deseja excluir um coment�rio: ");
									id = scn.nextInt();

									indexPostagemGeral = -1;
									for (Postagem p : postagens) {
										if (p.getId() == id) {
											indexPostagemGeral = postagens.indexOf(p);
										}
									}

									if (indexPostagemGeral != -1) {
										post = postagens.get(indexPostagemGeral);

										usu = post.getUsuario();

										indexPostagemUsuario = -1;
										for (Postagem p : usu.getPostagens()) {
											if (p.getId() == id) {
												indexPostagemUsuario = usu.getPostagens().indexOf(p);
											}
										}

										postUsuario = usu.getPostagens().get(indexPostagemUsuario);

										if (post.getComentarios().size() > 0) {
											for (Comentario c : post.getComentarios()) {
												System.out.printf(c.exibirComentario());
											}

											System.out.printf("Informe o ID do coment�rio que deseja excluir: ");
											id = scn.nextInt();

											indexComentario = -1;
											for (Comentario c : post.getComentarios()) {
												if (c.getId() == id) {
													indexComentario = post.getComentarios().indexOf(c);
												}
											}

											indexComentarioUsuario = -1;
											for (Comentario c : postUsuario.getComentarios()) {
												if (c.getId() == id) {
													indexComentarioUsuario = postUsuario.getComentarios().indexOf(c);
												}
											}

											if (indexComentario != -1) {
												// postUsuario.getComentarios().remove(indexComentarioUsuario);
												post.getComentarios().remove(indexComentario);
												System.out.printf("Coment�rio exclu�do com sucesso!\n\n");
											} else {
												System.out.printf("Coment�rio n�o encontrado!\n\n");
											}

										} else {
											System.out.printf("Essa postagem ainda n�o possui coment�rios!\n\n");
										}
									} else {
										System.out.printf("Postagem n�o encontrada!\n\n");
									}
								} else {
									System.out.printf("N�o temos postagens cadastradas no momento!\n\n");
								}

								System.in.read();
							} else if (opcao == 11) { // CADASTRO DE EMPRESAS
								Empresa empresa = new Empresa();

								if (empresas.size() > 0) {
									empresa.setId(empresas.get(empresas.size() - 1).getId() + 1);
								} else {
									empresa.setId(1);
								}

								System.out.printf("\n------- CADASTRO DE EMPRESA --------\n\n");
								System.out.printf("Nome da empresa: ");
								empresa.setNome(scn.next());
								System.out.printf("Informe o setor da empresa: ");
								empresa.setSetor(scn.next());
								System.out.printf("Possui IPO ativo(S/N)");
								resposta = scn.next().toUpperCase().charAt(0);

								while (resposta != 'S' && resposta != 'N') {
									System.out.printf("Utilizar padr�o S/N!\n");
									System.out.printf("Digite novamente: ");
									resposta = scn.next().toUpperCase().charAt(0);
								}

								if (resposta == 'S') {
									empresa.setAtivoIpo(true);
								} else {
									empresa.setAtivoIpo(false);
								}

								if (empresa.getAtivoIpo()) {

									Ipo ipo = new Ipo();

									if (ipos.size() > 0) {
										ipo.setId(ipos.get(ipos.size() - 1).getId() + 1);
									} else {
										ipo.setId(1);
									}

									System.out.printf("Informe o valor inicial do IPO: ");
									ipo.setValorInicial(scn.nextDouble());
									System.out.printf("Informe o descri��o do IPO: ");
									ipo.setDescricao(scn.next());

									ipos.add(ipo);
									empresa.setIpo(ipo);

								}

								System.out.printf("Gostaria de cadastrar a governan�a da empresa agora?");
								resposta = scn.next().toUpperCase().charAt(0);

								while (resposta != 'S' && resposta != 'N') {
									System.out.printf("Utilizar padr�o S/N!\n");
									System.out.printf("Digite novamente: ");
									resposta = scn.next().toUpperCase().charAt(0);
								}

								if (resposta == 'S') {
									Governanca governanca = new Governanca();

									if (empresa.getGovernancas().size() > 0) {
										governanca.setId(empresa.getGovernancas()
												.get(empresa.getGovernancas().size() - 1).getId() + 1);
									} else {
										governanca.setId(1);
									}

									do {
										System.out.printf("Informe o nome do presidente  da empresa: ");
										governanca.addPresidente(scn.next());
										System.out.printf("A empresa possui mais algum presidente?");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!\n");
											System.out.printf("Digite novamente: ");
											resposta = scn.next().toUpperCase().charAt(0);
										}
									} while (resposta == 'S');

									do {
										System.out.printf("Informe o nome de um membro da diretoria da empresa: ");
										governanca.addDiretoria(scn.next());
										System.out.printf("A diretoria possui mais algum membro?");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!\n");
											System.out.printf("Digite novamente: ");
											resposta = scn.next().toUpperCase().charAt(0);
										}
									} while (resposta == 'S');

									do {
										System.out.printf("Informe o nome de um membro do comite da empresa: ");
										governanca.addComite(scn.next());
										System.out.printf("O comite possui mais algum membro?");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!\n");
											System.out.printf("Digite novamente: ");
											resposta = scn.next().toUpperCase().charAt(0);
										}
									} while (resposta == 'S');

									do {
										System.out
												.printf("Informe o nome de um membro do conselho fiscal da empresa: ");
										governanca.addConselhoFiscal(scn.next());
										System.out.printf("O conselho fiscal possui mais algum membro?");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!\n");
											System.out.printf("Digite novamente: ");
											resposta = scn.next().toUpperCase().charAt(0);
										}
									} while (resposta == 'S');

									do {
										System.out.printf(
												"Informe o nome de um membro do conselho consultivo da empresa: ");
										governanca.addConselhoConsultivo(scn.next());
										System.out.printf("O conselho consultivo possui mais algum membro?");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!\n");
											System.out.printf("Digite novamente: ");
											resposta = scn.next().toUpperCase().charAt(0);
										}
									} while (resposta == 'S');

									do {
										System.out.printf(
												"Informe o nome de um membro do conselho administrativo da empresa: ");
										governanca.addConselhoAdministrativo(scn.next());
										System.out.printf("O conselho administrativo possui mais algum membro?");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!\n");
											System.out.printf("Digite novamente: ");
											resposta = scn.next().toUpperCase().charAt(0);
										}
									} while (resposta == 'S');

									do {
										System.out
												.printf("Informe o nome de um membro da assembleia geral da empresa: ");
										governanca.addAssembleiaGeral(scn.next());
										System.out.printf("A assembleia geral possui mais algum membro?");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!\n");
											System.out.printf("Digite novamente: ");
											resposta = scn.next().toUpperCase().charAt(0);
										}
									} while (resposta == 'S');

									System.out.printf(
											"Informe a data de in�cio da vig�ncia dessa governan�a(DD/MM/YYYY): ");
									governanca.setDtInicio(sdf.parse(scn.next()));
									System.out.printf(
											"Informe a data de final da vig�ncia dessa governan�a(DD/MM/YYYY): ");
									governanca.setDtFim(sdf.parse(scn.next()));

									empresa.addGovernanca(governanca);

								}

								System.out.printf("Gostaria de cadastrar os valores da empresa agora?");
								resposta = scn.next().toUpperCase().charAt(0);

								while (resposta != 'S' && resposta != 'N') {
									System.out.printf("Utilizar padr�o S/N!\n");
									System.out.printf("Digite novamente: ");
									resposta = scn.next().toUpperCase().charAt(0);
								}

								if (resposta == 'S') {
									do {

										Valores valor = new Valores();

										if (empresa.getValores().size() > 0) {
											valor.setId(
													empresa.getValores().get(empresa.getValores().size() - 1).getId()
															+ 1);
										} else {
											valor.setId(1);
										}

										System.out.printf("Digite um nome para o valor: ");
										valor.setNome(scn.next());
										System.out.printf("Digite uma descri��o para o valor: ");
										valor.setDescricao(scn.next());

										empresa.addValores(valor);

										System.out.printf("Gostaria de cadastrar mais valores da empresa agora?");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!\n");
											System.out.printf("Digite novamente: ");
											resposta = scn.next().toUpperCase().charAt(0);
										}

									} while (resposta == 'S');

								}

								System.out.printf("Gostaria de cadastrar o balan�o da empresa agora?");
								resposta = scn.next().toUpperCase().charAt(0);

								while (resposta != 'S' && resposta != 'N') {
									System.out.printf("Utilizar padr�o S/N!\n");
									System.out.printf("Digite novamente: ");
									resposta = scn.next().toUpperCase().charAt(0);
								}

								if (resposta == 'S') {
									Balanco balanco = new Balanco();

									if (empresa.getBalancos().size() > 0) {
										balanco.setId(
												empresa.getBalancos().get(empresa.getBalancos().size() - 1).getId()
														+ 1);
									} else {
										balanco.setId(1);
									}

									System.out.printf("Informe a data inicial do balan�o(DD/MM/YYYY): ");
									balanco.setDtInicio(sdf.parse(scn.next()));
									System.out.printf("Informe a data final do balan�o(DD/MM/YYYY): ");
									balanco.setDtFinal(sdf.parse(scn.next()));

									do {
										Ativo ativo = new Ativo();

										if (balanco.getAtivos().size() > 0) {
											ativo.setId(balanco.getAtivos().get(balanco.getAtivos().size() - 1).getId()
													+ 1);
										} else {
											ativo.setId(1);
										}

										System.out.printf("\n---------------- CADASTRO DE ATIVOS ----------------\n\n");
										System.out.printf("Informe o tipo de entrada: ");
										ativo.setTipo(scn.next());
										System.out.printf("Informe a descri��o da entrada: ");
										ativo.setDescricao(scn.next());
										System.out.printf("Informe o valor da entrada: ");
										ativo.setValor(scn.nextDouble());

										balanco.getAtivos().add(ativo);

										System.out.printf("Gostaria de adicionar mais uma entrada?");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!\n");
											System.out.printf("Digite novamente: ");
											resposta = scn.next().toUpperCase().charAt(0);
										}

									} while (resposta == 'S');

									do {
										Passivo passivo = new Passivo();

										if (balanco.getPassivos().size() > 0) {
											passivo.setId(
													balanco.getPassivos().get(balanco.getPassivos().size() - 1).getId()
															+ 1);
										} else {
											passivo.setId(1);
										}

										System.out
												.printf("\n---------------- CADASTRO DE PASSIVOS ----------------\n\n");
										System.out.printf("Informe a descri��o da sa�da: ");
										passivo.setDescricao(scn.next());
										System.out.printf("Informe o valor da sa�da: ");
										passivo.setValor(scn.nextDouble());

										balanco.getPassivos().add(passivo);

										System.out.printf("Gostaria de adicionar mais uma sa�da?");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!\n");
											System.out.printf("Digite novamente: ");
											resposta = scn.next().toUpperCase().charAt(0);
										}

									} while (resposta == 'S');

									balanco.setPatrimonioLiquido(balanco.getAtivos(), balanco.getPassivos());

									empresa.getBalancos().add(balanco);

								}

								empresas.add(empresa);

								System.out.printf("Empresa cadastrada com sucesso!\n\n");

								System.in.read();
							} else if (opcao == 12) { // ATUALIZA��O EMPRESA
								if (empresas.size() > 0) {
									for (Empresa e : empresas) {
										System.out.println("ID: " + e.getId() + "\tEmpresa: " + e.getNome());
									}

									System.out.printf("Informe o ID da empresa que deseja atualizar: ");
									idEmpresa = scn.nextInt();

									indexEmpresa = -1;
									for (Empresa p : empresas) {
										if (p.getId() == idEmpresa) {
											indexEmpresa = empresas.indexOf(p);
										}
									}

									if (indexEmpresa != -1) {
										emp = empresas.get(indexEmpresa);

										nome = emp.getNome();

										System.out.printf("Informe o nome da empresa atualizado: ");
										emp.setNome(scn.next());
										System.out.printf("Informe o nome do setor da empresa atualizado: ");
										emp.setSetor(scn.next());

										System.out.printf("Gostaria de atualizar o status do IPO?");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!\n");
											System.out.printf("Digite novamente");
											resposta = scn.next().toUpperCase().charAt(0);
										}

										if (resposta == 'S') {
											emp.setAtivoIpo(!emp.getAtivoIpo());
										}

										if (emp.getAtivoIpo()) {

											Ipo ipo = new Ipo();

											if (ipos.size() > 0) {
												ipo.setId(ipos.get(ipos.size() - 1).getId() + 1);
											} else {
												ipo.setId(1);
											}

											System.out.printf("Informe o valor inicial do IPO: ");
											ipo.setValorInicial(scn.nextDouble());
											System.out.printf("Informe o descri��o do IPO: ");
											ipo.setDescricao(scn.next());

											ipos.add(ipo);
											emp.setIpo(ipo);

										}

										System.out.printf("Gostaria de atualizar os dados de governan�a?");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!\n");
											System.out.printf("Digite novamente");
											resposta = scn.next().toUpperCase().charAt(0);
										}

										if (resposta == 'S') {

											if (emp.getGovernancas().size() > 0) {
												for (Governanca g : emp.getGovernancas()) {
													System.out.printf("ID: " + g.getId() + "\tPer�odo: "
															+ sdf.format(g.getDtInicio()) + "\t\t" + sdf.format(g.getDtFim())  + "\n");
												}

												System.out.printf("Informe o id da governan�a que deseja atualizar");
												idGovernanca = scn.nextInt();

												indexGovernanca = -1;
												for (Governanca g : emp.getGovernancas()) {
													if (g.getId() == idGovernanca) {
														indexGovernanca = emp.getGovernancas().indexOf(g);
													}
												}

												if (indexGovernanca != -1) {
													gov = emp.getGovernancas().get(indexGovernanca);

													gov.getPresidente().clear();
													do {
														System.out.printf(
																"Informe o nome atualizado do presidente  da empresa: ");
														gov.addPresidente(scn.next());
														System.out.printf("A empresa possui mais algum presidente?");
														resposta = scn.next().toUpperCase().charAt(0);

														while (resposta != 'S' && resposta != 'N') {
															System.out.printf("Utilizar padr�o S/N!\n");
															System.out.printf("Digite novamente: ");
															resposta = scn.next().toUpperCase().charAt(0);
														}
													} while (resposta == 'S');

													gov.getDiretoria().clear();
													do {
														System.out.printf(
																"Informe o nome atualizado de um membro da diretoria da empresa: ");
														gov.addDiretoria(scn.next());
														System.out.printf("A diretoria possui mais algum membro?");
														resposta = scn.next().toUpperCase().charAt(0);

														while (resposta != 'S' && resposta != 'N') {
															System.out.printf("Utilizar padr�o S/N!\n");
															System.out.printf("Digite novamente: ");
															resposta = scn.next().toUpperCase().charAt(0);
														}
													} while (resposta == 'S');

													gov.getComites().clear();
													do {
														System.out.printf(
																"Informe o nome atualizado de um membro do comite da empresa: ");
														gov.addComite(scn.next());
														System.out.printf("O comite possui mais algum membro?");
														resposta = scn.next().toUpperCase().charAt(0);

														while (resposta != 'S' && resposta != 'N') {
															System.out.printf("Utilizar padr�o S/N!\n");
															System.out.printf("Digite novamente: ");
															resposta = scn.next().toUpperCase().charAt(0);
														}
													} while (resposta == 'S');

													gov.getConselhoFiscal().clear();
													do {
														System.out.printf(
																"Informe o nome atualizado de um membro do conselho fiscal da empresa: ");
														gov.addConselhoFiscal(scn.next());
														System.out
																.printf("O conselho fiscal possui mais algum membro?");
														resposta = scn.next().toUpperCase().charAt(0);

														while (resposta != 'S' && resposta != 'N') {
															System.out.printf("Utilizar padr�o S/N!\n");
															System.out.printf("Digite novamente: ");
															resposta = scn.next().toUpperCase().charAt(0);
														}
													} while (resposta == 'S');

													gov.getConselhoConsultivo().clear();
													do {
														System.out.printf(
																"Informe o nome atulizado de um membro do conselho consultivo da empresa: ");
														gov.addConselhoConsultivo(scn.next());
														System.out.printf(
																"O conselho consultivo possui mais algum membro?");
														resposta = scn.next().toUpperCase().charAt(0);

														while (resposta != 'S' && resposta != 'N') {
															System.out.printf("Utilizar padr�o S/N!\n");
															System.out.printf("Digite novamente: ");
															resposta = scn.next().toUpperCase().charAt(0);
														}
													} while (resposta == 'S');

													gov.getConselhoAdministrativo().clear();
													do {
														System.out.printf(
																"Informe o nome atualizado de um membro do conselho administrativo da empresa: ");
														gov.addConselhoAdministrativo(scn.next());
														System.out.printf(
																"O conselho administrativo possui mais algum membro?");
														resposta = scn.next().toUpperCase().charAt(0);

														while (resposta != 'S' && resposta != 'N') {
															System.out.printf("Utilizar padr�o S/N!\n");
															System.out.printf("Digite novamente: ");
															resposta = scn.next().toUpperCase().charAt(0);
														}
													} while (resposta == 'S');

													gov.getAssembleiaGeral().clear();
													do {
														System.out.printf(
																"Informe o nome atulaizado de um membro da assembleia geral da empresa: ");
														gov.addAssembleiaGeral(scn.next());
														System.out
																.printf("A assembleia geral possui mais algum membro?");
														resposta = scn.next().toUpperCase().charAt(0);

														while (resposta != 'S' && resposta != 'N') {
															System.out.printf("Utilizar padr�o S/N!\n");
															System.out.printf("Digite novamente: ");
															resposta = scn.next().toUpperCase().charAt(0);
														}
													} while (resposta == 'S');

													System.out.printf(
															"Informe a data de in�cio da vig�ncia dessa governan�a atualizada(DD/MM/YYYY): ");
													gov.setDtInicio(sdf.parse(scn.next()));
													System.out.printf(
															"Informe a data de final da vig�ncia dessa governan�a atualizada(DD/MM/YYYY): ");
													gov.setDtFim(sdf.parse(scn.next()));

												} else {
													System.out.printf("Governan�a n�o encontrada!\n\n");
												}
											} else {
												Governanca governanca = new Governanca();

												if (emp.getGovernancas().size() > 0) {
													governanca.setId(emp.getGovernancas()
															.get(emp.getGovernancas().size() - 1).getId() + 1);
												} else {
													governanca.setId(1);
												}

												do {
													System.out.printf("Informe o nome do presidente  da empresa: ");
													governanca.addPresidente(scn.next());
													System.out.printf("A empresa possui mais algum presidente?");
													resposta = scn.next().toUpperCase().charAt(0);

													while (resposta != 'S' && resposta != 'N') {
														System.out.printf("Utilizar padr�o S/N!\n");
														System.out.printf("Digite novamente: ");
														resposta = scn.next().toUpperCase().charAt(0);
													}
												} while (resposta == 'S');

												do {
													System.out.printf(
															"Informe o nome de um membro da diretoria da empresa: ");
													governanca.addDiretoria(scn.next());
													System.out.printf("A diretoria possui mais algum membro?");
													resposta = scn.next().toUpperCase().charAt(0);

													while (resposta != 'S' && resposta != 'N') {
														System.out.printf("Utilizar padr�o S/N!\n");
														System.out.printf("Digite novamente: ");
														resposta = scn.next().toUpperCase().charAt(0);
													}
												} while (resposta == 'S');

												do {
													System.out.printf(
															"Informe o nome de um membro do comite da empresa: ");
													governanca.addComite(scn.next());
													System.out.printf("O comite possui mais algum membro?");
													resposta = scn.next().toUpperCase().charAt(0);

													while (resposta != 'S' && resposta != 'N') {
														System.out.printf("Utilizar padr�o S/N!\n");
														System.out.printf("Digite novamente: ");
														resposta = scn.next().toUpperCase().charAt(0);
													}
												} while (resposta == 'S');

												do {
													System.out.printf(
															"Informe o nome de um membro do conselho fiscal da empresa: ");
													governanca.addConselhoFiscal(scn.next());
													System.out.printf("O conselho fiscal possui mais algum membro?");
													resposta = scn.next().toUpperCase().charAt(0);

													while (resposta != 'S' && resposta != 'N') {
														System.out.printf("Utilizar padr�o S/N!\n");
														System.out.printf("Digite novamente: ");
														resposta = scn.next().toUpperCase().charAt(0);
													}
												} while (resposta == 'S');

												do {
													System.out.printf(
															"Informe o nome de um membro do conselho consultivo da empresa: ");
													governanca.addConselhoConsultivo(scn.next());
													System.out
															.printf("O conselho consultivo possui mais algum membro?");
													resposta = scn.next().toUpperCase().charAt(0);

													while (resposta != 'S' && resposta != 'N') {
														System.out.printf("Utilizar padr�o S/N!\n");
														System.out.printf("Digite novamente: ");
														resposta = scn.next().toUpperCase().charAt(0);
													}
												} while (resposta == 'S');

												do {
													System.out.printf(
															"Informe o nome de um membro do conselho administrativo da empresa: ");
													governanca.addConselhoAdministrativo(scn.next());
													System.out.printf(
															"O conselho administrativo possui mais algum membro?");
													resposta = scn.next().toUpperCase().charAt(0);

													while (resposta != 'S' && resposta != 'N') {
														System.out.printf("Utilizar padr�o S/N!\n");
														System.out.printf("Digite novamente: ");
														resposta = scn.next().toUpperCase().charAt(0);
													}
												} while (resposta == 'S');

												do {
													System.out.printf(
															"Informe o nome de um membro da assembleia geral da empresa: ");
													governanca.addAssembleiaGeral(scn.next());
													System.out.printf("A assembleia geral possui mais algum membro?");
													resposta = scn.next().toUpperCase().charAt(0);

													while (resposta != 'S' && resposta != 'N') {
														System.out.printf("Utilizar padr�o S/N!\n");
														System.out.printf("Digite novamente: ");
														resposta = scn.next().toUpperCase().charAt(0);
													}
												} while (resposta == 'S');

												System.out.printf(
														"Informe a data de in�cio da vig�ncia dessa governan�a(DD/MM/YYYY): ");
												governanca.setDtInicio(sdf.parse(scn.next()));
												System.out.printf(
														"Informe a data de final da vig�ncia dessa governan�a(DD/MM/YYYY): ");
												governanca.setDtFim(sdf.parse(scn.next()));

												emp.addGovernanca(governanca);
											}

										}

										System.out.printf("Gostaria de atualizar os dados de valores?");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!\n");
											System.out.printf("Digite novamente");
											resposta = scn.next().toUpperCase().charAt(0);
										}

										if (resposta == 'S') {

											do {

												if (emp.getValores().size() > 0) {
													for (Valores v : emp.getValores()) {
														System.out.printf(
																"ID: " + v.getId() + "Valor: " + v.getNome() + "\n");
													}

													System.out.printf("Digite o ID do valor que deseja atualizar: ");
													idValor = scn.nextInt();

													indexValor = -1;
													for (Valores v : emp.getValores()) {
														if (v.getId() == idValor) {
															indexValor = emp.getValores().indexOf(v);
														}
													}

													if (indexValor != -1) {
														var = emp.getValores().get(indexValor);

														System.out.printf("Digite um nome atualizado para o valor: ");
														var.setNome(scn.next());
														System.out.printf(
																"Digite uma descri��o atualizada para o valor: ");
														var.setDescricao(scn.next());

													} else {
														System.out.printf("Valor n�o encontrado!\n\n");
													}
												} else {
													do {

														Valores valor = new Valores();

														if (emp.getValores().size() > 0) {
															valor.setId(emp.getValores()
																	.get(emp.getValores().size() - 1).getId() + 1);
														} else {
															valor.setId(1);
														}

														System.out.printf("Digite um nome para o valor: ");
														valor.setNome(scn.next());
														System.out.printf("Digite uma descri��o para o valor: ");
														valor.setDescricao(scn.next());

														emp.addValores(valor);

														System.out.printf(
																"Gostaria de cadastrar mais valores da empresa agora?");
														resposta = scn.next().toUpperCase().charAt(0);

														while (resposta != 'S' && resposta != 'N') {
															System.out.printf("Utilizar padr�o S/N!\n");
															System.out.printf("Digite novamente: ");
															resposta = scn.next().toUpperCase().charAt(0);
														}

													} while (resposta == 'S');

												}

												System.out
														.printf("\nGostaria de atualizar mais algum valor da empresa?");
												resposta = scn.next().toUpperCase().charAt(0);

												while (resposta != 'S' && resposta != 'N') {
													System.out.printf("Utilizar padr�o S/N!\n");
													System.out.printf("Digite novamente: ");
													resposta = scn.next().toUpperCase().charAt(0);
												}

											} while (resposta == 'S');
										}

										System.out.printf("Gostaria de atualizar os dados de balan�os?");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!\n");
											System.out.printf("Digite novamente");
											resposta = scn.next().toUpperCase().charAt(0);
										}

										if (resposta == 'S') {
											if (emp.getBalancos().size() > 0) {
												for (Balanco b : emp.getBalancos()) {
													System.out.printf("ID: " + b.getId() + "Per�odo: "
															+ sdf.format(b.getDtInicio()) + "\t\t"
															+ sdf.format(b.getDtFinal()));
												}

												System.out.printf("Digite o ID do balan�o que deseja atualizar: ");
												idBalanco = scn.nextInt();

												indexBalanco = -1;
												for (Balanco b : emp.getBalancos()) {
													if (b.getId() == idBalanco) {
														indexBalanco = emp.getBalancos().indexOf(b);
													}
												}

												if (indexBalanco != -1) {
													bal = emp.getBalancos().get(indexBalanco);

													System.out.printf(
															"Informe a data inicial do balan�o atualizada(DD/MM/YYYY): ");
													bal.setDtInicio(sdf.parse(scn.next()));
													System.out.printf(
															"Informe a data final do balan�o atualizada(DD/MM/YYYY): ");
													bal.setDtFinal(sdf.parse(scn.next()));

													bal.getAtivos().clear();
													do {
														Ativo ativo = new Ativo();

														if (bal.getAtivos().size() > 0) {
															ativo.setId(bal.getAtivos().get(bal.getAtivos().size() - 1)
																	.getId() + 1);
														} else {
															ativo.setId(1);
														}

														System.out.printf(
																"\n---------------- ATUALIZA��O DE ATIVOS ----------------\n\n");
														System.out.printf("Informe o tipo de entrada: ");
														ativo.setTipo(scn.next());
														System.out.printf("Informe a descri��o da entrada: ");
														ativo.setDescricao(scn.next());
														System.out.printf("Informe o valor da entrada: ");
														ativo.setValor(scn.nextDouble());

														bal.getAtivos().add(ativo);

														System.out.printf("Gostaria de adicionar mais uma entrada?");
														resposta = scn.next().toUpperCase().charAt(0);

														while (resposta != 'S' && resposta != 'N') {
															System.out.printf("Utilizar padr�o S/N!\n");
															System.out.printf("Digite novamente: ");
															resposta = scn.next().toUpperCase().charAt(0);
														}

													} while (resposta == 'S');

													bal.getPassivos().clear();
													do {
														Passivo passivo = new Passivo();

														if (bal.getPassivos().size() > 0) {
															passivo.setId(bal.getPassivos()
																	.get(bal.getPassivos().size() - 1).getId() + 1);
														} else {
															passivo.setId(1);
														}

														System.out.printf(
																"\n---------------- ATUALIZA��O DE PASSIVOS ----------------\n\n");
														System.out.printf("Informe a descri��o da sa�da: ");
														passivo.setDescricao(scn.next());
														System.out.printf("Informe o valor da sa�da: ");
														passivo.setValor(scn.nextDouble());

														bal.getPassivos().add(passivo);

														System.out.printf("Gostaria de adicionar mais uma sa�da?");
														resposta = scn.next().toUpperCase().charAt(0);

														while (resposta != 'S' && resposta != 'N') {
															System.out.printf("Utilizar padr�o S/N!\n");
															System.out.printf("Digite novamente: ");
															resposta = scn.next().toUpperCase().charAt(0);
														}

													} while (resposta == 'S');

													bal.setPatrimonioLiquido(bal.getAtivos(), bal.getPassivos());

												} else {
													System.out.printf("Balan�o n�o encontrado!\n\n");
												}
											} else {
												Balanco balanco = new Balanco();

												if (emp.getBalancos().size() > 0) {
													balanco.setId(
															emp.getBalancos().get(emp.getBalancos().size() - 1).getId()
																	+ 1);
												} else {
													balanco.setId(1);
												}

												System.out.printf("Informe a data inicial do balan�o(DD/MM/YYYY): ");
												balanco.setDtInicio(sdf.parse(scn.next()));
												System.out.printf("Informe a data final do balan�o(DD/MM/YYYY): ");
												balanco.setDtFinal(sdf.parse(scn.next()));

												do {
													Ativo ativo = new Ativo();

													if (balanco.getAtivos().size() > 0) {
														ativo.setId(balanco.getAtivos()
																.get(balanco.getAtivos().size() - 1).getId() + 1);
													} else {
														ativo.setId(1);
													}

													System.out.printf(
															"\n---------------- CADASTRO DE ATIVOS ----------------\n\n");
													System.out.printf("Informe o tipo de entrada: ");
													ativo.setTipo(scn.next());
													System.out.printf("Informe a descri��o da entrada: ");
													ativo.setDescricao(scn.next());
													System.out.printf("Informe o valor da entrada: ");
													ativo.setValor(scn.nextDouble());

													balanco.getAtivos().add(ativo);

													System.out.printf("Gostaria de adicionar mais uma entrada?");
													resposta = scn.next().toUpperCase().charAt(0);

													while (resposta != 'S' && resposta != 'N') {
														System.out.printf("Utilizar padr�o S/N!\n");
														System.out.printf("Digite novamente: ");
														resposta = scn.next().toUpperCase().charAt(0);
													}

												} while (resposta == 'S');

												do {
													Passivo passivo = new Passivo();

													if (balanco.getPassivos().size() > 0) {
														passivo.setId(balanco.getPassivos()
																.get(balanco.getPassivos().size() - 1).getId() + 1);
													} else {
														passivo.setId(1);
													}

													System.out.printf(
															"\n---------------- CADASTRO DE PASSIVOS ----------------\n\n");
													System.out.printf("Informe a descri��o da sa�da: ");
													passivo.setDescricao(scn.next());
													System.out.printf("Informe o valor da sa�da: ");
													passivo.setValor(scn.nextDouble());

													balanco.getPassivos().add(passivo);

													System.out.printf("Gostaria de adicionar mais uma sa�da?");
													resposta = scn.next().toUpperCase().charAt(0);

													while (resposta != 'S' && resposta != 'N') {
														System.out.printf("Utilizar padr�o S/N!\n");
														System.out.printf("Digite novamente: ");
														resposta = scn.next().toUpperCase().charAt(0);
													}

												} while (resposta == 'S');

												balanco.setPatrimonioLiquido(balanco.getAtivos(),
														balanco.getPassivos());

												emp.getBalancos().add(balanco);
											}

											if (emp.getAtivoIpo()) {
												System.out.printf("\nGostaria de atualizar os dados do IPO?");
												resposta = scn.next().toUpperCase().charAt(0);

												while (resposta != 'S' && resposta != 'N') {
													System.out.printf("Utilizar padr�o S/N!\n");
													System.out.printf("Digite novamente: ");
													resposta = scn.next().toUpperCase().charAt(0);
												}

												if (resposta == 'S') {
													System.out.printf("Informe o valor inicial do IPO atualizado: ");
													emp.getIpo().setValorInicial(scn.nextDouble());
													System.out.printf("Informe a descri��o do IPO atualizada: ");
													emp.getIpo().setDescricao(scn.next());
												}

											} else {
												emp.setIpo(null);
											}

										}

										// ATUALIZAR EMPRESA SE TIVER NA LISTA DE FAVORITOS DE ALGUM USU�RIO
										indexEmpresaUsuario = -1;
										for (Usuario u : usuarios) {
											if (u.getEmpresas().size() > 0) {
												for (Empresa e : u.getEmpresas()) {
													if (e.getNome() == nome) {
														indexEmpresaUsuario = u.getEmpresas().indexOf(e);

														empUsuario = u.getEmpresas().get(indexEmpresaUsuario);

														empUsuario.setNome(emp.getNome());
														empUsuario.setSetor(emp.getSetor());
														empUsuario.setAtivoIpo(emp.getAtivoIpo());
														empUsuario.setGovernancas(emp.getGovernancas());
														empUsuario.setValores(emp.getValores());
														empUsuario.setBalancos(emp.getBalancos());
														empUsuario.setIpo(emp.getIpo());
													}
												}
											}
										}

										System.out.printf("Empresa atualizada com sucesso!\n\n");

									} else {
										System.out.printf("Empresa n�o encontrada!\n\n");
									}

								} else {
									System.out.printf("N�o existe empresas cadastradas para serem atualizadas\n\n");
								}

								System.in.read();
							} else if (opcao == 13) { // EXCLUS�O EMPRESA
								if (empresas.size() > 0) {
									for (Empresa e : empresas) {
										System.out.println("ID: " + e.getId() + "\tEmpresa: " + e.getNome());
									}

									System.out.printf("Informe o ID da empresa que deseja excluir: ");
									idEmpresa = scn.nextInt();

									indexEmpresa = -1;
									for (Empresa p : empresas) {
										if (p.getId() == idEmpresa) {
											indexEmpresa = empresas.indexOf(p);
										}
									}

									if (indexEmpresa != -1) {

										emp = empresas.get(indexEmpresa);

										for (Usuario u : usuarios) {
											if (u.getEmpresas().size() > 0) {
												for (Empresa e : u.getEmpresas()) {
													if (e.getId() == idEmpresa) {
														indexEmpresaUsuario = u.getEmpresas().indexOf(e);

														u.getEmpresas().remove(indexEmpresaUsuario);
														break;

													}
												}
											}
										}

										empresas.remove(indexEmpresa);
										System.out.printf("Empresa exclu�da com sucesso!\n\n");
									} else {
										System.out.printf("Empresa n�o encontrada!\n\n");
									}
								} else {
									System.out.println("N�o existe empresas cadastradas para serem exclu�das\n\n");
								}

								System.in.read();
							} else if (opcao == 14) { // LISTAR EMPRESAS
								if (empresas.size() > 0) {
									for (Empresa e : empresas) {
										System.out.printf("ID: " + e.getId() + "\tEmpresa: " + e.getNome() + "\n");
									}

									System.out.printf("\nGostaria de ver alguma empresa com mais detalhes?");
									resposta = scn.next().toUpperCase().charAt(0);

									while (resposta != 'S' && resposta != 'N') {
										System.out.printf("Utilizar padr�o S/N!\n");
										System.out.printf("Digite novamente: ");
										resposta = scn.next().toUpperCase().charAt(0);
									}

									if (resposta == 'S') {
										do {
											System.out.printf(
													"Informe o ID da empresa que deseja ver com mais detalhes: ");
											idEmpresa = scn.nextInt();

											indexEmpresa = -1;
											for (Empresa p : empresas) {
												if (p.getId() == idEmpresa) {
													indexEmpresa = empresas.indexOf(p);
												}
											}

											if (indexEmpresa != -1) {
												emp = empresas.get(indexEmpresa);
												System.out.printf(
														"\n-------------------------------------------------------------------------------------------\n");
												System.out.printf("ID: " + emp.getId() + "\tEmpresa: " + emp.getNome()
														+ "\tSetor: " + emp.getSetor() + "\tPossui IPO: "
														+ emp.getAtivoIpo());

												if (emp.getGovernancas().size() > 0) {
													System.out.printf(
															"\n\n--------------- GOVERNAN�A ---------------\n\n");
													for (Governanca c : emp.getGovernancas()) {
														System.out.printf("Per�do: " + sdf.format(c.getDtInicio())
																+ "\t\t" + sdf.format(c.getDtFim()) + "\n\n");
														System.out.printf("Presidentes: ");
														for (String s : c.getPresidente()) {
															System.out.printf(s + " |\t");
														}

														System.out.printf("\nDiretoria: ");
														for (String s : c.getDiretoria()) {
															System.out.printf(s + " |\t");
														}

														System.out.printf("\nComit�: ");
														for (String s : c.getComites()) {
															System.out.printf(s + " |\t");
														}

														System.out.printf("\nConselho Fiscal: ");
														for (String s : c.getConselhoFiscal()) {
															System.out.printf(s + " |\t");
														}

														System.out.printf("\nConselho Consultivo: ");
														for (String s : c.getConselhoConsultivo()) {
															System.out.printf(s + " |\t");
														}

														System.out.printf("\nConselho Administrativo: ");
														for (String s : c.getConselhoAdministrativo()) {
															System.out.printf(s + " |\t");
														}

														System.out.printf("\nAssembleia Geral: ");
														for (String s : c.getAssembleiaGeral()) {
															System.out.printf(s + " |\t");
														}
													}
												} else {
													System.out.printf(
															"N�o existe uma governan�a cadastrada para essa empresa ainda!\n");
												}

												if (emp.getValores().size() > 0) {
													System.out
															.printf("\n\n--------------- VALORES ---------------\n\n");
													for (Valores v : emp.getValores()) {
														System.out.printf("Valor: " + v.getNome() + "\nDescri��o: "
																+ v.getDescricao() + "\n\n");
													}
												} else {
													System.out.printf(
															"N�o existe valores cadastrados para essa empresa ainda!\n");
												}

												if (emp.getBalancos().size() > 0) {
													System.out
															.printf("\n\n--------------- BALAN�OS ---------------\n\n");
													for (Balanco b : emp.getBalancos()) {
														System.out.printf("Per�odo: " + sdf.format(b.getDtInicio())
																+ "\t\t" + sdf.format(b.getDtFinal()) + "\n\n");
														System.out.printf("Ativos: \n");
														for (Ativo a : b.getAtivos()) {
															System.out.printf("ID: " + a.getId() + "\tNome: "
																	+ a.getDescricao() + "\tTipo: " + a.getTipo()
																	+ "\tValor: R$" + a.getValor() + "\n");
														}

														System.out.printf("\nPassivos: \n");
														for (Passivo p : b.getPassivos()) {
															System.out.printf(
																	"ID: " + p.getId() + "\tNome: " + p.getDescricao()
																			+ "\tValor: R$" + p.getValor() + "\n");
														}

														System.out.printf(
																"\n-------------------------------------------------------------------------------------------\n");
														System.out.printf(
																"Patrim�nio l�quido: R$" + b.getPatrimonioLiquido());
													}
												} else {
													System.out.printf(
															"N�o existe balan�os cadastrados para essa empresa ainda!\n");
												}

												if (emp.getAtivoIpo()) {
													System.out.printf("\n\n--------------- IPO ---------------\n\n");
													System.out.printf("Descri��o:" + emp.getIpo().getDescricao()
															+ "\tValor Inicial: " + emp.getIpo().getValorInicial());
												}

											} else {
												System.out.printf("Empresa n�o encontrada!\n\n");
											}

											System.out.printf("\n\nGostaria de ver outra empresa?");
											resposta = scn.next().toUpperCase().charAt(0);

											while (resposta != 'S' && resposta != 'N') {
												System.out.printf("Utilizar padr�o S/N!\n");
												System.out.printf("Digite novamente");
												resposta = scn.next().toUpperCase().charAt(0);
											}
										} while (resposta == 'S');

									}

								} else {
									System.out.println("N�o existe empresas cadastradas!\n\n");
								}

								System.in.read();
							} else if (opcao == 15) { // LISTAR USU�RIOS
								if (usuarios.size() > 0) {
									for (Usuario u : usuarios) {
										System.out.println("ID: " + u.getId() + "\tNome: " + u.getNome() + "\tEmail: "
												+ u.getEmail() + "\tData Nascimento: " + sdf.format(u.getDtNascimento())
												+ "\tPapel: " + u.getPapel() + "\n");
									}

								} else {
									System.out.printf("N�o existe usu�rios cadastrados!\n\n");
								}

								System.in.read();
							} else if (opcao == 16) { // EDITAR USU�RIO
								indexUsuarioLogado = -1;
								for (Usuario u : usuarios) {
									if (u.getId() == usuarioLogado.getId()) {
										indexUsuarioLogado = usuarios.indexOf(u);
									}
								}

								System.out.printf("Gostaria de editar o seu usu�rio?");
								resposta = scn.next().toUpperCase().charAt(0);

								while (resposta != 'S' && resposta != 'N') {
									System.out.printf("Utilizar padr�o S/N!\n");
									System.out.printf("Digite novamente: ");
									resposta = scn.next().toUpperCase().charAt(0);
								}

								if (resposta == 'S') {
									usu = usuarios.get(indexUsuarioLogado);

									nome = usu.getNome();

									System.out.printf("Informe o seu nome atualizado: ");
									usu.setNome(scn.next());
									System.out.printf("Informe o seu e-mail atualizado: ");
									email = scn.next();

									emailJaExistente = 'N';
									for (Usuario u : usuarios) {
										if (u.getEmail() == email) {
											emailJaExistente = 'S';
											break;
										}
									}

									if (emailJaExistente == 'S') {
										System.out.printf("Esse e-mail j� est� sendo utilizado em algum cadastro!\n\n");
									} else {
										usu.setEmail(email);
									}

									System.out.printf("Informe a sua data de nascimento atualizada: ");
									usu.setDtNascimento(sdf.parse(scn.next()));

									System.out.printf("Usu�rio atualizado com sucesso!\n\n");

									for (Postagem e : usu.getPostagens()) {
										if (e.getUsuario().getNome() == nome) {
											e.getUsuario().setNome(usu.getNome());
										}
									}
								}

								System.in.read();
							} else if (opcao == 17) {
								indexUsuarioLogado = -1;
								for (Usuario u : usuarios) {
									if (u.getId() == usuarioLogado.getId()) {
										indexUsuarioLogado = usuarios.indexOf(u);
									}
								}

								System.out.printf("Gostaria de excluir o seu usu�rio?");
								resposta = scn.next().toUpperCase().charAt(0);

								while (resposta != 'S' && resposta != 'N') {
									System.out.printf("Utilizar padr�o S/N!\n");
									System.out.printf("Digite novamente: ");
									resposta = scn.next().toUpperCase().charAt(0);
								}

								if (resposta == 'S') {
									usuarios.remove(indexUsuarioLogado);
									usuarioLogado = null;
									System.out.printf("Usu�rio exclu�do com sucesso!\n\n");
								}

								System.in.read();
							} else if (opcao == 18) {
								Usuario usuario = new Usuario();

								if (usuarios.size() > 0) {
									usuario.setId(usuarios.get(usuarios.size() - 1).getId() + 1);
								} else {
									usuario.setId(1);
								}

								System.out.printf("Digite o nome do novo adm: ");
								usuario.setNome(scn.next());
								System.out.printf("Digite o e-mail do novo adm: ");
								email = scn.next();

								emailJaExistente = 'N';
								for (Usuario u : usuarios) {
									if (u.getEmail() == email) {
										emailJaExistente = 'S';
										break;
									}
								}

								if (emailJaExistente == 'N') {
									usuario.setEmail(email);
									System.out.printf("Digite sua senha: ");
									usuario.setSenha(scn.next());
									System.out.printf("Digite sua data de nascimento(DD/MM/YYYY): ");
									usuario.setDtNascimento(sdf.parse(scn.next()));

									usuario.setPapel("admin");

									usuarios.add(usuario);

									System.out.printf("Usu�rio cadastrado com sucesso!\n\n");
								} else {
									System.out.println("E-mail j� cadastrado no sistema!\n\n");
								}

								System.in.read();
							}
						} // FIM MENU ADMINISTRATIVO

					} else {
						System.out.printf("Login ou senha inv�lidos!\n\n");
						break;
					}

				} while (opcao >= 1 && opcao <= 18 && usuarioLogado != null);

				opcao = 1;

			} else if (opcao == 2) {
				Usuario usuario = new Usuario();

				if (usuarios.size() > 0) {
					usuario.setId(usuarios.get(usuarios.size() - 1).getId() + 1);
				} else {
					usuario.setId(1);
				}

				System.out.printf("Digite seu nome: ");
				usuario.setNome(scn.next());
				System.out.printf("Digite seu e-mail: ");
				email = scn.next();

				emailJaExistente = 'N';
				for (Usuario u : usuarios) {
					if (u.getEmail() == email) {
						emailJaExistente = 'S';
						break;
					}
				}

				if (emailJaExistente == 'N') {
					usuario.setEmail(email);
					System.out.printf("Digite sua senha: ");
					usuario.setSenha(scn.next());
					System.out.printf("Digite sua data de nascimento(DD/MM/YYYY): ");
					usuario.setDtNascimento(sdf.parse(scn.next()));

					usuario.setPapel("comum");

					usuarios.add(usuario);

					System.out.printf("Usu�rio cadastrado com sucesso!\n\n");
				} else {
					System.out.println("E-mail j� cadastrado no sistema!\n\n");
				}

				System.in.read();
			} else if (opcao == 3) {
				do {
					System.out.printf("--------------- MENU ---------------\n\n");
					System.out.printf("1 - Acessar Blog\n" + "2 - Acessar empresas\n" + "3 - Sair\n");
					System.out.printf("Digite a op��o desejada: ");
					opcao = scn.nextInt();

					while (opcao != 1 && opcao != 2 && opcao != 3) {
						System.out.printf("\nOp��o Inv�lida!\n");
						System.out.printf("Digite novamente: ");
						opcao = scn.nextInt();
					}

					if (opcao == 1) {
						
						System.out.println("Valores di�rios das taxas:\n");
						taxas.forEach((key, value) -> {
				            System.out.println(key + ": " + value + "%\n");
				          });
						
						if (postagens.size() > 0) {
							for (Postagem p : postagens) {
								System.out.printf("ID: " + p.getId() + "\tPostagem: " + p.getTitulo() + "\n");
							}

							System.out.printf("Informe o ID da postagem que deseja visualizar: ");
							id = scn.nextInt();

							indexPostagemGeral = -1;
							for (Postagem p : postagens) {
								if (p.getId() == id) {
									indexPostagemGeral = postagens.indexOf(p);
								}
							}

							if (indexPostagemGeral != -1) {
								post = postagens.get(indexPostagemGeral);
								System.out.printf(
										"\n-------------------------------------------------------------------------------------------\n");
								System.out.printf("\n\nT�tulo: " + post.getTitulo() + "\t\t\tCriada por: "
										+ post.getUsuario().getNome() + "\n\nConte�do: " + post.getConteudo()
										+ "\n\nImagem URL: " + post.getImgUrl() + "\n\nData: "
										+ sdf.format(post.getDate()) + "\tCategoria: " + post.getCategoria()
										+ "\t\t\tLikes: " + post.getLikes());
								System.out.printf(
										"\n-------------------------------------------------------------------------------------------\n");

								if (post.getComentarios() != null && post.getComentarios().size() > 0) {
									System.out.printf("Coment�rios:\n\n");
									for (Comentario c : post.getComentarios()) {
										System.out.printf(c.getUsuario().getNome() + ": " + c.getConteudo()
												+ "\t\tData: " + sdf.format(c.getData()) + "\n");
									}
									System.out.printf(
											"\n-------------------------------------------------------------------------------------------\n\n");
								}

								System.out.printf("Para curtir e comentar � necess�rio estar logado!\n");
								System.out.printf("Gostaria de realizar o login/cadastro?(S/N)");
								resposta = scn.next().toUpperCase().charAt(0);

								while (resposta != 'S' && resposta != 'N') {
									System.out.printf("Utilizar padr�o S/N!\n");
									System.out.printf("Digite novamente: ");
									resposta = scn.next().toUpperCase().charAt(0);
								}

								if (resposta == 'S') {
									System.out.printf("J� possui conta?(S/N)");
									resposta = scn.next().toUpperCase().charAt(0);

									while (resposta != 'S' && resposta != 'N') {
										System.out.printf("Utilizar padr�o S/N!\n");
										System.out.printf("Digite novamente: ");
										resposta = scn.next().toUpperCase().charAt(0);
									}

									if (resposta == 'S') {
										System.out.printf("Informe o seu e-mail: ");
										email = scn.next();
										System.out.printf("Informe a sua senha: ");
										senha = scn.next();

										indexUsuarioLogado = -1;
										for (Usuario u : usuarios) {
											if ((u.getEmail().equals(email)) && (u.getSenha().equals(senha))) {
												System.out.printf("Usu�rio logado com sucesso!\n");
												indexUsuarioLogado = usuarios.indexOf(u);
												break;
											}
										}

										if (indexUsuarioLogado != -1) {
											usuarioLogado = usuarios.get(indexUsuarioLogado);

											System.out.printf("Curtiu essa postagem?(S/N)");
											resposta = scn.next().toUpperCase().charAt(0);

											while (resposta != 'S' && resposta != 'N') {
												System.out.printf("Utilizar padr�o S/N!\n");
												System.out.printf("Digite novamente: ");
												resposta = scn.next().toUpperCase().charAt(0);
											}

											if (resposta == 'S') {
												post.setLikes(post.getLikes() + 1);
											}

											System.out
													.printf("\nGostaria de deixar um comentario nessa postagem?(S/N)");
											resposta = scn.next().toUpperCase().charAt(0);

											while (resposta != 'S' && resposta != 'N') {
												System.out.printf("Utilizar padr�o S/N!\n");
												System.out.printf("Digite novamente: ");
												resposta = scn.next().toUpperCase().charAt(0);
											}

											if (resposta == 'S') {
												Comentario comentario = new Comentario();

												if (post.getComentarios().size() > 0) {
													comentario.setId(post.getComentarios()
															.get(post.getComentarios().size() - 1).getId() + 1);
												} else {
													comentario.setId(1);
												}

												System.out.printf("Entre com o seu coment�rio: ");
												comentario.setConteudo(scn.next());
												comentario.setData(new Date());
												comentario.setUsuario(usuarioLogado);

												post.addComentario(comentario);
												System.out.println("Coment�rio realizado com sucesso!");
											}
										} else {
											System.out.printf("Usu�rio n�o encontrado");
										}
									} else {
										Usuario usuario = new Usuario();

										if (usuarios.size() > 0) {
											usuario.setId(usuarios.get(usuarios.size() - 1).getId() + 1);
										} else {
											usuario.setId(1);
										}

										System.out.printf("Digite seu nome: ");
										usuario.setNome(scn.next());
										System.out.printf("Digite seu e-mail: ");
										email = scn.next();

										emailJaExistente = 'N';
										for (Usuario u : usuarios) {
											if (u.getEmail() == email) {
												emailJaExistente = 'S';
												break;
											}
										}

										if (emailJaExistente == 'N') {
											usuario.setEmail(email);
											System.out.printf("Digite sua senha: ");
											usuario.setSenha(scn.next());
											System.out.printf("Digite sua data de nascimento(DD/MM/YYYY): ");
											usuario.setDtNascimento(sdf.parse(scn.next()));

											usuario.setPapel("comum");

											usuarios.add(usuario);

											System.out.printf("Usu�rio cadastrado com sucesso!\n\n");
										} else {
											System.out.println("E-mail j� cadastrado no sistema!\n\n");
										}

										System.out.printf("Informe o seu e-mail: ");
										email = scn.next();
										System.out.printf("Informe a sua senha: ");
										senha = scn.next();

										indexUsuarioLogado = -1;
										for (Usuario u : usuarios) {
											if ((u.getEmail().equals(email)) && (u.getSenha().equals(senha))) {
												System.out.printf("Usu�rio logado com sucesso!\n");
												indexUsuarioLogado = usuarios.indexOf(u);
												break;
											}
										}

										if (indexUsuarioLogado != -1) {
											usuarioLogado = usuarios.get(indexUsuarioLogado);

											System.out.printf("Curtiu essa postagem?(S/N)");
											resposta = scn.next().toUpperCase().charAt(0);

											while (resposta != 'S' && resposta != 'N') {
												System.out.printf("Utilizar padr�o S/N!\n");
												System.out.printf("Digite novamente: ");
												resposta = scn.next().toUpperCase().charAt(0);
											}

											if (resposta == 'S') {
												post.setLikes(post.getLikes() + 1);
											}

											System.out
													.printf("\nGostaria de deixar um comentario nessa postagem?(S/N)");
											resposta = scn.next().toUpperCase().charAt(0);

											while (resposta != 'S' && resposta != 'N') {
												System.out.printf("Utilizar padr�o S/N!\n");
												System.out.printf("Digite novamente: ");
												resposta = scn.next().toUpperCase().charAt(0);
											}

											if (resposta == 'S') {
												Comentario comentario = new Comentario();

												if (post.getComentarios().size() > 0) {
													comentario.setId(post.getComentarios()
															.get(post.getComentarios().size() - 1).getId() + 1);
												} else {
													comentario.setId(1);
												}

												System.out.printf("Entre com o seu coment�rio: ");
												comentario.setConteudo(scn.next());
												comentario.setData(new Date());
												comentario.setUsuario(usuarioLogado);

												post.addComentario(comentario);
												System.out.println("Coment�rio realizado com sucesso!");
											}
										} else {
											System.out.printf("Usu�rio n�o encontrado");
										}

									}
								}
							}
						} else {
							System.out.printf("N�o existem postagens cadastradas!\n\n");
						}

						System.in.read();

					} else if (opcao == 2) {
						if (empresas.size() > 0) {
							do {
								System.out.printf("--------------- MENU ---------------\n\n");
								System.out.printf("1 - Empresas\n" + "2 - IPOs\n" + "3 - Sair\n");
								System.out.printf("Digite a op��o desejada: ");
								opcao = scn.nextInt();

								while (opcao != 1 && opcao != 2 && opcao != 3) {
									System.out.printf("\nOp��o Inv�lida!\n");
									System.out.printf("Digite novamente: ");
									opcao = scn.nextInt();
								}

								if (opcao == 1) {
									count = 0;
									for (Empresa e : empresas) {
										if (e.getGovernancas().size() > 0 && e.getValores().size() > 0
												&& e.getBalancos().size() > 0) {
											System.out.printf("ID: " + e.getId() + "\tEmpresa: " + e.getNome() + "\n");
											count++;
										}
									}

									if (count > 0) {
										System.out.printf("\nGostaria de ver alguma empresa com mais detalhes?");
										resposta = scn.next().toUpperCase().charAt(0);

										while (resposta != 'S' && resposta != 'N') {
											System.out.printf("Utilizar padr�o S/N!\n");
											System.out.printf("Digite novamente: ");
											resposta = scn.next().toUpperCase().charAt(0);
										}

										if (resposta == 'S') {
											do {
												System.out.printf(
														"Informe o ID da empresa que deseja ver com mais detalhes: ");
												idEmpresa = scn.nextInt();

												indexEmpresa = -1;
												for (Empresa p : empresas) {
													if (p.getId() == idEmpresa) {
														indexEmpresa = empresas.indexOf(p);
													}
												}

												if (indexEmpresa != -1) {
													emp = empresas.get(indexEmpresa);
													System.out.printf(
															"\n-------------------------------------------------------------------------------------------\n\n");
													System.out.printf("ID: " + emp.getId() + "\tEmpresa: "
															+ emp.getNome() + "\tSetor: " + emp.getSetor()
															+ "\tPossui IPO: " + emp.getAtivoIpo());

													if (emp.getGovernancas().size() > 0) {
														System.out.printf(
																"\n\n--------------- GOVERNAN�A ---------------\n\n");
														for (Governanca c : emp.getGovernancas()) {
															System.out.printf("Per�do: " + sdf.format(c.getDtInicio())
																	+ "\t\t" + sdf.format(c.getDtFim()) + "\n\n");
															System.out.printf("Presidentes: ");
															for (String s : c.getPresidente()) {
																System.out.printf(s + " |\t");
															}

															System.out.printf("\nDiretoria: ");
															for (String s : c.getDiretoria()) {
																System.out.printf(s + " |\t");
															}

															System.out.printf("\nComit�: ");
															for (String s : c.getComites()) {
																System.out.printf(s + " |\t");
															}

															System.out.printf("\nConselho Fiscal: ");
															for (String s : c.getConselhoFiscal()) {
																System.out.printf(s + " |\t");
															}

															System.out.printf("\nConselho Consultivo: ");
															for (String s : c.getConselhoConsultivo()) {
																System.out.printf(s + " |\t");
															}

															System.out.printf("\nConselho Administrativo: ");
															for (String s : c.getConselhoAdministrativo()) {
																System.out.printf(s + " |\t");
															}

															System.out.printf("\nAssembleia Geral: ");
															for (String s : c.getAssembleiaGeral()) {
																System.out.printf(s + " |\t");
															}
														}
													} else {
														System.out.printf(
																"\nN�o existe uma governan�a cadastrada para essa empresa ainda!");
													}

													if (emp.getValores().size() > 0) {
														System.out.printf(
																"\n\n--------------- VALORES ---------------\n\n");
														for (Valores v : emp.getValores()) {
															System.out.printf("Valor: " + v.getNome() + "\nDescri��o: "
																	+ v.getDescricao() + "\n\n");
														}
													} else {
														System.out.printf(
																"\nN�o existe valores cadastrados para essa empresa ainda!");
													}

													if (emp.getBalancos().size() > 0) {
														System.out.printf(
																"\n\n--------------- BALAN�OS ---------------\n\n");
														for (Balanco b : emp.getBalancos()) {
															System.out.printf("Per�odo: " + sdf.format(b.getDtInicio())
																	+ "\t\t" + sdf.format(b.getDtFinal()) + "\n\n");
															System.out.printf("\n\nAtivos: \n");
															for (Ativo a : b.getAtivos()) {
																System.out.printf("ID: " + a.getId() + "\tNome: "
																		+ a.getDescricao() + "\tTipo: " + a.getTipo()
																		+ "\tValor: R$" + a.getValor() + "\n");
															}

															System.out.printf("\n\nPassivos: \n");
															for (Passivo p : b.getPassivos()) {
																System.out.printf("ID: " + p.getId() + "\tNome: "
																		+ p.getDescricao() + "\tValor: R$"
																		+ p.getValor() + "\n");
															}

															System.out.printf(
																	"\n-------------------------------------------------------------------------------------------\n");
															System.out.printf("Patrim�nio l�quido: R$"
																	+ b.getPatrimonioLiquido());
														}
													} else {
														System.out.printf(
																"\nN�o existe balan�os cadastrados para essa empresa ainda!");
													}

													if (emp.getAtivoIpo()) {
														System.out
																.printf("\n\n--------------- IPO ---------------\n\n");
														System.out.printf("Descri��o:" + emp.getIpo().getDescricao()
																+ "\tValor Inicial: " + emp.getIpo().getValorInicial());
													}

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
														System.out.printf("J� possui conta?(S/N)");
														resposta = scn.next().toUpperCase().charAt(0);

														while (resposta != 'S' && resposta != 'N') {
															System.out.printf("Utilizar padr�o S/N!\n");
															System.out.printf("Digite novamente: ");
															resposta = scn.next().toUpperCase().charAt(0);
														}

														if (resposta == 'S') {
															System.out.printf("Informe o seu e-mail: ");
															email = scn.next();
															System.out.printf("Informe a sua senha: ");
															senha = scn.next();

															indexUsuarioLogado = -1;
															for (Usuario u : usuarios) {
																if ((u.getEmail().equals(email))
																		&& (u.getSenha().equals(senha))) {
																	System.out.printf("Usu�rio logado com sucesso!\n");
																	indexUsuarioLogado = usuarios.indexOf(u);
																	break;
																}
															}

															if (indexUsuarioLogado != -1) {
																usuarioLogado = usuarios.get(indexUsuarioLogado);

																empresaJaExistente = 'N';
																for (Empresa e : usuarioLogado.getEmpresas()) {
																	if (e.getNome() == emp.getNome()) {
																		empresaJaExistente = 'S';
																		break;
																	}
																}

																if (empresaJaExistente == 'N') {
																	usuarioLogado.addEmpresa(emp);
																	System.out.printf(
																			"Empresa adicionada com sucesso!\n\n");
																} else {
																	System.out.println(
																			"Empresa j� consta na sua lista de favoritos!\n\n");
																}

															} else {
																System.out.printf("Usu�rio n�o encontrado");
															}
														} else {
															Usuario usuario = new Usuario();

															if (usuarios.size() > 0) {
																usuario.setId(
																		usuarios.get(usuarios.size() - 1).getId() + 1);
															} else {
																usuario.setId(1);
															}

															System.out.printf("Digite seu nome: ");
															usuario.setNome(scn.next());
															System.out.printf("Digite seu e-mail: ");
															email = scn.next();

															emailJaExistente = 'N';
															for (Usuario u : usuarios) {
																if (u.getEmail() == email) {
																	emailJaExistente = 'S';
																	break;
																}
															}

															if (emailJaExistente == 'N') {
																usuario.setEmail(email);
																System.out.printf("Digite sua senha: ");
																usuario.setSenha(scn.next());
																System.out.printf(
																		"Digite sua data de nascimento(DD/MM/YYYY): ");
																usuario.setDtNascimento(sdf.parse(scn.next()));

																usuario.setPapel("comum");

																usuarios.add(usuario);

																System.out
																		.printf("Usu�rio cadastrado com sucesso!\n\n");
															} else {
																System.out.println(
																		"E-mail j� cadastrado no sistema!\n\n");
															}

															System.out.printf("Informe o seu e-mail: ");
															email = scn.next();
															System.out.printf("Informe a sua senha: ");
															senha = scn.next();

															indexUsuarioLogado = -1;
															for (Usuario u : usuarios) {
																if ((u.getEmail().equals(email))
																		&& (u.getSenha().equals(senha))) {
																	System.out.printf("Usu�rio logado com sucesso!\n");
																	indexUsuarioLogado = usuarios.indexOf(u);
																	break;
																}
															}

															if (indexUsuarioLogado != -1) {
																usuarioLogado = usuarios.get(indexUsuarioLogado);
																empresaJaExistente = 'N';

																for (Empresa e : usuarioLogado.getEmpresas()) {
																	if (e.getNome() == emp.getNome()) {
																		empresaJaExistente = 'S';
																		break;
																	}
																}

																if (empresaJaExistente == 'N') {
																	usuarioLogado.addEmpresa(emp);
																	System.out.printf(
																			"Empresa adicionada com sucesso!\n\n");
																} else {
																	System.out.println(
																			"Empresa j� consta na sua lista de favoritos!\n\n");
																}
															} else {
																System.out.printf("Usu�rio n�o encontrado");
															}

														}
													}

												} else {
													System.out.printf("Empresa n�o encontrada!\n\n");
												}

												System.out.printf(
														"\n-------------------------------------------------------------------------------------------\n\n");
												System.out.printf("Gostaria de ver outra empresa?");
												resposta = scn.next().toUpperCase().charAt(0);

												while (resposta != 'S' && resposta != 'N') {
													System.out.printf("Utilizar padr�o S/N!\n");
													System.out.printf("Digite novamente: ");
													resposta = scn.next().toUpperCase().charAt(0);
												}
											} while (resposta == 'S');
										}
									} else {
										System.out.printf(
												"Infelizmente no momento n�o possu�mos empresas cadastradas!\n\n");
									}

									System.in.read();
								} else if (opcao == 2) {
									System.out.printf("\n--------------- ATEN��O ---------------\n\n");
									System.out.printf("Segue as datas em que a bolsa n�o opera: \n");
									
									feriados.forEach((chave, valor) -> {
							            System.out.println(chave + ": " + valor);
									});  
									
									count = 0;
									for (Empresa e : empresas) {
										if (e.getGovernancas().size() > 0 && e.getValores().size() > 0
												&& e.getBalancos().size() > 0) {
											count++;
										}
									}

									if (count > 0) {

										count = 0;
										for (Empresa e : empresas) {
											if (e.getAtivoIpo()) {
												System.out.printf(
														"ID: " + e.getId() + "\tEmpresa: " + e.getNome() + "\n");
												count++;
											}
										}

										if (count > 0) {
											System.out.printf(
													"\nEssas s�o as empresas que possuem IPOs ativos no momento. Gostaria de ver alguma com mais detalhes? ");
											resposta = scn.next().toUpperCase().charAt(0);

											while (resposta != 'S' && resposta != 'N') {
												System.out.printf("Utilizar padr�o S/N!\n");
												System.out.printf("Digite novamente: ");
												resposta = scn.next().toUpperCase().charAt(0);
											}

											if (resposta == 'S') {
												do {
													System.out.printf(
															"Informe o ID da empresa que deseja ver com mais detalhes: ");
													idEmpresa = scn.nextInt();

													indexEmpresa = -1;
													for (Empresa p : empresas) {
														if (p.getId() == idEmpresa) {
															indexEmpresa = empresas.indexOf(p);
														}
													}

													if (indexEmpresa != -1) {
														emp = empresas.get(indexEmpresa);
														System.out.printf(
																"\n-------------------------------------------------------------------------------------------\n\n");
														System.out.printf("ID: " + emp.getId() + "\tEmpresa: "
																+ emp.getNome() + "\tSetor: " + emp.getSetor()
																+ "\tPossui IPO: " + emp.getAtivoIpo());

														if (emp.getGovernancas().size() > 0) {
															System.out.printf(
																	"\n\n--------------- GOVERNAN�A ---------------\n\n");
															for (Governanca c : emp.getGovernancas()) {
																System.out.printf("Per�do: "
																		+ sdf.format(c.getDtInicio()) + "\t\t"
																		+ sdf.format(c.getDtFim()) + "\n\n");
																System.out.printf("Presidentes: ");
																for (String s : c.getPresidente()) {
																	System.out.printf(s + " |\t");
																}

																System.out.printf("\nDiretoria: ");
																for (String s : c.getDiretoria()) {
																	System.out.printf(s + " |\t");
																}

																System.out.printf("\nComit�: ");
																for (String s : c.getComites()) {
																	System.out.printf(s + " |\t");
																}

																System.out.printf("\nConselho Fiscal: ");
																for (String s : c.getConselhoFiscal()) {
																	System.out.printf(s + " |\t");
																}

																System.out.printf("\nConselho Consultivo: ");
																for (String s : c.getConselhoConsultivo()) {
																	System.out.printf(s + " |\t");
																}

																System.out.printf("\nConselho Administrativo: ");
																for (String s : c.getConselhoAdministrativo()) {
																	System.out.printf(s + " |\t");
																}

																System.out.printf("\nAssembleia Geral: ");
																for (String s : c.getAssembleiaGeral()) {
																	System.out.printf(s + " |\t");
																}
															}
														} else {
															System.out.printf(
																	"\nN�o existe uma governan�a cadastrada para essa empresa ainda!");
														}

														if (emp.getValores().size() > 0) {
															System.out.printf(
																	"\n\n--------------- VALORES ---------------\n\n");
															for (Valores v : emp.getValores()) {
																System.out.printf("Valor: " + v.getNome()
																		+ "\nDescri��o: " + v.getDescricao() + "\n\n");
															}
														} else {
															System.out.printf(
																	"\nN�o existe valores cadastrados para essa empresa ainda!");
														}

														if (emp.getBalancos().size() > 0) {
															System.out.printf(
																	"\n\n--------------- BALAN�OS ---------------\n\n");
															for (Balanco b : emp.getBalancos()) {
																System.out.printf("Per�odo: "
																		+ sdf.format(b.getDtInicio()) + "\t\t"
																		+ sdf.format(b.getDtFinal()) + "\n\n");
																System.out.printf("\n\nAtivos: \n");
																for (Ativo a : b.getAtivos()) {
																	System.out.printf("ID: " + a.getId() + "\tNome: "
																			+ a.getDescricao() + "\tTipo: "
																			+ a.getTipo() + "\tValor: R$" + a.getValor()
																			+ "\n");
																}

																System.out.printf("\n\nPassivos: \n");
																for (Passivo p : b.getPassivos()) {
																	System.out.printf("ID: " + p.getId() + "\tNome: "
																			+ p.getDescricao() + "\tValor: R$"
																			+ p.getValor() + "\n");
																}

																System.out.printf(
																		"\n-------------------------------------------------------------------------------------------\n");
																System.out.printf("Patrim�nio l�quido: R$"
																		+ b.getPatrimonioLiquido());
															}
														} else {
															System.out.printf(
																	"\nN�o existe balan�os cadastrados para essa empresa ainda!");
														}

														if (emp.getAtivoIpo()) {
															System.out.printf(
																	"\n\n--------------- IPO ---------------\n\n");
															System.out.printf("Descri��o:" + emp.getIpo().getDescricao()
																	+ "\tValor Inicial: "
																	+ emp.getIpo().getValorInicial());
														}

														System.out.printf(
																"\n\nPara salvar uma empresa na sua lista de favoritos � necess�rio estar logado!\n");
														System.out
																.printf("Gostaria de realizar o login/cadastro?(S/N)");
														resposta = scn.next().toUpperCase().charAt(0);

														while (resposta != 'S' && resposta != 'N') {
															System.out.printf("Utilizar padr�o S/N!\n");
															System.out.printf("Digite novamente: ");
															resposta = scn.next().toUpperCase().charAt(0);
														}

														if (resposta == 'S') {
															System.out.printf("J� possui conta?(S/N)");
															resposta = scn.next().toUpperCase().charAt(0);

															while (resposta != 'S' && resposta != 'N') {
																System.out.printf("Utilizar padr�o S/N!\n");
																System.out.printf("Digite novamente: ");
																resposta = scn.next().toUpperCase().charAt(0);
															}

															if (resposta == 'S') {
																System.out.printf("Informe o seu e-mail: ");
																email = scn.next();
																System.out.printf("Informe a sua senha: ");
																senha = scn.next();

																indexUsuarioLogado = -1;
																for (Usuario u : usuarios) {
																	if ((u.getEmail().equals(email))
																			&& (u.getSenha().equals(senha))) {
																		System.out.printf(
																				"Usu�rio logado com sucesso!\n");
																		indexUsuarioLogado = usuarios.indexOf(u);
																		break;
																	}
																}

																if (indexUsuarioLogado != -1) {
																	usuarioLogado = usuarios.get(indexUsuarioLogado);

																	empresaJaExistente = 'N';
																	for (Empresa e : usuarioLogado.getEmpresas()) {
																		if (e.getNome() == emp.getNome()) {
																			empresaJaExistente = 'S';
																			break;
																		}
																	}

																	if (empresaJaExistente == 'N') {
																		usuarioLogado.addEmpresa(emp);
																		System.out.printf(
																				"Empresa adicionada com sucesso!\n\n");
																	} else {
																		System.out.println(
																				"Empresa j� consta na sua lista de favoritos!\n\n");
																	}

																} else {
																	System.out.printf("Usu�rio n�o encontrado");
																}
															} else {
																Usuario usuario = new Usuario();

																if (usuarios.size() > 0) {
																	usuario.setId(
																			usuarios.get(usuarios.size() - 1).getId()
																					+ 1);
																} else {
																	usuario.setId(1);
																}

																System.out.printf("Digite seu nome: ");
																usuario.setNome(scn.next());
																System.out.printf("Digite seu e-mail: ");
																email = scn.next();

																emailJaExistente = 'N';
																for (Usuario u : usuarios) {
																	if (u.getEmail() == email) {
																		emailJaExistente = 'S';
																		break;
																	}
																}

																if (emailJaExistente == 'N') {
																	usuario.setEmail(email);
																	System.out.printf("Digite sua senha: ");
																	usuario.setSenha(scn.next());
																	System.out.printf(
																			"Digite sua data de nascimento(DD/MM/YYYY): ");
																	usuario.setDtNascimento(sdf.parse(scn.next()));

																	usuario.setPapel("comum");

																	usuarios.add(usuario);

																	System.out.printf(
																			"Usu�rio cadastrado com sucesso!\n\n");
																} else {
																	System.out.println(
																			"E-mail j� cadastrado no sistema!\n\n");
																}

																System.out.printf("Informe o seu e-mail: ");
																email = scn.next();
																System.out.printf("Informe a sua senha: ");
																senha = scn.next();

																indexUsuarioLogado = -1;
																for (Usuario u : usuarios) {
																	if ((u.getEmail().equals(email))
																			&& (u.getSenha().equals(senha))) {
																		System.out.printf(
																				"Usu�rio logado com sucesso!\n");
																		indexUsuarioLogado = usuarios.indexOf(u);
																		break;
																	}
																}

																if (indexUsuarioLogado != -1) {
																	usuarioLogado = usuarios.get(indexUsuarioLogado);
																	empresaJaExistente = 'N';

																	for (Empresa e : usuarioLogado.getEmpresas()) {
																		if (e.getNome() == emp.getNome()) {
																			empresaJaExistente = 'S';
																			break;
																		}
																	}

																	if (empresaJaExistente == 'N') {
																		usuarioLogado.addEmpresa(emp);
																		System.out.printf(
																				"Empresa adicionada com sucesso!\n\n");
																	} else {
																		System.out.println(
																				"Empresa j� consta na sua lista de favoritos!\n\n");
																	}
																} else {
																	System.out.printf("Usu�rio n�o encontrado");
																}

															}
														}

													} else {
														System.out.printf("Empresa n�o encontrada!\n\n");
													}

													System.out.printf("\n\nGostaria de ver outra empresa?");
													resposta = scn.next().toUpperCase().charAt(0);

													while (resposta != 'S' && resposta != 'N') {
														System.out.printf("Utilizar padr�o S/N!\n");
														System.out.printf("Digite novamente: ");
														resposta = scn.next().toUpperCase().charAt(0);
													}
												} while (resposta == 'S');
											}
										} else {
											System.out.printf("N�o encontramos empresas com IPOs abertos!\n\n");
										}

									} else {
										System.out.printf(
												"Infelizmente no momento n�o possu�mos empresas cadastradas!\n\n");
									}

									System.in.read();
								}

							} while (opcao >= 1 && opcao <= 2);
						} else {
							System.out.println("N�o possu�mos empresas cadastradas no momento\n\n");
						}

					}

				} while (opcao >= 1 && opcao <= 2);

			} else if (opcao == 4) {
				System.out.printf("Informe o seu email: ");
				email = scn.next();

				indexUsuario = -1;
				for (Usuario u : usuarios) {
					if (u.getEmail() == email) {
						indexUsuario = usuarios.indexOf(u);
					}
				}

				if (indexUsuario != -1) {
					usu = usuarios.get(indexUsuario);

					System.out.printf("Digite sua nova senha: ");
					usu.setSenha(scn.next());

					System.out.printf("Senha atualizada com sucesso!\n\n");

				} else {
					System.out.println("Usu�rio n�o cadastrado no sistema!\n\n");
				}

				System.in.read();
			}

		} while (opcao >= 1 && opcao <= 4);

	}

}
