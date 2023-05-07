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

public class CommunicationThread {

/**
 * Module implémentant le thread responsable de la communication entre 
 * le server websocket et le server TCP
 * 	@author Hany
 */
	
	/*
	 * Socket client qui recevra les modifications
	 */
	private Socket Clisoc;
	
	/*
	 * Socket Server qui recevra les modifications
	 * de la part du client
	 */
	private ServerSocket ServSock;
	
	

}
