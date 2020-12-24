package PenetrationTestingAutomatized;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class AutomatizedPenetrationTestingScript {
	public static String currentTestName;
	
	//TODO far funzionare anche se trasformo in jar questo progetto... (problema con script dentro al progetto)
	public static void main(String[] args) {
		System.out.println(System.getenv().toString());
		//funzione per creare la cartella dove salverò l'esito del penetration test
		currentTestName = GestisciSalvataggioFilePenetrationTest.creaCartellaPenetrationTest();
		
		//decisione per ip o dominio
		int atkType = sceltaAttacco();
		//oggetto che gestirà tutto il penetration test
		PenetrationTestWrapper ptw;
		
		switch(atkType) {
		case 1:
			//ip based
			ptw = ipBasedPTWIstance();
			ptw.runWithIP();
			break;
		case 2:
			//domain based
			ptw = domainBasedPTWIstance();
			ptw.runWithDomain();
			break;
		case 0:
			System.exit(0);
			break;
		default:
			//errore, esci
			System.exit(-1);				
		}
	}


	// **************************** METODI VARI ****************************
	private static int sceltaAttacco() {
		System.out.println("vuoi inserire un IP oppure un dominio?\n1)IP\n2)Dominio\n0)esci");
		Integer target = -1;
		try {
			target = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
		} catch (NumberFormatException | IOException e) {
			if(e instanceof NumberFormatException) System.out.println("ERRORE NEL PARSING A INTEGER");
			if(e instanceof IOException) System.out.println("ERRORE NELL'INPUT");
			e.printStackTrace();
		}
		return target;
	}
	
	private static PenetrationTestWrapper ipBasedPTWIstance() {
		InetAddress ip = null;
		try {
			System.out.println("Inserisci l'IP");
			ip = InetAddress.getByName(new BufferedReader(new InputStreamReader(System.in)).readLine());
		} catch (IOException e) {
			System.err.println("ERRORE NELL'INSERIMENTO DELL'IP!");
			e.printStackTrace();
		}
		//Creo e ritorno oggetto che mi gestirà il mio penetration test automatizzato
		return new PenetrationTestWrapper(currentTestName, ip);
	}

	private static PenetrationTestWrapper domainBasedPTWIstance() {
		String dominio = "";
		try {
			System.out.println("Inserisci il dominio");
			dominio = new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			System.err.println("ERRORE NELL'INSERIMENTO DEL DOMINIO!");
			e.printStackTrace();
		}
		//Creo e ritorno oggetto che mi gestirà il mio penetration test automatizzato
		return new PenetrationTestWrapper(currentTestName, dominio);		
	}

	// **************************** FINE METODI VARI ****************************
}
