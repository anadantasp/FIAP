package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.dao.ComentarioDao;
import br.com.fiap.model.Comentario;


public class ComentarioBo {

	private ComentarioDao comentarioDao = new ComentarioDao();

	public ArrayList<Comentario> getAll() throws SQLException {
		return comentarioDao.getAll();
	}

	public Comentario getComentario(int id) throws SQLException {
		return comentarioDao.getComentario(id);
	}
	
	public int maiorId() throws SQLException {
		return comentarioDao.maiorId();
	}

	public void insert(Comentario comentario) throws SQLException {
		comentarioDao.insert(comentario);
	}
	
	public ArrayList<Comentario> getComentariosPostagem(int postagemID) throws SQLException {
		return comentarioDao.getComentariosPostagem(postagemID);
	}

	/*
	public void update(Postagem postagem) throws SQLException {
		postagemDao.update(postagem);
	}

	public void delete(int id) throws SQLException {
		postagemDao.delete(id);
	}
	*/
}
