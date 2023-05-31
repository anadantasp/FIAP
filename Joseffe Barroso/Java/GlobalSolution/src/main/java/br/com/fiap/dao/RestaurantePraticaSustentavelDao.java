package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.fiap.bo.PraticaSustentavelBo;
import br.com.fiap.bo.RestauranteBo;
import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.RestaurantePraticaSustentavel;

public class RestaurantePraticaSustentavelDao {
	
	PraticaSustentavelBo praticaSustentavelBo = new PraticaSustentavelBo();
	RestauranteBo restauranteBo = new RestauranteBo();
	
	public void insert(RestaurantePraticaSustentavel restaurantePraticaSustentavel) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
            String query = String.format("insert into restaurante_praticassustentaveis(fk_restaurante_cnpj,fk_id_praticas_sustentaveis) values(%s,%s)", 
            		restaurantePraticaSustentavel.getRestaurante().getCnpj(), restaurantePraticaSustentavel.getPraticaSustentavel().getId());
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
            
            System.out.println("Relacionamento inserido com sucesso!");
        }catch (Exception e){
            System.out.println("Erro ao inserir relacionamento! - " + e);
        }
        finally {
        	conn.close();
        }
	}
	
	public ArrayList<RestaurantePraticaSustentavel> getAll() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        ArrayList<RestaurantePraticaSustentavel> list = null;
       
        try {
            String query= "select * from restaurante_praticassustentaveis";
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            list = new ArrayList<RestaurantePraticaSustentavel>(); 
            while(rs.next()){
            	RestaurantePraticaSustentavel restaurantePraticaSustentavel = new RestaurantePraticaSustentavel();
            	restaurantePraticaSustentavel.setPraticaSustentavel(praticaSustentavelBo.getPraticaSustentavel(rs.getInt("fk_id_praticas_sustentaveis")));
            	restaurantePraticaSustentavel.setRestaurante(restauranteBo.getRestaurante(rs.getLong("fk_restaurante_cnpj")));
            	
                list.add(restaurantePraticaSustentavel);
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir relacionamentos! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return list;
	}
	
	public ArrayList<RestaurantePraticaSustentavel> getRestaurantePraticaSustentavel(long cnpj) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        ArrayList<RestaurantePraticaSustentavel> list = null;
        
        try {
            String query= String.format("select * from restaurante_praticassustentaveis where fk_restaurante_cnpj = %s", cnpj);
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            list = new ArrayList<RestaurantePraticaSustentavel>(); 
            while(rs.next()){
            	RestaurantePraticaSustentavel restaurantePraticaSustentavel = new RestaurantePraticaSustentavel();
            	restaurantePraticaSustentavel.setPraticaSustentavel(praticaSustentavelBo.getPraticaSustentavel(rs.getInt("fk_id_praticas_sustentaveis")));
            	restaurantePraticaSustentavel.setRestaurante(restauranteBo.getRestaurante(rs.getLong("fk_restaurante_cnpj")));
            	
                list.add(restaurantePraticaSustentavel);
          
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir práticas sustentáveis do restaurante! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return list;
	}
	
	public void delete(long cnpj, int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
            String query = String.format("delete from restaurante_praticassustentaveis where fk_restaurante_cnpj = %s and fk_id_praticas_sustentaveis = %s ",cnpj, id);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao excluir relacionamento! - " + e);
        }
        finally {
        	conn.close();
        }
	}

}
