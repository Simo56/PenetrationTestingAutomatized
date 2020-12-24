package PenetrationTestingAutomatized;

import java.net.InetAddress;

public class PenetrationTestWrapper {
	private ExploitationModule exploitationModuleTool;
	private ScanningModule scanningModuleTool;
	private InetAddress ip;
	private String domain;
	
	
	// **************************** COSTRUTTORI ****************************
	
	//costruttore con solo IP, strumenti di default (nmap e metasploit)
	public PenetrationTestWrapper(InetAddress ip) {
		//istanzia gli oggetti che gestiranno le varie fasi del penetration test
		this.ip = ip;
		this.scanningModuleTool = new nmapScanningTool();
		//TODO this.exploitationModuleTool = metasploit!!!
		
	}
	
	//costruttore con solo Dominio, strumenti di default (nmap e metasploit)
	public PenetrationTestWrapper(String domain) {
		//istanzia gli oggetti che gestiranno le varie fasi del penetration test
		this.domain = domain;
		this.scanningModuleTool = new nmapScanningTool();
		//TODO this.exploitationModuleTool = metasploit!!!
		
	}
	
		
	
	/* TODO costruttore con parametri per decidere tool di scansione e exploit
	public PenetrationTestWrapper(InetAddress ip, String scanningToolName, String exploitationToolName) {
		
	}
	*/
	
	// **************************** FINE COSTRUTTORI ****************************

	
	
	
	
	// **************************** METODO PER FAR PARTIRE IL PENETRATION TEST ****************************
	
	public void runWithIP() {
		//avvia la scansione tramite ip
		this.scanningModuleTool.scanIP(this.ip);
		this.scanningModuleTool.saveXMLScannedData();
		//TODO
		//this.exploitationModule.retrieve etc etc exploit
	}
	
	public void runWithDomain() {
		//avvia la scansione tramite dominio
		this.scanningModuleTool.scanDomain(this.domain);
		this.scanningModuleTool.saveXMLScannedData();
		//TODO
		//this.exploitationModule.retrieve etc etc exploit
	}
	
	// **************************** FINE METODO PER FAR PARTIRE IL PENETRATION TEST ****************************
}
