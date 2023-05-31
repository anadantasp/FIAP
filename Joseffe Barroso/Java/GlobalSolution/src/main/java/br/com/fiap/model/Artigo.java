package br.com.fiap.model;

import java.util.Date;

public class Artigo {
	private int id;
	private String titulo;
	private String texto;
	private String imgUrl;
	private Date data;
	private Categoria categoria;
	
	public Artigo() {
		
	}

	public Artigo(int id, String titulo, String texto, String imgUrl, Date data, Categoria categoria) {
		this.id = id;
		this.titulo = titulo;
		this.texto = texto;
		this.imgUrl = imgUrl;
		this.data = data;
		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
