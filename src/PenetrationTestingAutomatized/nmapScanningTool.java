package PenetrationTestingAutomatized;

import java.io.IOException;
import java.net.InetAddress;

public class nmapScanningTool implements ScanningModule {
	// oggetto per eseguire sottoprocessi, nmap in questo caso
	private ProcessBuilder process;
	
	
	// **************************** COSTRUTTORI ****************************

	public nmapScanningTool() {
		this.process = new ProcessBuilder();
	}

	// **************************** FINE COSTRUTTORI ****************************

	
	// **************************** METODI ****************************

	@Override
	public void scanIP(InetAddress ip) {
		//adjust directory to the right one identified by the currentTestPath var
		this.process.directory(AutomatizedPenetrationTestingScript.currentTestPath.toFile());
		// scan and find CVE
		// nmap -A -Pn -sV --script vulners -oX /PENETRATION_TEST_SALVATI/CURRENT_TEST_NAME/NmapScanOutputCVE.xml -sV -sC IP
		process.command("nmap",  "-sV", "-A", "-Pn", "--script", "vulners", "-oX", "NmapScanOutputCVE.xml", ip.getHostAddress()).inheritIO();
	}

	@Override
	public void scanDomain(String domain) {
		//adjust directory to the right one identified by the currentTestPath var
		this.process.directory(AutomatizedPenetrationTestingScript.currentTestPath.toFile());
		// scan and find CVE
		// nmap -Pn -sV --script vulners -oX /PENETRATION_TEST_SALVATI/CURRENT_TEST_NAME/NmapScanOutputCVE.xml -sV -sC DOMAIN
		process.command("nmap", "-A", "-Pn", "-sV","--script", "vulners", "-oX", "NmapScanOutputCVE.xml", domain).inheritIO();
	}

	@Override
	public void saveXMLScannedData() {
		try {
			this.process.start();
		} catch (IOException e) {
			System.err.println("ERRORE NELL'ESECUZIONE DELLO SCRIPT");
			e.printStackTrace();
		}
	}

	// **************************** FINE METODI ****************************
}
