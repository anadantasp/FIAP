package ex02;

import java.util.ArrayList;

public class Programa {
	
	public static void main(String[] args) {
		String baseDados = "CJose dos Santos,42,Sao Paulo;CSandra Silva,36,Sao Jose do Rio Preto;CAugusto Soares,22,Sao Paulo;CVanderlei Azevedo,45,Santos;CVanessa Ferreira,27,Sao Paulo;PMouse,1,9.90;PTeclado,3,19.90;PMonitor,2,349.90;PHD SSD,2,199.90;PProcessador,1,350.00";

		ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
		ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
		
		String[] dados = baseDados.split(";");
		
		String[] dadosCliente;
		String[] dadosProduto;
		
		for(String dado: dados) {
			//System.out.println(dado);
			
			if(dado.startsWith("C")){
				dadosCliente = dado.split(",");
				
				Cliente cliente = new Cliente();
				
				cliente.setNome(dadosCliente[0].substring(1,dadosCliente[0].length()));
				cliente.setIdade(Integer.parseInt(dadosCliente[1]));
				cliente.setCidade(dadosCliente[2]);
				
				listaClientes.add(cliente);
			} else if(dado.startsWith("P")) {
				dadosProduto = dado.split(",");
				
				Produto produto = new Produto();
				
				produto.setNome(dadosProduto[0].substring(1, dadosProduto[0].length()));
				produto.setQuantidadeEstoque(Integer.parseInt(dadosProduto[1]));
				produto.setPreco(Double.parseDouble(dadosProduto[2]));
				
				listaProdutos.add(produto);
			}
		}
		
		System.out.println("-------------------- Clientes --------------------");
		for(Cliente c: listaClientes) {
			System.out.println("Nome: " + c.getNome() + "\tIdade: " + c.getIdade()
								+ "\tCidade: " + c.getCidade());
		}
		
		System.out.println("\n-------------------- Produtos --------------------");
		for(Produto p: listaProdutos) {
			System.out.println("Nome: " + p.getNome() + "\tQuantidade em estoque: " + p.getQuantidadeEstoque()
								+ "\tPreço: R$" + p.getPreco());
		}
	}

}
