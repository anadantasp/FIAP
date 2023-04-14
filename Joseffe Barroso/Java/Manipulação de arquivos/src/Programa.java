import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Programa {
	
	public static void main(String[] args){
		Scanner scn = new Scanner(System.in);
		
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		
		String path = "C:\\teste\\lista.txt";
		
		System.out.println("---------- CADASTRO DE PRODUTOS ----------");
		for(int i = 0; i < 10; i++) {
			Produto produto = new Produto();
			
			System.out.printf("Digite o nome do produto: ");
			produto.setNome(scn.next());
			System.out.printf("Digite a quantidade do produto em estoque: ");
			produto.setQuantidade(scn.nextInt());
			System.out.printf("Digite o preço unitário do produto: ");
			produto.setPrecoUnitario(scn.nextDouble());
			
			produtos.add(produto);
		}
		
		/*
		for(Produto p: produtos) {
			System.out.println("Nome: " + p.getNome() + "Quantidade: " + p.getQuantidade()
			+ "Preço unitário: R$" + p.getPrecoUnitario());
		}
		*/
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for(Produto p: produtos) {
                
                bw.write(p.getNome() + "," + Integer.toString(p.getQuantidade()) + ","
                		+ Double.toString(p.getPrecoUnitario()));
                
                bw.newLine();
            }

        }catch(IOException e) {
            e.printStackTrace();
        }

		
	}

}
