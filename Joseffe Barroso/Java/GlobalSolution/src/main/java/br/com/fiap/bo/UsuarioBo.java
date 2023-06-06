package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.model.Usuario;

public class UsuarioBo {

	private UsuarioDao usuarioDao = new UsuarioDao();

	public ArrayList<Usuario> getAll() throws SQLException {
		return usuarioDao.getAll();
	}

	public Usuario getUsuario(String email, String senha) throws SQLException {
		return usuarioDao.getUsuario(email, senha);
	}
	
	public Usuario getUsuario(int id) throws SQLException {
		return usuarioDao.getUsuario(id);
	}

	public void insert(Usuario usuario) throws SQLException {
		usuarioDao.insert(usuario);
	}

	public void atualizarSenha(Usuario usuario, int id) throws SQLException {
		usuarioDao.updateSenha(usuario, id);
	}
	
	public void atualizarUsuario(Usuario usuario, int id) throws SQLException {
		usuarioDao.update(usuario, id);
	}

	public void delete(int id) throws SQLException {
		usuarioDao.delete(id);
	}
}
