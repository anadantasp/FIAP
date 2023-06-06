package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.dao.ArtigoDao;
import br.com.fiap.model.Artigo;
import br.com.fiap.model.Curtida;

public class ArtigoBo {

	private ArtigoDao artigoDao = new ArtigoDao();

	public ArrayList<Artigo> getAll() throws SQLException {
		return artigoDao.getAll();
	}

	public Artigo getArtigo(int id) throws SQLException {
		return artigoDao.getArtigo(id);
	}
	
	public ArrayList<Artigo> getArtigoCategoria(int idCategoria) throws SQLException {
		return artigoDao.getArtigoCategoria(idCategoria);
	}
	
	public int getTotalCurtidasArtigo(int id) throws SQLException {
		ArrayList<Curtida> curtidas = artigoDao.getCurtidasArtigo(id);
		
		int totalCurtidas = 0;
		
		for(Curtida curtida: curtidas) {
			totalCurtidas++;
		}
		
		return totalCurtidas;	 
	}

	public void insert(Artigo artigo) throws SQLException {
		artigoDao.insert(artigo);
	}
	
	public void insertCurtida(Curtida curtida) throws SQLException {
		artigoDao.insertCurtida(curtida);
	}

	public void update(Artigo artigo, int id) throws SQLException {
		artigoDao.update(artigo, id);
	}

	public void delete(int id) throws SQLException {
		artigoDao.delete(id);
	}

}
