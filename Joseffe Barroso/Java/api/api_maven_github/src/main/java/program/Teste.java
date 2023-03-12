package program;

import java.io.IOException;
import java.util.Scanner;

import model.Usuario;
import service.GitHubService;

public class Teste {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);

		String username;

		System.out.printf("Digite o seu username do github: ");
		username = scn.next();

		GitHubService githubservice = new GitHubService();

		try {
			Usuario usuario = githubservice.getUsuario(username);

			System.out.println(usuario.imprimirUsuario());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
