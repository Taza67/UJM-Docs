package connexion;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.sql.Statement;



import connexion.document.Document;

public class DbManager extends ParamBD {

	private static Connection connection;


	public DbManager() {
		init();
	}


	public static void init() {
		ParamBD.init("src/main/webapp/WEB-INF/web.xml");
		String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		String jdbcUrl = ParamBD.bdURL;
		String jdbcLogin = ParamBD.bdLogin;
		String jdbcPassword = ParamBD.bdPassword;

		System.out.println("JDBC_DRIVER: " + "com.mysql.cj.jdbc.Driver");
		System.out.println("JDBC_URL: " + bdURL);
		System.out.println("JDBC_LOGIN: " + bdLogin);
		System.out.println("JDBC_PASSWORD: " + bdPassword);

		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcUrl, jdbcLogin, jdbcPassword);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {return connection;}

	public static User IsUserValid(String p, String pw) {
		int uid = -1;
		try {
			Connection c = DriverManager.getConnection(bdURL, bdLogin, bdPassword);
			String sql = "SELECT id, pseudo, mot_de_passe "
					+ "FROM utilisateur "
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
		} catch(SQLException e) {
			e.printStackTrace();
		}
		if(uid == -1) {
			System.out.println("Aucun utilisateur correspondant.");
			return null;
		}

		System.out.println("Utilisateur trouvé.");
		return new User(uid, p, pw);
	}

