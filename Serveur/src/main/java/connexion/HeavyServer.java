package connexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Classe représentant un server vis-à-vis de la communication TCP
 * @author Hany
 *
 */



public class HeavyServer {

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


	public HeavyServer() {
		try {
			servSoc = new ServerSocket(PORT);
			System.out.println("S : serveur actif");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

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
		DbManager m = new DbManager();
		while(true) {
			try {
				cliSoc = servSoc.accept();
				System.err.println("S : un client vient de se connecter depuis " + cliSoc.getInetAddress());

				// Initialisation des flux d'entrée/sortie
				out = new DataOutputStream(cliSoc.getOutputStream());
				in = new DataInputStream(cliSoc.getInputStream());

				String pseudo = in.readUTF();
				String pw = in.readUTF();

				User u = new User(-1, pseudo, pw);
				boolean accept = m.IsUserValid(u);
				if(!accept) {
					out.writeUTF("Identification impossible. Erreur dans le pseudo ou mot de passe"); 
				}
				else {
					System.out.println("Communication établie. Bienvenue " + u.getPseudo());
					out.writeUTF("Communication établie. Bienvenue " + u.getPseudo());
					out.writeInt(1);
				}
			}
			catch (IOException e) {
				e.getStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		HeavyServer s = new HeavyServer();
		s.listen();
		
	}

}
