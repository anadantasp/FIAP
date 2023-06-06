package br.com.fiap.resource;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.bo.ArtigoBo;
import br.com.fiap.model.Curtida;

@Path("/curtida")
public class CurtidaResource {
	
	ArtigoBo artigoBo = new ArtigoBo();
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	/*RESPONSE É CLASSE DO PACOTE JAVAX ELA GERA O HTTP COMO RETORNO*/
	public Response insert(Curtida curtida) throws SQLException {
		artigoBo.insertCurtida(curtida);;
		return Response.status(Response.Status.CREATED).entity(curtida).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public int getTotalCurtidasArtigo(@PathParam("id")int id) throws SQLException {
		return artigoBo.getTotalCurtidasArtigo(id);
	}

}
