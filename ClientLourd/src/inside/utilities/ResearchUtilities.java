package inside.utilities;

import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 * Classe implémentant des méthodes utiles à la recherche de mots
 *
 * @author mourtaza
 *
 */
public class ResearchUtilities {
	/**
	 * Recherche les occurences d'un mot dans le contenu de l'éditeur et les
	 * surligne
	 *
	 * @param editorPane Référence à l'éditeur de texte
	 * @param searchText Texte à recherche
	 */
	public static void searchAndHighlight(JEditorPane editorPane, String searchText) {
		// Suppression des précédents surlignages
		Highlighter highlighter = editorPane.getHighlighter();
		highlighter.removeAllHighlights();

		// Recherche du texte et surlignage des occurrences trouvées
		String editorText = editorPane.getText();
		int searchLength = searchText.length();

		if (searchLength > 0) {
			int cursorPosition = 0;
			while ((cursorPosition = editorText.indexOf(searchText, cursorPosition)) != -1) {
				try {
					highlighter.addHighlight(cursorPosition, cursorPosition + searchLength,
							DefaultHighlighter.DefaultPainter);
					cursorPosition += searchLength;
				} catch (BadLocationException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
