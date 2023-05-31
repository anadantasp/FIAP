package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Categoria;

public class CategoriaDao {
	public void insert(Categoria c) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        
        int id = getMaiorIdCategoria() + 1;
       
        try {
            String query = String.format("insert into categoria_artigo(id_categoriaartigo,descricao) values(%s,'%s')", id, c.getDescricao());
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
            
            System.out.println("Categoria inserida com sucesso!");
        }catch (Exception e){
            System.out.println("Erro ao inserir o usuário! - " + e);
        }
        finally {
        	conn.close();
        }
	}

	public ArrayList<Categoria> getAll() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        ArrayList<Categoria> list = null;
       
        try {
            String query= "select * from categoria_artigo order by id_categoriaartigo";
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            list = new ArrayList<Categoria>(); 
            while(rs.next()){
            	Categoria c = new Categoria();
            	c.setId(Integer.parseInt(rs.getString("id_categoriaartigo")));
            	c.setDescricao(rs.getString("descricao"));
            	
                list.add(c);
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir o usuário! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return list;
	}

	public Categoria getCategoria(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        Categoria categoria = null;
        
        try {
            String query= String.format("select * from categoria_artigo where id_categoriaartigo = %s", id);
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            
            while(rs.next()){
            	categoria = new Categoria();
            	categoria.setId(Integer.parseInt(rs.getString("id_categoriaartigo")));
            	categoria.setDescricao(rs.getString("descricao"));
          
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir a categoria! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return categoria;
	}
	
	public int getMaiorIdCategoria() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		int maiorId = 0;

		try {
			String query = "select max(id_categoriaartigo) from categoria_artigo";

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			while (rs.next()) {
				maiorId = rs.getInt("max(id_categoriaartigo)");
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar o maior id! - " + e);
		} finally {
			conn.close();
		}
		
		return maiorId;
	}
	
	
	public void delete(int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
            String query = String.format("delete from categoria_artigo where id_categoriaartigo = %s", id);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao excluir o usuário! - " + e);
        }
        finally {
        	conn.close();
        }
	}

	public void update(Categoria c, int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
            String query = String.format("update categoria_artigo set descricao = '%s' where id_categoriaartigo = %s", c.getDescricao(), id);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao atualizar o usuário! - " + e);
        }
        finally {
        	conn.close();
        }
	}

}
