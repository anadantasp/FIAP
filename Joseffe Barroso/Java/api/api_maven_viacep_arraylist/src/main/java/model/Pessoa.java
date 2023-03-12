package model;

public class Pessoa {
	private int id;
	private String nome;
	private String email;
	private Endereco endereco;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String imprimirPessoa() {
		if (endereco.getComplementoCasa() != null) {
			return "\nID: " + id + "\nNome: " + nome + "\nE-mail: " + email + "\n ---------- Endere�o ----------"
					+ "\nLogradouro: " + endereco.getLogradouro() + "\nN�mero: " + endereco.getNumero()
					+ "\nComplemento: " + endereco.getComplementoCasa() + "\nBairro: " + endereco.getBairro()
					+ "\nCidade: " + endereco.getLocalidade() + " - " + endereco.getUf();
		} else {
			return "\nID: " + id + "\nNome: " + nome + "\nE-mail: " + email + "\n ---------- Endere�o ----------"
					+ "Logradouro: " + endereco.getLogradouro() + "N�mero: " + endereco.getNumero() + "\nBairro: "
					+ endereco.getBairro() + "\nCidade: " + endereco.getLocalidade() + " - " + endereco.getUf();
		}

	}

}
