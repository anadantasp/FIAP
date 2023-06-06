package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Usuario;

public class UsuarioDao {
	public int getMaiorIdUsuario() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		int maiorId = 0;

		try {
			String query = "select max(id_usuario) from usuario";

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			while (rs.next()) {
				maiorId = rs.getInt("max(id_usuario)");
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar o maior id! - " + e);
		} finally {
			conn.close();
		}
		
		return maiorId;
	}

	public void insert(Usuario usuario) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        
        usuario.setId(getMaiorIdUsuario() + 1);
       
        try {
            String query = String.format("insert into usuario(id_usuario, nome, senha, email, tipousuario) values(%s,'%s','%s','%s','%s')",usuario.getId(),
            	usuario.getNome(), usuario.getSenha(),usuario.getEmail(), usuario.getTipoUsuario());
            
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
            
            System.out.printf("Usuário cadastrado com sucesso!\n\n");
        }catch (Exception e){
            System.out.println("Erro ao inserir o usuário! - " + e);
        }
        finally {
        	conn.close();
        }
	}
	
	public ArrayList<Usuario> getAll() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        ArrayList<Usuario> list = null;
       
        try {
            String query= "select * from usuario order by id_usuario";
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            list = new ArrayList<Usuario>(); 
            while(rs.next()){
            	Usuario usuario = new Usuario();
            	usuario.setId(rs.getInt("id_usuario"));
            	usuario.setEmail(rs.getString("email"));
            	usuario.setNome(rs.getString("nome"));
            	usuario.setSenha(rs.getString("senha"));
            	usuario.setTipoUsuario(rs.getString("tipousuario"));
            	
                list.add(usuario);
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir o usuário! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return list;
	}
	
	public Usuario getUsuario(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        Usuario usuario = null;
        
        try {
            String query= String.format("select * from usuario where id_usuario = %s", id);
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            usuario = new Usuario();
            while(rs.next()){
            	usuario.setId(rs.getInt("id_usuario"));
            	usuario.setEmail(rs.getString("email"));
            	usuario.setNome(rs.getString("nome"));
            	usuario.setSenha(rs.getString("senha"));
            	usuario.setTipoUsuario(rs.getString("tipousuario"));
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir o usuário! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return usuario;
	}
	
	public Usuario getUsuario(String email, String senha) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        Usuario usuario = null;
        
        try {
            String query= String.format("select * from usuario where email = '%s' and senha = '%s'", email, senha);
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            usuario = new Usuario();
            while(rs.next()){
            	usuario.setId(rs.getInt("id_usuario"));
            	usuario.setEmail(rs.getString("email"));
            	usuario.setNome(rs.getString("nome"));
            	usuario.setSenha(rs.getString("senha"));
            	usuario.setTipoUsuario(rs.getString("tipousuario"));
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir o usuário! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return usuario;
	}
	
	public void delete(int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
            String query = String.format("delete from usuario where id_usuario = %s", id);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao excluir o usuário! - " + e);
        }
        finally {
        	conn.close();
        }
	}
	
	public void updateSenha(Usuario usuario, int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
            String query = String.format("update usuario set senha = '%s' where id_usuario = %s", usuario.getSenha(), id);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao atualizar senha! - " + e);
        }
        finally {
        	conn.close();
        }
		
	}
	
	public void update(Usuario usuario, int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
           
            String query = String.format("update usuario set email = '%s', nome = '%s' where id_usuario = %s", 
            		usuario.getEmail(), usuario.getNome(), id);
           
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
