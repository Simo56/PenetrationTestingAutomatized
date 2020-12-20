package PenetrationTestingAutomatized;

import java.net.InetAddress;

public interface ScanningModule {
	//metodi per scansionare ip/dominio
	public void scanIP(String currentTestName, InetAddress ip);
	public void scanDomain(String currentTestName, String domain);
}
