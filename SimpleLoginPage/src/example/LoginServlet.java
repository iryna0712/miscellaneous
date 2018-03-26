package example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (    Objects.equals(req.getSession().getAttribute("fullname"), req.getParameter("fullname")) &&
                Objects.equals(req.getSession().getAttribute("password"), req.getParameter("password"))
        ) {
            resp.sendRedirect("/postWelcome?page=loggedin");
        } else {
            resp.getWriter().println("Wrong credentials");
        }
    }
}
