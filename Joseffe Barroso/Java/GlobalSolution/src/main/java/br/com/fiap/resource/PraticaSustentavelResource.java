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

import br.com.fiap.bo.PraticaSustentavelBo;
import br.com.fiap.model.PraticaSustentavel;

@Path("/praticasustentavel")
public class PraticaSustentavelResource {
	
	private PraticaSustentavelBo praticaSustentavelBO = new PraticaSustentavelBo();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PraticaSustentavel> getAll() throws SQLException {
		return praticaSustentavelBO.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PraticaSustentavel getCulinaria(@PathParam("id")int id) throws SQLException {
		return praticaSustentavelBO.getPraticaSustentavel(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	/*RESPONSE É CLASSE DO PACOTE JAVAX ELA GERA O HTTP COMO RETORNO*/
	public Response insert(PraticaSustentavel praticaSustentavel, @Context UriInfo uriInfo) throws SQLException {
		/*INSERIR NA BASE*/
		praticaSustentavelBO.insert(praticaSustentavel);;
		/*CONSTRUIR O CAMINHO(PATH DE RETORNO PEGANDO TODO O MEU URI*/
	UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	/*MONTAR O CAMINHO E PEGAR O CODIGO*/
	builder.path(Integer.toString(praticaSustentavel.getId()));
	/*RETORNAR O CAMINHO E O STATUS CODE 201*/
	return Response.created(builder.build()).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualiza (PraticaSustentavel praticaSustentavel, @PathParam("id") int id) throws SQLException {
		praticaSustentavelBO.update(praticaSustentavel, id);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public void exlcuir(@PathParam("id") int id) throws SQLException
	{
		praticaSustentavelBO.delete(id);
	}

}
