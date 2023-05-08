package fr.clientleger;

import connexion.DbManager;
import connexion.ParamBD;
import connexion.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Inscription", value = "/inscription")
public class Inscription extends HttpServlet {

    private static final long serialVersionUID = 1L;

	public void init(){
        ParamBD.init(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/inscription.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pseudo = request.getParameter("pseudo");
        String mdp = request.getParameter("mot_de_passe");
        String verif = request.getParameter("verification");

        User u;

        if(!mdp.equals(verif)) {
            request.setAttribute("error", "diff");

            doGet(request, response);
            return;
        }

        if(DbManager.IsUserValid(pseudo, mdp)!= null) {
            request.setAttribute("error", "exists");
            doGet(request, response);
        }

        DbManager.AddUser(new User(1, pseudo, mdp));

        request.getSession().setAttribute("inscrit", "true");

        response.sendRedirect("");

    }
}
