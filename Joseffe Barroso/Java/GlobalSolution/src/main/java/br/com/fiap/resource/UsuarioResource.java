package br.com.fiap.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.bo.UsuarioBo;
import br.com.fiap.model.Categoria;
import br.com.fiap.model.Usuario;


@Path("/usuario")
public class UsuarioResource {
	
	private UsuarioBo usuarioBo = new UsuarioBo();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Usuario> getAll() throws SQLException {
		return usuarioBo.getAll();
	}
	
	@GET
	@Path("/{email}/{senha}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario buscar(@PathParam("email")String email, @PathParam("senha")String senha) throws SQLException {
		return usuarioBo.getUsuario(email, senha);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getCategoria(@PathParam("id")int id) throws SQLException {
		return usuarioBo.getUsuario(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	/*RESPONSE É CLASSE DO PACOTE JAVAX ELA GERA O HTTP COMO RETORNO*/
	public Response insert(Usuario usuario, @Context UriInfo uriInfo) throws SQLException {
		/*INSERIR NA BASE*/
		usuarioBo.insert(usuario);;
		/*CONSTRUIR O CAMINHO(PATH DE RETORNO PEGANDO TODO O MEU URI*/
	UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	/*MONTAR O CAMINHO E PEGAR O CODIGO*/
	builder.path(usuario.getEmail());
	/*RETORNAR O CAMINHO E O STATUS CODE 201*/
	return Response.created(builder.build()).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Usuario usuario, @PathParam("id") int id) throws SQLException {
		usuarioBo.update(usuario, id);
		return Response.ok().build();
	}
	
	
	@DELETE
	@Path("/{id}")
	public void excluir(@PathParam("id") int id) throws SQLException
	{
		usuarioBo.delete(id);
	}

}
