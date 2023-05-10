package connexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

import connexion.document.Document;
/**
 * Classe représentant un server vis-à-vis de la communication TCP
 * @author Hany
 *
 */



public class HeavyServer extends Thread{

	/**
	 * Port de connexion
	 */
	private final int PORT = 8080;
	/**
	 * Socket du server
	 */
	private ServerSocket servSoc;

	/*
	 * Utilisateur qui essaye de se connecter
	 */
	private User u;


	/*
	 * Liste des sockets identifiées par l'identifiant des utilisateurs
	 */
	private volatile HashMap<Integer, Socket> SocketList = new HashMap<>();
	

	/**
	 * Instancie un objet représentant un serveur
	 *
	 */

	public HeavyServer() {
		try {
			servSoc = new ServerSocket(PORT);
			System.out.println("S : serveur actif");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Retourne l'utilisateur qui essaie de se connecter
	 * @return l'utilisateur qui essaie de s'identifier
	 */
	public User getUser() {return u;}


	/**
	 * Retourne la hashmap contenant la liste des
	 * socket et l'identifiant de chaque utilisateur
	 * @return la hashmap contenant la liste des
	 * socket et l'identifiant de chaque utilisateur
	 */

	public HashMap<Integer, Socket> getSocketList() {return SocketList;}


	/**
	 * Modifie l'utilisateur qui essaie de se connecter
	 */
	public void setU(User u) {this.u = u;}


	/*
	 * Methode qui gère le thread de communication
	 */
	public void listen() {
		DbManager.init();

		while(!Thread.currentThread().isInterrupted()) {
			try {
				Socket cliSoc = servSoc.accept();
				System.err.println("S : un client vient de se connecter depuis " + cliSoc.getInetAddress());

				// Initialisation des flux d'entrée/sortie
				DataOutputStream out = new DataOutputStream(cliSoc.getOutputStream());
				DataInputStream in = new DataInputStream(cliSoc.getInputStream());

				// savoir si c'est une connexion ou une inscription
				// 0 = connexion, 1 = inscription
				int type = in.readInt();
				if(type == 0) { // CONNEXION
					String pseudo = in.readUTF();
					String pw = in.readUTF();
					u = new User(-1, pseudo, pw);
					boolean accept = DbManager.IsUserValid(u);
					if(!accept) {
						// 0 = Identification impossible
						System.err.println("Identification impossible HeavyServer(l109)");
						out.writeInt(0);
						in.close();
						out.close();
						cliSoc.close();
					}
					else {
						System.out.println("Communication établie. Bienvenue " + u.getPseudo());
						SocketList.put(u.getId(), cliSoc);
						out.writeInt(1);
						LinkedList<Document> docList = DbManager.loadAllDocuments(u);
						System.err.println(docList.size());
						String s= "";
						for(Document doc : docList)
							s+=doc.getID() + "\b" + doc.getName() + "\b";
						System.err.println("AFFICHAGE DE LA CHAINE " + s);
						System.err.println("Envoie des noms des doc + leur id");
						out.writeUTF(s);
						System.err.println("Je vais crée le DocStatusThread");
						// création du thread intermédiaire
						DocStatusThread docThread = new DocStatusThread(servSoc, cliSoc,u,this);
						docThread.start();
					}

				}
				// type = inscription
				else {

				}



			}
			catch (IOException e) {
				e.getStackTrace();
			}
		}
	}

	/*
	 * Thread qui assure la connexion et
	 * l'acceptation de la demande de
	 * connexion du client
	 */
	@Override
	public void run() {
		listen();
	}

	public static void main(String[] args) {
		HeavyServer s = new HeavyServer();
		s.start();

	}

}
