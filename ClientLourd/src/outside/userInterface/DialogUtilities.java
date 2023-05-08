package outside.userInterface;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Classe implémentant des méthodes utiles à la gestion des dialogues avec
 * l'utilisateur
 * @author mourtaza
 *
 */
public class DialogUtilities {
	/**
	 * Ouvre une JOptionPane avec les valeurs données
	 * et retourne la valeur sélectionnée par l'utilisateur
	 * @param parent Fenêtre parente
	 * @param options Liste des options
	 */
	public static String askChoiceFromOptions(JFrame parent, String title, String message, String[] options) {
	    // Création de la boîte et attente de la réponse
		String selection = (String)JOptionPane.showInputDialog(
    		parent, message, title,
    		JOptionPane.PLAIN_MESSAGE,
    		null, options, options[0]
    	);
	    
		// Vérification de la réponse
		while (selection == null) {
			selection = (String)JOptionPane.showInputDialog(
	    		parent, message + "Attention ! Réponse incorrecte !",
	    		title, JOptionPane.PLAIN_MESSAGE, null,
	    		options, options[0]
	    	);
		}
		
		return selection;
	}
	
	
}
