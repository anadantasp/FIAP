package model;

public class Ipo {
	private int id;
	private double valorInicial;
	private String descricao;
	private Empresa empresa;
	
	public Ipo() {
		
	}

	public Ipo(int id, double valorInicial, String descricao, Empresa empresa) {
		this.id = id;
		this.valorInicial = valorInicial;
		this.descricao = descricao;
		this.empresa = empresa;
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
}
