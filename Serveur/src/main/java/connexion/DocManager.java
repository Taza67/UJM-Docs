package connexion;

import connexion.document.Document;

/**
 * Cette classe représente le gestionnaire des documents qui permet la modification des documents d'une façon synchronisée
 * @author BAYAZID Hany
 */


public class DocManager {

	/**
	 * Thread de communication
	 */
	private CommunicationThread commThread;

	/**
	 * Document à modifier
	 */
	private Document d;
	
	/**
	 * Le server lourd 
	 * 
	 */
	private HeavyServer serv;

	// un doc manager sera crée quand un gars va essayer de crée ou de prendre accès d'un document

	public DocManager(Document doc, HeavyServer s) {
		d = doc;
		serv = s;
		commThread = new CommunicationThread(s, this);
	}
	

	public Document getD() {return d;}

	public HeavyServer getHeavyServer () {return serv;}

	public synchronized void startCommunication() {
		commThread.start();
	}

	/**
	 * Methode qui gère l'ajout ou la suppression de contenu dans le document
	 */
	public synchronized void processMessage(String message) {
		d.updatePages(message);
	}

}
