package connexion;

public class CommunicationThread extends Thread{

/**
 * Module implémentant le thread responsable de la communication entre
 * le server websocket et le server TCP
 * 	@author Hany
 */

	/*
	 * Server contenant les flux d'entrées et sorties
	 */
	private HeavyServer server;

	/*
	 * Gestionnaire des documents
	 */
	private DocManager manager;


	public CommunicationThread(HeavyServer s, DocManager m) {
		this.server = s;
		this.manager = m;
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {

		}

	}
}
