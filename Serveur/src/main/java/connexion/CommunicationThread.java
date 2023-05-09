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
			HashMap<Integer, Socket> cliList = server.getSocketList();
			for(Integer i : cliList.keySet()) {
				Socket tmp = cliList.get(i);
				try {
					DataInputStream in = new DataInputStream(tmp.getInputStream());
					DataOutputStream out = new DataOutputStream(tmp.getOutputStream());
					int action = in.readInt();
					if(action == -1) { // NO_ACTION
						System.err.println("NO_ACTION (communicationThread)");
						out.writeInt(-1);
					}
					else if (action == 2) { // CREATION_DE_DOC
						ArrayList<User> users = new ArrayList<>();
						System.err.println("DOCUMENT_CREATION(communicationThread)");
						String nomDocument = in.readUTF();
						System.err.println("Nom du document recu " + nomDocument);
						Document d = new Document(users.get(i), nomDocument);
						DbManager.addDocument(users.get(i), d);
						System.err.println("Ajout confirmé (CommunicationThread)");
						manager = new DocManager(d);
					}
					else if (action == 5) { // MODIFICATION

					}
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}
}
