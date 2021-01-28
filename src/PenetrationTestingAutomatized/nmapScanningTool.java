package PenetrationTestingAutomatized;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

public class nmapScanningTool implements ScanningModule {
	//object for handling the subprocesses, in this case NMAP
	private ProcessBuilder process;

	public nmapScanningTool() {
		this.process = new ProcessBuilder();
	}

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
	public List<String> saveXMLScannedData() {
		try {
			//waitFor() because wrapper needs to wait for the result of the scan that is now executing
			this.process.start().waitFor();
			
			//create the String list for the CVEs
			return XMLFileParserClass.nmapVulnersExtrapolateExploitablesVulnerabilitiesFromXML();
		} catch (IOException | InterruptedException e) {
			if(e instanceof IOException) System.err.println("I/O ERROR DURING EXECUTION OF THE SUBPROCESS");
			if(e instanceof InterruptedException) System.err.println("ERROR WHILE INTERRUPTING THE SUBPROCESS");
			e.printStackTrace();
			return null;
		}
	}
}
