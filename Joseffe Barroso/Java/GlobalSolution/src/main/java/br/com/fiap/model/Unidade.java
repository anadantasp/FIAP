package br.com.fiap.model;

public class Unidade {
	private int id;
	private String imagem;
	private String cep;
	private String rua;
	private String cidade;
	private String estado;
	private String bairro;
	private Restaurante restaurante;
	
	public Unidade() {
		
	}

	

	public Unidade(int id, String imagem, String cep, String rua, String cidade, String estado, String bairro,
			Restaurante restaurante) {
		super();
		this.id = id;
		this.imagem = imagem;
		this.cep = cep;
		this.rua = rua;
		this.cidade = cidade;
		this.estado = estado;
		this.bairro = bairro;
		this.restaurante = restaurante;
	}

	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getImagem() {
		return imagem;
	}



	public void setImagem(String imagem) {
		this.imagem = imagem;
	}



	public String getCep() {
		return cep;
	}



	public void setCep(String cep) {
		this.cep = cep;
	}



	public String getRua() {
		return rua;
	}



	public void setRua(String rua) {
		this.rua = rua;
	}



	public String getCidade() {
		return cidade;
	}



	public void setCidade(String cidade) {
		this.cidade = cidade;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public String getBairro() {
		return bairro;
	}



	public void setBairro(String bairro) {
		this.bairro = bairro;
	}



	public Restaurante getRestaurante() {
		return restaurante;
	}



	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

}
