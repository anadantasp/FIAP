package br.com.fiap.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.fiap.bo.RestauranteBo;
import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Endereco;
import br.com.fiap.model.Unidade;
import br.com.fiap.service.ViaCepService;

public class UnidadeDao {
	
	RestauranteBo restauranteBo = new RestauranteBo();

	public int getMaiorIdUnidade() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;
		ResultSet rs = null;
		int maiorId = 0;

		try {
			String query = "select max(id_unidade) from unidade";

			statement = conn.createStatement();

			rs = statement.executeQuery(query);

			while (rs.next()) {
				maiorId = rs.getInt("max(id_unidade)");
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar o maior id! - " + e);
		} finally {
			conn.close();
		}

		return maiorId;
	}

	public void insert(Unidade unidade) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		Statement statement;

		int id = getMaiorIdUnidade() + 1;

		ViaCepService viacepservice = new ViaCepService();
		Endereco endereco = null;
		
		try {
			endereco = viacepservice.getEndereco(unidade.getCep());
			
			unidade.setRua(endereco.getLogradouro());
			unidade.setCidade(endereco.getLocalidade());
			unidade.setEstado(endereco.getUf());
			unidade.setBairro(endereco.getBairro());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			String query = String.format(
					"insert into unidade(id_unidade,imagem, cep, rua, cidade, estado, bairro, fk_restaurante) "
							+ "values(%s,'%s', '%s', '%s', '%s', '%s', '%s', %s)",
					id, unidade.getImagem(), unidade.getCep(), unidade.getRua(), unidade.getCidade(),
					unidade.getEstado(), unidade.getBairro(), unidade.getRestaurante().getCnpj());

			statement = conn.createStatement();
			statement.executeUpdate(query);

			System.out.println("Unidade inserida com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao inserir unidade! - " + e);
		} finally {
			conn.close();
		}
	}
	
	public ArrayList<Unidade> getAll() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        ArrayList<Unidade> list = null;
       
        try {
            String query= "select * from unidade order by id_unidade";
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            list = new ArrayList<Unidade>(); 
            while(rs.next()){
            	Unidade unidade = new Unidade();
            	unidade.setId(rs.getInt("id_unidade"));
            	unidade.setImagem(rs.getString("imagem"));
            	unidade.setCep(rs.getString("cep"));
            	unidade.setRua(rs.getString("rua"));
            	unidade.setCidade(rs.getString("cidade"));
            	unidade.setEstado(rs.getString("estado"));
            	unidade.setBairro(rs.getString("bairro"));
            	unidade.setRestaurante(restauranteBo.getRestaurante(rs.getLong("fk_restaurante")));
            	
                list.add(unidade);
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir unidades! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return list;
	}
	
	public Unidade getUnidade(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        Unidade unidade = null;
        
        try {
            String query= String.format("select * from unidade where id_unidade = %s", id);
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            
            while(rs.next()){
            	unidade = new Unidade();
            	unidade.setId(rs.getInt("id_unidade"));
            	unidade.setImagem(rs.getString("imagem"));
            	unidade.setCep(rs.getString("cep"));
            	unidade.setRua(rs.getString("rua"));
            	unidade.setCidade(rs.getString("cidade"));
            	unidade.setEstado(rs.getString("estado"));
            	unidade.setBairro(rs.getString("bairro"));
            	unidade.setRestaurante(restauranteBo.getRestaurante(rs.getLong("fk_restaurante")));
          
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir unidade! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return unidade;
	}
	
	public void delete(int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
            String query = String.format("delete from unidade where id_unidade = %s", id);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao excluir unidade! - " + e);
        }
        finally {
        	conn.close();
        }
	}
	
	public void update(Unidade unidade, int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        
        ViaCepService viacepservice = new ViaCepService();
		Endereco endereco = null;
		
		try {
			endereco = viacepservice.getEndereco(unidade.getCep());
			
			unidade.setRua(endereco.getLogradouro());
			unidade.setCidade(endereco.getLocalidade());
			unidade.setEstado(endereco.getUf());
			unidade.setBairro(endereco.getBairro());
		} catch (IOException e) {
			e.printStackTrace();
		}
       
        try {
            String query = String.format("update unidade set imagem = '%s', cep = '%s', rua = '%s', cidade = '%s'"
            		+ ", estado = '%s', bairro = '%s', fk_restaurante = %s where id_unidade = %s", unidade.getImagem(),
            		unidade.getCep(), unidade.getRua(), unidade.getCidade(), unidade.getEstado(), unidade.getBairro(),
            		unidade.getRestaurante().getCnpj(),id);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao atualizar unidade! - " + e);
        }
        finally {
        	conn.close();
        }
	}

}