	/**
	 *  Fonction qui liste tous les documents enregistrés en base de donnée associé à un utilisateur, qu'il soit créateur ou collaborateur
	 * @param u l'utilisateur
	 * @return la liste de documents associé à l'utilisateur
	 * @author Bruno ROMAIN
	 */
	public static LinkedList<Document> loadAllDocuments(User u) {
		LinkedList<Document> library = new LinkedList<>();
		try {
			Connection c = DriverManager.getConnection(bdURL, bdLogin, bdPassword);
			String sql = "SELECT * FROM documents LEFT JOIN collaborateurs ON documents.id=collaborateurs.DOC "
					+ "LEFT JOIN utilisateur ON collaborateurs.USER=utilisateur.id WHERE documents.id_utilisateur=? OR collaborateurs.USER=?";
			PreparedStatement request = c.prepareStatement(sql);
			request.setInt(1, u.getId());
			request.setInt(2, u.getId());
			ResultSet rs = request.executeQuery();
			while(rs.next()) {
				Document doc = new Document(rs.getString("chemin"),u);
				doc.setNom(rs.getString("nom"));
				doc.setDerniereModification(rs.getDate("date_de_modification"));
				doc.setId(rs.getInt("id"));
				library.add(doc);
			}
			rs.close();
			c.close();
		} catch(SQLException | FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return library;
	}
	
	/**
	 *  Fonction qui liste tous les documents enregistrés en base de donnée
	 * @return la liste de tous les documents
	 * @author Bruno ROMAIN
	 */

	/*public static LinkedList<Document> loadAllDocuments() {
		LinkedList<Document> library = new LinkedList<>();
		try {
			Connection c = DriverManager.getConnection(bdURL, bdLogin, bdPassword);
			String sql = "SELECT * FROM documents LEFT JOIN collaborateurs ON documents.id=collaborateurs.DOC "
					+ "LEFT JOIN utilisateur ON collaborateurs.USER=utilisateur.id ";
			PreparedStatement request = c.prepareStatement(sql);
			ResultSet rs = request.executeQuery();
			while(rs.next()) {
				Document doc = new Document(rs.getString("chemin"));
				System.err.println("\n\nLe chemin du document est " + rs.getString("chemin") + "\n\n");
				doc.setName(rs.getString("nom"));
				doc.setLasModifDate(rs.getDate("date_de_modification"));
				doc.setID(rs.getInt("id"));
				library.add(doc);
				System.err.println(doc);
			}
			rs.close();
			c.close();
		} catch(SQLException | FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return library;
	}*/

	/**
	 * Fonction qui sauvegarde un document dans la base de donnée
	 * @param document le document à sauvegarder
	 * @return vrai si la requête réussi et faux sinon
	 */
	public static boolean saveDocument(Document document) {
		try {
			Connection c = DriverManager.getConnection(bdURL, bdLogin, bdPassword);
			String sql = "INSERT INTO documents (id_utilisateur, date_de_modification, chemin, nom) VALUES (?, ?, ?, ?)";
			PreparedStatement request = c.prepareStatement(sql);
			request.setInt(1, document.getCreator().getId());
			request.setDate(2, document.getDerniereModification());
			request.setString(3, document.getChemin());
			request.setString(4, document.getNom());
			ResultSet rs = request.executeQuery();
			rs.last();
			return rs.getRow() >= 1;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public static ArrayList<User> getUserListFromDB() {
		ArrayList<User> users = new ArrayList<>();
		try {
			Connection c = DriverManager.getConnection(bdURL, bdLogin, bdPassword);
			String sql  = "SELECT * FROM utilisateur;";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				users.add(new User(rs.getInt("id"), rs.getString("pseudo"), rs.getString("mot_de_passe")));
			}
			rs.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public static boolean IsUserValid(User u) {
		int uid = -1;
		try {
			Connection c = DriverManager.getConnection(bdURL, bdLogin, bdPassword);
			String sql = "SELECT id, pseudo, mot_de_passe "
					+ "FROM utilisateur "
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
		} catch(SQLException e) {
			e.printStackTrace();
		}
		if(uid == -1) {
			System.out.println("Aucun utilisateur correspondant.");
			return false;
		}

		System.out.println("Utilisateur trouvé.");
		u.setId(uid);
		return true;
	}


	public static void AddUser(User u) {
		int ligne = -1;
		try {
			Connection c = DriverManager.getConnection(bdURL, bdLogin, bdPassword);
			String sql = "INSERT INTO utilisateur "
					+ "(pseudo,mot_de_passe) "+
					"VALUES (?,?);";
			PreparedStatement requete = c.prepareStatement(sql);
			if(u.getPseudo() == null || u.getPassword() == null) {
				System.out.println("Le pseudo et le mot de passe sont nuls (fichier DbManager).");
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


	/*public static void addDocument(User u, Document d) {
		int ligne = -1;
		try {
			Connection c = DriverManager.getConnection(bdURL, bdLogin, bdPassword);
			String sql = "INSERT INTO documents "
					+ "(id_utilisateur,date_de_modification,chemin) "+
					"VALUES (?,?,?);";
			PreparedStatement requete = c.prepareStatement(sql);
			if(u.getPseudo() == null || u.getPassword() == null) {
				System.out.println("Le pseudo et le mot de passe sont nuls (DbManager(addDocument)).");
				requete.close();
				c.close();
				return;
			}
			else {
				requete.setInt(1,u.getId());
				requete.setDate(2, d.getLastModifDate());
				requete.setString(3,u.getPassword());
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
		
	}*/
	
	/**
	 * Methode qui permet l'ajout d'un document appartenant
	 * à un utilisateur dans la base de données
	 * @return identifiant du document dans la table documents
	 */
	public static int addDocument(User u, Document d) {
	    int id = 0;
	    try {
	        Connection c = DriverManager.getConnection(bdURL, bdLogin, bdPassword);
	        String sql = "INSERT INTO documents "
	                + "(id_utilisateur, date_de_modification, chemin) " +
	                "VALUES (?, ?, ?);";
	        PreparedStatement requete = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        if (u.getPseudo() == null || u.getPassword() == null) {
	            System.out.println("Le pseudo et le mot de passe sont nuls (DbManager(addDocument)).");
	            requete.close();
	            c.close();
	            return id;
	        } else {
	            requete.setInt(1, u.getId());
	            requete.setDate(2, d.getDerniereModification());
	            requete.setString(3, u.getPassword());
	        }
	        int rowsAffected = requete.executeUpdate();
	        if (rowsAffected == 1) {
	            ResultSet rs = requete.getGeneratedKeys();
	            if (rs.next()) {
	                id = rs.getInt(1);
	            }
	        }
	        requete.close();
	        c.close();

	        if (id == 0) {
	            System.out.println("ERREUR INSERTION");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return id;
	}




}
