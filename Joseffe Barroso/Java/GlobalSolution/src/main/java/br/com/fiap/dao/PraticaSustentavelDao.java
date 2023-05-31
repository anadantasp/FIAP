package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.PraticaSustentavel;

public class PraticaSustentavelDao {
	
	public int getMaiorIdPraticaSustentavel() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		int maiorId = 0;

		try {
			String query = "select max(id_praticas_sustentaveis) from praticas_sustentaveis";

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			while (rs.next()) {
				maiorId = rs.getInt("max(id_praticas_sustentaveis)");
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar o maior id! - " + e);
		} finally {
			conn.close();
		}
		
		return maiorId;
	}
	
	public void insert(PraticaSustentavel praticaSustentavel) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        
        int id = getMaiorIdPraticaSustentavel() + 1;
       
        try {
            String query = String.format("insert into praticas_sustentaveis(id_praticas_sustentaveis, nome, descricao) values(%s,'%s', '%s')", id, 
            		praticaSustentavel.getNome(), praticaSustentavel.getDescricao());
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
            
            System.out.println("Prática sustentável inserida com sucesso!");
        }catch (Exception e){
            System.out.println("Erro ao inserir prática sustentável! - " + e);
        }
        finally {
        	conn.close();
        }
	}
	
	public ArrayList<PraticaSustentavel> getAll() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        ArrayList<PraticaSustentavel> list = null;
       
        try {
            String query= "select * from praticas_sustentaveis order by id_praticas_sustentaveis";
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            list = new ArrayList<PraticaSustentavel>(); 
            while(rs.next()){
            	PraticaSustentavel praticaSustentavel = new PraticaSustentavel();
            	praticaSustentavel.setId(rs.getInt("id_praticas_sustentaveis"));
            	praticaSustentavel.setNome(rs.getString("nome"));
            	praticaSustentavel.setDescricao(rs.getString("descricao"));
            	
                list.add(praticaSustentavel);
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir prática sustentável! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return list;
	}

	public PraticaSustentavel getCulinaria(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        PraticaSustentavel praticaSustentavel = null;
        
        try {
            String query= String.format("select * from praticas_sustentaveis where id_praticas_sustentaveis = %s", id);
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            
            while(rs.next()){
            	praticaSustentavel = new PraticaSustentavel();
            	praticaSustentavel.setId(rs.getInt("id_praticas_sustentaveis"));
            	praticaSustentavel.setNome(rs.getString("nome"));
            	praticaSustentavel.setDescricao(rs.getString("descricao"));
          
            }
        }catch (Exception e){
        	System.out.println("Erro ao exibir prática sustentável! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return praticaSustentavel;
	}
	
	public void delete(int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
            String query = String.format("delete from praticas_sustentaveis where id_praticas_sustentaveis = %s", id);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao excluir prática sustentável! - " + e);
        }
        finally {
        	conn.close();
        }
	}
	
	public void update(PraticaSustentavel praticaSustentavel, int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
            String query = String.format("update praticas_sustentaveis set nome = '%s', descricao = '%s' where id_praticas_sustentaveis = %s", praticaSustentavel.getNome(),
            		praticaSustentavel.getDescricao(), id);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao atualizar prática sustentável! - " + e);
        }
        finally {
        	conn.close();
        }
	}
}
