package by.it.academy.homework_1.controllers.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ExitServlet", urlPatterns = "/exit")
public class ExitServlet extends HttpServlet {

    private final String URL_FORWARD_KEY = "/views/mainPage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        session.invalidate();

        req.getRequestDispatcher(URL_FORWARD_KEY).forward(req, resp);
    }
}
