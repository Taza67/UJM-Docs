package interieur;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Classe représentant un client vis-à-vis de la communication TCP
 * @author mourtaza
 *
 */
public class Client {
	/**
	 * Hôte du serveur
	 */
	public static final String HOTE = "localhost";
	/**
	 * Socket du client
	 */
	private Socket cliSoc;
	/**
	 * Port de connexion
	 */
	private int port;
	/**
	 * Flux d'entrée serveur -> client
	 */
	private DataInputStream entree;
	/**
	 * Flux de sortie client -> serveur
	 */
	private DataOutputStream sortie;
	
	
	/**
	 * Instancie un objet représentant un client
	 */
	public Client(int p) {
		port = p;
	}
	
	/**
	 * Initie la connexion avec le serveur
	 * @return true si la connexion s'est réalisée, false sinon
	 */
	public boolean initierConnexion(String pseudo, String motDePasse) {
		try {
			// Connexion au serveur
			cliSoc = new Socket(HOTE, port);
			System.err.println("- Client connecté");
			
			// Initialisation des flux d'entrée/sortie
			entree = new DataInputStream(cliSoc.getInputStream());
			sortie = new DataOutputStream(cliSoc.getOutputStream());
			System.err.println("- Flux d'entrée/sortie initialisés");
			
			// Récupération de la notice d'utilisation
			sortie.writeUTF(pseudo);
			sortie.writeUTF(motDePasse);
			System.err.println("- Envoi des identifiants de connexion");
			
			// Attente de la réponse
			int reponse = entree.readInt();
			if (reponse == 0) {
				System.err.println("- Échec de la connexion");
				return false;
			}
			
			System.out.println("- ");
		} catch (IOException e) {
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
}
