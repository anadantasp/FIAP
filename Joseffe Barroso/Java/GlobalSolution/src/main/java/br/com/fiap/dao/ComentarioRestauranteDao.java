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
import br.com.fiap.model.ComentarioRestaurante;

public class ComentarioRestauranteDao {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	UsuarioBo usuarioBo = new UsuarioBo();
	RestauranteBo restauranteBo = new RestauranteBo();
	
	public int getMaiorIdComentarioRestaurante() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		int maiorId = 0;

		try {
			String query = "select max(id_comentario_restaurante) from comentario_restaurante";

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			while (rs.next()) {
				maiorId = rs.getInt("max(id_comentario_restaurante)");
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar o maior id! - " + e);
		} finally {
			conn.close();
		}
		
		return maiorId;
	}
	
	public void insert(ComentarioRestaurante comentarioRestaurante) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        
        int id = getMaiorIdComentarioRestaurante() + 1;
        
        String data = sdf.format(comentarioRestaurante.getData());
       
        try {
            String query = String.format("insert into comentario_restaurante(id_comentario_restaurante, texto, data, fk_idusuario, fk_restaurante_cnpj) "
            		+ "values(%s,'%s', '%s', %s, %s)", id, comentarioRestaurante.getTexto(), data, comentarioRestaurante.getUsuario().getId(), comentarioRestaurante.getRestaurante().getCnpj());
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
            
            System.out.println("Comentário inserido com sucesso!");
        }catch (Exception e){
            System.out.println("Erro ao inserir comentário! - " + e);
        }
        finally {
        	conn.close();
        }
	}
	
	public ArrayList<ComentarioRestaurante> getAll() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        ArrayList<ComentarioRestaurante> list = null;
       
        try {
            String query= "select * from comentario_restaurante order by id_comentario_restaurante";
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            list = new ArrayList<ComentarioRestaurante>(); 
            while(rs.next()){
            	ComentarioRestaurante comentarioRestaurante = new ComentarioRestaurante();
            	comentarioRestaurante.setId(rs.getInt("id_comentario_restaurante"));
            	comentarioRestaurante.setTexto(rs.getString("texto"));
            	comentarioRestaurante.setData(rs.getDate("data"));
            	comentarioRestaurante.setUsuario(usuarioBo.getUsuario(rs.getInt("fk_idusuario")));
            	comentarioRestaurante.setRestaurante(restauranteBo.getRestaurante(rs.getLong("fk_restaurante_cnpj")));
            	
                list.add(comentarioRestaurante);
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir comentários! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return list;
	}
	
	public ComentarioRestaurante getComentarioRestaurante(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        ComentarioRestaurante comentarioRestaurante = null;
        
        try {
            String query= String.format("select * from comentario_restaurante where id_comentario_restaurante = %s", id);
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            
            while(rs.next()){
            	comentarioRestaurante = new ComentarioRestaurante();
            	comentarioRestaurante.setId(rs.getInt("id_comentario_restaurante"));
            	comentarioRestaurante.setTexto(rs.getString("texto"));
            	comentarioRestaurante.setData(rs.getDate("data"));
            	comentarioRestaurante.setUsuario(usuarioBo.getUsuario(rs.getInt("fk_idusuario")));
            	comentarioRestaurante.setRestaurante(restauranteBo.getRestaurante(rs.getLong("fk_restaurante_cnpj")));
          
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir comentário! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return comentarioRestaurante;
	}
	
	public void delete(int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
            String query = String.format("delete from comentario_restaurante where id_comentario_restaurante = %s", id);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao excluir comentario! - " + e);
        }
        finally {
        	conn.close();
        }
	}
	
	public void update(ComentarioRestaurante comentarioRestaurante, int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        
        String data = sdf.format(new Date());
       
        try {
            String query = String.format("update comentario_restaurante set texto = '%s', data = '%s' where id_comentario_restaurante = %s", 
            		comentarioRestaurante.getTexto(), data, id);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao atualizar comentário! - " + e);
        }
        finally {
        	conn.close();
        }
	}

}
