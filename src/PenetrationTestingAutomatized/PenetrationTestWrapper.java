package PenetrationTestingAutomatized;

import java.net.InetAddress;
import java.util.List;

public class PenetrationTestWrapper {
	private ExploitationModule exploitationModuleTool;
	private ScanningModule scanningModuleTool;
	private InetAddress ip;
	private String domain;

	// constructor with only IP, default tools are: NMAP & METASPLOIT
	public PenetrationTestWrapper(InetAddress ip) {
		// create the instances of the objects that will be used in this automatized
		// penetration test
		this.ip = ip;
		this.scanningModuleTool = new nmapScanningTool();
		this.exploitationModuleTool = new MetasploitExploitationTool();

	}

	// constructor with only DOMAIN, default tools are: NMAP & METASPLOIT
	public PenetrationTestWrapper(String domain) {
		// create the instances of the objects that will be used in this automatized
		// penetration test
		this.domain = domain;
		this.scanningModuleTool = new nmapScanningTool();
		this.exploitationModuleTool = new MetasploitExploitationTool();

	}

	/*
	 * TODO costruttore con parametri per decidere tool di scansione e exploit
	 * public PenetrationTestWrapper(InetAddress ip, String scanningToolName, String
	 * exploitationToolName) {
	 * 
	 * }
	 */

	public void runWithIP() {
		// start scanning with the IP
		this.scanningModuleTool.scanIP(this.ip);
		List<String> exploitsList = this.scanningModuleTool.saveXMLScannedData();

		if (!exploitsList.isEmpty()) {
			// vulnerabilities found!
			this.exploitationModuleTool.searchAndExploit(exploitsList);
						
			// TODO sfrutta le vulnerabilita trovate
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
			this.exploitationModuleTool.searchAndExploit(exploitsList);
			
			// TODO sfrutta le vulnerabilita trovate
		} else {
			// if no vulnerabilities has been found...
			System.err.println("Vulnerabilities not found!... \\nclosing...");
			System.exit(-1);
		}
	}

}
