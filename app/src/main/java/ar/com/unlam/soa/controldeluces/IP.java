package ar.com.unlam.soa.controldeluces;

/**
 * Created by Ariel on 23/06/2016.
 */
public class IP {
    private static IP ourInstance = new IP();
    private String ip;
    public static IP getInstance() {
        return ourInstance;
    }
    public void setInstance(String ipD) { this.ip = ipD; }
    private IP() {
    }
    public String getIp() { return this.ip; }
}
