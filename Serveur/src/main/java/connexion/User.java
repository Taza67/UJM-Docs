package connexion;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Identifiant de l'utilisateur
	 */
	private int id;
	/**
	 * Position du curseur de l'utilisateur
	 */

	private int position;
	/**
	 * Pseudo de l'utilisateur
	 */
	private String pseudo;

	/**
	 * Mot de passe de l'utilisateur
	 */
	private String password;

	/**
	 * Booleen indiquant si l'utilisateur
	 * est un invité ou non
	 */

	private boolean guest;
	
	/**
	 * Variable indiquant le numéro de page sur lequel se trouve un utilisateur
	 */
	private int pageNum;


	// Getters

	/**
	 * Retourne l'identifiant de l'utilisateur
	 * @return identifiant de l'utilisateur
	 *
	 * @see User#id
	 */

	public int getId() {return id;}

	/**
	 * Retourne la position de l'utilisateur
	 * @return la position de l'utilisateur
	 *
	 * @see User#position
	 */
	public int getposition() {return position;}

	/**
	 * Retourne le pseudo de l'utilisateur
	 * @return le pseudo de l'utilisateur
	 *
	 * @see User#pseudo
	 */
	public String getPseudo() {return pseudo;}

	/**
	 * Retourne le mot de passe de l'utilisateur
	 * @return le mot de passe de l'utilisateur
	 *
	 * @see User#password
	 */
	public String getPassword() {return password;}
	
	/**
	 * Retourne le n° de page de l'utilisateur
	 * @return le n° de page  de l'utilisateur
	 *
	 * @see User#pageNum
	 */
	public int getPageNum() {return pageNum;}


	// Setters

	/**
	 * Change la valeur de l'identifiant de l'utilisateur
	 * @param id le nouvel identifiant de l'utilisateur
	 *
	 * @see User#id
	 */
	public void setId(int id) {this.id = id;}

	/**
	 * Change la valeur de la position du curseur de l'utilisateur
	 * @param position la nouvelle position du curseur de l'utilisateur
	 *
	 * @see User#position
	 */
	public void setPosition(int position) {this.position = position;}

	/**
	 * Change le pseudo de l'utilisateur
	 * @param pseudo le nouveau pseudo de l'utilisateur
	 *
	 * @see User#pseudo
	 */
	public void setPseudo(String pseudo) {this.pseudo = pseudo;}

	/**
	 * Change le mot de passe de l'utilisateur
	 * @param password le nouveau mot de passe de l'utilisateur
	 *
	 * @see User#password
	 */
	public void setPassword(String password) {this.password = password;}

	/**
	 * Change le statut de l'uitilisateur
	 * @param state le nouveau de l'utilisateur (invité ou non)
	 *
	 * @see User#guest
	 */
	public void setGuest(boolean state) {this.guest = state;}

	/**
	 * Modifie le numéro de page l'uitilisateur
	 * @param pageNum le nouveau numéro de page de l'utilisateur
	 *
	 * @see User#pageNum
	 */
	public void setPageNum(int pageNum) {this.pageNum = pageNum;}
	
	/**
	 * Constructeur vide assurant le fonctionnement correcte des javaBean
	 */
	public User() {

	}
	
	
	public User(int idt, String p, String mdp) {
		this.pageNum = 0;
		this.id = idt;
		this.pseudo = p;
		this.password = mdp;
		position = 0;
		guest = false;
	}


	public User(int idt, String p, String mdp, boolean guest) {
		this(idt, p, mdp);
		this.guest = guest;
	}

	/**
	 * Indique si l'utilisateur est connecté ou non
	 * @see User#guest
	 */
	public boolean isGuest() {return guest;}

	public static void AddUser() {

	}


}
