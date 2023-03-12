package program;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.http.client.ClientProtocolException;

import service.FeriadoService;

public class Programa {

	public static void main(String[] args) throws ClientProtocolException, IOException {

		Scanner scn = new Scanner(System.in);

		FeriadoService fs = new FeriadoService();
		HashMap<String, String> feriados = fs.getFeriados();

		String dataDigitada;

		System.out.print("Digite uma data (dd/mm/yyy): ");
		dataDigitada = scn.next();
		
		if (feriados.containsKey(dataDigitada)){
			System.out.print("Essa data � um feriado: " + dataDigitada + " - " + feriados.get(dataDigitada));
		}
		else {
			System.out.println("Essa data n�o � um feriado! Por�m, temos os seguintes feriados no m�s: \n");

			for (String data: feriados.keySet()) {
								
				if (dataDigitada.substring(3, 5).equals(data.substring(3, 5))) {
					System.out.println(data + " - " + feriados.get(data));
				}
			}
		}

	}

}
