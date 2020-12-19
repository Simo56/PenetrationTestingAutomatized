package PenetrationTestingAutomatized;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class AutomatizedPenetrationTestingScript {
	
	// **************************** INIZIO MAIN ****************************
	
	public static void main(String[] args) {
		
		//funzione per creare la cartella dove salverò l'esito del penetration test
		GestisciFilePenetrationTest.creaCartellaPenetrationTest();
		
		System.out.println("vuoi attaccare un IP oppure un dominio?\n1)IP\n2)Dominio\n0)esci");
		sceltaTargetAttacco();
		
	}

	// **************************** FINE MAIN ****************************
	
	
	// **************************** METODI VARI ****************************
	
	private static void sceltaTargetAttacco() {
		Integer target = -1;
		try {
			target = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
		} catch (NumberFormatException | IOException e) {
			if(e instanceof NumberFormatException) System.out.println("ERRORE NEL PARSING A INTEGER");
			if(e instanceof IOException) System.out.println("ERRORE NELL'INPUT");
			e.printStackTrace();
		}
		
		PenetrationTestWrapper ptw;
		
		switch(target) {
		
		//scelto ip
		case 1:
			InetAddress ip = null;
			try {
				System.out.println("Inserisci l'IP");
				ip = InetAddress.getByName(new BufferedReader(new InputStreamReader(System.in)).readLine());
			} catch (IOException e) {
				System.err.println("ERRORE NELL'INSERIMENTO DELL'IP!");
				e.printStackTrace();
			}
			//Creo oggetto che mi gestirà il mio penetration test automatizzato
			ptw = new PenetrationTestWrapper(ip);
			ptw.startWithIP();
			break;
			
		//scelto dominio
		case 2:
			String dominio = "";
			try {
				System.out.println("Inserisci il dominio");
				dominio = new BufferedReader(new InputStreamReader(System.in)).readLine();
			} catch (IOException e) {
				System.err.println("ERRORE NELL'INSERIMENTO DEL DOMINIO!");
				e.printStackTrace();
			}
			//Creo oggetto che mi gestirà il mio penetration test automatizzato
			ptw = new PenetrationTestWrapper(dominio);
			ptw.startWithDomain();
			break;
			
		//errore / uscita
		default:
			System.exit(-1);
		}
	}
	
	// **************************** FINE METODI VARI ****************************
}
