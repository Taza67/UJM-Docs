package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.Date;
import com.mysql.cj.xdevapi.Result;

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
	public int getId() {return id;}
	public int getposition() {return position;}
	public String getPseudo() {return pseudo;}
	public String getPassword() {return password;}

	// Setters
	public void setId(int id) {this.id = id;}
	public void setPosition(int position) {this.position = position;}
	public void setPseudo(String pseudo) {this.pseudo = pseudo;}
	public void setPassword(String password) {this.password = password;}
	
	
	
	
	
}
