package connexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
	 * Utilisateur qui essaye de se connecter
	 */
	private User u;

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
	 * Modifie l'utilisateur qui essaie de se connecter
	 */
	public void setU(User u) {this.u = u;}



	public void disconnectClient() {
		try {
			System.out.println("Vous êtes déconnecté");
			in.close();
			out.close();
			cliSoc.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void listen() {
		while(!Thread.currentThread().isInterrupted()) {
			try {
				cliSoc = servSoc.accept();
				System.err.println("S : un client vient de se connecter depuis " + cliSoc.getInetAddress());

				// Initialisation des flux d'entrée/sortie
				out = new DataOutputStream(cliSoc.getOutputStream());
				in = new DataInputStream(cliSoc.getInputStream());

				String pseudo = in.readUTF();
				String pw = in.readUTF();

				u = new User(-1, pseudo, pw);
				boolean accept = DbManager.IsUserValid(u);
				if(!accept) {
					out.writeUTF("Identification impossible. Erreur dans le pseudo ou mot de passe");
				}
				else {
					System.out.println("Communication établie. Bienvenue " + u.getPseudo());
					out.writeUTF("Communication établie. Bienvenue " + u.getPseudo());
					out.writeInt(1);
				}
				
				// création du thread intermédiaire
				DocStatusThread docThread = new DocStatusThread(servSoc, cliSoc,u);
				docThread.start();
				
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
	public void run() {
		listen();
	}

	public static void main(String[] args) {
		//HeavyServer s = new HeavyServer();
		//s.listen();

	}

}
