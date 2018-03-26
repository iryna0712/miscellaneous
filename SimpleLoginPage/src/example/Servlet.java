package example;

import java.io.IOException;


public class Servlet extends javax.servlet.http.HttpServlet {

    private static final String NOT_FOUND = "notfound";
    private static final String EXTENSION = ".jsp";

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String requestedPage = request.getParameter("page");
        String url = "/";

        switch (requestedPage) {
            //in case of recognized parameter, page name is the same as the parameter
            case "login":
            case "signup":
            case "about":
            case "loggedin":
                url += requestedPage + EXTENSION;
                break;
            default:
                url += NOT_FOUND + EXTENSION;
                break;
        }

        assert (!url.equals("/"));
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }
}
