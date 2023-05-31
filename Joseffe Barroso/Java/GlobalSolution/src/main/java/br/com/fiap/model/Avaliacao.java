package br.com.fiap.model;

import java.util.Date;

public class Avaliacao {
	
	private int id;
	private int valor;
	private Date momento;
	private Restaurante restaurante;
	private Usuario usuario;
	
	public Avaliacao() {
		
	}

	public Avaliacao(int id, int valor, Date momento, Restaurante restaurante, Usuario usuario) {
		this.id = id;
		this.valor = valor;
		this.momento = momento;
		this.restaurante = restaurante;
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Date getMomento() {
		return momento;
	}

	public void setMomento(Date momento) {
		this.momento = momento;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
