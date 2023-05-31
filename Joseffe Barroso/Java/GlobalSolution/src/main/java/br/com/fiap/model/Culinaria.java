package br.com.fiap.model;

public class Culinaria {
	private int id;
	private String nome;
	
	public Culinaria() {
		
	}

	public Culinaria(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
