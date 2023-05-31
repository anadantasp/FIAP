package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.dao.ComentarioRestauranteDao;
import br.com.fiap.model.ComentarioRestaurante;

public class ComentarioRestauranteBo {
	private ComentarioRestauranteDao comentarioRestauranteDao = new ComentarioRestauranteDao();

	public ArrayList<ComentarioRestaurante> getAll() throws SQLException {
		return comentarioRestauranteDao.getAll();
	}

	public ComentarioRestaurante getCulinaria(int id) throws SQLException {
		return comentarioRestauranteDao.getComentarioRestaurante(id);
	}

	public void insert(ComentarioRestaurante comentarioRestaurante) throws SQLException {
		comentarioRestauranteDao.insert(comentarioRestaurante);
	}

	public void update(ComentarioRestaurante comentarioRestaurante, int id) throws SQLException {
		comentarioRestauranteDao.update(comentarioRestaurante, id);
	}

	public void delete(int id) throws SQLException {
		comentarioRestauranteDao.delete(id);
	}

}
