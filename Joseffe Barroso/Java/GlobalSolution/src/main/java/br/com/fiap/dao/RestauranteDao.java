package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.fiap.bo.CulinariaBo;
import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Restaurante;

public class RestauranteDao {
	
	CulinariaBo culinariaBo = new CulinariaBo();
	
	public void insert(Restaurante restaurante) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        
       
       
        try {
            String query = String.format("insert into restaurante(cnpj, nome, descricao, fk_id_culinaria) values(%s,'%s', '%s', %s)", restaurante.getCnpj(), 
            		restaurante.getNome(), restaurante.getDescricao(), restaurante.getCulinaria().getId());
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
            
            System.out.println("Restaurante inserido com sucesso!");
        }catch (Exception e){
            System.out.println("Erro ao inserir restaurante! - " + e);
        }
        finally {
        	conn.close();
        }
	}
	
	public ArrayList<Restaurante> getAll() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        ArrayList<Restaurante> list = null;
       
        try {
            String query= "select * from restaurante order by nome";
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            list = new ArrayList<Restaurante>(); 
            while(rs.next()){
            	Restaurante restaurante = new Restaurante();
            	restaurante.setCnpj(rs.getLong("cnpj"));
            	restaurante.setNome(rs.getString("nome"));
            	restaurante.setDescricao(rs.getString("descricao"));
            	restaurante.setCulinaria(culinariaBo.getCulinaria(rs.getInt("fk_id_culinaria")));
            	
                list.add(restaurante);
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir restaurante! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return list;
	}
	
	public Restaurante getRestaurante(long cnpj) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        Restaurante restaurante = null;
        
        try {
            String query= String.format("select * from restaurante where cnpj = %s", cnpj);
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            
            while(rs.next()){
            	restaurante = new Restaurante();
            	restaurante.setCnpj(rs.getLong("cnpj"));
            	restaurante.setNome(rs.getString("nome"));
            	restaurante.setDescricao(rs.getString("descricao"));
            	restaurante.setCulinaria(culinariaBo.getCulinaria(rs.getInt("fk_id_culinaria")));
          
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir restaurante! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return restaurante;
	}
	
	public ArrayList<Restaurante> getRestauranteCulinaria(int idCulinaria) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        ArrayList<Restaurante> list = null;
       
        try {
            String query= "select * from restaurante where fk_id_culinaria = " + idCulinaria;
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            list = new ArrayList<Restaurante>(); 
            while(rs.next()){
            	Restaurante restaurante = new Restaurante();
            	restaurante.setCnpj(rs.getLong("cnpj"));
            	restaurante.setNome(rs.getString("nome"));
            	restaurante.setDescricao(rs.getString("descricao"));
            	restaurante.setCulinaria(culinariaBo.getCulinaria(rs.getInt("fk_id_culinaria")));
            	
                list.add(restaurante);
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir restaurante! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return list;
	}
	
	public void delete(long cnpj) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
            String query = String.format("delete from restaurante where cnpj = %s", cnpj);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao excluir restaurante! - " + e);
        }
        finally {
        	conn.close();
        }
	}
	
	public void update(Restaurante restaurante, long cnpj) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
            String query = String.format("update restaurante set nome = '%s', descricao = '%s', fk_id_culinaria = %s  where cnpj = %s", 
            		restaurante.getNome(), restaurante.getDescricao(), restaurante.getCulinaria().getId(), cnpj);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao atualizar restaurante! - " + e);
        }
        finally {
        	conn.close();
        }
	}
	
	

}
