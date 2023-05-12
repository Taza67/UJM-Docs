package inside.communication;

import inside.Manager;

/**
 * Classe représentant le module d'envoi de données depuis le serveur
 * Il s'agit d'un thread qui envoi en permanence des messages au serveur sur des
 * modifications à prendre en compte
 *
 * @author mourtaza
 *
 */
public class TSender extends Thread {
	/**
	 * Manager de l'application
	 */
	private Manager manager;
	
	
	/**
	 * Instancie le module d'envoi de données
	 * @param c  Référence au client représentant l'application sur la communication
	 * @param ma Référence au manager de l'application
	 */
	public TSender(Manager ma) {
		manager = ma;
	}

	
	/**
	 * Fonction principale du thread
	 * Exécute le thread
	 */
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			manager.sendAction();
		}
	}
}
