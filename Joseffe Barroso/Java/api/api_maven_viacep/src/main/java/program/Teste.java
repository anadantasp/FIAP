package program;

import java.io.IOException;
import java.util.Scanner;

import model.Endereco;
import service.ViaCepService;

public class Teste {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);

		String cep;

		System.out.printf("Digite o seu CEP: ");
		cep = scn.next();

		ViaCepService viacepservice = new ViaCepService();

		try {
			Endereco endereco = viacepservice.getEndereco(cep);

			System.out.println(endereco.imprimirEndereco());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
