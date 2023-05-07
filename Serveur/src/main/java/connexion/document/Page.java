package connexion.document;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

import connexion.User;

/**
 * Cette classe représente une page dans note modèle de données.
 * Une page est composée d'une liste chainée de mots, est verrouillable, et sera accessible par les utilisateur whitelisté
 * @author Bruno ROMAIN
 */
public class Page {

	private final Word POINT = new Word(".");
	private final Word VIRGULE = new Word(",");
	private final Word POINT_VIRGULE = new Word(";");
	private final Word DEUX_POINTS = new Word(":");
	private final Word POINT_D_EXCLAMATION = new Word("!");

	private LinkedList<Word> content;
	private ArrayList<User> authorizedModification;
	private boolean locked;

	public Page() {
		this.content = new LinkedList<>();
		this.authorizedModification = new ArrayList<>();
		this.locked = false;
	}

	public Page(User creator) {
		this();
		this.authorizedModification.add(creator);
	}

	public User getCreator() {
		if (this.authorizedModification.isEmpty()) {
			return null;
		}
		return this.authorizedModification.get(0);
	}

	/**
	 * Fonction qui vide la liste des utilisateur en laissant le créateur
	 * @author Bruno ROMAIN
	 */
	public void emptyAuthorized() {
		User creator = this.getCreator();
		if (creator == null) {
			return;
		}
		this.authorizedModification = new ArrayList<>();
		this.authorizedModification.add(creator);
	}

	public void removeAuthorized(User u) {
		if (u == null || u == this.getCreator()) {
			return;
		}
		this.authorizedModification.remove(u);
	}

	public boolean isAuthorized(User u) {
		return this.authorizedModification.contains(u);
	}

	public void addAuthorized(User u) {
		if (u == null) {
			return;
		}
		this.authorizedModification.add(u);
	}

	public ArrayList<User> getAuthorizedModification() {
		return (ArrayList<User>) authorizedModification.stream().distinct().collect(Collectors.toList());
	}

	public void lock() {
		this.locked = true;
	}

	/**
	 * Fonction qui permet d'obtenir le mot à la position donnée
	 * @param pos la position
	 * @return le mot trouvé à l'indice pos, null sinon
	 */
	public Word getWordAtPos(int pos) {
		int i=0;
		for(Word w: content) {
			for(char c: w.getContent()) {
				if (i == pos) {
					return w;
				}
				i++;
			}
			if (i == pos) {
				return w;
			}
			i++;
		}
		return null;
	}

	/**
	 * Cette fonction permet d'ajouter un mot dans une page à une position donnée. Si la position est invalide, le mot sera soit rajouté
	 * en début ou en fin de page
	 * @param pos la position
	 * @param word le mot
	 */
	public void addWord(int pos, String word) {
		if (pos < 0) {
			switch(word.charAt(0)) {
				case '.':
					content.addFirst(POINT);
					break;
				case ',':
					content.addFirst(VIRGULE);
					break;
				case ';':
					content.addFirst(POINT_VIRGULE);
					break;
				case ':':
					content.addFirst(DEUX_POINTS);
					break;
				case '!':
					content.addFirst(POINT_D_EXCLAMATION);
					break;
				default:
					content.addFirst(new Word(word));
			}
		}
		if (pos >= content.size()) {
			switch(word.charAt(0)) {
				case '.':
					content.addLast(POINT);
					break;
				case ',':
					content.addLast(VIRGULE);
					break;
				case ';':
					content.addLast(POINT_VIRGULE);
					break;
				case ':':
					content.addLast(DEUX_POINTS);
					break;
				case '!':
					content.addLast(POINT_D_EXCLAMATION);
					break;
				default:
					content.addLast(new Word(word));
			}
		}
		switch(word.charAt(0)) {
			case '.':
				content.add(pos, POINT);
				break;
			case ',':
				content.add(pos, VIRGULE);
				break;
			case ';':
				content.add(pos, POINT_VIRGULE);
				break;
			case ':':
				content.add(pos, DEUX_POINTS);
				break;
			case '!':
				content.add(pos, POINT_D_EXCLAMATION);
				break;
			default:
				content.add(pos, new Word(word));
		}

	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
