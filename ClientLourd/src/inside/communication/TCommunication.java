package inside.communication;

import javax.swing.undo.UndoManager;

import inside.Actions;
import inside.IConfig;
import inside.Manager;

/**
 * Classe représentant le module qui permet la communication avec le serveurs Il
 * s'agit d'un thread qui communique en constamment avec le serveur sur des
 * modifications à envoyer/recevoir
 *
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
	 *
	 * @param c  Référence au client représentant l'application sur la communication
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
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			// Action à envoyer
			if (actions.getSize() != 0) {
				// S'il y a une action à envoyer
				sendFirstAction();
			} else
				sendNoAction();

			System.err.println("- Envoi du code au serveur");
			
			// On attend un code de continuation ou d'action du serveur
			int codeServer = client.waitInt();

			System.err.println("- Réception d'un code du serveur");
			
			// Vérification
			if (codeServer == IMPOSSIBLE_CODE)
				return;

			// Une action a été demandée
			if (codeServer != NO_ACTION_CODE) realiseAskedAction(codeServer);
		}
	}

	/**
	 * Réalise une action demandée par le serveur
	 */
	private void realiseAskedAction(int codeActionServer) {
		int userId;
		switch (codeActionServer) {
		case NEW_DOCUMENT_REQUEST_CODE:
			// Chargement d'un doucument depuis le serveur
			int documentId = client.waitInt();
			
			// Prise en compte de l'id par le manager
			manager.setCurrentDocumentId(documentId);
			
			// Ajout d'une page dans le document
			manager.getCurrentDocument().addPage(0, "");
			
			// Application dans l'éditeur
			manager.putModification("", 0, 1);
			
			// On libère le blocus
			synchronized (manager) {
				manager.notifyAll();
			}
			
			return;
		case LOAD_DOCUMENT_REQUEST_CODE:
			// Chargement d'un document depuis le serveur
			int pageNumber = client.waitInt();
			int pageCount = client.waitInt();
			String content = client.waitString();
			
			System.err.println(pageNumber + " " + pageCount + " " + content);
			
			// Ajout des pages dans le document
			manager.getCurrentDocument().addPages(0, pageCount);
			
			// Application dans l'éditeur
			manager.putModification(content, pageNumber, pageCount);
			
			// Gestionnaire refaire/défaire
			manager.reinitUndoManager();
			
			// On libère le blocus
			synchronized (manager) {
				manager.notifyAll();
			}
			
			return;
		case SAVE_DOCUMENT_REQUEST_CODE:
			// On libère le blocus
			synchronized (manager) {
				manager.notifyAll();
			}
			
			return;
		case MODIFY_DOCUMENT_REQUEST_CODE:
			userId = client.waitInt();
			pageNumber = client.waitInt();
			pageCount = client.waitInt();
			content = client.waitString();

			// Application dans l'éditeur
			manager.putModification(content, pageNumber, pageCount);
			
			System.err.println("- Requête d'application de modification de " + "texte reçue depuis le serveur");
			
			return;
		case COLLABORATOR_MOVEMENT_APPLICATION_REQUEST_CODE:
			// Récupération de l'identité du collaborateur
			//
			// Récupération de la nouvelle position du collaborateur
			//
			// Application
			//
			System.err.println("- Requête d'application de déplacement " + "de collaborateur reçue depuis le serveur");
			
			return;
		default:
			System.err.println("- TCommunication#realiseAskedAction() -> " + "Attention ! Un code inconnu "
					+ codeActionServer + " a été récupéré ");
		}
	}

	/**
	 * Envoie la première action de l'ensemble d'actions
	 */
	private void sendFirstAction() {
		System.err.println("- Envoi de l'action " + actions.getHeadAction().getCode() + " en tête de file au serveur");
		client.sendInt(actions.getHeadAction().getCode());
		client.sendString(actions.getHeadAction().getMessage());
		actions.removeHeadAction();
	}

	/**
	 * Envoie un code indiquant au serveur qu'il n'y a aucune action à réaliser
	 */
	private void sendNoAction() {
		System.err.println("- Envoi d'un no action au serveur");
		client.sendInt(NO_ACTION_CODE);
	}
}
