package PenetrationTestingAutomatized;

import java.net.InetAddress;

public interface ScanningModule {
	//metodi per scansionare ip/dominio
	public void scanIP(InetAddress ip);
	public void scanDomain(String domain);
	//metodo per salvare file XML
	public void saveXMLScannedData();
}
