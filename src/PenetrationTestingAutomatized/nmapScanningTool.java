package PenetrationTestingAutomatized;

import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;

public class nmapScanningTool implements ScanningModule{
	//percorso per avviare nmap
	private final Path nmapPath = Paths.get(System.getProperty("user.dir") + "/nmap");;
	private InetAddress ip;
	private String domain;
	
	// **************************** COSTRUTTORI ****************************
	
	public nmapScanningTool(InetAddress ip) {
		this.ip = ip;
	}
	
	public nmapScanningTool(String domain) {
		this.domain = domain;
	}
	
	// **************************** FINE COSTRUTTORI ****************************
	
	
	
	
	
	// **************************** METODI ****************************
	
	@Override
	public void sendXMLScannedData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scanIP(InetAddress ip) {
		// TODO AVVIA SCANSIONE NMAP CON IP, CREA SOTTOPROCESSO!
		
	}

	@Override
	public void scanDomain(String domain) {
		// TODO AVVIA SCANSIONE NMAP CON DOMINIO, CREA SOTTOPROCESSO!
		
	}
	
	// **************************** FINE METODI ****************************
}
