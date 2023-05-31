package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.dao.AvaliacaoDao;
import br.com.fiap.model.Avaliacao;

public class AvaliacaoBo {
	
	private AvaliacaoDao avaliacaoDao = new AvaliacaoDao();
	
	public ArrayList<Avaliacao> getAll() throws SQLException {
		return avaliacaoDao.getAll();
	}

	public Avaliacao getAvaliacao(int id) throws SQLException {
		return avaliacaoDao.getAvaliacao(id);
	}
	
	public void insert(Avaliacao avaliacao) throws SQLException {
		avaliacaoDao.insert(avaliacao);
	}
	
	public void update(Avaliacao avaliacao, int id) throws SQLException {
		avaliacaoDao.update(avaliacao, id);
	}

	public void delete(int id) throws SQLException {
		avaliacaoDao.delete(id);
	}

}
