package inside;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.undo.UndoManager;
import javax.swing.JLabel;

import inside.communication.Client;
import inside.communication.TCommunication;
import inside.utilities.PDFUtilities;
import inside.utilities.Tuple;
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
	 * Référence au JLabel indiquant le nombre de pages
	 */
	private JLabel pageIndicator;
	/**
	 * Référence au JLabel indiquant le numéro de page
	 */
	private JLabel pageNumberIndicator;
	/**
	 * Variable qui indique si un premier document a déjà été ouvert
	 */
	private boolean firstDocumentOpen = false;
	/**
	 * Listes des identifiants et noms des documents de l'utilisateur
	 */
	private List<Tuple> listDocuments;

	
	/**
	 * Instancie un objet représentant le module de gestion
	 * @param ed Référence au JPanelEditor contenant le document édité
	 */
	public Manager(JEditorPanePerso ed, JLabel pi, JLabel pni) {
		client = new Client(COMMUNICATION_PORT);
		actions = new Actions();
		communication = new TCommunication(client, actions, this);
		editor = ed;
		pageIndicator = pi;
		pageNumberIndicator = pni;
		listDocuments = new ArrayList<Tuple>();
	}
	
	/**
	 * Retourne la liste des documents <id, nom>
	 * @return Liste des documents
	 */
	synchronized public List<Tuple> getListDocuments() {
		return listDocuments;
	}
	
	/**
	 * Connecte l'application au serveur de données
	 * @param ps Pseudo à communiquer pour l'authentification
	 * @param pass Mot de passe à communiquer pour l'authentification
	 * @param isNew true si c'est un nouvel utilisateur, false sinon
	 */
	synchronized public boolean connectApplication(String ps, String pass, boolean isNew) {
		// Connexion refusée
		if (!client.connect(ps, pass, isNew)) return false;
		
		// Connexion accordée
		pseudo = ps;
		password = pass;
		
		// Récupération des documents
		if (!isNew)
			loadListDocuments();
		
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
	 * Récupère tous les documents de l'utilisateur sur le serveur
	 */
	synchronized public void loadListDocuments() {
		String listDocumentsString = client.waitString();
		String[] listDocumentsSplit = listDocumentsString.split("\0");
		
		System.out.println(listDocumentsString);
		
		// Parcours de la liste récupérée depuis le serveur
		for (int i = 0; i < listDocumentsSplit.length; i += 2) {
			int id = Integer.parseInt(listDocumentsSplit[i]);
			String name = listDocumentsSplit[i + 1];
			listDocuments.add(new Tuple(id, name));
		}
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
			
			if (!firstDocumentOpen) {
				beginCommunication();
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				firstDocumentOpen = true;
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
		if (!firstDocumentOpen) {
			beginCommunication();
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			firstDocumentOpen = true;
		}
		currentDocumentName = documentName;
		// ATTENTION
		currentDocument = null;		// IL FAUDRA RÉCUPÉRER LE DOCUMENT D'UNE CERTAINE MANIÈRE
		pageIndicator.setText("1 page");
		pageNumberIndicator.setText("1 / 1 page");
		
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
		pageIndicator.setText("1 page");
		pageNumberIndicator.setText("1 / 1p");
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
		
		pageIndicator.setText(currentDocument.getPageCount() + " page" + (currentDocument.getPageCount() > 1 ? "s" : ""));
		pageNumberIndicator.setText((currentPageNumber + 1) + " / " + currentDocument.getPageCount() + "p");
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
		
		pageIndicator.setText(currentDocument.getPageCount() + " page" + (currentDocument.getPageCount() > 1 ? "s" : ""));
		pageNumberIndicator.setText((currentPageNumber + 1) + " / " + currentDocument.getPageCount() + "p");
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
