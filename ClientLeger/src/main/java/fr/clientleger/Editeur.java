package fr.clientleger;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;

import connexion.DbManager;
import connexion.ParamBD;
import connexion.User;
import connexion.document.Document;

@WebServlet(name = "Editeur", value = "/editeur")
public class Editeur extends HttpServlet {
    private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Gson gson = new Gson();

        if(session == null || session.getAttribute("user") == null) {
            session.setAttribute("errorRedirect", "inaccessible");
            response.sendRedirect("index");
            return;
        }

        User u = (User)session.getAttribute("user");
        if(u.isGuest()) {
            session.setAttribute("guest", "true");
        } else if(!(DbManager.IsUserValid(u))) {
            session.setAttribute("errorRedirect", "weird");
            response.sendRedirect("index");
            return;
        } else {
            LinkedList<Document> documents = DbManager.loadAllDocuments(u);
            String documentsJson = gson.toJson(documents);
            String escapeDocumentsJson = URLEncoder.encode(documentsJson,StandardCharsets.UTF_8.toString());
            session.setAttribute("documentsJson", escapeDocumentsJson);
            System.out.println(documentsJson);
        }

        String userJson = gson.toJson(u);
        String escapedUserJson = URLEncoder.encode(userJson, StandardCharsets.UTF_8.toString());
        System.out.println(userJson);
        System.out.println(escapedUserJson);
        session.setAttribute("userJson", escapedUserJson);
        session.setAttribute("user", u);
        request.getRequestDispatcher("WEB-INF/jsp/editeur.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String deconnexion = request.getParameter("deconnexion");
        if(deconnexion != null) {
            System.out.println("deconnexion");
            session.invalidate();

            session = request.getSession();
            session.setAttribute("deconnexion", "true");
            response.sendRedirect("index");
            return;
        }
        doGet(request, response);
    }
}
