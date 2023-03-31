package model;

public class Ipo {
	private int id;
	private double valorInicial;
	private String descricao;
	
	public Ipo() {
		
	}

	public Ipo(int id, double valorInicial, String descricao) {
		this.id = id;
		this.valorInicial = valorInicial;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(double valorInicial) {
		this.valorInicial = valorInicial;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
