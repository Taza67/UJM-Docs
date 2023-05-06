package interieur;

/**
 * Classe représentant le module qui permet la communication avec le serveurs
 * Il s'agit d'un thread qui communique en constamment avec le serveur
 * sur des modifications à envoyer/recevoir
 * @author mourtaza
 *
 */
public class TCommunication extends Thread implements IConfig {
	/**
	 * Client représentant l'application
	 */
	private Client client;
	/**
	 * Pseudo de l'utilisateur
	 */
	private String pseudo;
	/**
	 * Mot de passe de l'utilisateur
	 */
	private String password;
	/**
	 * Ensemble d'actions réalisées par l'utilisateur et à transmettre au serveur
	 */
	private Actions actions;
	
	
	/**
	 * Instancie un objet représentant le module de communication
	 * @param ps Pseudo à communiquer
	 * @param pass Mot de passe à communiquer
	 */
	public TCommunication(String ps ,String pass) {
		client = new Client(COMMUNICATION_PORT);
		pseudo = ps;
		password = pass;
	}
	
	
	/**
	 * Fonction principale du thread
	 * Exécute le thread
	 */
	public void run() {
		// Connexion refusée
		if (!client.connect(pseudo, password)) return;
		
		while (!Thread.currentThread().isInterrupted()) {			
			// On attend un code de continuation ou d'action du serveur
			int codeServer = client.waitInt();
			
			// Vérification
			if (codeServer == IMPOSSIBLE_CODE) return;
			
			// Une action a été demandée
			if (codeServer != NO_ACTION_CODE) realiseAskedAction(codeServer);
			
			// Action à envoyer
			if (actions.getSize() != 0)
				// S'il y a une action à envoyer
				sendFirstAction();
			else sendNoAction();
		}
	}
	
	/**
	 * Réalise une action demandée par le serveur
	 */
	private void realiseAskedAction(int codeActionServer) {
		
	}
	
	/**
	 * Envoie la première action de l'ensemble d'actions
	 */
	private void sendFirstAction() {
		
	}
	
	/**
	 * Envoie un code indiquant au serveur qu'il
	 * n'y a aucune action à réaliser
	 */
	private void sendNoAction() {
		
	}
}
