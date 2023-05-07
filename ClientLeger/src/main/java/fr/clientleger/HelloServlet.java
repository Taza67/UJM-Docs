package fr.clientleger;

import connexion.DbManager;
import connexion.ParamBD;
import connexion.User;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet")
public class HelloServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
	public void init() {
        ParamBD.init(this.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pseudo = req.getParameter("pseudo");
        String mdp = req.getParameter("mot_de_passe");
        String invite = req.getParameter("invite");

        if(invite != null && invite.equals("true")) {

            req.setAttribute("invite", "true");

            doGet(req, resp);
            return;
        }
        // test validité utilisateur
        User u;
        if((u = DbManager.IsUserValid(pseudo, mdp)) == null) {
            req.setAttribute("error", "pseudo");
            doGet(req, resp);
            return;
        }

        HttpSession ses = req.getSession();
        ses.setAttribute("user", u);
        resp.sendRedirect("editeur");
    }
    public void destroy() {
    }
}