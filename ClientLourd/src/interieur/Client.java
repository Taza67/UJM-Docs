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
public class Client implements IConfig {
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
	private DataInputStream in;
	/**
	 * Flux de sortie client -> serveur
	 */
	private DataOutputStream out;
	
	
	/**
	 * Instancie un objet représentant un client
	 * @param p Port de connexion
	 */
	public Client(int p) {
		port = p;
	}
	
	
	/**
	 * Initie la connexion avec le serveur
	 * @return true si la connexion s'est réalisée, false sinon
	 */
	public boolean connect(String pseudo, String password) {
		try {
			// Connexion au serveur
			cliSoc = new Socket(COMMUNICATION_HOST, port);
			System.err.println("- Client connecté");
			
			// Initialisation des flux d'entrée/sortie
			in = new DataInputStream(cliSoc.getInputStream());
			out = new DataOutputStream(cliSoc.getOutputStream());
			System.err.println("- Flux d'entrée/sortie initialisés");
			
			out.writeUTF(pseudo);
			out.writeUTF(password);
			System.err.println("- Envoi des identifiants de connexion");
			
			// Attente de la réponse
			int response = in.readInt();
			if (response == 0) {
				// Refus
				System.err.println("- Échec de la connexion (Connexion refusée par le serveur)");
				
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("- Client#connect() -> Échec de la connexion (Connexion interrompue par une erreur IO)");
			
			return false;
		}
		
		// Accord
		System.out.println("- Succès de la connexion");
		
		return true;
	}
	
	/**
	 * Déconnecte le client
	 */
	public void disconnect() {
		try {
			in.close();
			out.close();
			cliSoc.close();
			System.out.println("- Client déconnecté");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("- Client#disconnect() -> Échec de la déconnexion (Déconnexion interrompue par une erreur IO)");
		}
	}
	
	/**
	 * Attend un entier depuis le serveur
	 * @return Entier récupéré sur le flux d'entrée
	 */
	public int waitInt() {
		try {
			// Récupération d'un entier sur le flux d'entrée
			int response = in.readInt();
			
			// On vérifie qu'il ne s'agit pas de l'entier impossible
			if (response == IMPOSSIBLE_CODE)
				// Avertissement
				System.err.println("- Client#WaitInt() -> Attention ! L'entier impossible " +
					IMPOSSIBLE_CODE + " a été récupéré !");
			
			return response;	
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("- Client#waitInt() -> "
				+ "Échec de la récupération de l'entier (Interruption par une erreur IO)");
		}
		
		// On suppose dans notre cas que cet entier est impossible
		// à récupérer sur le flux d'entrée
		return IMPOSSIBLE_CODE; 
	}
	
	public static void main(String[] args) {
		Client c = new Client(COMMUNICATION_PORT);
		c.connect("tom","tomlerat");
	}
}
