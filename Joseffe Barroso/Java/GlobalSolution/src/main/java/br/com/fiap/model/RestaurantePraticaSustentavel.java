package br.com.fiap.model;

public class RestaurantePraticaSustentavel {
	private Restaurante restaurante;
	private PraticaSustentavel praticaSustentavel;
	
	public RestaurantePraticaSustentavel() {
		
	}

	public RestaurantePraticaSustentavel(Restaurante restaurante, PraticaSustentavel praticaSustentavel) {
		this.restaurante = restaurante;
		this.praticaSustentavel = praticaSustentavel;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public PraticaSustentavel getPraticaSustentavel() {
		return praticaSustentavel;
	}

	public void setPraticaSustentavel(PraticaSustentavel praticaSustentavel) {
		this.praticaSustentavel = praticaSustentavel;
	}
}
