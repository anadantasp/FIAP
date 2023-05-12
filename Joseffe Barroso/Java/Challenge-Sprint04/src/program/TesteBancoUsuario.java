package program;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import dao.UsuarioDao;
import model.Usuario;

public class TesteBancoUsuario {

	public static void main(String[] args) throws ParseException, SQLException {
		UsuarioDao usuDao = new UsuarioDao();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		Usuario usu = new Usuario("Diego", "diego@email.com.br", "senha123", sdf.parse("09-02-1992"), "admin");
		
	    System.out.println(sdf.format(usu.getDtNascimento()));
		
		//usuDao.insert(usu);
	    
	    /*
	    for(Usuario u: usuDao.getAll()) {
	    	System.out.println("E-mail: " + u.getEmail()
	    	+ " - Nome: " + u.getNome() + " - Data Nascimento" + sdf.format(u.getDtNascimento())
	    	+ " - Senha: " + u.getSenha() + " - Papel: " + u.getPapel());
	    }
	    */
	    
	    Usuario usuario = usuDao.getUsuario("ana@email.com.br", "senha123");
	    
	    System.out.println("E-mail: " + usuario.getEmail()
	    	+ " - Nome: " + usuario.getNome() + " - Data Nascimento" + sdf.format(usuario.getDtNascimento())
	    	+ " - Senha: " + usuario.getSenha() + " - Papel: " + usuario.getPapel());
		
		
		/*
		usuario.setEmail(email);
		System.out.printf("Digite sua senha: ");
		usuario.setSenha(scn.next());
		System.out.printf("Digite sua data de nascimento(DD/MM/YYYY): ");
		usuario.setDtNascimento(sdf.parse(scn.next()));

		usuario.setPapel("comum");

		usuarios.add(usuario);
		*/
		

	}

}
