package by.it.academy.homework_1.controllers.web.servlets;

import by.it.academy.homework_1.service.StorageUserService;
import by.it.academy.homework_1.service.api.IStorageUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SignInServlet", urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {

    private final IStorageUserService storage;
    private final String USER_KEY = "user";
    private final String INFORM_KEY = "inf";
    private final String URL_FORWARD_1_KEY = "/views/signIn.jsp";
    private final String URL_FORWARD_2_KEY = "/views/mainPage.jsp";

    public SignInServlet() {
        this.storage = StorageUserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(URL_FORWARD_1_KEY).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (!storage.authenticationUser(login, password)) {
            session.setAttribute(INFORM_KEY, "Логин и(или) пароль неверные(ый)");
            req.getRequestDispatcher(URL_FORWARD_1_KEY).forward(req, resp);
        } else {
            session.setAttribute(USER_KEY, login);
            req.getRequestDispatcher(URL_FORWARD_2_KEY).forward(req, resp);
        }
    }
}