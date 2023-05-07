package inside.communication;

import inside.Actions;
import inside.IConfig;
import inside.Manager;

/**
 * Classe représentant le module qui permet la communication avec le serveurs
 * Il s'agit d'un thread qui communique en constamment avec le serveur
 * sur des modifications à envoyer/recevoir
 * @author mourtaza
 *
 */
public class TCommunication extends Thread implements IConfig {
	/**
	 * Client représentant l'application sur la communication
	 */
	private Client client;
	/**
	 * Ensemble d'actions réalisées par l'utilisateur et à transmettre au serveur
	 */
	private Actions actions;
	/**
	 * Manager de l'application
	 */
	private Manager manager;
	
	
	/**
	 * Instancie un objet représentant le module de communication
	 * @param c Référence au client représentant l'application sur la communication
	 * @param ac Référence à un ensemble d'actions réalisées par l'utilisateur
	 * @param ma Référence au manager de l'application
	 */
	public TCommunication(Client c, Actions ac, Manager ma) {
		client = c;
		actions = ac;
		manager = ma;
	}
	
	
	/**
	 * Fonction principale du thread
	 * Exécute le thread
	 */
	public void run() {		
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
		switch (codeActionServer) {
		case MODIFICATION_APPLICATION_REQUEST_CODE:
			// Récupération du texte depuis le serveur
			// String text = ""; // À CHANGER
			
			// Application
			// manager.putModification(text);
			System.err.println("- Requête d'application de modification de "
				+ "texte reçue depuis le serveur");
			return;
		case COLLABORATOR_MOVEMENT_APPLICATION_REQUEST_CODE:
			// Récupération de l'identité du collaborateur
			//
			// Récupération de la nouvelle position du collaborateur
			//
			// Application
			//
			System.err.println("- Requête d'application de déplacement "
				+ "de collaborateur reçue depuis le serveur");
			return;
		default:
			System.err.println("- TCommunication#realiseAskedAction() -> "
				+ "Attention ! Un code inconnu " + codeActionServer + " a été récupéré ");
		}
	}
	
	/**
	 * Envoie la première action de l'ensemble d'actions
	 */
	private void sendFirstAction() {
		System.err.println("- Envoi de l'action en tête de file au serveur");
	}
	
	/**
	 * Envoie un code indiquant au serveur qu'il
	 * n'y a aucune action à réaliser
	 */
	private void sendNoAction() {
		System.err.println("- Envoi d'un no action au serveur");
	}
}
