package connexion;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/websocketserver")
public class WebSocketComm {

	private Session session;
	List<User> UsersList = new ArrayList<User>();
	


	@OnOpen
	public void onOpen(Session session, User u) {
		this.session = session;
		UsersList.add(u);
		// Logic to handle the opening of a WebSocket connection
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		// Logic to handle the closing of a WebSocket connection
		System.err.println("Fermeture de la session " + session + " pour la raison suivante : " + closeReason);
	}
	
	@OnMessage
	public void OnMessage(String message, Session session) {
		
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		// Logic to handle any errors that occur with the WebSocket connection
	}

	public void send(String message) throws Exception {
		this.session.getBasicRemote().sendText(message);
	}

	public void close() throws Exception {
		this.session.close();
	}
}

