package PenetrationTestingAutomatized;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;

public class PenetrationTestWrapper {
	private ExploitationModule exploitationModuleTool;
	private ScanningModule scanningModuleTool;
	private InetAddress ip;
	private String domain;

	// constructor with only DOMAIN, default tools are: NMAP-VULN & METASPLOIT
	public PenetrationTestWrapper(String domain) {
		// create the instances of the objects that will be used in this automatized
		// penetration test
		this.domain = domain;
		this.scanningModuleTool = new NMAPScanningToolVulnScript();
		this.exploitationModuleTool = new MetasploitExploitationTool();
	}

	// constructor with only IP, default tools are: NMAP-VULN & METASPLOIT
	public PenetrationTestWrapper(InetAddress ip) {
		// create the instances of the objects that will be used in this automatized
		// penetration test
		this.ip = ip;
		this.scanningModuleTool = new NMAPScanningToolVulnScript();
		this.exploitationModuleTool = new MetasploitExploitationTool();
	}

	/*
	 * TODO costruttore con parametri per decidere tool di scansione e exploit
	 * public PenetrationTestWrapper(InetAddress ip, String scanningToolName, String
	 * exploitationToolName) {
	 * 
	 * }
	 */

	/***
	 * TODO COSTRUTTORE CON PARAMETRI PER DECIDERE I TOOL DI SCANSIONE E EXPLOIT
	 ***/
	/*
	 * public PenetrationTestWrapper(InetAddress ip, String scanningToolName, String
	 * exploitationToolName) {}
	 */

	/*** COSTRUTTORI PER NMAP CON VULNERS!!!!! ***/
	/*
	 * // constructor with only IP, default tools are: NMAP-VULNERS & METASPLOIT
	 * public PenetrationTestWrapper(InetAddress ip) { // create the instances of
	 * the objects that will be used in this automatized // penetration test this.ip
	 * = ip; this.scanningModuleTool = new NMAPScanningToolVulnersScript();
	 * this.exploitationModuleTool = new MetasploitExploitationTool();
	 * 
	 * }
	 * 
	 * // constructor with only DOMAIN, default tools are: NMAP-VULNERS & METASPLOIT
	 * public PenetrationTestWrapper(String domain) { // create the instances of the
	 * objects that will be used in this automatized // penetration test this.domain
	 * = domain; this.scanningModuleTool = new NMAPScanningToolVulnersScript();
	 * this.exploitationModuleTool = new MetasploitExploitationTool(); }
	 */

	public void runWithIP() {
		// start scanning with the IP
		this.scanningModuleTool.scanIP(this.ip);
		List<String> exploitsList = this.scanningModuleTool.saveXMLScannedData();

		if (!exploitsList.isEmpty()) {
			// vulnerabilities found!
			this.exploitationModuleTool.searchAndExploit(exploitsList, this.ip);

		} else {
			// if no vulnerabilities has been found
			System.err.println("Vulnerabilities not found!... \\nclosing...");
			System.exit(-1);
		}
	}

	public void runWithDomain() throws UnknownHostException, MalformedURLException {
		// start scanning with the DOMAIN
		this.scanningModuleTool.scanDomain(this.domain);
		List<String> exploitsList = this.scanningModuleTool.saveXMLScannedData();

		if (!exploitsList.isEmpty()) {
			// vulnerabilities found!
			this.exploitationModuleTool.searchAndExploit(exploitsList,
					InetAddress.getByName(new URL(this.domain).getHost()));

		} else {
			// if no vulnerabilities has been found...
			System.err.println("Vulnerabilities not found!... \\nclosing...");
			System.exit(-1);
		}
	}

}
