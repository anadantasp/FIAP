package br.com.fiap.model;

public class Restaurante {
	private long cnpj;
	private String nome;
	private String descricao;
	private Culinaria culinaria;
	private String site;
	
	public Restaurante() {
		
	}

	public Restaurante(long cnpj, String nome, String descricao, Culinaria culinaria, String site) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.descricao = descricao;
		this.culinaria = culinaria;
		this.site = site;
	}

	public long getCnpj() {
		return cnpj;
	}

	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Culinaria getCulinaria() {
		return culinaria;
	}

	public void setCulinaria(Culinaria culinaria) {
		this.culinaria = culinaria;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
}
