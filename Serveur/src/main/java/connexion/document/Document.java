package connexion.document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
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
	private String path;

	public Document(User creator, String name) {
		this.name = name;
		this.authorizedUser = new ArrayList<>();
		this.content = new LinkedList<>();
		this.authorizedUser.add(creator);
		this.lastModifDate = new Date(System.currentTimeMillis());
	}

	/**
	 * Constructeur qui va chercher un document enregistré sur le système
	 * @param path le chemin d'accès
	 * @author Bruno ROMAIN
	 */
	public Document(String path) throws FileNotFoundException {
		File file = FileUtils.getFile(path);
		if (file == null) {
			throw new FileNotFoundException("Le fichier spécifié : " + path + " est introuvable");
		}
		this.path = path;
		String fileContent;
		try {
			fileContent = FileUtils.readFileToString(file, "UTF-8");
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
		updatePages(fileContent);
	}

	public Document(User creator){
		this(creator, "Nouveau document");
	}

	public void updatePages(String str) {
		if (str == null) {
			return;
		}
		StringTokenizer token = new StringTokenizer(str, "" + '\0');
		String cmd, content;
		int page, idUser, pos;
		if (token.hasMoreTokens()) {
			cmd = token.nextToken();
		} else {
			return;
		}
		if (token.hasMoreTokens()) {
			page = Integer.valueOf(token.nextToken());
		} else {
			return;
		}
		if (token.hasMoreTokens()) {
			idUser = Integer.valueOf(token.nextToken());
		} else {
			return;
		}
		if (token.hasMoreTokens()) {
			pos = Integer.valueOf(token.nextToken());
		} else {
			return;
		}
		if (token.hasMoreTokens()) {
			content = token.nextToken();
		} else {
			return;
		}
		Page toModify = this.content.get(page);
		if (cmd.equalsIgnoreCase("ADD")) {
			StringTokenizer token2 = new StringTokenizer(content, " ");
			while (token2.hasMoreTokens()) {
				String word = token2.nextToken();
				toModify.addWord(pos, word);
				pos += word.length();
				toModify.updateCurseur(idUser, pos);
			}
		} else if (cmd.equalsIgnoreCase("DEL")) {
			for (int i=0; i<Integer.valueOf(content); i++) {
				toModify.deleteCharFromPos(pos);
				pos--;
				toModify.updateCurseur(idUser, pos);
			}
		}

	}

	public void updateLastModifiedDate() {
		this.lastModifDate = new Date(System.currentTimeMillis());
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

	public void saveDocument(String path) {

	}

	public void setLasModifDate(Date date) {
		this.lastModifDate = date;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String toString(int pageNum) {
		return this.name + '\0' + pageNum + '\0' + this.content.size() + '\0' + this.content.get(pageNum).toString();
	}
}
