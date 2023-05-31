package br.com.fiap.model;

import java.util.Date;

public class ComentarioRestaurante {
	
	private int id;
	private String texto;
	private Date data;
	private Usuario usuario;
	private Restaurante restaurante;
	
	public ComentarioRestaurante() {
		
	}

	public ComentarioRestaurante(int id, String texto, Date data, Usuario usuario, Restaurante restaurante) {
		this.id = id;
		this.texto = texto;
		this.data = data;
		this.usuario = usuario;
		this.restaurante = restaurante;
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

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	
}
