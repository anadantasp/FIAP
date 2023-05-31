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

import br.com.fiap.bo.RestauranteBo;
import br.com.fiap.model.Restaurante;

@Path("/restaurante")
public class RestauranteResource {

	private RestauranteBo restauranteBO = new RestauranteBo();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Restaurante> getAll() throws SQLException {
		return restauranteBO.getAll();
	}
	
	@GET
	@Path("/{cnpj}")
	@Produces(MediaType.APPLICATION_JSON)
	public Restaurante getRestaurante(@PathParam("cnpj")long cnpj) throws SQLException {
		return restauranteBO.getRestaurante(cnpj);
	}
	
	@GET
	@Path("/restauranteculinaria/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Restaurante> getRestauranteCulinaria(@PathParam("id")int idCulinaria) throws SQLException {
		return restauranteBO.getRestauranteCulinaria(idCulinaria);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	/*RESPONSE É CLASSE DO PACOTE JAVAX ELA GERA O HTTP COMO RETORNO*/
	public Response insert(Restaurante restaurante, @Context UriInfo uriInfo) throws SQLException {
		/*INSERIR NA BASE*/
		restauranteBO.insert(restaurante);;
		/*CONSTRUIR O CAMINHO(PATH DE RETORNO PEGANDO TODO O MEU URI*/
	UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	/*MONTAR O CAMINHO E PEGAR O CODIGO*/
	builder.path(String.valueOf(restaurante.getCnpj()));
	/*RETORNAR O CAMINHO E O STATUS CODE 201*/
	return Response.created(builder.build()).build();
	}
	
	@PUT
	@Path("/{cnpj}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualiza (Restaurante restaurante, @PathParam("cnpj") long cnpj) throws SQLException {
		restauranteBO.update(restaurante, cnpj);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{cnpj}")
	public void exlcuir(@PathParam("cnpj") long cnpj) throws SQLException
	{
		restauranteBO.delete(cnpj);
	}
	
}
