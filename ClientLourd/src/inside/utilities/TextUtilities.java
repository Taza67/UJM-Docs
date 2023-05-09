package inside.utilities;

/**
 * Classe implémentant des méthodes utiles au traitement de texte
 *
 * @author mourtaza
 *
 */
public class TextUtilities {
	/**
	 * Compte le nombre de mots dans un texte
	 *
	 * @param text Texte en question
	 * @return Nombre de mots du texte
	 */
	public static int countWords(String text) {
		String[] words = text.split("[\\s\\n]+");
		int count = 0;

		for (String word : words)
			if (!word.trim().isEmpty())
				count++;

		return count;
	}

	/**
	 * Compte le nombre de lignes dans un texte
	 *
	 * @param text Texte en question
	 * @return Nombre de lignes du texte
	 */
	public static int countLines(String text) {
		text += "#";
		String[] lines = text.split("\n");
		return lines.length;
	}

	/**
	 * Compte le nombre de caractères dans un texte
	 *
	 * @param text Texte en question
	 * @return Nombre de caractères du texte
	 */
	public static int countChars(String text) {
		return text.length();
	}

	/**
	 * Récupère le numéro de ligne du curseur
	 *
	 * @param text           Texte dans lequel on recherche le numéro de ligne
	 * @param carretPosition Position du curseur dans tout le texte
	 */
	public static int getCarretLine(String text, int caretPosition) {
		int begin = 0;
		String[] lines = (text + "#").split("\n");

		for (int i = 0; i < lines.length; i++) {
			if (caretPosition >= begin && caretPosition <= begin + lines[i].length())
				return i + 1;
			begin += lines[i].length() + 1;
		}

		return 0;
	}
}
