package fr.clientleger;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connexion.DbManager;
import connexion.ParamBD;
import connexion.User;


@WebServlet(name = "helloServlet", value="/index")
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
        ParamBD.init(this.getServletContext());
    }

    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
    }

    @Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pseudo = req.getParameter("pseudo");
        String mdp = req.getParameter("mot_de_passe");
        String invite = req.getParameter("invite");

        if(invite != null && invite.equals("true")) {
            User u = new User(1, "invité", "ok");
            u.setGuest(true);

            HttpSession ses = req.getSession();
            ses.setAttribute("user", u);
            resp.sendRedirect("editeur");
            return;
        }
        // test validité utilisateur
        User u = new User(1, pseudo, mdp);
        if(!(DbManager.IsUserValid(u))) {
            req.setAttribute("error", "pseudo");
            doGet(req, resp);
            return;
        }

        HttpSession ses = req.getSession();
        ses.setAttribute("user", u);

        resp.sendRedirect("editeur");
    }
}