package model;

public class Endereco {
	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
	private int numero;
	private String complementoCasa;
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getComplementoCasa() {
		return complementoCasa;
	}
	public void setComplementoCasa(String complementoCasa) {
		this.complementoCasa = complementoCasa;
	}
	
	public String imprimirEndereco() {
		return "\nLogradouro: " + logradouro + "\nBairro: " + bairro + "\nCidade: " + localidade + "Estado: " + uf;
	}
	
	public String imprimirEnderecoCompleto() {
		if(complementoCasa != null) {
			return "\nLogradouro: " + logradouro + "\nN�mero: " + numero
					+ "\nComplemento: " + complementoCasa + "\nBairro: " + bairro
					+ "\nCidade: " + localidade + " - " + uf;
		}else {
			return "\nLogradouro: " + logradouro + "\nN�mero: " + numero
					+ "\nBairro: " + bairro
					+ "\nCidade: " + localidade + " - " + uf;
		}
	}

}
