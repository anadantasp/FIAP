package br.com.fiap.model;

import java.util.ArrayList;

public class PessoaGovernanca {

	private int id;
	private String nome;
	private String cargo;
	private ArrayList <Governanca> governanca;
	
	public PessoaGovernanca() {
		
	}
	
	public PessoaGovernanca(int id, String nome, String cargo, ArrayList <Governanca> governanca) {
		this.id = id;
		this.cargo = cargo;
		this.governanca = governanca;
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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public ArrayList <Governanca> getGovernanca() {
		return governanca;
	}

	public void setGovernanca(ArrayList <Governanca> governanca) {
		this.governanca = governanca;
	}
}
