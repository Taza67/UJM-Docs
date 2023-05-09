package connexion.document;

import java.util.LinkedList;

import connexion.User;

/**
 * Cette classe représente un mot dans notre modèle de donnée: un mot est une liste chainée de caractère et sera considéré comme verrouillé
 * si un utilisateur "modifie" le mot
 * @author Bruno ROMAIN
 */
public class Word {

	private LinkedList<Character> content;
	private int length;
	private User modifying;

	public Word(String word) {
		for(char c: word.toCharArray()) {
			this.content.add(c);
		}
		this.length = word.length();
		this.modifying = null;
	}

	/**
	 * Fonction qui ajoute dans un mot un caractère à l'indice donné
	 * @param c le caractère à ajouter
	 * @param pos la position dans le mot à ajouter. Si la position est invalide, l'ajout n'est pas fait
	 */
	public void addCharacter(char c, int pos) {
		if(pos >= this.content.size() || pos < 0) {
			return;
		}
		this.content.add(pos, c);
	}

	@Override
	public String toString() {
		String res = "";
		for (char c: content) {
			res += c;
		}
		return res + " ";
	}

	/**
	 * Fonction qui ajoute un caractère en fin de mot
	 * @param c le caractère à ajouter
	 */
	public void addCharacter(char c) {
		this.content.addLast(c);
	}

	/**
	 * Fonction qui verouille un mot avec un utilisateur donné
	 * @param u l'utilisateur
	 */
	public void lock(User u) {
		if (u == null) {
			return;
		}
		this.modifying = u;
	}

	public void unlock() {
		this.modifying = null;
	}

	public boolean isLocked() {
		return this.modifying != null;
	}

	public void deleteCharFromPos(int pos) {
		this.content.remove(pos);
	}

	public void deleteLastChar() {
		this.content.removeLast();
	}

	public LinkedList<Character> getContent() {
		return this.content;
	}
}
