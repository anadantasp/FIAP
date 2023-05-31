package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.dao.CulinariaDao;
import br.com.fiap.model.Culinaria;

public class CulinariaBo {

	private CulinariaDao culinariaDao = new CulinariaDao();

	public ArrayList<Culinaria> getAll() throws SQLException {
		return culinariaDao.getAll();
	}

	public Culinaria getCulinaria(int id) throws SQLException {
		return culinariaDao.getCulinaria(id);
	}

	public void insert(Culinaria culinaria) throws SQLException {
		culinariaDao.insert(culinaria);
	}

	public void update(Culinaria culinaria, int id) throws SQLException {
		culinariaDao.update(culinaria, id);
	}

	public void delete(int id) throws SQLException {
		culinariaDao.delete(id);
	}

}
