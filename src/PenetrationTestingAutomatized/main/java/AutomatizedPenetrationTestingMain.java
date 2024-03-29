package PenetrationTestingAutomatized.main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AutomatizedPenetrationTestingMain {
	// protected because other classes needs to see this variable to save their
	// files
	protected static Path currentTestPath;

	public static void main(String[] args) {
		// Create the folder in which i will save all the other files
		createPenetrationTestFolder();

		// TODO choose scanning tool
		// int scanningTool = chooseScanningTool();
		// TODO choose exploitation tool
		// int exploitationTool = chooseExploitationTool();

		// choose if it's an IP based or Domain based penetration test
		int atkType = chooseAtkType();
		// this is the object that will handle all the penetration test
		PenetrationTestWrapper ptw;

		switch (atkType) {
			case 0:
				System.exit(0);
				break;
			case 1:
				// ip based
				ptw = ipBasedPTWIstance();
				ptw.runWithIP();
				break;
			case 2:
				// domain based
				ptw = domainBasedPTWIstance();
				try {
					ptw.runWithDomain();
				} catch (Exception e) {
					if (e instanceof UnknownHostException)
						System.err.println("UnknownHostException ERROR DURING EXECUTION OF THE SUBPROCESS");
					if (e instanceof MalformedURLException)
						System.err.println("MalformedURLException ERROR DURING EXECUTION OF THE SUBPROCESS");
					e.printStackTrace();
				}
				break;
			default:
				// error, exit
				System.exit(-1);
		}
	}

	private static int chooseAtkType() {
		System.out.println("Do you want to attack an IP or a Domain?\n1)IP\n2)Domain\n0)Exit");
		Integer target = -1;
		try {
			target = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
		} catch (NumberFormatException | IOException e) {
			if (e instanceof NumberFormatException)
				System.out.println("ERROR WHILE PARSING THE INTEGER");
			if (e instanceof IOException)
				System.out.println("INPUT ERROR");
			e.printStackTrace();
		}
		return target.intValue();
	}

	private static PenetrationTestWrapper ipBasedPTWIstance() {
		InetAddress ip = null;
		try {
			System.out.println("Enter the IP: ");
			ip = InetAddress.getByName(new BufferedReader(new InputStreamReader(System.in)).readLine());
		} catch (IOException e) {
			System.err.println("IP INPUT ERROR!");
			e.printStackTrace();
		}
		// create and return the object that will handle my automatized penetration test
		return new PenetrationTestWrapper(ip);
	}

	private static PenetrationTestWrapper domainBasedPTWIstance() {
		String domain = "";
		try {
			System.out.println("Enter the Domain: ");
			domain = new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			System.err.println("ERROR WHILE ENTERING THE DOMAIN");
			e.printStackTrace();
		}
		// create and return the object that will handle my automatized penetration test
		return new PenetrationTestWrapper(domain);
	}

	public static String createPenetrationTestFolder() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// get the directory in which the script has been executed for creating the
		// folders
		// in which I will save all the files
		Path path = Paths.get(System.getProperty("user.dir"));

		String currentTestName = "";

		// ask and check for the current penetration test name [to not be already used]
		do {
			System.out
					.println("Enter the penetration test name: [needs to be unique, otherwise it will be re-asked!]:");
			try {
				currentTestName = br.readLine();
			} catch (IOException e) {
				System.err.println("FOLDER INPUT ERROR!");
				e.printStackTrace();
			}
			// sanity check of the input for security reasons
			path = Paths.get(System.getProperty("user.dir"), "PenetrationTestSaved",
					currentTestName.replaceAll("[^a-zA-Z0-9.-]", ""));
		} while (Files.exists(path));

		// creating the folder
		try {
			Files.createDirectories(path);
		} catch (IOException e) {
			System.err.println("ERROR WHILE CREATING THE FOLDER!");
			e.printStackTrace();
		}

		System.out.println("Folder created in this path: " + path.toString());

		// actual path of the penetration test
		currentTestPath = path;

		return currentTestName;
	}
}
