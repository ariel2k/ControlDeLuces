package servicio;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import manejador.archivo.ManejadorArchivo;

@Path("/despertador")
public class Despertador {
	@GET
	@Path("/{hora}/{min}")
	public Response getMsg(@PathParam("hora") String hora,@PathParam("min") String min) {
		ManejadorArchivo.escribirArchivo(ParserXMLSerivicio.getInstance().getArchivoDespertador(), hora+" "+min);
		return Response.status(200).build();
	}
}
