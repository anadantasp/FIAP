package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.fiap.bo.ArtigoBo;
import br.com.fiap.bo.UsuarioBo;
import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.ComentarioArtigo;

public class ComentarioArtigoDao {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	UsuarioBo usuarioBo = new UsuarioBo();
	ArtigoBo artigoBo = new ArtigoBo();

	public int getMaiorIdComentarioArtigo() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		int maiorId = 0;

		try {
			String query = "select max(id_comentario_artigo) from comentario_artigo";

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			while (rs.next()) {
				maiorId = rs.getInt("max(id_comentario_artigo)");
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar maior id! - " + e);
		} finally {
			conn.close();
		}

		return maiorId;
	}

	public void insert(ComentarioArtigo comentarioArtigo) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;

		comentarioArtigo.setId(getMaiorIdComentarioArtigo() + 1);
		
		String data = sdf.format(new Date());

		try {
			String query = String.format(
					"insert into comentario_artigo(id_comentario_artigo, texto, data, fk_id_usuario, fk_id_artigo) values(%s,'%s', '%s', %s, %s)",
					comentarioArtigo.getId(), comentarioArtigo.getTexto(), data,
					comentarioArtigo.getUsuario().getId(), comentarioArtigo.getArtigo().getId());

			statement = conn.createStatement();
			statement.executeUpdate(query);

			System.out.println("Comentario inserido com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao inserir comentario! - " + e);
		} finally {
			conn.close();
		}
	}

	public ArrayList<ComentarioArtigo> getAll() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		ArrayList<ComentarioArtigo> list = null;

		try {
			String query = "select * from comentario_artigo order by id_comentario_artigo";

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			list = new ArrayList<ComentarioArtigo>();
			while (rs.next()) {
				ComentarioArtigo c = new ComentarioArtigo();
				c.setId(rs.getInt("id_comentario_artigo"));
				c.setTexto(rs.getString("texto"));
				c.setData(rs.getDate("data"));
				c.setUsuario(usuarioBo.getUsuario(rs.getInt("fk_id_usuario")));
				c.setArtigo(artigoBo.getArtigo(rs.getInt("fk_id_artigo")));

				list.add(c);
			}
		} catch (Exception e) {
			System.out.println("Erro ao exibir artigo! - " + e);
		} finally {
			conn.close();
		}

		return list;
	}
	
	public ArrayList<ComentarioArtigo> getComentariosArtigo(int idArtigo) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		ArrayList<ComentarioArtigo> list = null;

		try {
			String query = String.format("select * from comentario_artigo where fk_id_artigo = %s", idArtigo);

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			list = new ArrayList<ComentarioArtigo>();
			while (rs.next()) {
				ComentarioArtigo c = new ComentarioArtigo();
				c.setId(rs.getInt("id_comentario_artigo"));
				c.setTexto(rs.getString("texto"));
				c.setData(rs.getDate("data"));
				c.setUsuario(usuarioBo.getUsuario(rs.getInt("fk_id_usuario")));
				c.setArtigo(null);

				list.add(c);
			}
		} catch (Exception e) {
			System.out.println("Erro ao exibir comentarios artigo! - " + e);
		} finally {
			conn.close();
		}

		return list;
	}

	public ComentarioArtigo getComentarioArtigo(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		ComentarioArtigo comentarioArtigo = null;

		try {
			String query = String.format("select * from comentario_artigo where id_comentario_artigo = %s", id);

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			while (rs.next()) {
				comentarioArtigo = new ComentarioArtigo();
				comentarioArtigo.setId(rs.getInt("id_comentario_artigo"));
				comentarioArtigo.setTexto(rs.getString("texto"));
				comentarioArtigo.setData(rs.getDate("data"));
				comentarioArtigo.setUsuario(usuarioBo.getUsuario(rs.getInt("fk_id_usuario")));
				comentarioArtigo.setArtigo(artigoBo.getArtigo(rs.getInt("fk_id_artigo")));
			}
		} catch (Exception e) {
			System.out.println("Erro ao exibir a categoria! - " + e);
		} finally {
			conn.close();
		}

		return comentarioArtigo;
	}

	public void delete(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;

		try {
			String query = String.format("delete from comentario_artigo where id_comentario_artigo = %s", id);

			statement = conn.createStatement();
			statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro ao excluir comentário! - " + e);
		} finally {
			conn.close();
		}
	}

	public void update(ComentarioArtigo comentarioArtigo, int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		
		String data = sdf.format(new Date());

		try {
			String query = String.format("update comentario_artigo set texto = '%s', data = '%s' where id_comentario_artigo = %s",
					comentarioArtigo.getTexto(), data, id);

			statement = conn.createStatement();
			statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro ao atualizar comentario! - " + e);
		} finally {
			conn.close();
		}
	}
}
