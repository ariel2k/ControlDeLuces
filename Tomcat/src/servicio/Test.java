package servicio;
import java.io.*;
public class Test {
	public void main(String[] args) throws IOException{
		FileWriter fw = new FileWriter( new File("C:/aa.txt"));
		BufferedWriter out = new BufferedWriter(fw);
		out.write("asd");
		out.flush();
		out.close();
	}
}
