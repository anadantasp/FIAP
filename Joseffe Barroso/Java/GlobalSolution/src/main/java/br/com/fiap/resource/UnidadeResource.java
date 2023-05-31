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

import br.com.fiap.bo.UnidadeBo;
import br.com.fiap.model.Unidade;

@Path("/unidade")
public class UnidadeResource {
	
	UnidadeBo unidadeBo = new UnidadeBo();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Unidade> getAll() throws SQLException {
		return unidadeBo.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Unidade getUnidade(@PathParam("id")int id) throws SQLException {
		return unidadeBo.getUnidade(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	/*RESPONSE É CLASSE DO PACOTE JAVAX ELA GERA O HTTP COMO RETORNO*/
	public Response insert(Unidade unidade, @Context UriInfo uriInfo) throws SQLException {
		/*INSERIR NA BASE*/
		unidadeBo.insert(unidade);;
		/*CONSTRUIR O CAMINHO(PATH DE RETORNO PEGANDO TODO O MEU URI*/
	UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	/*MONTAR O CAMINHO E PEGAR O CODIGO*/
	builder.path(Integer.toString(unidade.getId()));
	/*RETORNAR O CAMINHO E O STATUS CODE 201*/
	return Response.created(builder.build()).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualiza (Unidade unidade, @PathParam("id") int id) throws SQLException {
		unidadeBo.update(unidade, id);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public void exlcuir(@PathParam("id") int id) throws SQLException
	{
		unidadeBo.delete(id);
	}

}
