package PenetrationTestingAutomatized;

import java.io.IOException;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLFileParserClass {
	public static boolean searchAndExtrapolateVulnerabilitiesFromNMAPXMLtoCVE() {
	    Document doc = null;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(Paths.get(AutomatizedPenetrationTestingScript.currentTestPath.toRealPath().toString(), "NmapScanOutputCVE.xml").toFile());
		} catch (SAXException | IOException | ParserConfigurationException e) {
			if(e instanceof SAXException) System.err.println("SAX ERROR");
			if(e instanceof IOException) System.err.println("I/O ERROR");
			if(e instanceof ParserConfigurationException) System.err.println("PARSER CONFIGURATION ERROR");
			e.printStackTrace();
			return false;
		}
		// normalize the document by removing errors,comments and/or merge newlines
	    doc.getDocumentElement().normalize();

	    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	    
	    NodeList nList = doc.getElementsByTagName("table");
	    
	    for (int temp = 0; temp < nList.getLength(); temp++) {

	        Node nNode = nList.item(temp);
	                	                
	        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	            Element eElement = (Element) nNode;

	            System.out.println(eElement.getElementsByTagName("elem").item(0).getTextContent());

	        }
	    }
	    
	    return true;
	}
}
