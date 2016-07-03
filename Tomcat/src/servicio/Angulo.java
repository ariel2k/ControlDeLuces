package servicio;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import manejador.archivo.ManejadorArchivo;

@Path("/angulo")
public class Angulo {
	@GET
	@Path("/{angulo}")
	public Response getMsg(@PathParam("angulo") String angulo) {		
		ManejadorArchivo.escribirArchivo(ParserXMLSerivicio.getInstance().getArchivoAngulo(), angulo);
		return Response.status(200).build();
	}
	
}
