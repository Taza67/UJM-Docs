package connexion.document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
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
 * Un document est représenté par un nom, une date de dernière modification, un ID (BDD), et une liste de pages
 * Une liste d'utilisateurs représentant les utilisateurs autorisés à modifier le document
 * @author Bruno ROMAIN
 */
public class Document {
	private String nom;
	private Date derniereModification;
	private int id;
	private ArrayList<User> collaborateurs;
	private LinkedList<Page> pages;
	private String chemin;

	public Document(User creator, String name) {
		this.nom = name;
		this.collaborateurs = new ArrayList<>();
		this.pages = new LinkedList<>();
		this.collaborateurs.add(creator);
		this.derniereModification = new Date(System.currentTimeMillis());
	}

	/**
	 * Constructeur qui va chercher un document enregistré sur le système
	 * @param chemin Chemin d'accès
	 * @param utilisateur Utilisateur du document
	 * @author Bruno ROMAIN
	 */
	public Document(String chemin, User utilisateur) throws FileNotFoundException {
		String contenuFichier;
		String[] contenusPages;
		File fichier = FileUtils.getFile(chemin);
		
		// Vérification
		if (fichier == null)
			throw new FileNotFoundException("Le fichier spécifié : " + chemin + " est introuvable");
		
		// Initialisations
		this.chemin = chemin;
		this.nom = fichier.getName();
		this.collaborateurs = new ArrayList<User>();
		this.pages = new LinkedList<Page>();
		this.derniereModification = new Date(System.currentTimeMillis());
		
		// Récupération du contenu
		try {
			contenuFichier = FileUtils.readFileToString(fichier, "UTF-8");
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		// Récupération page par page
		int numeroPage = 0;
		contenusPages = contenuFichier.split("\f");
		for (String contenuPage : contenusPages) {
			String ajout = "ADD\b";
			ajout += numeroPage + "\b";
			ajout += 0 + "\b";
			ajout += contenuPage;
			
			// Ajout
			Page nouvellePage = new Page(utilisateur);
			nouvellePage.insererChaine(0, contenuPage);
			pages.add(nouvellePage);
		}
	}

	public Document(User creator){
		this(creator, "Nouveau document");
	}

	public int getId() {
		return id;
	}
	
	public String getChemin() {
		return chemin;
	}

	public Date getDerniereModification() {
		return derniereModification;
	}

	public String getNom() {
		return nom;
	}
	
	public int getNombrePage() {
		return pages.size();
	}
	
	public void setDerniereModification(Date date) {
		this.derniereModification = date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String toString() {
		String contenu = "";
		
		for (int i = 0; i < pages.size() - 1; i++)
			contenu += pages.get(i) + "\f";
		contenu += pages.get(pages.size() - 1);
		
		return contenu;
	}
	
	/**
	 * Retourne la page du numéro donné
	 * @param numero Numéro de page
	 * @return Page associé au numéro de page
	 */
	public Page getPage(int numero) {
		return pages.get(numero);
	}
	
	
	/**
	 * Applique la modification au document
	 * @param modification Message formatté contenant les infos sur la modification à appliquer
	 */
	public void appliquerModification(String modification) {
		// Vérification
		if (modification == null)
			throw new IllegalArgumentException("Le message de modification ne pas être nul");
		
		// On récupère les infos du message
		// // Vérificateur du respect du format attendu
		boolean estBonFormat = true;
		StringTokenizer modificationTokenized = new StringTokenizer(modification, "\b");
		String typeModification = "", modificationAAppliquer = "";
		
		int numeroPage = 0, modificationPos = 0;
		
		// Type de modification
		if (estBonFormat = modificationTokenized.hasMoreTokens())
			typeModification = modificationTokenized.nextToken();
		
		// Numéro de page
		if (estBonFormat = modificationTokenized.hasMoreTokens())
			numeroPage = Integer.valueOf(modificationTokenized.nextToken());
			
		// Position de départ de la modification dans la page
		if (estBonFormat = modificationTokenized.hasMoreTokens())
			modificationPos = Integer.valueOf(modificationTokenized.nextToken());
	
		// Contenu de la modification 
		// (SI 'ADD' ALORS chaineAAjouter SINON SI 'DELETE' ALORS longueurSuppression)
		if (estBonFormat = modificationTokenized.hasMoreTokens())
			modificationAAppliquer = modificationTokenized.nextToken();
		
		// Récupèration de la page à modifier
		Page pageAModifier = pages.get(numeroPage);
		
		System.err.println("- " + pageAModifier.toString());
		
		// Application de la modification
		if (estBonFormat = typeModification.equalsIgnoreCase("ADD"))
			pageAModifier.insererChaine(modificationPos, modificationAAppliquer);
		else if (estBonFormat = typeModification.equalsIgnoreCase("DEL"))
			pageAModifier.supprimerChaine(modificationPos, Integer.parseInt(modificationAAppliquer));
		
		// Vérification
		if (!estBonFormat)
			throw new IllegalArgumentException("Le message ne respecte pas le format attendu !");
	}

	/**
	 * Met à jour la date de dernière modification du document
	 */
	public void majDerniereModification() {
		this.derniereModification = new Date(System.currentTimeMillis());
	}
	
	/**
	 * Retourne le créateur du document
	 * @return
	 */
	public User getCreator() {
		return collaborateurs.get(0);
	}

	/**
	 * Méthode pour ajouter une nouvelle page vide
	 * @author Bruno ROMAIN
	 */
	public void inserePage() {
		pages.add(new Page(this.getCreator()));
		majDerniereModification();
	}

	/**
	 * Insère une page en position donnée
	 * Si la position est erronée, la page se rajoutera 
	 * au début du document ou à la fin en fonction de l'indice
	 * @param pos Position où insérer la page
	 */
	public void insererPage(int pos) {
		if (pos >= this.pages.size())
			pages.addLast(new Page(this.getCreator()));
 		else if (pos < 0)
			pages.addFirst(new Page(this.getCreator()));
		else
			pages.add(pos, new Page(this.getCreator()));
		majDerniereModification();
	}

	/**
	 * Fonction permettant de supprimer une page de numéro donné
	 * @param pos Numéro de page à supprimer
	 */
	public void supprimerPage(int pos) {
		if (pos < 0 || pos >= pages.size())
			throw new IllegalArgumentException("La position doit être valide, donc comprise entre 0 et"
				+ "le nombre de pages total - 1");
		
		pages.remove(pos);
		this.majDerniereModification();
	}

	/**
	 * Ajoute un collaborateur à la liste des collaborateurs connectés à la session
	 * @param collaborateur Collaborateur à ajouter
	 */
	public void ajouterCollaborateur(User collaborateur) {
		if (collaborateur == null)
			throw new IllegalArgumentException("Le collaborateur à ajouter ne peut pas être nul");
			
		collaborateurs.add(collaborateur);
	}

	/**
	 * Supprimer un collaborateur de la liste des collaborateurs connectés à la session
	 * @param collaborateur Collaborateur à enlever
	 */
	public void enleverCollaborateurs(User collaborateur) {
		collaborateurs.remove(collaborateur);
	}

	/**
	 * Supprime tous les collaborateurs à l'exception du créateur
	 */
	public void emptyCollaborators() {
		User createur = this.getCreator();
		
		collaborateurs = new ArrayList<>();
		collaborateurs.add(createur);
	}

	public boolean estCollaborateur(User u) {
		return collaborateurs.contains(u);
	}

	public void saveDocument(String chemin) {
		
	}


}
