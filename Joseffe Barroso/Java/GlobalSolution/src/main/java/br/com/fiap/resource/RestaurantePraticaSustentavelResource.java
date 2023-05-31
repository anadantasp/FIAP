package br.com.fiap.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.bo.RestaurantePraticaSustentavelBo;
import br.com.fiap.model.RestaurantePraticaSustentavel;

@Path("/restaurantepraticasustentavel")
public class RestaurantePraticaSustentavelResource {
	
	private RestaurantePraticaSustentavelBo restaurantePraticaSustentavelBO = new RestaurantePraticaSustentavelBo();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<RestaurantePraticaSustentavel> getAll() throws SQLException {
		return restaurantePraticaSustentavelBO.getAll();
	}
	
	@GET
	@Path("/{cnpj}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<RestaurantePraticaSustentavel> getRestaurantePraticaSustentavel(@PathParam("cnpj")long cnpj) throws SQLException {
		return restaurantePraticaSustentavelBO.getRestaurantePraticaSustentavel(cnpj);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	/*RESPONSE É CLASSE DO PACOTE JAVAX ELA GERA O HTTP COMO RETORNO*/
	public Response insert(RestaurantePraticaSustentavel restaurantePraticaSustentavel, @Context UriInfo uriInfo) throws SQLException {
		restaurantePraticaSustentavelBO.insert(restaurantePraticaSustentavel);;
		return Response.status(Response.Status.CREATED).entity(restaurantePraticaSustentavel).build();
	}
	
	@DELETE
	@Path("/{cnpj}/{id}")
	public void exlcuir(@PathParam("cnpj")long cnpj, @PathParam("id") int id) throws SQLException
	{
		restaurantePraticaSustentavelBO.delete(cnpj, id);
	}

}
