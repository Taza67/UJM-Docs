package fr.clientleger;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connexion.DbManager;
import connexion.User;

@WebServlet(name = "Editeur", value = "/editeur")
public class Editeur extends HttpServlet {
    private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session == null || session.getAttribute("user") == null) {
            session.setAttribute("error", "inaccessible");
            response.sendRedirect("");
            return;
        }

        User u = (User)session.getAttribute("user");

        if(!(DbManager.IsUserValid(u))) {
            session.setAttribute("error", "weird");
            response.sendRedirect("");
            return;
        }
        if(u.isGuest()) {
            session.setAttribute("guest", "true");
        }

        request.getRequestDispatcher("WEB-INF/jsp/editeur.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
