package PenetrationTestingAutomatized;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class APTSaveFilesClass {
	
	// ******************** FUNZIONE PER CREARE LA CARTELLA DOVE SALVARE IL RISULTATO DEL PENETRATION TEST ********************
	public static String createPenetrationTestFolder() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Path path;
		
		//get the directory in which the script has been executed for creating the folders
		//in which I will save all the files
		path = Paths.get(System.getProperty("user.dir"));

		String currentTestName = "";

		//ask and check for the current penetration test name [to not be already used]
		do {
			System.out.println("Enter the penetration test name: [needs to be unique, otherwise it will be re-asked!]:");
			try {
				currentTestName = br.readLine();
			} catch (IOException e) {
				System.err.println("FOLDER INPUT ERROR!");
				e.printStackTrace();
			}
			//sanity check of the input for security reasons
			path = Paths.get(System.getProperty("user.dir"), "PenetrationTestSaved", currentTestName.replaceAll("[^a-zA-Z0-9.-]", ""));
		} while (Files.exists(path));
		
		//creating the folder
		try {
			Files.createDirectories(path);
		} catch (IOException e) {
			System.err.println("ERROR WHILE CREATING THE FOLDER!");
			e.printStackTrace();
		}
		
		System.out.println("Folder created in this path: " + path.toString());
		
		return currentTestName;
	}
	
	// ******************** FINE FUNZIONE PER CREARE LA CARTELLA DOVE SALVARE IL RISULTATO DEL PENETRATION TEST ********************
	
	
}
