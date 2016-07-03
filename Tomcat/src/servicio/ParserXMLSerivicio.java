package servicio;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ParserXMLSerivicio {
	
	//    private static final File INPUT_FILE = new File("../webapps/testWS/FtpDatosConexion.xml"); 
	   private static final File INPUT_FILE = new File("C:/apache-tomcat-8.0.36/webapps/arduino/Servicio.xml"); 


	   private String archivoAngulo;
	   private String archivoColor;
	   private String archivoDespertador;
	   private String archivoEstado;
	   private static ParserXMLSerivicio instancia=null;
		private ParserXMLSerivicio(){
			buscarConfiguracionXML();
		}
		
		public static ParserXMLSerivicio getInstance(){
				if(instancia==null)
					instancia= new ParserXMLSerivicio();
			return instancia;
		}
		
	   public void buscarConfiguracionXML(){	   
		  try {	

		     DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		     DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		     Document doc = dBuilder.parse(INPUT_FILE);
		     doc.getDocumentElement().normalize();
		     Element eElement = doc.getDocumentElement();
		     archivoAngulo= eElement
		              .getElementsByTagName("archivoAngulo")
		          .item(0)
		          .getFirstChild().getNodeValue();
		     archivoColor= eElement
		              .getElementsByTagName("archivoColor")
		          .item(0)
		          .getFirstChild().getNodeValue();
		     archivoDespertador= eElement
		              .getElementsByTagName("archivoDespertador")
		          .item(0)
		          .getFirstChild().getNodeValue();
		     archivoEstado= eElement
		              .getElementsByTagName("archivoEstado")
		          .item(0)
		          .getFirstChild().getNodeValue();
		     System.out.println(archivoDespertador+"--"+archivoEstado);
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		   }

	public String getArchivoEstado() {
		return archivoEstado;
	}

	public void setArchivoEstado(String archivoEstado) {
		this.archivoEstado = archivoEstado;
	}

	public String getArchivoAngulo() {
		return archivoAngulo;
	}

	public void setArchivoAngulo(String archivoAngulo) {
		this.archivoAngulo = archivoAngulo;
	}

	public String getArchivoColor() {
		return archivoColor;
	}

	public void setArchivoColor(String archivoColor) {
		this.archivoColor = archivoColor;
	}

	public String getArchivoDespertador() {
		return archivoDespertador;
	}

	public void setArchivoDespertador(String archivoDespertador) {
		this.archivoDespertador = archivoDespertador;
	}
	   

	   
}