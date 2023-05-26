package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.dao.CategoriaDao;
import br.com.fiap.model.Categoria;

public class CategoriaBo {

	private CategoriaDao categoriaDao = new CategoriaDao();
	
	
	public ArrayList<Categoria> getAll() throws SQLException{
		return categoriaDao.getAll();
	}
	
	public Categoria getCategoria(int id) throws SQLException {
		return categoriaDao.getCategoria(id);
	}
	
	public int getMaiorIdCategoria() throws SQLException {
		return categoriaDao.getMaiorIdCategoria();
	}
	public void insert(Categoria categoria) throws SQLException {
		categoriaDao.insert(categoria);
	}
	
	public void update(Categoria categoria, int id) throws SQLException {
		categoriaDao.update(categoria, id);
	}
	
	public void delete(int id) throws SQLException{
		categoriaDao.delete(id);
	}
}
