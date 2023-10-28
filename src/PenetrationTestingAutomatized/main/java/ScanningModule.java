package PenetrationTestingAutomatized.main.java;

import java.net.InetAddress;
import java.util.List;

public interface ScanningModule {
	// methods for scanning with IP or DOMAIN
	public void scanIP(InetAddress ip);

	public void scanDomain(String domain);

	// method for saving xml scanned data on a file and then return a String List
	// with CVEs {String list because of compatibility issues from any scanning
	// script}
	public List<String> saveXMLScannedData();
}
