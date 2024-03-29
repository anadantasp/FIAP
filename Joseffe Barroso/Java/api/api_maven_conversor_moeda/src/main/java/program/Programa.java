package program;

import java.io.IOException;
import java.util.Scanner;

import service.CotacaoService;

public class Programa {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);

		String conversor;
		float valor, resultado;
		int opcao;
		
		System.out.printf("---------- CONVERSOR DE MOEDAS ----------");
		System.out.printf("\nDigite o valor em reais que deseja converter: ");
		valor = scn.nextFloat();
		
		System.out.printf("\nDeseja converter para: "
				+"\n1 - D�lar"
				+"\n2 - Euro"
				+"\n3 - Bitcoin");
		System.out.printf("\nDigite a op��o deseja: ");
		opcao = scn.nextInt();
		
		while(opcao < 1 || opcao > 3) {
			System.out.println("Op��o inv�lida! Digite novamente: ");
			opcao = scn.nextInt();
		}
		
		if(opcao == 1) {
			conversor = "USD-BRL";
		}else if (opcao == 2){
			conversor = "EUR-BRL";
		}else {
			conversor = "BTC-BRL";
		}
		

		CotacaoService cotacaoService = new CotacaoService();

		try {
			float moeda = cotacaoService.getConversao(conversor);
			System.out.println(moeda);
			
			resultado = valor /moeda;
			
			System.out.printf("O valor de R$%.2f em %s � %.2f", valor, conversor.substring(0,3), resultado);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
