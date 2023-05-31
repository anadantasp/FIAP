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

import br.com.fiap.bo.CulinariaBo;
import br.com.fiap.model.Culinaria;

@Path("/culinaria")
public class CulinariaResource {
	private CulinariaBo culinariaBO = new CulinariaBo();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Culinaria> getAll() throws SQLException {
		return culinariaBO.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Culinaria getCulinaria(@PathParam("id")int id) throws SQLException {
		return culinariaBO.getCulinaria(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	/*RESPONSE É CLASSE DO PACOTE JAVAX ELA GERA O HTTP COMO RETORNO*/
	public Response insert(Culinaria culinaria, @Context UriInfo uriInfo) throws SQLException {
		/*INSERIR NA BASE*/
		culinariaBO.insert(culinaria);;
		/*CONSTRUIR O CAMINHO(PATH DE RETORNO PEGANDO TODO O MEU URI*/
	UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	/*MONTAR O CAMINHO E PEGAR O CODIGO*/
	builder.path(Integer.toString(culinaria.getId()));
	/*RETORNAR O CAMINHO E O STATUS CODE 201*/
	return Response.created(builder.build()).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualiza (Culinaria culinaria, @PathParam("id") int id) throws SQLException {
		culinariaBO.update(culinaria, id);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public void exlcuir(@PathParam("id") int id) throws SQLException
	{
		culinariaBO.delete(id);
	}

}
