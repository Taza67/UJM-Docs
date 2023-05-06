package connexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.Date;


import java.util.HashMap;
import java.util.List;
import java.sql.Date;
import com.mysql.cj.xdevapi.Result;



public class DbManager extends ParamBD{

	private volatile HashMap<Integer, User> UserList;

	public DbManager() {
		UserList = new HashMap();
	}

	public HashMap<Integer, User> getUserList() {
		return UserList;
	}

	public static User IsUserValid(String p, String pw) {
		int uid = -1;
		try {
			Connection c = DriverManager.getConnection(bdURL, bdLogin, bdPassword);
			String sql = "SELECT id, pseudo,mot_de_passe"
					+ "FROM utilisateur"
					+ "WHERE pseudo = ? "
					+ "AND mot_de_passe = ?;";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, p);
			pst.setString(2, pw);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				uid = rs.getInt("id");
			}
			rs.close();
			pst.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		if(uid == -1) {
			System.out.println("Aucun utilisateur correspondant.");
			return null;
		}
		else {
			System.out.println("Utilisateur trouvé.");
			return new User(uid, p,pw);
		}
	}
	
	
	public static boolean IsUserValid(User u) {
		int uid = -1;
		try {
			Connection c = DriverManager.getConnection(bdURL, bdLogin, bdPassword);
			String sql = "SELECT id, pseudo,mot_de_passe"
					+ "FROM utilisateur"
					+ "WHERE pseudo = ? "
					+ "AND mot_de_passe = ?;";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, u.getPseudo());
			pst.setString(2, u.getPassword());
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				uid = rs.getInt("id");
			}
			rs.close();
			pst.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		if(uid == -1) {
			System.out.println("Aucun utilisateur correspondant.");
			return false;
		}
		else {
			System.out.println("Utilisateur trouvé.");
			return true;
		}
	}


	public void AddUser(User u) {
		int ligne = -1;
		try {
			Connection c = DriverManager.getConnection(bdURL, bdLogin, bdPassword);
			String sql = "INSERT utilisateur "
					+ "(pseudo,mot_de_passe) "+
					"VALUES (?,?);";
			PreparedStatement requete = c.prepareStatement(sql);
			if(u.getPseudo() == null || u.getPassword() == null) {
				System.out.println("Le pseudo et le mot de passe sont nul (fichier DbManager).");
				requete.close();
				c.close();
				return;
			}
			else {
				requete.setString(1,u.getPseudo());
				requete.setString(2,u.getPassword());
			}
			ligne = requete.executeUpdate();
			requete.close();
			c.close();

			if(ligne==-1) {
				System.out.println("ERREUR INSERTION");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	






}
