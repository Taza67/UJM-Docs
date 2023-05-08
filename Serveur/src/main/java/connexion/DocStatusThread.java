package connexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import connexion.document.Document;

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
					DbManager.addDocument(owner, d);
					System.err.println("Ajout confirmé");
					manager = new DocManager(d);
					manager.start();
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
