package inside;

import inside.communication.Client;
import inside.communication.TCommunication;
import inside.utilities.PDFUtilities;
import outside.userInterface.JEditorPanePerso;

/**
 * Classe représentant le module qui gère toute l'application
 * C'est-à-dire qu'elle contient toutes les méthodes principales
 * qui appellent les autres méthodes
 * @author mourtaza
 *
 */
public class Manager implements IConfig {
	/**
	 * Thread de communication avec le serveur de données
	 */
	private TCommunication communication;
	/**
	 * Pseudo de l'utilisateur
	 */
	private String pseudo;
	/**
	 * Mot de passe de l'utilisateur
	 */
	private String password;
	/**
	 * Client représentant l'utilisateur sur la communication TCP
	 */
	private Client client;
	/**
	 * Ensemble d'actions réalisées par l'utilisateur et à transmettre au serveur
	 */
	private Actions actions;
	/**
	 * Éditeur de texte
	 */
	private JEditorPanePerso editor;
	/**
	 * Nom du document ouvert
	 */
	private String currentDocumentName;
	
	
	/**
	 * Instancie un objet représentant le module de gestion
	 * @param ed Référence au JPanelEditor contenant le document édité
	 */
	public Manager(JEditorPanePerso ed) {
		client = new Client(COMMUNICATION_PORT);
		actions = new Actions();
		communication = new TCommunication(client, actions, this);
		editor = ed;
	}
	
	
	/**
	 * Connecte l'application au serveur de données
	 * @param ps Pseudo à communiquer pour l'authentification
	 * @param pass Mot de passe à communiquer pour l'authentification
	 */
	synchronized public boolean connectApplication(String ps, String pass) {
		// Connexion refusée
		if (!client.connect(pseudo, password)) return false;
		
		// Connexion accordée
		pseudo = ps;
		password = pass;
		
		return true;
	}
	
	/**
	 * Débute l'échange de données entre le serveur et le client
	 */
	synchronized public void beginCommunication() {
		communication.start();
	}
	
	/**
	 * Demande la création d'un nouveau document
	 * @param documentName Nom du nouveau document
	 */
	synchronized public void askNewDocument(String documentName) {
		actions.addAction(NEW_DOCUMENT_REQUEST_CODE);
		// Récupération du document ///////////////////////////////////////////
		//
		//
		currentDocumentName = documentName;
		System.err.println("- Nom du document : " + currentDocumentName);
	}
	
	/**
	 * Demande l'ouverture d'un document
	 * @param documentName Nom du document à ouvrir
	 */
	synchronized public void askLoadDocument(String documentName) {
		actions.addAction(LOAD_DOCUMENT_REQUEST_CODE);
		// Récupération du document ///////////////////////////////////////////
		//
		//
		currentDocumentName = documentName;
		System.err.println("- Nom du document : " + currentDocumentName);
	}
	
	/**
	 * Demande la sauvegarde du document
	 */
	synchronized public void askSaveDocument() {
		actions.addAction(SAVE_DOCUMENT_REQUEST_CODE);
	}
	
	/**
	 * Demande l'application de la modification du document réalisée par l'utilisateur
	 */
	synchronized public void askModifyDocument() {
		actions.addAction(MODIFY_DOCUMENT_REQUEST_CODE);
	}
	
	/**
	 * Applique la modification du document reçue depuis le serveur
	 * @param text Contenu à mettre dans l'éditeur
	 */
	synchronized public void putModification(String text) {
		editor.setText(text);
	}
	
	/**
	 * Exporte le document sous format pdf
	 * @param filePath Chemin du fichier pdf
	 */
	synchronized public void exportDocument(String filePath) {
		PDFUtilities.exportToPDF(editor.getText(), filePath);
	}
}
