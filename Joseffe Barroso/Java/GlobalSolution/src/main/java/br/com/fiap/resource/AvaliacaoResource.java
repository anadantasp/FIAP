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

import br.com.fiap.bo.AvaliacaoBo;
import br.com.fiap.model.Avaliacao;

@Path("/avaliacao")
public class AvaliacaoResource {
	
	private AvaliacaoBo avaliacaoBO = new AvaliacaoBo();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Avaliacao> getAll() throws SQLException {
		return avaliacaoBO.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Avaliacao getCulinaria(@PathParam("id")int id) throws SQLException {
		return avaliacaoBO.getAvaliacao(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	/*RESPONSE É CLASSE DO PACOTE JAVAX ELA GERA O HTTP COMO RETORNO*/
	public Response insert(Avaliacao avaliacao, @Context UriInfo uriInfo) throws SQLException {
		/*INSERIR NA BASE*/
		avaliacaoBO.insert(avaliacao);;
		/*CONSTRUIR O CAMINHO(PATH DE RETORNO PEGANDO TODO O MEU URI*/
	UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	/*MONTAR O CAMINHO E PEGAR O CODIGO*/
	builder.path(Integer.toString(avaliacao.getId()));
	/*RETORNAR O CAMINHO E O STATUS CODE 201*/
	return Response.created(builder.build()).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualiza (Avaliacao avaliacao, @PathParam("id") int id) throws SQLException {
		avaliacaoBO.update(avaliacao, id);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public void exlcuir(@PathParam("id") int id) throws SQLException
	{
		avaliacaoBO.delete(id);
	}

}
