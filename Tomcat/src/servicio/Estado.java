package servicio;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import manejador.archivo.ManejadorArchivo;

@Path("/estado")
public class Estado {
		@GET
		@Path("/{estadoNuevo}")
		public Response getMsg(@PathParam("estadoNuevo") String estadoNuevo) {
			ManejadorArchivo.escribirArchivo(ParserXMLSerivicio.getInstance().getArchivoEstado(), estadoNuevo);
			return Response.status(200).build();
		}
}
