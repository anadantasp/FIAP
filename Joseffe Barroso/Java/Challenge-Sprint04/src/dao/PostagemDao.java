package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import connection.ConnectionFactory;
import model.Categoria;
import model.Postagem;


public class PostagemDao {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	CategoriaDao categoriaDao = new CategoriaDao();
	Categoria categoria = null;
	
	public void insert(Postagem p) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        
        
        
        String dataPostagem = sdf.format(p.getDate());
        
        categoria = categoriaDao.getCategoria(p.getCategoria().getId());
        
        if(categoria != null) {
        	try {
                String query = String.format("insert into postagem values(%s,'%s', '%s', '%s', '%s', %s, %s)", p.getId(), dataPostagem, 
                		p.getTitulo(), p.getConteudo(), p.getImgUrl(), p.getLikes(), p.getCategoria().getId());
               
                statement = conn.createStatement();          
                statement.executeUpdate(query);
            }catch (Exception e){
                System.out.println("Erro ao inserir o usuário! - " + e);
            }
            finally {
            	conn.close();
            }
        }else {
        	System.out.println("A postagem precisa ser associada a uma categoria válida");
        }
       
        
	}

	public ArrayList<Postagem> getAll() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        ArrayList<Postagem> list = null;
       
        try {
            String query= "select * from postagem order by id";
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            list = new ArrayList<Postagem>(); 
            while(rs.next()){
            	Postagem p = new Postagem();
            	p.setId(Integer.parseInt(rs.getString("id_post")));
            	p.setDate(sdf.parse(rs.getString("data")));
            	p.setTitulo(rs.getString("titulo"));
            	p.setConteudo(rs.getString("texto"));
            	p.setImgUrl(rs.getString("img_url"));
            	p.setLikes(Integer.parseInt(rs.getString("likes")));
            	
            	categoria = categoriaDao.getCategoria(Integer.parseInt(rs.getString("fk_cat")));
            	
            	p.setCategoria(categoria);
            	
                list.add(p);
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir o usuário! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return list;
	}

	public Postagem getPostagem(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        Postagem postagem = null;
        
        try {
            String query= "select * from postagem order by id";
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            postagem = new Postagem();
            while(rs.next()){
            	postagem.setId(Integer.parseInt(rs.getString("id_post")));
            	postagem.setDate(sdf.parse(rs.getString("data")));
            	postagem.setTitulo(rs.getString("titulo"));
            	postagem.setConteudo(rs.getString("texto"));
            	postagem.setImgUrl(rs.getString("img_url"));
            	postagem.setLikes(Integer.parseInt(rs.getString("likes")));
            	
            	categoria = categoriaDao.getCategoria(Integer.parseInt(rs.getString("fk_cat")));
            	
            	postagem.setCategoria(categoria);
            	
            	
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir o usuário! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return postagem;
	}
}
