package checkpoint;

import java.util.ArrayList;

public class Cliente {
	
	private String nome;
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
	
	public void addProdutos(Produto produto) {
		this.produtos.add(produto);
	}
	
	
	

}
