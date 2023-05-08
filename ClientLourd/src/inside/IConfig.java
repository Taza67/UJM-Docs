package inside;

import java.awt.Dimension;

/**
 * Interface implémentant les diverses et multiples constantes utilisées dans le projet
 * @author mourtaza
 *
 */
public interface IConfig {
	// Éditeur ////////////////////////////////////////
	/**
	 * Dimensions par défaut du panneau d'édition de texte
	 */
	Dimension DEFAULT_EDITOR_PANE_DIMENSIONS = new Dimension(100, 2000);
	/**
	 * Dimensions minimales par défaut du panneau d'édition de texte
	 */
	Dimension DEFAUTL_EDITOR_PANE_MIN_DIMENSIONS = new Dimension(100, 2000);
	/**
	 * Marge par défaut de l'éditeur
	 */
	int DEFAULT_EDITOR_MARGE = 200;
	// Document ///////////////////////////////////////
	/**
	 * Marge par défaut du document
	 */
	int DEFAULT_PAGE_MARGE = 60;
	/**
	 * Police par défaut du document
	 */
	int DEFAULT_DOCUMENT_FONT_SIZE = 12;
	
	// Communication //////////////////////////////////
	/**
	 * Hôte du serveur
	 */
	String COMMUNICATION_HOST = "192.168.1.146";
	/**
	 * Port de communication
	 */
	int COMMUNICATION_PORT = 8080;
	/**
	 * Entier imposible à récupérer sur le flux d'entrée
	 * À noter -> cet entier ne sera pas utilisé dans d'autres cas
	 */
	int IMPOSSIBLE_CODE = -2;
	/**
	 * Entier signifiant que le serveur ne demande aucune action
	 * À noter -> cet entier ne sera pas utilisé dans d'autres cas
	 */
	int NO_ACTION_CODE = -1;
	/**
	 * Entier signifiant une demande de connexion d'utilisateur
	 * au serveur
	 * Client => REQUÊTE => Serveur
	 */
	int CONNECTION_REQUEST_CODE = 0;
	/**
	 * Entier signifiant une demande d'inscription d'utilisateur
	 * au serveur
	 * Client => REQUÊTE => Serveur
	 */
	int SUBSCRIBTION_REQUEST_CODE = 1;
	/**
	 * Entier signifiant la demande de création d'un nouveau document
	 * par l'application au serveur
	 * Client => REQUÊTE => Serveur
	 */
	int NEW_DOCUMENT_REQUEST_CODE = 2;
	/**
	 * Entier signifiant la demande de chargement d'un document
	 * par l'application au serveur
	 * Client => REQUÊTE => Serveur
	 */
	int LOAD_DOCUMENT_REQUEST_CODE = 3;
	/**
	 * Entier signifiant la demande de sauvegarde du document
	 * courant par l'application au serveur
	 * Client => REQUÊTE => Serveur
	 */
	int SAVE_DOCUMENT_REQUEST_CODE = 4;
	/**
	 * Entier signifiant la demande de modification du document
	 * courant par l'application au serveur
	 * Client => REQUÊTE => Serveur
	 */
	int MODIFY_DOCUMENT_REQUEST_CODE = 5;
	/**
	 * Entier signifiant la demande d'application de modification
	 * du document par le serveur à l'application
	 * Client <= REQUÊTE <= Serveur
	 */
	int MODIFICATION_APPLICATION_REQUEST_CODE = 6;
	/**
	 * Entier signifiant la demande d'application du déplacement
	 * d'un collaborateur par le serveur à l'application
	 * Client <= REQUÊTE <= Serveur
	 */
	int COLLABORATOR_MOVEMENT_APPLICATION_REQUEST_CODE = 7;
	
	
	
}
