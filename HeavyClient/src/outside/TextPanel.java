package outside;

import javax.swing.JEditorPane;

/**
 * Classe représentant le panneau d'édition personnalisé
 * @author mourtaza
 */
public class TextPanel extends JEditorPane {
	private static final long serialVersionUID = 198715356584007136L;

	/**
	 * Construit une instance du panneau d'édition du texte
	 * @param content
	 */
	public TextPanel(String content) {
		super();
		
		// Type du contenu
		this.setContentType("text/html");
		
		// Insertion du contenu
        this.setText(content);
	}
}
