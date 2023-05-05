package connexion;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = "/login", loadOnStartup = 1)
public class Login extends HttpServlet{
	
		private static final long serialVersionUID = 1L;

		public void init() {
			ParamBD.init(this.getServletContext());
		}


		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			// si la personne est déjà connectée, on l'envoie sur sa TODOList
			HttpSession ses = req.getSession();
		
		}


		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			}
		}
}


