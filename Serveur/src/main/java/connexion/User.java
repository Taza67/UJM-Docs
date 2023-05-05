package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.Date;
import com.mysql.cj.xdevapi.Result;

import communication.AttackInformations;
import inside.Board;

public class User {
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

	// Setters
	
	/**
	 * Change la valeur de l'identifiant de l'utilisateur
	 * @param le nouvel identifiant de l'utilisateur
	 * 
	 * @see User#id
	 */
	public void setId(int id) {this.id = id;}
	
	/**
	 * Change la valeur de la position du curseur de l'utilisateur
	 * @param la nouvelle position du curseur de l'utilisateur
	 * 
	 * @see User#position
	 */
	public void setPosition(int position) {this.position = position;}

	/**
	 * Change le pseudo de l'utilisateur
	 * @param le nouveau pseudo de l'utilisateur
	 * 
	 * @see User#pseudo
	 */
	public void setPseudo(String pseudo) {this.pseudo = pseudo;}

	/**
	 * Change le mot de passe de l'utilisateur
	 * @param le nouveau mot de passe de l'utilisateur
	 * 
	 * @see User#password
	 */
	public void setPassword(String password) {this.password = password;}
	
	
}
