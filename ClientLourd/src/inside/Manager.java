package inside;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.undo.UndoManager;

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
	 * Document ouvert
	 */
	private Document currentDocument;
	/**
	 * Numéro de la page ouverte
	 */
	private int currentPageNumber;
	
	
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
		if (!client.connect(ps, pass)) return false;
		
		// Connexion accordée
		pseudo = ps;
		password = pass;
		
		return true;
	}
	
	/**
	 * Déconnecte l'application au serveur de données
	 */
	synchronized public void disconnectApplication() {
		communication.interrupt();
		client.disconnect();
	}
	
	
	/**
	 * Débute l'échange de données entre le serveur et le client
	 */
	synchronized public void beginCommunication() {
		communication.start();
	}
	
	/**
	 * Demande la création d'un nouveau document
	 * @param newDocument Nom à donner au nouveau document
	 * @param isOnline true si l'utilisateur est connecte, false sinon
	 */
	synchronized public void askNewDocument(String newDocument, boolean isOnline) {
		if (isOnline) {
			actions.addAction(NEW_DOCUMENT_REQUEST_CODE, newDocument);
			
			beginCommunication();
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		currentDocumentName = newDocument;
		currentDocument = new Document(newDocument);
		currentDocument.addPage(0, "");
		currentPageNumber = 0;
		editor.setText("");
		editor.setUndoManager(new UndoManager());
		
		System.err.println("- Nom du document : " + currentDocumentName);
	}
	
	
	
	/**
	 * Demande l'ouverture d'un document
	 * @param documentName Nom du document à ouvrir
	 */
	synchronized public void askLoadDocument(String documentName) {
		actions.addAction(LOAD_DOCUMENT_REQUEST_CODE, documentName);
		// Récupération du document ///////////////////////////////////////////
		//
		//
		currentDocumentName = documentName;
		// ATTENTION
		currentDocument = null;		// IL FAUDRA RÉCUPÉRER LE DOCUMENT D'UNE CERTAINE MANIÈRE
		
		System.err.println("- Nom du document : " + currentDocumentName);
	}
	
	/**
	 * Demande la sauvegarde du document
	 */
	synchronized public void askSaveDocument() {
		actions.addAction(SAVE_DOCUMENT_REQUEST_CODE, "sauvegarde");
	}
	
	/**
	 * Demande l'application de la modification du document réalisée par l'utilisateur
	 */
	synchronized public void askModifyDocument() {
		actions.addAction(MODIFY_DOCUMENT_REQUEST_CODE, "modification");
	}
	
	/**
	 * Applique la modification du document reçue depuis le serveur
	 * @param text Contenu à mettre dans l'éditeur
	 */
	synchronized public void putModification(String text) {
		editor.setText(text); // NE MARCHE PAS
		// ATTENTION, GESTION DU DOCUMENT SOUS FORME DE PAGES
	}
	
	/**
	 * Exporte le document sous format pdf
	 */
	synchronized public void exportDocument() {
		// Application des modifications
		currentDocument.setPage(currentPageNumber, editor.getText());
		
		// Récupération du document en un seul morceau
		String allInOne = currentDocument.getAllDocument();
		
		// Création du sélecteur de fichier
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Spécifiez un fichier pour sauvegarder");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
        	// Attente du choix de l'utilisateur
            File fileToSave = fileChooser.getSelectedFile();
            
            // Sauvegarde
            PDFUtilities.exportToPDF(allInOne, fileToSave.getAbsolutePath());
        }
	}
	
	/**
	 * Ajoute une nouvelle page au document
	 */
	synchronized public void addNewPage() {
		// Application des modifications
		currentDocument.setPage(currentPageNumber, editor.getText());
		
		currentPageNumber++;
		currentDocument.addPage(currentPageNumber, "");
		editor.setText("");
	}
	
	/**
	 * Déplace l'éditeur vers la page associé au numéro
	 * @param pageNumber Numéro de page
	 */
	synchronized public void goToPage(int pageNumber) {
		if (pageNumber < 0 || pageNumber >= currentDocument.getPageCount()) return;
		
		// Application des modifications
		currentDocument.setPage(currentPageNumber, editor.getText());
		
		// Déplacement
		currentPageNumber = pageNumber;
		editor.setText(currentDocument.getPage(pageNumber));
	}
	
	/**
	 * Avance d'une page
	 */
	synchronized public void moveForward() {
		goToPage(currentPageNumber + 1);
	}
	
	/**
	 * Recule d'une page
	 */
	synchronized public void moveBackward() {
		goToPage(currentPageNumber - 1);
	}
}
