package PenetrationTestingAutomatized;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestisciSalvataggioFilePenetrationTest {
	
	// ******************** FUNZIONE PER CREARE LA CARTELLA DOVE SALVARE IL RISULTATO DEL PENETRATION TEST ********************
	public static String creaCartellaPenetrationTest() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Path path;
		
		// ricavo il percorso da cui viene eseguito lo script per poi creare le cartelle
		// in cui salverò il penetration test
		path = Paths.get(System.getProperty("user.dir"));

		String nomeCartella = "";

		// chiedo il nome del penetration test e controllo che non sia già esistente
		do {
			System.out.println("Inserisci il nome del penetration test [dev'essere unico, altrimenti verrà chiesto nuovamente!]:");
			try {
				nomeCartella = br.readLine();
			} catch (IOException e) {
				System.err.println("ERRORE IN INPUT DEL NOME DELLA CARTELLA!");
				e.printStackTrace();
			}
			// faccio anche sanificazione dell'input per evitare directory trasversal!
			path = Paths.get(System.getProperty("user.dir"), "PENETRATION_TEST_SALVATI", nomeCartella.replaceAll("[^a-zA-Z0-9.-]", "_"));
		} while (Files.exists(path));
		
		//creo la cartella
		try {
			Files.createDirectories(path);
		} catch (IOException e) {
			System.err.println("ERRORE DURANTE LA CREAZIONE DELLA CARTELLA!");
			e.printStackTrace();
		}
		
		System.out.println("cartella creata in questo percorso: " + path.toString());
		
		return nomeCartella;
	}
	
	// ******************** FINE FUNZIONE PER CREARE LA CARTELLA DOVE SALVARE IL RISULTATO DEL PENETRATION TEST ********************
	
	
}
