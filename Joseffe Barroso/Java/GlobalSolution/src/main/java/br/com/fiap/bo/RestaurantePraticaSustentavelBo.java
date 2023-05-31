package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.dao.RestaurantePraticaSustentavelDao;
import br.com.fiap.model.RestaurantePraticaSustentavel;

public class RestaurantePraticaSustentavelBo {
	private RestaurantePraticaSustentavelDao restaurantePraticaSustentavelDao = new RestaurantePraticaSustentavelDao();

	public ArrayList<RestaurantePraticaSustentavel> getAll() throws SQLException {
		return restaurantePraticaSustentavelDao.getAll();
	}

	public ArrayList<RestaurantePraticaSustentavel> getRestaurantePraticaSustentavel(long cnpj) throws SQLException {
		return restaurantePraticaSustentavelDao.getRestaurantePraticaSustentavel(cnpj);
	}

	public void insert(RestaurantePraticaSustentavel restaurantePraticaSustentavel) throws SQLException {
		restaurantePraticaSustentavelDao.insert(restaurantePraticaSustentavel);
	}

	public void delete(long cnpj, int id) throws SQLException {
		restaurantePraticaSustentavelDao.delete(cnpj, id);
	}

}
