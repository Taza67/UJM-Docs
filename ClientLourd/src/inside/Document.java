package inside;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe représentant un document dans l'application
 * @author mourtaza
 *
 */
public class Document {
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
	 * @param dn Nom du document
	 */
	public Document(String dn) {
		documentName = dn;
		pages = new LinkedList<String>();
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
	 * Retourne l'ensemble du document
	 * dans une seule chaîne de caractères
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
	 * Remplace le contenu de la page associé au numéro donné
	 * par le contenu donné
	 * @param pageNumber Numéro de la page à modifier
	 * @param newContent Contenu à mettre dans la page
	 */
	public void setPage(int pageNumber, String newContent) {
		pages.set(pageNumber, newContent);
	}
	
	/**
	 * Ajoute une nouvelle page au document
	 * @param pageNumber Position où ajouter la nouvelle page
	 * @param newContent Contenu à mettre dans la page
	 */
	public void addPage(int pageNumber, String newContent) {
		pages.add(pageNumber, newContent);
	}
}
