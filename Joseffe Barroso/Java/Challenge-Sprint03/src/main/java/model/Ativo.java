package model;

public class Ativo {
	private int id;
	private String tipo;
	private String descricao;
	private double valor;
	
	public Ativo() {
		
	}

	public Ativo(int id, String tipo, String descricao, double valor) {
		this.id = id;
		this.tipo = tipo;
		this.descricao = descricao;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}
