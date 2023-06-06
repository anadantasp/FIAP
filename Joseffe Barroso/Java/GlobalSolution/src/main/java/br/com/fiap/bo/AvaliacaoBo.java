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
	
	public double getMediaAvaliacaoRestaurante(long cnpj) throws SQLException {
		double mediaAvaliacao = 0f;
		double totalAvaliacoes = 0f;
		int quantidadeAvaliacoes = 0;
		
		ArrayList<Avaliacao> avaliacoes = avaliacaoDao.getAvaliacaoRestaurante(cnpj);
		
		for(Avaliacao a: avaliacoes) {
			totalAvaliacoes += a.getValor();
			quantidadeAvaliacoes++;
		}
		
		mediaAvaliacao = totalAvaliacoes / quantidadeAvaliacoes;
		
		return mediaAvaliacao;
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
