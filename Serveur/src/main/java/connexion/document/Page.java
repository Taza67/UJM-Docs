package connexion.document;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

import connexion.DbManager;
import connexion.User;

/**
 * Cette classe représente une page dans note modèle de données.
 * Une page est représentée par une chaîne de caractères
 * @author Bruno ROMAIN
 */
public class Page {
	private String contenu;
	private ArrayList<User> collaborateurs;
	
	/**
	 * Instancie une page
	 */
	public Page() {
		contenu = "";
		collaborateurs = new ArrayList<>();
	}
	public Page(User createur) {
		collaborateurs.add(createur);
	}

	
	/**
	 * Retourne le nombre de caractères de la page
	 * @return Nombre de caractères de la page
	 */
	public int getLongueur() {
		return contenu.length();
	}

	/**
	 * Retourne le contenu de la page
	 * @return Contenu de la page
	 */
	public String getContent() { return contenu; }
	
	/**
	 * Ajoute un collaborateur sur la page
	 * Utilisé lorsqu'un collaborateur se déplace sur une autre page
	 */
	public void ajouterCollaborateur(User collaborateurAAjouter) {
		collaborateurs.add(collaborateurAAjouter);
	}
	
	/**
	 * Enlève un collaborateur de la page
	 * Utilisé lorsqu'un collaborateu se déplace sur une autre page
	 */
	public void enleverCollaborateur(User collaborateurAEnlever) {
		collaborateurs.remove(collaborateurAEnlever);
	}
	
	/**
	 * Insère une chaîne de caractères à une certaine position de la page
	 * @param posModification Position où la chaine de caractères doit être ajoutée
	 * @param chaineAInserer Chaîne de caractères à insérer
	 */
	public void insererChaine(int posModification, String chaineAInserer) {
		// Vérifie si la position est valide
        if (posModification < 0 || posModification > contenu.length()) {
            throw new IllegalArgumentException("La position est hors des limites de la chaîne originale.");
        }
       
        // Divise la chaîne originale et insère la nouvelle chaîne à la position souhaitée
        String debut = contenu.substring(0, posModification);
        String fin = contenu.substring(posModification);
        contenu = debut + chaineAInserer + fin;
        
        // Mise à jour des curseurs
        majCurseurs(posModification, chaineAInserer.length(), true);
	}

	/**
	 * Supprime une chaîne de caractères à une certaine position de la page
	 * @param posModification Position où la chaine de caractères doit être ajoutée
	 * @param longueurSuppression longueur de la chaîne à supprimer
	 */
	public void supprimerChaine(int posModification, int longueurSuppression) {
		// Vérifications
        if (posModification < 0 || posModification > contenu.length())
            throw new IllegalArgumentException("La position est hors des limites de la chaîne originale.");

        if (posModification + longueurSuppression > contenu.length())
            throw new IllegalArgumentException("La longueur spécifiée dépasse la fin de la chaîne originale.");
        
        // Suppression
        String debut = contenu.substring(0, posModification);
        String fin = contenu.substring(posModification + longueurSuppression);
        contenu = debut + fin;
        
        // Mise à jour des curseurs
        majCurseurs(posModification, longueurSuppression, false);
	}
	
	/**
	 * Met à jour les positions des curseurs des utilisateurs
	 * @param posModification Position de départ de la modification
	 * @param longueur Longueur de la modification
	 * @param estUnAjout true s'il s'agit d'un ajout, false s'il s'agit d'une suppression
	 */
	public void majCurseurs(int posModification, int longueur, boolean estUnAjout) {
        for (User utilisateur : collaborateurs) {
        	int curseurPosition = utilisateur.getposition();
        	
        	if (estUnAjout) {
        		// Du contenu a été ajouté, le curseur doit peut-être avancé
	        	if (curseurPosition >= posModification)
	        		utilisateur.setPosition(curseurPosition + longueur);
        	} else {
        		// Du contenu a été retiré, le curseur doit peut-être reculé
        		if (curseurPosition >= posModification) {
        			int newPosition = curseurPosition - longueur;
        			utilisateur.setPosition(Math.max(posModification, newPosition));
        		}
        	}
        }
	}
	
	public String toString() {
		return contenu;
	}
}
