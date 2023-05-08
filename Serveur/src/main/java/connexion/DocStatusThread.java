package connexion;

import connexion.document.Document;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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

				int type = in.readInt();
				// Création d'un nouveau document
				if(type == 1) {
					System.err.println("Type reçu : Création du fichier confirmée");
					String nomDocument = in.readUTF();
					Document d = new Document(owner, nomDocument);
					DbManager.addDocument(owner, d);
					manager = new DocManager(d);
					manager.start();
				}
			}
			catch (IOException e) {
				e.getStackTrace();
			}
		}

	}
	
	public void run() {
		connect();
	}
}
