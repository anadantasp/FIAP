package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.fiap.bo.RestauranteBo;
import br.com.fiap.bo.UsuarioBo;
import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Avaliacao;

public class AvaliacaoDao {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	RestauranteBo restauranteBo = new RestauranteBo();
	UsuarioBo usuarioBo = new UsuarioBo();
	
	public int getMaiorIdAvaliacao() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		int maiorId = 0;

		try {
			String query = "select max(id_avaliacao) from avaliacao";

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			while (rs.next()) {
				maiorId = rs.getInt("max(id_avaliacao)");
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar o maior id! - " + e);
		} finally {
			conn.close();
		}
		
		return maiorId;
	}
	
	public void insert(Avaliacao avaliacao) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        
        int id = getMaiorIdAvaliacao() + 1;
        
        String data = sdf.format(avaliacao.getMomento());
       
        try {
            String query = String.format("insert into avaliacao(id_avaliacao, valor, momento, fk_restaurante_cnpj, fk_idusuario) values(%s, %s, '%s', %s, %s)", id, 
            		avaliacao.getValor(), data, avaliacao.getRestaurante().getCnpj(), avaliacao.getUsuario().getId());
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
            
            System.out.println("Avaliação inserida com sucesso!");
        }catch (Exception e){
            System.out.println("Erro ao inserir avaliacao! - " + e);
        }
        finally {
        	conn.close();
        }
	}
	
	public ArrayList<Avaliacao> getAll() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        ArrayList<Avaliacao> list = null;
       
        try {
            String query= "select * from avaliacao order by id_avaliacao";
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            list = new ArrayList<Avaliacao>(); 
            while(rs.next()){
            	Avaliacao avaliacao = new Avaliacao();
            	avaliacao.setId(rs.getInt("id_avaliacao"));
            	avaliacao.setValor(rs.getInt("valor"));
            	avaliacao.setMomento(rs.getDate("momento"));
            	avaliacao.setRestaurante(restauranteBo.getRestaurante(rs.getLong("fk_restaurante_cnpj")));
            	avaliacao.setUsuario(usuarioBo.getUsuario(rs.getInt("fk_idusuario")));
            	
                list.add(avaliacao);
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir avaliações! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return list;
	}
	
	public Avaliacao getAvaliacao(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        Avaliacao avaliacao = null;
        
        try {
            String query= String.format("select * from avaliacao where id_avaliacao = %s", id);
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            
            while(rs.next()){
            	avaliacao = new Avaliacao();
            	avaliacao.setId(rs.getInt("id_avaliacao"));
            	avaliacao.setValor(rs.getInt("valor"));
            	avaliacao.setMomento(rs.getDate("momento"));
            	avaliacao.setRestaurante(restauranteBo.getRestaurante(rs.getLong("fk_restaurante_cnpj")));
            	avaliacao.setUsuario(usuarioBo.getUsuario(rs.getInt("fk_idusuario")));
          
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir avaliação! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return avaliacao;
	}
	
	public void delete(int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
            String query = String.format("delete from avaliacao where id_avaliacao = %s", id);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao excluir avaliacao! - " + e);
        }
        finally {
        	conn.close();
        }
	}
	
	public void update(Avaliacao avaliacao, int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        
        String data = sdf.format(new Date());
       
        try {
            String query = String.format("update avaliacao set valor = %s, momento = '%s' where id_avaliacao = %s", 
            		avaliacao.getValor(), data, id);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao atualizar avaliacao! - " + e);
        }
        finally {
        	conn.close();
        }
	}

}
