package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Culinaria;

public class CulinariaDao {
	
	public int getMaiorIdCulinaria() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		int maiorId = 0;

		try {
			String query = "select max(id_culinaria) from culinaria";

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			while (rs.next()) {
				maiorId = rs.getInt("max(id_culinaria)");
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar o maior id! - " + e);
		} finally {
			conn.close();
		}
		
		return maiorId;
	}

	public void insert(Culinaria culinaria) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        
        int id = getMaiorIdCulinaria() + 1;
       
        try {
            String query = String.format("insert into culinaria(id_culinaria,nome) values(%s,'%s')", id, culinaria.getNome());
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
            
            System.out.println("Culinária inserida com sucesso!");
        }catch (Exception e){
            System.out.println("Erro ao inserir culinária! - " + e);
        }
        finally {
        	conn.close();
        }
	}
	
	public ArrayList<Culinaria> getAll() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        ArrayList<Culinaria> list = null;
       
        try {
            String query= "select * from culinaria order by id_culinaria";
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            list = new ArrayList<Culinaria>(); 
            while(rs.next()){
            	Culinaria culinaria = new Culinaria();
            	culinaria.setId(Integer.parseInt(rs.getString("id_culinaria")));
            	culinaria.setNome(rs.getString("nome"));
            	
                list.add(culinaria);
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir culinária! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return list;
	}
	
	public Culinaria getCulinaria(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        Culinaria culinaria = null;
        
        try {
            String query= String.format("select * from culinaria where id_culinaria = %s", id);
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            
            while(rs.next()){
            	culinaria = new Culinaria();
            	culinaria.setId(Integer.parseInt(rs.getString("id_culinaria")));
            	culinaria.setNome(rs.getString("nome"));
          
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir culinária! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return culinaria;
	}
	
	public void delete(int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
            String query = String.format("delete from culinaria where id_culinaria = %s", id);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao excluir culinária! - " + e);
        }
        finally {
        	conn.close();
        }
	}
	
	public void update(Culinaria culinaria, int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
            String query = String.format("update culinaria set nome = '%s' where id_culinaria = %s", culinaria.getNome(), id);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao atualizar culinaria! - " + e);
        }
        finally {
        	conn.close();
        }
	}
}
