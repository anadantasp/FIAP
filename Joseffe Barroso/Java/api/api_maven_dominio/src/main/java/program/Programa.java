package program;

import java.io.IOException;
import java.util.Scanner;

import service.DominioService;

public class Programa {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		String dominio;
		String retorno;
		String dataFormatada;
		
		System.out.print("Digite um nome de dom�nio para verificar a disponibilidade: ");
		dominio = scn.next();
		
		DominioService dominioService = new DominioService();
		
		try {
			retorno = dominioService.verificaDisponibilidadeDominio(dominio);
			
			if(retorno == "") {
				System.out.print("Dom�nio " + dominio + " dispon�vel!");
			}else {
				System.out.println(retorno);
				dataFormatada = retorno.substring(8,10) + "/" + retorno.substring(5,7) + "/"
						+ retorno.substring(0, 4);
				System.out.println("Dom�nio " + dominio + " j� utilizado, por�m expira em " + dataFormatada);
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
