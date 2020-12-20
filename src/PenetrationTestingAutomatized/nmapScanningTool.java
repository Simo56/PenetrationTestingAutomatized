package PenetrationTestingAutomatized;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;

public class nmapScanningTool implements ScanningModule {
	// percorso per avviare nmap
	private final Path nmapPath;
	private final Path currentTestPath;
	private ProcessBuilder process;
	
	
	// **************************** COSTRUTTORI ****************************

	public nmapScanningTool(String currentTestName) {
		this.nmapPath = Paths.get(System.getProperty("user.dir") + "\\nmap");
		this.currentTestPath = Paths.get(System.getProperty("user.dir") + "\\PENETRATION_TEST_SALVATI\\" + currentTestName);
		this.process = new ProcessBuilder();
		//this.process.directory(nmapPath.toFile());
	}

	// **************************** FINE COSTRUTTORI ****************************

	
	// **************************** METODI ****************************

	@Override
	public void scanIP(String currentTestName, InetAddress ip) {
		try {	
			// scan + OS and service detection with fast execution
			// nmap -A -T4 -Pn -oX /PENETRATION_TEST_SALVATI/CURRENT_TEST_NAME/NmapScanOutputOS&Services.xml IP
			process.command("cmd.exe", "/c", nmapPath + "\\nmap.exe", "-A","-T4", "-oX", currentTestPath + "\\NmapScanOutputOS+Services.xml", ip.toString().replace("/", "")).inheritIO();
			process.start();
			
			// scan and find CVE
			// nmap -Pn -sV --script vulners -oX /PENETRATION_TEST_SALVATI/CURRENT_TEST_NAME/NmapScanOutputCVE.xml -sV -sC IP
			process.command("cmd.exe", "/c", nmapPath + "\\nmap.exe", "-sV","--script", "vulners", "-oX", currentTestPath + "\\NmapScanOutputCVE.xml", ip.toString().replace("/", "")).inheritIO();
			process.start();
		}catch (IOException e) {
			System.err.println("ERRORE NELLA CREAZIONE DEL SOTTOPROCESSO");
			e.printStackTrace();
		}
	}

	@Override
	public void scanDomain(String currentTestName, String domain) {
		try {	
			// scan + OS and service detection with fast execution
			// nmap -A -T4 -Pn -oX /PENETRATION_TEST_SALVATI/CURRENT_TEST_NAME/NmapScanOutputOS&Services.xml DOMAIN
			process.command("cmd.exe", "/c", nmapPath + "\\nmap.exe", "-A","-T4", "-Pn", "-oX", currentTestPath + "\\NmapScanOutputOS+Services.xml", domain).inheritIO();
			process.start();
			
			// scan and find CVE
			// nmap -Pn -sV --script vulners -oX /PENETRATION_TEST_SALVATI/CURRENT_TEST_NAME/NmapScanOutputCVE.xml -sV -sC DOMAIN
			process.command("cmd.exe", "/c", nmapPath + "\\nmap.exe", "-Pn", "-sV","--script", "vulners", "-oX", currentTestPath + "\\NmapScanOutputCVE.xml", domain).inheritIO();
			process.start();
		}catch (IOException e) {
			System.err.println("ERRORE NELLA CREAZIONE DEL SOTTOPROCESSO");
			e.printStackTrace();
		}
	}

	// **************************** FINE METODI ****************************
}
