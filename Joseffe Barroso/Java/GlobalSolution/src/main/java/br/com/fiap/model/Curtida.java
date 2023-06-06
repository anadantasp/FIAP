package br.com.fiap.model;

public class Curtida {
	private Usuario usuario;
	private Artigo artigo;
	
	public Curtida() {
		
	}

	public Curtida(Usuario usuario, Artigo artigo) {
		this.usuario = usuario;
		this.artigo = artigo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Artigo getArtigo() {
		return artigo;
	}

	public void setArtigo(Artigo artigo) {
		this.artigo = artigo;
	}
}
