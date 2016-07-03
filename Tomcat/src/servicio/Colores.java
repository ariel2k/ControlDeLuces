package servicio;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import manejador.archivo.ManejadorArchivo;
 
@Path("/colores")
public class Colores {
 
	@GET
	@Path("/{red}/{green}/{blue}")
	public Response getMsg(@PathParam("red") String red, @PathParam("green") String green, @PathParam("blue") String blue) {
		String  colores=red+" "+green+" "+blue;
		ManejadorArchivo.escribirArchivo(ParserXMLSerivicio.getInstance().getArchivoColor(), colores);
		return Response.status(200).entity(ParserXMLSerivicio.getInstance().getArchivoColor()).build();
	}
 
}