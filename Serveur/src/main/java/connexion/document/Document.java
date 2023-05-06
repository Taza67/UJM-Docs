package main.java.connexion.document;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import connexion.User;

/**
 * Cette classe définit nos documents dans notre modèle de données
 * Un document est représenté par un nom, une date de dernière modification, un ID (BDD),
 * une liste d'utilisateurs représentant les utilisateurs autorisés à modifier le document, et une liste de pages.
 * @author Bruno ROMAIN
 */
public class Document {

	private String name;
	private Date lastModifDate;
	private int ID;
	private ArrayList<User> authorizedUser;
	private LinkedList<Page> content;

	public Document(User creator, String name) {
		this.name = name;
		this.authorizedUser = new ArrayList<>();
		this.content = new LinkedList<>();
		this.authorizedUser.add(creator);
		this.lastModifDate = new Date(System.currentTimeMillis());
	}

	public void updateLastModifiedDate() {
		this.lastModifDate = new Date(System.currentTimeMillis());
	}

	public Document(User creator){
		this(creator, "Nouveau document");
	}

	public User getCreator() {
		return this.authorizedUser.get(0);
	}

	/**
	 * Fonction permettant d'accéder la page à la position donnée
	 * @param i la position
	 * @return la page à l'indice donnée si indice valide, sinon la première ou la dernière page en fonction de l'indice
	 */
	public Page getPageAtIndex(int i) {
		if (i >= this.content.size()) {
			return this.content.getLast();
		} else if(i < 0) {
			return this.content.getFirst();
		} else {
			return this.content.get(i);
		}
	}

	public List<Page> getPages() {
		return this.content;
	}

	public void addPage() {
		this.content.add(new Page(this.getCreator()));
		this.updateLastModifiedDate();
	}

	/**
	 * Fonction permettant d'insérer une page en position donnée Si la position est erronée, la page se rajoutera au début du document où à
	 * la fin en fonction de l'indice
	 * @param pos la position
	 */
	public void insertPage(int pos) {
		if (pos >= this.content.size()) {
			this.content.addLast(new Page(this.getCreator()));
 		} else if (pos < 0) {
			this.content.addFirst(new Page(this.getCreator()));
		} else {
			this.content.add(pos, new Page(this.getCreator()));
		}
		this.updateLastModifiedDate();
	}

	/**
	 * Fonction permettant de supprimer une page en position donnée Si la position est erronée, la page se rajoutera au début du document où à
	 * la fin en fonction de l'indice
	 * @param pos la position
	 */
	public void deletePage(int pos) {
		if (pos > this.content.size()) {
			this.content.removeLast();
		} else if (pos < 0) {
			this.content.removeFirst();
		} else {
			this.content.remove(pos);
		}
		this.updateLastModifiedDate();
	}

	public void addAuthorized(User u) {
		if (u == null) {
			return;
		}
		this.authorizedUser.add(u);
	}

	public ArrayList<User> getAuthorizedModification() {
		return (ArrayList<User>) authorizedUser.stream().distinct().collect(Collectors.toList());
	}

	public void deleteAuthorized(User u) {
		this.authorizedUser.remove(u);
	}

	/**
	 * Fonction permettant de vider la liste des personnes autorisée, tout en laissant le créateur du document
	 */
	public void emptyAuthorized() {
		User user = this.getCreator();
		this.authorizedUser= new ArrayList<>();
		this.authorizedUser.add(user);
	}

	public boolean isAuthorized(User u) {
		return this.authorizedUser.contains(u);
	}

	public Date getLastModifDate() {
		return lastModifDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
