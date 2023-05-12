package inside;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.undo.UndoManager;

import inside.communication.Client;
import inside.communication.TReceiver;
import inside.communication.TSender;
import inside.utilities.PDFUtilities;
import inside.utilities.Tuple;
import outside.userInterface.JEditorPanePerso;

/**
 * Classe représentant le module qui gère toute l'application C'est-à-dire
 * qu'elle contient toutes les méthodes principales qui appellent les autres
 * méthodes
 *
 * @author mourtaza
 *
 */
public class Manager implements IConfig {
	/**
	 * Threads de communication avec le serveur de données
	 */
	private TSender sending;
	private TReceiver receiving;
	/**
	 * Client représentant l'utilisateur sur la communication TCP
	 */
	private Client client;
	/**
	 * Éditeur de texte
	 */
	private JEditorPanePerso editor;
	/**
	 * Nom du document ouvert
	 */
	private String currentDocumentName;
	/**
	 * Identifiant du document ouvert
	 */
	private int currentDocumentId;
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
	 * Queue qui permet de stocker les modifications 
	 * apportées par l'utilisateur
	 */
	private BlockingQueue<Action> modificationBuffer;
	

	/**
	 * Instancie un objet représentant le module de gestion
	 *
	 * @param ed Référence au JPanelEditor contenant le document édité
	 * @param pi Référence au JLabel indiquant le nombre de pages
	 * @param pni Référence au JLabel indiquant le numéro de page
	 * @param eb Référence à un objet que l'éditeur attend à chaque fois qu'il envoie une action
	 */
	public Manager(JEditorPanePerso ed, JLabel pi, JLabel pni) {
		client = new Client(COMMUNICATION_PORT);
		modificationBuffer = new LinkedBlockingQueue<Action>();
		receiving = new TReceiver(client, this);
		sending = new TSender(this);
		editor = ed;
		pageIndicator = pi;
		pageNumberIndicator = pni;
		listDocuments = new ArrayList<>();
	}

	
	/**
	 * Retourne le document actuellement ouvert
	 */
	synchronized public Document getCurrentDocument() {
		return currentDocument;
	}
	
	/**
	 * Retourne la liste des documents par tuple (id,docuument)
	 * @return Liste des documents
	 */
	synchronized public List<Tuple> getListDocuments() {
		return listDocuments;
	}
	
	/**
	 * Retourne le numéro de page
	 * @return Numéro de page
	 */
	synchronized public int getCurrentPageNumber() {
		return currentPageNumber;
	}
	
	/**
	 * Modifie la valeur de l'identifiant du document courant
	 * @param did Valeur à affecter
	 */
	synchronized public void setCurrentDocumentId(int did) {
		currentDocumentId = did;
		if (currentDocument != null)
			currentDocument.setDocumentId(did);
	}
	
