package manejador.archivo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ManejadorArchivo {
	public static void escribirArchivo(String path, String contenido){
	 	File fichero = new File(path); 
	 	boolean excepcion = true;
	 	while(excepcion){
	        try{
	    		FileWriter fw = new FileWriter( fichero);
	    		BufferedWriter out = new BufferedWriter(fw);
	    		out.write(contenido);
	    		out.flush();
	    		out.close();
//	        	fichero.delete();
//			 	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fichero, false)));
//		        out.println(contenido);
//				out.close();
			 	excepcion = false;
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	 	}
	}
}
