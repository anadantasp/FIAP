package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.fiap.bo.PostagemBo;
import br.com.fiap.bo.UsuarioBo;
import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Categoria;
import br.com.fiap.model.Comentario;
import br.com.fiap.model.Postagem;

public class PostagemDao {

	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	UsuarioBo usuarioBo = new UsuarioBo();
	
	CategoriaDao categoriaDao = new CategoriaDao();
	Categoria categoria = null;

	public void insert(Postagem p) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;

		String dataPostagem = sdf.format(p.getDate());

		try {
			String query = String.format("insert into postagem values(%s,'%s', '%s', '%s', '%s', %s, %s)", p.getId(),
					dataPostagem, p.getTitulo(), p.getConteudo(), p.getImgUrl(), p.getLikes(),
					p.getCategoria().getId());

			statement = conn.createStatement();
			statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro ao inserir o usuário! - " + e);
		} finally {
			conn.close();
		}

	}

	public ArrayList<Postagem> getAll() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		ArrayList<Postagem> list = null;

		try {
			String query = "select * from postagem order by id_post";

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			list = new ArrayList<Postagem>();
			while (rs.next()) {
				Postagem p = new Postagem();
				p.setId(Integer.parseInt(rs.getString("id_post")));
				p.setDate(rs.getDate("data"));
				p.setTitulo(rs.getString("titulo"));
				p.setConteudo(rs.getString("texto"));
				p.setImgUrl(rs.getString("img_url"));
				p.setLikes(Integer.parseInt(rs.getString("likes")));

				categoria = categoriaDao.getCategoria(Integer.parseInt(rs.getString("fk_cat")));

				p.setCategoria(categoria);

				list.add(p);
			}
		} catch (Exception e) {
			System.out.println("Erro ao exibir postagem! - " + e);
		} finally {
			conn.close();
		}

		return list;
	}

	public Postagem getPostagem(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		Postagem postagem = null;
		Statement comStatement;
		ResultSet comRs = null;

		try {
			String query = String.format("select * from postagem where id_post = %s", id);

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			postagem = new Postagem();
			while (rs.next()) {
				postagem.setId(Integer.parseInt(rs.getString("id_post")));
				postagem.setDate(rs.getDate("data"));
				postagem.setTitulo(rs.getString("titulo"));
				postagem.setConteudo(rs.getString("texto"));
				postagem.setImgUrl(rs.getString("img_url"));
				postagem.setLikes(Integer.parseInt(rs.getString("likes")));

				categoria = categoriaDao.getCategoria(Integer.parseInt(rs.getString("fk_cat")));

				postagem.setCategoria(categoria);

			}
		} catch (Exception e) {
			System.out.println("Erro ao exibir o postagem! - " + e);
		} finally {
			conn.close();
		}

		return postagem;
	}

	public ArrayList<Postagem> getPostagemCategoria(int idCategoria) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		ArrayList<Postagem> list = null;

		try {
			String query = String.format("select * from postagem where fk_cat = %s", idCategoria);

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			list = new ArrayList<Postagem>();
			while (rs.next()) {
				Postagem postagem = new Postagem();
				postagem.setId(Integer.parseInt(rs.getString("id_post")));
				postagem.setDate(sdf.parse(rs.getString("data")));
				postagem.setTitulo(rs.getString("titulo"));
				postagem.setConteudo(rs.getString("texto"));
				postagem.setImgUrl(rs.getString("img_url"));
				postagem.setLikes(Integer.parseInt(rs.getString("likes")));
				postagem.setCategoria(categoriaDao.getCategoria(Integer.parseInt(rs.getString("fk_cat"))));

				list.add(postagem);
			}
		} catch (Exception e) {
			System.out.println("Erro ao exibir o postagem! - " + e);
		} finally {
			conn.close();
		}

		return list;
	}

	public void update(Postagem postagem) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;

		try {
			String query = String.format(
					"update postagem set titulo = '%s', texto = '%s', img_url = '%s', fk_cat = %s where id_post = %s",
					postagem.getTitulo(), postagem.getConteudo(), postagem.getImgUrl(), postagem.getCategoria().getId(),
					postagem.getId());

			statement = conn.createStatement();
			statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro ao atualizar os likes da postagem! - " + e);
		} finally {
			conn.close();
		}
	}

	public void updateLikes(Postagem postagem) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;

		try {
			String query = String.format("update postagem set likes = %s where id_post = %s", postagem.getLikes(),
					postagem.getId());

			statement = conn.createStatement();
			statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro ao atualizar os likes da postagem! - " + e);
		} finally {
			conn.close();
		}

	}

	public int getMaiorIdPostagem() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		int maiorId = 0;

		try {
			String query = "select max(id_post) from postagem";

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			while (rs.next()) {
				maiorId = rs.getInt("max(id_post)");
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar maior ID! - " + e);
		} finally {
			conn.close();
		}

		return maiorId;
	}
	
	

	public void delete(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;

		try {
			
			//DELETANDO DO RELACIONANMENTO
			String query = String.format("delete from postagem where id_post = %s", id);

			statement = conn.createStatement();
			statement.executeUpdate(query);
			
			System.out.println("Postagem excluída com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao excluir a postagem! - " + e);
		} finally {
			conn.close();

		}

	}
	
	

}
