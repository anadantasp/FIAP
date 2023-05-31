package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.dao.UnidadeDao;
import br.com.fiap.model.Unidade;

public class UnidadeBo {

	private UnidadeDao unidadeDao = new UnidadeDao();
	
	public ArrayList<Unidade> getAll() throws SQLException {
		return unidadeDao.getAll();
	}

	public Unidade getUnidade(int id) throws SQLException {
		return unidadeDao.getUnidade(id);
	}

	public void insert(Unidade unidade) throws SQLException {
		unidadeDao.insert(unidade);
	}
	
	public void update(Unidade unidade, int id) throws SQLException {
		unidadeDao.update(unidade, id);
	}

	public void delete(int id) throws SQLException {
		unidadeDao.delete(id);
	}
}
