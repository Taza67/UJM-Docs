package inside.communication;

import javax.swing.*;
import inside.IConfig;
import inside.Manager;

/**
 * Classe représentant le module de réception de données depuis le serveur
 * Il s'agit d'un thread qui attend en permanence des messages depuis le serveur sur des
 * modifications à prendre en compte
 *
 * @author mourtaza
 *
 */
public class TReceiver extends Thread implements IConfig {
	/**
	 * Client représentant l'application sur la communication
	 */
	private Client client;
	/**
	 * Manager de l'application
	 */
	private Manager manager;
	
	
	/**
	 * Instancie le module de réception de données
	 * @param c  Référence au client représentant l'application sur la communication
	 * @param ma Référence au manager de l'application
	 */
	public TReceiver(Client c, Manager ma) {
		client = c;
		manager = ma;
	}
	
	/**
	 * Fonction principale du thread
	 * Exécute le thread
	 */
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			// Attente d'un code depuis le serveur
			int codeServer = client.waitInt();
			
			// Réalisation d'une action
			if (codeServer != NO_ACTION_CODE) realiseAskedAction(codeServer);
		}
	}
	
	/**
	 * Réalise une action demandée par le serveur
	 */
	private void realiseAskedAction(int codeActionServer) {
		int userId, positionCursor;
		
		System.err.println("- Réception d'un code " + codeActionServer + " du serveur");
		switch (codeActionServer) {
		case NEW_DOCUMENT_REQUEST_CODE:
			// Chargement d'un doucument depuis le serveur
			int documentId = client.waitInt();
			
			// Prise en compte de l'id par le manager
			manager.setCurrentDocumentId(documentId);
			
			// Ajout d'une page dans le document
			manager.getCurrentDocument().addPage(0, "");
			
			// Application dans l'éditeur
            SwingUtilities.invokeLater(() -> {
                manager.putModification("", 0, 1, 0);
            });
			
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
            SwingUtilities.invokeLater(() -> {
                manager.putModification(content, pageNumber, pageCount, 0);
                // Gestionnaire refaire/défaire
    			manager.reinitUndoManager();
            });
			
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
			positionCursor = client.waitInt();
			
			System.err.println(userId + " " + pageNumber + " " + pageCount + " \"" + content + "\" " + positionCursor);
			
			// Application dans l'éditeur
            SwingUtilities.invokeLater(() -> {
                manager.removeModificationListenerFromEditor();
                manager.putModification(content, pageNumber, pageCount, positionCursor);
                manager.addModificationListenerToEditor();
            });
			
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
}
