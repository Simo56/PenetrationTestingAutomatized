package PenetrationTestingAutomatized;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PenetrationTestWrapper {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private Path path;
	
	public PenetrationTestWrapper() {
		//funzione per creare la cartella dove salverò l'esito del penetration test
		try {
			creaCartellaPenetrationTest();
		} catch (IOException e) {
			System.err.println("ERRORE IN I/O");
			e.printStackTrace();
		}

	}

	private void creaCartellaPenetrationTest() throws IOException{
		// ricavo il percorso da cui viene eseguito lo script per poi creare le cartelle
		// in cui salverò il penetration test
		path = Paths.get(System.getProperty("user.dir")+"/PenetrationTestSalvati");
		
		String nomeCartella = "";
		// chiedo il nome del penetration test e controllo che non sia già esistente
		do {
			System.out.println("Inserisci il nome del penetration test:");
			nomeCartella = br.readLine();
			//faccio anche sanificazione dell'input per evitare directory trasversal!
			path = Paths.get(System.getProperty("user.dir") + "/PENETRATION_TEST_SALVATI/" + nomeCartella.replaceAll("[^a-zA-Z0-9.-]", "_"));
		} while (Files.exists(path));
		
		Files.createDirectories(path);
		System.out.println("cartella creata in questo percorso:" + path.toString());
	}

}
