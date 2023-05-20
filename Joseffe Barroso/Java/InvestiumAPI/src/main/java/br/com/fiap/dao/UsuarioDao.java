package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.fiap.bo.EmpresaBo;
import br.com.fiap.bo.SetorBo;
import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Empresa;
import br.com.fiap.model.Setor;
import br.com.fiap.model.Usuario;
import br.com.fiap.model.UsuarioIpo;
import br.com.fiap.model.UsuarioPostagem;

public class UsuarioDao {

	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	EmpresaBo empresaBo = new EmpresaBo();
	SetorBo setorBo = new SetorBo();

	public void insert(Usuario u) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        
        String dataNascimento = sdf.format(u.getDtNascimento());
       
        try {
            String query = String.format("insert into usuario_java values('%s','%s','%s','%s','%s')", u.getEmail(), u.getNome(), dataNascimento, u.getSenha(), u.getPapel());
           
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
            String query= "select * from usuario_java order by email";
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            list = new ArrayList<Usuario>(); 
            while(rs.next()){
            	Usuario u = new Usuario();
            	u.setEmail(rs.getString("email"));
            	u.setNome(rs.getString("nome"));
            	u.setDtNascimento((rs.getDate("d_nasc")));
            	u.setSenha(rs.getString("senha"));
            	u.setPapel(rs.getString("papel"));
            	
            	
                list.add(u);
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir o usuário! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return list;
	}
	
	public Usuario getUsuario(String email, String senha) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        Usuario usuario = null;
        
        try {
            String query= String.format("select * from usuario_java where email = '%s' and senha = '%s'", email, senha);
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            usuario = new Usuario();
            while(rs.next()){
            	usuario.setEmail(rs.getString("email"));
            	usuario.setNome(rs.getString("nome"));
            	usuario.setDtNascimento(rs.getDate("d_nasc"));
            	usuario.setSenha(rs.getString("senha"));
            	usuario.setPapel(rs.getString("papel"));
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir o usuário! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return usuario;
	}
	
	public Usuario getUsuario(String email) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        Usuario usuario = null;
        
        try {
            String query= String.format("select * from usuario_java where email = '%s'", email);
            
            statement=conn.createStatement();
           
            rs = statement.executeQuery(query);
           
            
            while(rs.next()){
            	usuario = new Usuario();
            	usuario.setEmail(rs.getString("email"));
            	usuario.setNome(rs.getString("nome"));
            	usuario.setDtNascimento(sdf.parse(rs.getString("d_nasc")));
            	usuario.setSenha(rs.getString("senha"));
            	usuario.setPapel(rs.getString("papel"));
            }
        }catch (Exception e){
            System.out.println("Usuário não encontrado! - " + e);
        }
        finally {
        	conn.close();
        }
        
        return usuario;
	}
	
	public Empresa getEmpresaSalva(int idEmpresa, String idUsuario) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        Empresa empresa = null;
        
        try {
            String query = String.format("SELECT * FROM explora "
            		+ "WHERE fk_empresa = %S and fk_usuario = '%S'", idEmpresa, idUsuario);
            
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
           
            while(rs.next()){
            	empresa = empresaBo.getEmpresa(rs.getInt("fk_empresa"));
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir as empresas! - " + e);
        }
        finally {
        	conn.close();
        }
        
		return empresa;
	}

	public void delete(String email) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
            String query = String.format("delete from usuario_java where email = '%s'", email);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao excluir o usuário! - " + e);
        }
        finally {
        	conn.close();
        }
	}

	public void salvarPostagem(UsuarioPostagem usuarioPostagem) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        
       
        try {
            String query = String.format("insert into usuario_postagem values('%s',%s)", usuarioPostagem.getEmailUsuario(), usuarioPostagem.getIdPostagem());
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao salvar postagem! - " + e);
        }
        finally {
        	conn.close();
        }
		
	}

	public void salvarIpo(UsuarioIpo usuarioIpo) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        
       
        try {
            String query = String.format("insert into explora values(%s, '%s')", usuarioIpo.getIdEmpresa(), usuarioIpo.getEmailUsuario());
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao salvar empresa/ipo! - " + e);
        }
        finally {
        	conn.close();
        }
		
	}

	public ArrayList<Empresa> getEmpresasSalvas(String idUsuario) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
        ResultSet rs = null;
        ArrayList<Empresa> list = null;
        
        try {
            String query = String.format("SELECT * FROM explora "
            		+ "WHERE fk_usuario = '%S'", idUsuario);
            
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
           
            list = new ArrayList<Empresa>();
            while(rs.next()){
            	Empresa e = new Empresa();
            	e.setId(rs.getInt("id_empresa"));
            	e.setNome(rs.getString("nome"));
            	e.setDescricao(rs.getString("descricao_empresa"));
            	
            	String ativo = rs.getString("ativo_ipo");
            	e.setAtivoIpo(ativo.contentEquals("S") ? true : false);
            	
            	e.setValorInicialIpo(rs.getDouble("valor_inicial_ipo"));
            	e.setDescricaoIpo(rs.getString("descricao_ipo"));
            	e.setLinkEmpresa(rs.getString("link_empresa"));
            	e.setLinkProspecto(rs.getString("link_prospecto"));
            	e.setImagem(rs.getString("img_empresa"));
            	
            	Setor s = setorBo.getSetor(rs.getInt("fk_setor"));
            	e.setSetor(s);
            	
            	list.add(e);
            }
        }catch (Exception e){
            System.out.println("Erro ao exibir as empresas! - " + e);
        }
        finally {
        	conn.close();
        }
        
		return list;
        
	}
	
	public void ExcluirEmpresaDosSalvos(int idEmpresa) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
            String query = String.format("delete from explora where fk_empresa = %s", idEmpresa);
           
            statement = conn.createStatement();          
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Erro ao excluir a empresa salva! - " + e);
        }
        finally {
        	conn.close();
        }
	}

	public void update(Usuario usuario) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
        Statement statement;
       
        try {
            String query = String.format("update usuario_java set senha = '%s' where email = '%s'", usuario.getSenha(), usuario.getEmail());
           
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
