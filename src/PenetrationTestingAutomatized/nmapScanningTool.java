package PenetrationTestingAutomatized;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;

public class nmapScanningTool implements ScanningModule {
	// percorso salvataggio file xml
	private final Path currentTestPath;
	// oggetto per eseguire sottoprocessi
	private ProcessBuilder process;
	
	
	// **************************** COSTRUTTORI ****************************

	public nmapScanningTool(String currentTestName) {
		this.currentTestPath = Paths.get(System.getProperty("user.dir") + "\\PENETRATION_TEST_SALVATI\\" + currentTestName);
		this.process = new ProcessBuilder();
	}

	// **************************** FINE COSTRUTTORI ****************************

	
	// **************************** METODI ****************************

	@Override
	public void scanIP(InetAddress ip) {
		// scan and find CVE
		// nmap -A -Pn -sV --script vulners -oX /PENETRATION_TEST_SALVATI/CURRENT_TEST_NAME/NmapScanOutputCVE.xml -sV -sC IP
		process.command("nmap",  "-sV", "-A", "-Pn", "--script", "vulners", "-oX", this.currentTestPath + "\\NmapScanOutputCVE.xml", ip.toString().replace("/", "")).inheritIO();
	}

	@Override
	public void scanDomain(String domain) {
		// scan and find CVE
		// nmap -Pn -sV --script vulners -oX /PENETRATION_TEST_SALVATI/CURRENT_TEST_NAME/NmapScanOutputCVE.xml -sV -sC DOMAIN
		process.command("nmap", "-A", "-Pn", "-sV","--script", "vulners", "-oX", this.currentTestPath + "\\NmapScanOutputCVE.xml", domain).inheritIO();
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
