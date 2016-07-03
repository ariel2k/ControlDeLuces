package ar.com.unlam.soa.controldeluces;

/**
 * Created by Ariel on 27/06/2016.
 */
public class UsoAngulo {
    private static UsoAngulo ourInstance = new UsoAngulo();
    private int estado;
    public static UsoAngulo getInstance() {
        return ourInstance;
    }
    public void SetInstance(int e) { this.estado = e;}
    public int getUso() {
        return this.estado;
    }
    private UsoAngulo() {
    }
}
