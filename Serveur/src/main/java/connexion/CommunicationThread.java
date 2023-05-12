package connexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import connexion.document.Document;

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
			HashMap<User, Socket> cliList = server.getSocketList();
			int codeToSend = 0;
			for(User u : cliList.keySet()) {
				Socket tmp = cliList.get(u);
				try {
					DataInputStream in = new DataInputStream(tmp.getInputStream());
					DataOutputStream out = new DataOutputStream(tmp.getOutputStream());
					int action = in.readInt();
					if(action == -1) { // NO_ACTION
						//System.err.println("NO_ACTION (communicationThread)");
						codeToSend = -1;
					}
					else if (action == 2) { // CREATION_DE_DOC
						ArrayList<User> users = new ArrayList<>();
						System.err.println("DOCUMENT_CREATION(communicationThread)");
						String nomDocument = in.readUTF();
						System.err.println("Nom du document recu " + nomDocument);
						Document d = new Document(u, nomDocument);
						DbManager.addDocument(u, d);
						System.err.println("Ajout confirmé (CommunicationThread)");
						
						// manager.removeCollaborateur();
						
						DocManager newManager = new DocManager(d, server);
						newManager.startCommunication();
					}
					else if (action == 5) { // AJOUT DE CONTENU
						//On attend le format pour ajouter du contenu
						String format = in.readUTF();
						System.err.println("Réception du format => " + format);
						System.err.println("Réception du format réussie..");
						manager.processMessage(format);
						codeToSend = 5;
					}
					else if(action == 6) { //SUPRRESSION DE CONTENU
						String format = in.readUTF();
						System.err.println("Réception du format => " + format);
						System.err.println("Réception du format réussie..");
						manager.processMessage(format);
						codeToSend = 5;
					}
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			for(User u : cliList.keySet()) {
				Socket tmp = cliList.get(u);
				try {
					DataInputStream in = new DataInputStream(tmp.getInputStream());
					DataOutputStream out = new DataOutputStream(tmp.getOutputStream());
					if(codeToSend == -1) {
						out.writeInt(codeToSend);
						continue;
					}
					
						
					
					//Envoie du message aux clients
					System.err.println("Envoie du code " + codeToSend);
					out.writeInt(codeToSend);
					//Envoie de l'identifiant de l'utilisateur
					System.err.println("Envoie de l'identifiant de l'utilisateur " + u.getId());
					out.writeInt(u.getId());
					System.err.println("Envoie du N° de page " + u.getPageNum());
					//Envoie du n° de page de l'utilisateur
					out.writeInt(u.getPageNum());
					System.err.println("Envoie du nombre de pages " + manager.getD().getNombrePage());
					//Envoie du nombre total de pages
					out.writeInt(manager.getD().getNombrePage());
					System.err.println("Envoie du contenu " + manager.getD().getPage(u.getPageNum()).toString());
					//Envoie du contenu
					out.writeUTF(manager.getD().getPage(u.getPageNum()).toString());
					//Envoie du curseur
					System.err.println("Envoie de la position du curseur => " + u.getposition());
					out.writeInt(u.getposition());
				}
				catch (IOException e) {
					e.printStackTrace();
				}

			}

		}
	}
}
