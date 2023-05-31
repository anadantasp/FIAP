package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.dao.RestauranteDao;
import br.com.fiap.model.Restaurante;

public class RestauranteBo {
	
	private RestauranteDao restauranteDao = new RestauranteDao();

	public ArrayList<Restaurante> getAll() throws SQLException {
		return restauranteDao.getAll();
	}

	public Restaurante getRestaurante(long cnpj) throws SQLException {
		return restauranteDao.getRestaurante(cnpj);
	}
	
	public ArrayList<Restaurante> getRestauranteCulinaria(int idCulinaria) throws SQLException {
		return restauranteDao.getRestauranteCulinaria(idCulinaria);
	}

	public void insert(Restaurante restaurante) throws SQLException {
		restauranteDao.insert(restaurante);
	}

	public void update(Restaurante restaurante, long cnpj) throws SQLException {
		restauranteDao.update(restaurante, cnpj);
	}

	public void delete(long cnpj) throws SQLException {
		restauranteDao.delete(cnpj);
	}

}
