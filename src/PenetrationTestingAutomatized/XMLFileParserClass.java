package PenetrationTestingAutomatized;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLFileParserClass {
	public static List<String> nmapVulnersExtrapolateExploitablesVulnerabilitiesFromXML() {
		//String list for storing CVEs
		List<String> exploitsList = new ArrayList<String>();
		
		Document nmapOutputXML = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true); // never forget this!
			DocumentBuilder builder = factory.newDocumentBuilder();
			try {
				nmapOutputXML = builder.parse(Paths.get(AutomatizedPenetrationTestingScript.currentTestPath.toRealPath().toString(), "NmapScanOutputCVE.xml").toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Create XPath

			XPathFactory xpathfactory = XPathFactory.newInstance();
			XPath xpath = xpathfactory.newXPath();

			//get only exploitables vulnerabilities by executing this xpath query ----> find which attribute is_exploit is set to true, and get the value of the element
			XPathExpression expr = xpath.compile("//table/table/elem[@key='is_exploit' and contains(text(),\"true\")]/../elem[@key=\"id\"]/text()");
			Object result = expr.evaluate(nmapOutputXML, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			for (int i = 0; i < nodes.getLength(); i++) {
				exploitsList.add(nodes.item(i).getNodeValue());
			}
		} catch (ParserConfigurationException | XPathExpressionException e) {
			if (e instanceof SAXException)
				System.err.println("SAX ERROR");
			if (e instanceof ParserConfigurationException)
				System.err.println("PARSER CONFIGURATION ERROR");
			if (e instanceof XPathExpressionException)
				System.err.println("XPATH EXPRESSION ERROR");
			e.printStackTrace();
			return null;
		}

		return exploitsList;
	}
}
