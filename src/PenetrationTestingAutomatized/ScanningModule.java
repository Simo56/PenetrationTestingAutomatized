package PenetrationTestingAutomatized;

import java.net.InetAddress;

public interface ScanningModule {
	//methods for scanning with IP or DOMAIN
	public void scanIP(InetAddress ip);
	public void scanDomain(String domain);
	//method for saving xml scanned data
	public void saveXMLScannedData();
}
