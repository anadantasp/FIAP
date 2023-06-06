package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.fiap.bo.CategoriaBo;
import br.com.fiap.bo.UsuarioBo;
import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Artigo;
import br.com.fiap.model.Curtida;

public class ArtigoDao {
	
	CategoriaBo categoriaBo = new CategoriaBo();
	UsuarioBo usuarioBo = new UsuarioBo();
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	public int getMaiorIdArtigo() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		int maiorId = 0;

		try {
			String query = "select max(id_artigo) from artigo";

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			while (rs.next()) {
				maiorId = rs.getInt("max(id_artigo)");
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar o maior id de artigo! - " + e);
		} finally {
			conn.close();
		}
		
		return maiorId;
	}

	public void insert(Artigo artigo) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;

		artigo.setId(getMaiorIdArtigo() + 1);
		String dataArtigo = sdf.format(new Date());

		try {
			String query = String.format("insert into artigo(id_artigo, titulo, texto, imagem, data, fk_id_categoria_artigo) values(%s,'%s', '%s', '%s', '%s', %s)", artigo.getId(),
					artigo.getTitulo(), artigo.getTexto(), artigo.getImgUrl(), dataArtigo, artigo.getCategoria().getId(),
					artigo.getCategoria().getId());

			statement = conn.createStatement();
			statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro ao inserir artigo! - " + e);
		} finally {
			conn.close();
		}

	}
	
	public void insertCurtida(Curtida curtida) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;

		try {
			String query = String.format("insert into curte(fk_id_artigo, fk_id_usuario) values(%s,%s)", curtida.getArtigo().getId(),
					curtida.getUsuario().getId());

			statement = conn.createStatement();
			statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro ao inserir curtida! - " + e);
		} finally {
			conn.close();
		}

	}
	
	public ArrayList<Curtida> getCurtidasArtigo(int idArtigo) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		ArrayList<Curtida> list = null;

		try {
			String query = String.format("select * from curte where fk_id_artigo = %s", idArtigo);

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			list = new ArrayList<Curtida>();
			while (rs.next()) {
				Curtida curtida = new Curtida();
				curtida.setArtigo(getArtigo(rs.getInt("fk_id_artigo")));
				curtida.setUsuario(usuarioBo.getUsuario(rs.getInt("fk_id_usuario")));

				list.add(curtida);
			}
		} catch (Exception e) {
			System.out.println("Erro ao exibir curtidas relacionadas ao artigo!" + e);
		} finally {
			conn.close();
		}

		return list;
	} 

	public ArrayList<Artigo> getAll() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		ArrayList<Artigo> list = null;

		try {
			String query = "select * from artigo order by id_artigo";

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			list = new ArrayList<Artigo>();
			while (rs.next()) {
				Artigo artigo = new Artigo();
				artigo.setId(Integer.parseInt(rs.getString("id_artigo")));
				artigo.setData(rs.getDate("data"));
				artigo.setTitulo(rs.getString("titulo"));
				artigo.setTexto(rs.getString("texto"));
				artigo.setImgUrl(rs.getString("imagem"));

				artigo.setCategoria(categoriaBo.getCategoria(rs.getInt("fk_id_categoria_artigo")));

				list.add(artigo);
			}
		} catch (Exception e) {
			System.out.println("Erro ao exibir artigo!" + e);
		} finally {
			conn.close();
		}

		return list;
	}

	public Artigo getArtigo(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		Artigo artigo = null;

		try {
			String query = String.format("select * from artigo where id_artigo = %s", id);

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			artigo = new Artigo();
			while (rs.next()) {
				artigo.setId(Integer.parseInt(rs.getString("id_artigo")));
				artigo.setData(rs.getDate("data"));
				artigo.setTitulo(rs.getString("titulo"));
				artigo.setTexto(rs.getString("texto"));
				artigo.setImgUrl(rs.getString("imagem"));

				artigo.setCategoria(categoriaBo.getCategoria(rs.getInt("fk_id_categoria_artigo")));
			}
		} catch (Exception e) {
			System.out.println("Erro ao exibir o artigo! - " + e);
		} finally {
			conn.close();
		}

		return artigo;
	}
	
	public ArrayList<Artigo> getArtigoCategoria(int idCategoria) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		ArrayList<Artigo> list = null;

		try {
			String query = String.format("select * from artigo where fk_id_categoria_artigo = %s", idCategoria);

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			list = new ArrayList<Artigo>();
			while (rs.next()) {
				Artigo artigo = new Artigo();
				artigo.setId(rs.getInt("id_artigo"));
				artigo.setData(rs.getDate("data"));
				artigo.setTitulo(rs.getString("titulo"));
				artigo.setTexto(rs.getString("texto"));
				artigo.setImgUrl(rs.getString("imagem"));

				artigo.setCategoria(categoriaBo.getCategoria(rs.getInt("fk_id_categoria_artigo")));
				
				list.add(artigo);
			}
		} catch (Exception e) {
			System.out.println("Erro ao exibir o artigo! - " + e);
		} finally {
			conn.close();
		}

		return list;
	}

	public void update(Artigo artigo, int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		
		String dataArtigo = sdf.format(new Date());

		try {
			String query = String.format(
					"update artigo set titulo = '%s', texto = '%s', imagem = '%s', data = '%s', fk_id_categoria_artigo = %s where id_artigo = %s",
					artigo.getTitulo(), artigo.getTexto(), artigo.getImgUrl(), dataArtigo, artigo.getCategoria().getId(),
					id);

			statement = conn.createStatement();
			statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro ao atualizar o artigo - " + e);
		} finally {
			conn.close();
		}
	}
	
	public void delete(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;

		try {
			
			//DELETANDO DO RELACIONANMENTO
			String query = String.format("delete from artigo where id_artigo = %s", id);

			statement = conn.createStatement();
			statement.executeUpdate(query);
			
			System.out.println("Artigo excluído com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao excluir artigo! - " + e);
		} finally {
			conn.close();

		}

	}
}
