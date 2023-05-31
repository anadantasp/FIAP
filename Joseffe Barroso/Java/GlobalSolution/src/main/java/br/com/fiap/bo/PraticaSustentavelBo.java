package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.dao.PraticaSustentavelDao;
import br.com.fiap.model.PraticaSustentavel;

public class PraticaSustentavelBo {
	
	private PraticaSustentavelDao praticaSustentavelDao = new PraticaSustentavelDao();

	public ArrayList<PraticaSustentavel> getAll() throws SQLException {
		return praticaSustentavelDao.getAll();
	}

	public PraticaSustentavel getPraticaSustentavel(int id) throws SQLException {
		return praticaSustentavelDao.getCulinaria(id);
	}

	public void insert(PraticaSustentavel praticaSustentavel) throws SQLException {
		praticaSustentavelDao.insert(praticaSustentavel);
	}

	public void update(PraticaSustentavel praticaSustentavel, int id) throws SQLException {
		praticaSustentavelDao.update(praticaSustentavel, id);
	}

	public void delete(int id) throws SQLException {
		praticaSustentavelDao.delete(id);
	}

}
