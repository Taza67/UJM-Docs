package connexion;

import connexion.document.Document;

/**
 * Cette classe représente le gestionnaire des documents qui permet la modification des documents d'une façon synchronisée
 * @author BAYAZID Hany
 */


public class DocManager {
	/**
	 * Document à modifier
	 */
	private Document d;

	// un doc manager sera crée quand un gars va essayer de crée ou de prendre accès d'un document

	public DocManager(Document doc) {
		d = doc;
	}


	public static void main(String[] args) {

	}
}
