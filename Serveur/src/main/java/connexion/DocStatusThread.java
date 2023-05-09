package connexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import connexion.document.Document;
import connexion.document.Page;
import connexion.document.Word;

public class DocStatusThread extends Thread{
	/**
	 * Port de connexion
	 */
	private final int PORT = 8080;
	/**
	 * Socket du server
	 */
	private ServerSocket servSoc;
	/**
	 * Socket du client
	 */
	private Socket cliSoc;
	/**
	 * Flux d'entrée
	 */
	private DataInputStream in;
	/**
	 * Flux de sortie
	 */
	private DataOutputStream out;

	/*
	 * Utilisateur possédant le document
	 */
	private User owner;

	/*
	 * Gestionnaire des documents qui lancera les threads
	 */
	private DocManager manager;

	DocStatusThread(ServerSocket s, Socket client, User u){
		this.servSoc = s;
		this.cliSoc = client;
		owner = u;
	}

	public void connect() {
		while(!Thread.currentThread().isInterrupted()) {
			try {
				// Lecture du type de la requête
				// type = Création d'un nouveau doc/Edition d'un doc
				in = new DataInputStream(cliSoc.getInputStream());
				out = new DataOutputStream(cliSoc.getOutputStream());
				int type = in.readInt();
				// Création d'un nouveau document
				if(type == 1) {
					System.err.println("Type reçu : Création du fichier confirmée");
					String nomDocument = in.readUTF();
					System.err.println("Nom du document recu " + nomDocument);
					Document d = new Document(owner, nomDocument);
					int idDoc = DbManager.addDocument(owner, d);
					System.err.println("Ajout confirmé");
					//Envoie de l'identifiant du document ajouté
					out.writeInt(idDoc);
					System.err.println("Identifiant du document envoyé");
					manager = new DocManager(d);
				}
				else if (type == 3) { // CHARGEMENT D'UN DOC
					System.err.println("Type reçu : Chargement du fichier confirmée");
					int idoc = Integer.parseInt(in.readUTF());	 // RECUPERATION DE L'ID DU FICHIER A CHARGER (sent as string).
					System.err.println("ID DU DOC = " + idoc);
					LinkedList<Document> library = new LinkedList<>();
					library = DbManager.loadAllDocuments();
					System.err.println("Confirmation du chargement de document au client..");
					out.writeInt(3);
					System.err.println("Envoie du N° de page en cours..");
					out.writeInt(0);
					System.err.println("TAILLE DE LA BIBLIOTHEQUE " + library.size());
					System.err.println("Contenu du doc 1 :" + library.get(0).getPages().get(0));
					System.err.println("Envoie du N° total de page en cours.." + library.get(idoc-1).getPages().size());
					out.writeInt(library.get(idoc).getPages().size());
					System.err.println("Envoie du contenu en cours..");
					LinkedList<Word> Listecontenu = new LinkedList<>();
					String contenu = "";
					for(Page p :library.get(idoc).getPages()) {
						Listecontenu.addAll(p.getContent());
					}
					for(int i = 0; i< Listecontenu.size();i++) {
						contenu += Listecontenu.get(i) + "\b";
					}
					System.err.println("Contenu = " + contenu);
					out.writeUTF(contenu);
					System.err.println("Contenu envoyé");
					//Envoie de l'identifiant du document ajouté
					manager = new DocManager(library.get(idoc));
				}
				else {
					System.err.println("J'ai rien reçu");
				}
			}
			catch (IOException e) {
				e.getStackTrace();
			}
		}

	}

	@Override
	public void run() {
		connect();
	}
}
