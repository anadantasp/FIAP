package br.com.fiap.model;

import java.util.Date;

public class ComentarioArtigo {

	private int id;
	private String texto;
	private Date data;
	private Usuario usuario;
	private Artigo artigo;

	public ComentarioArtigo() {
		
	}

	public ComentarioArtigo(int id, String texto, Date data, Usuario usuario, Artigo artigo) {
		super();
		this.id = id;
		this.texto = texto;
		this.data = data;
		this.usuario = usuario;
		this.artigo = artigo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
