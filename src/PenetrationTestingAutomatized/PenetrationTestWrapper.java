package PenetrationTestingAutomatized;

import java.net.InetAddress;
import java.util.List;

public class PenetrationTestWrapper {
	private ExploitationModule exploitationModuleTool;
	private ScanningModule scanningModuleTool;
	private InetAddress ip;
	private String domain;

	// **************************** COSTRUTTORI ****************************

	// constructor with only IP, default tools are: NMAP & METASPLOIT
	public PenetrationTestWrapper(InetAddress ip) {
		// create the instances of the objects that will be used in this automatized
		// penetration test
		this.ip = ip;
		this.scanningModuleTool = new nmapScanningTool();
		// TODO this.exploitationModuleTool = metasploit!!!

	}

	// constructor with only DOMAIN, default tools are: NMAP & METASPLOIT
	public PenetrationTestWrapper(String domain) {
		// create the instances of the objects that will be used in this automatized
		// penetration test
		this.domain = domain;
		this.scanningModuleTool = new nmapScanningTool();
		// TODO this.exploitationModuleTool = metasploit!!!

	}

	/*
	 * TODO costruttore con parametri per decidere tool di scansione e exploit
	 * public PenetrationTestWrapper(InetAddress ip, String scanningToolName, String
	 * exploitationToolName) {
	 * 
	 * }
	 */

	// **************************** FINE COSTRUTTORI ****************************

	public void runWithIP() {
		// start scanning with the IP
		this.scanningModuleTool.scanIP(this.ip);
		List<String> exploitsList = this.scanningModuleTool.saveXMLScannedData();

		if (!exploitsList.isEmpty()) {
			// vulnerabilities found!
			
			// TODO sfrutta le vulnerabilità trovate
			// this.exploitationModule.retrieve etc etc exploit
		} else {
			// if no vulnerabilities has been found
			System.err.println("Vulnerabilities not found!... \\nclosing...");
			System.exit(-1);
		}
	}

	public void runWithDomain() {
		// start scanning with the DOMAIN
		this.scanningModuleTool.scanDomain(this.domain);
		List<String> exploitsList = this.scanningModuleTool.saveXMLScannedData();

		if (!exploitsList.isEmpty()) {
			// vulnerabilities found!
			
			// TODO sfrutta le vulnerabilità trovate
			// this.exploitationModule.retrieve etc etc exploit
		} else {
			// if no vulnerabilities has been found...
			System.err.println("Vulnerabilities not found!... \\nclosing...");
			System.exit(-1);
		}
	}

}
