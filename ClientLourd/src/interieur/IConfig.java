package interieur;

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
	Dimension textPaneDimensions = new Dimension(100, 1000);
	
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
	String COMMUNICATION_HOST = "localhost";
	/**
	 * Port de communication
	 */
	int COMMUNICATION_PORT = 8080;
	/**
	 * Entier imposible à récupérer sur le flux d'entrée
	 * À noter -> cet entier ne sera pas utilisé dans d'autres cas
	 */
	int IMPOSSIBLE_CODE = -1;
	/**
	 * Entier signifiant que le serveur ne demande aucune action
	 * À noter -> cet entier ne sera pas utilisé dans d'autres cas
	 */
	int NO_ACTION_CODE = -2;
}
