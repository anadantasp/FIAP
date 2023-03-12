package model;

public class Usuario {

	private String name;
	private String public_repos;
	private String followers;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPublic_repos() {
		return public_repos;
	}
	public void setPublic_repos(String public_repos) {
		this.public_repos = public_repos;
	}
	public String getFollowers() {
		return followers;
	}
	public void setFollowers(String followers) {
		this.followers = followers;
	}
	
	public String imprimirUsuario() {
		return "\nNome: " + name + "\nQuantidade de Repositórios: " + public_repos + "\nQuantidade de Seguidores: " + followers;
	}
}
