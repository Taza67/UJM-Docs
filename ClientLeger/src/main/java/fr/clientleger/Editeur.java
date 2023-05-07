package fr.clientleger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Editeur", value = "/editeur")
public class Editeur extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session == null || session.getAttribute("user") == null) {
            session.setAttribute("error", "inaccessible");
            response.sendRedirect("");
            return;
        }

        User u = (User)session.getAttribute("user");

        if(!(DbManager.isUserValid(u))) {
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
