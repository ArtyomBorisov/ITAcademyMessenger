package by.it.academy.homework_1.controller.web.servlets;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.view.UserService;
import by.it.academy.homework_1.view.api.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {

    private final IUserService userService;
    private final String USER_KEY = "user";
    private final String INFORM_KEY = "inf";
    private final String URL_FORWARD_1_KEY = "/views/signUp.jsp";
    private final String URL_FORWARD_2_KEY = "/views/mainPage.jsp";

    public SignUpServlet() {
        this.userService = UserService.getInstance();
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
        String name = req.getParameter("name");
        String birthdayRaw = req.getParameter("birthday");
        LocalDate birthday = null;
        try {
            birthday = LocalDate.parse(birthdayRaw);
        } catch (DateTimeParseException e) {
        }

        User user = new User(login, password, name, birthday);

        try {
            userService.signUp(user);
            req.getSession().setAttribute(USER_KEY, login);
            session.setAttribute(INFORM_KEY, "Аккаунт успешно создан.");
            req.getRequestDispatcher(URL_FORWARD_2_KEY).forward(req, resp);
        } catch (IllegalArgumentException e){
            session.setAttribute(INFORM_KEY, e.getMessage());
            req.getRequestDispatcher(URL_FORWARD_1_KEY).forward(req, resp);
        }
    }
}