	/**
	 * Envoie la première action du buffer de modification au serveur s'il y en a
	 * Un code NO_ACTION sinon
	 */
	synchronized public void sendAction() {
		if (modificationBuffer.isEmpty())
			client.sendInt(NO_ACTION_CODE);
		else {
			try {
				Action a = modificationBuffer.take();
				
				System.err.println("- Action " + a.getCode() + " - " + a.getMessage());
				client.sendInt(a.getCode());
				client.sendString(a.getMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	
	/**
	 * Réinitialise le gestionnaire refaire/défaire de l'éditeur
	 */
	synchronized public void reinitUndoManager() {
		editor.setUndoManager(new UndoManager());
	}
	
	/**
	 * Connecte l'application au serveur de données
	 *
	 * @param ps    Pseudo à communiquer pour l'authentification
	 * @param pass  Mot de passe à communiquer pour l'authentification
	 * @param isNew true si c'est un nouvel utilisateur, false sinon
	 */
	synchronized public boolean connectApplication(String ps, String pass, boolean isNew) {
		// Connexion refusée
		if (!client.connect(ps, pass, isNew))
			return false;

		// Récupération des documents
		if (!isNew)
			loadListDocuments();

		return true;
	}

	/**
	 * Déconnecte l'application au serveur de données
	 */
	synchronized public void disconnectApplication() {
		sending.interrupt();
		receiving.interrupt();
		client.disconnect();
	}

	/**
	 * Récupère tous les documents de l'utilisateur sur le serveur
	 */
	synchronized public void loadListDocuments() {
		String listDocumentsString = client.waitString();
		String[] listDocumentsSplit = listDocumentsString.split("\b");

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
		sending.start();
		receiving.start();
	}

	/**
	 * Ajoute le gestionnaire de modification à l'editeur
	 */
	synchronized public void addModificationListenerToEditor() {
		editor.addDocumentListener();
	}
	
	/**
	 * Retire le gestionnaire de modification de l'éditeur
	 */
	synchronized public void removeModificationListenerFromEditor() {
		editor.removeDocumentListener();
	}
	
	/**
	 * Demande la création d'un nouveau document
	 *
	 * @param newDocument Nom à donner au nouveau document
	 * @param isOnline    true si l'utilisateur est connecte, false sinon
	 */
	synchronized public void askNewDocument(String newDocument, boolean isOnline) {
		if (isOnline) {
			Action a = new Action(NEW_DOCUMENT_REQUEST_CODE, newDocument);
			
			// Ajout de l'action
			try {
				modificationBuffer.put(a);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Représentation du document
			currentDocumentName = newDocument;
			currentDocument = new Document(currentDocumentName);
			
			// S'il s'agit du premier document,
			// la communication n'aura pas encore démarré
			if (!firstDocumentOpen) {
				beginCommunication();
				firstDocumentOpen = true;
			}
			
			// On bloque le manager et donc tout l'éditeur
			// lorsqu'on attend la réponse du serveur
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			currentDocument = new Document(currentDocumentName);
			currentPageNumber = 0;
			
			currentDocument.addPages(0, 1);
			
			// Initialisation de l'éditeur
			editor.setText("");
		}
		
		// Gestionnaire refaire/défaire
		editor.setUndoManager(new UndoManager());
	}

	/**
	 * Demande l'ouverture d'un document
	 *
	 * @param doc Couple (id du document, nom du document)
	 */
	synchronized public void askLoadDocument(Tuple doc) {
		Action a = new Action(LOAD_DOCUMENT_REQUEST_CODE, Integer.toString((int)doc.getFIRST()));
		
		// Ajout de l'action
		try {
			modificationBuffer.put(a);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Représentation du document
		currentDocumentId = (int)doc.getFIRST();
		currentDocumentName = (String)doc.getSECOND();
		currentDocument = new Document(currentDocumentId, currentDocumentName);
		
		// S'il s'agit du premier document,
		// la communication n'aura pas encore démarré
		if (!firstDocumentOpen) {
			beginCommunication();
			firstDocumentOpen = true;
		}
		
		// On bloque le manager et donc tout l'éditeur
		// lorsqu'on attend la réponse du serveur
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Demande la sauvegarde du document
	 */
	synchronized public void askSaveDocument() {
		Action a = new Action(SAVE_DOCUMENT_REQUEST_CODE, "SAVE\b" + currentDocumentId);
		
		// Ajout de l'action
		modificationBuffer.add(a);
		
		// On se met en attente de la réponse du serveur
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Demande l'application de la modification du document réalisée par
	 * l'utilisateur
	 * @param actionCode Code de l'action (DEL, ADD)
	 * @param message Message à envoyer
	 */
	synchronized public void askModifyDocument(int actionCode, String message) {
		Action a = new Action(actionCode, message);
		
		// Ajout de l'action
		try {
			modificationBuffer.put(a);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Applique la modification du document reçue depuis le serveur
	 *
	 * @param text Contenu à mettre dans l'éditeur
	 */
	synchronized public void putModification(String text, int pageNumber, int pageCount, int cursorPosition) {
		// Document
		currentPageNumber = pageNumber;
		currentDocument.setPage(pageNumber, text);
		
		// Éditeur
		removeModificationListenerFromEditor();
		
		editor.setText(text);
		editor.setCaretPosition(cursorPosition);
		pageIndicator.setText(pageNumber + " page" + (pageNumber > 1 ? "s" : ""));
		pageNumberIndicator.setText(pageNumber + " / " + pageCount + "p");
		
        addModificationListenerToEditor();
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

		pageIndicator
				.setText(currentDocument.getPageCount() + " page" + (currentDocument.getPageCount() > 1 ? "s" : ""));
		pageNumberIndicator.setText((currentPageNumber + 1) + " / " + currentDocument.getPageCount() + "p");
	}

	/**
	 * Déplace l'éditeur vers la page associé au numéro
	 *
	 * @param pageNumber Numéro de page
	 */
	synchronized public void goToPage(int pageNumber) {
		if (pageNumber < 0 || pageNumber >= currentDocument.getPageCount())
			return;

		// Application des modifications
		currentDocument.setPage(currentPageNumber, editor.getText());

		// Déplacement
		currentPageNumber = pageNumber;
		editor.setText(currentDocument.getPage(pageNumber));

		pageIndicator
				.setText(currentDocument.getPageCount() + " page" + (currentDocument.getPageCount() > 1 ? "s" : ""));
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
