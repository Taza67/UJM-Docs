package inside;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe représentant un document dans l'application
 *
 * @author mourtaza
 *
 */
public class Document {
	/**
	 * Identifiant du document auprès du serveur
	 */
	private int documentId = -1;
	/**
	 * Nom du document
	 */
	private String documentName;
	/**
	 * Liste des pages du document
	 */
	private List<String> pages;

	/**
	 * Instancie un document
	 *
	 * @param dn Nom du document
	 */
	public Document(String dn) {
		documentName = dn;
		pages = new LinkedList<>();
	}
	
	/**
	 * Instancie un document
	 *
	 * @param did Identifiant du document auprès du serveur
	 * @param dn Nom du document
	 */
	public Document(int did, String dn) {
		this(dn);
		documentId = did;
	}

	
	/**
	 * Retourne l'identifiant du document
	 * @return Identifiant du document
	 */
	public int getDocumentId() {
		return documentId;
	}
	
	/**
	 * Retourne le nombre de pages du document
	 * @return Nombre de pages du document
	 */
	public int getPageCount() {
		return pages.size();
	}

	/**
	 * Retourne la page associée au numéro donné
	 * @param pageNumber Numéro de page
	 * @return Page recherchée
	 */
	public String getPage(int pageNumber) {
		return pages.get(pageNumber);
	}

	/**
	 * Retourne l'ensemble du document dans une seule chaîne de caractères
	 */
	public String getAllDocument() {
		String doc = "";

		// Assemblage de toutes les pages
		for (int i = 0; i < pages.size() - 1; i++)
			doc += pages.get(i) + "\f";
		doc += pages.get(pages.size() - 1);

		return doc;
	}

	/**
	 * Remplace le contenu de la page associé au numéro donné par le contenu donné
	 * @param pageNumber Numéro de la page à modifier
	 * @param newContent Contenu à mettre dans la page
	 */
	public void setPage(int pageNumber, String newContent) {
		pages.set(pageNumber, newContent);
	}
	
	/**
	 * Modifie la valeur de l'identifiant du document
	 * @param did Nouvel identifiant
	 */
	public void setDocumentId(int did) {
		documentId = did;
	}
	

	/**
	 * Ajoute une nouvelle page au document
	 * @param pageNumber Position où ajouter la nouvelle page
	 * @param newContent Contenu à mettre dans la page
	 */
	public void addPage(int pageNumber, String newContent) {
		pages.add(pageNumber, newContent);
	}
	
	/**
	 * Ajoute un certain nombre de page
	 * @param pageNumber Position où ajouter la nouvelle page
	 * @param pageCount Nombre de pages à ajouter
	 */
	public void addPages(int pageNumber, int pageCount) {
		for (int i = 0; i < pageCount; i++)
			addPage(pageNumber + i, "");
	}
}
