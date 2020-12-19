package PenetrationTestingAutomatized;

import java.net.InetAddress;

public interface ScanningModule {
	//metodi per scansionare ip/dominio
	public void scanIP(InetAddress ip);
	public void scanDomain(String domain);
	//invia all'exploitation module i dati per poter sfruttare la vulnerabilità
	public void sendXMLScannedData();

}
