package model;

public class Endereco {

	private String logradouro;
	private String bairro;
	private String localidade;
	
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
	
	public String imprimirEndereco() {
		return "\nLogradouro: " + logradouro + "\nBairro: " + bairro + "\nCidade: " + localidade;
	}
	
	
}
