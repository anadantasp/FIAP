package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.dao.ComentarioArtigoDao;
import br.com.fiap.model.ComentarioArtigo;

public class ComentarioArtigoBo {
	private ComentarioArtigoDao comentarioArtigoDao = new ComentarioArtigoDao();

	public ArrayList<ComentarioArtigo> getAll() throws SQLException {
		return comentarioArtigoDao.getAll();
	}

	public ComentarioArtigo getComentarioArtigo(int id) throws SQLException {
		return comentarioArtigoDao.getComentarioArtigo(id);
	}

	public void insert(ComentarioArtigo comentarioArtigo) throws SQLException {
		comentarioArtigoDao.insert(comentarioArtigo);
	}

	public void update(ComentarioArtigo comentarioArtigo, int id) throws SQLException {
		comentarioArtigoDao.update(comentarioArtigo, id);
	}

	public void delete(int id) throws SQLException {
		comentarioArtigoDao.delete(id);
	}

}
