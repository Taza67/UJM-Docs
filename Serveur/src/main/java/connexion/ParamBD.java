package connexion;

import javax.servlet.ServletContext;

public class ParamBD {
	
	protected static String bdURL;
	protected static String bdLogin;
	protected static String bdPassword;

	protected static void init(ServletContext context) {
		try {
			Class.forName(context.getInitParameter("JDBC_DRIVER"));
			bdURL = context.getInitParameter("JDBC_URL");
			bdLogin = context.getInitParameter("JDBC_LOGIN");
			bdPassword = context.getInitParameter("JDBC_PASSWORD");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
