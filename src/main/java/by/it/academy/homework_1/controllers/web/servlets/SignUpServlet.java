package by.it.academy.homework_1.controllers.web.servlets;

import by.it.academy.homework_1.service.StorageUserService;
import by.it.academy.homework_1.service.api.IStorageUserService;
import by.it.academy.homework_1.service.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

//регистрация
@WebServlet(name = "SignUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {

    private final IStorageUserService storage;
    private final String USER_KEY = "user";
    private final String INFORM_KEY = "inf";
    private final String URL_FORWARD_1_KEY = "/views/signUp.jsp";
    private final String URL_FORWARD_2_KEY = "/views/mainPage.jsp";

    public SignUpServlet() {
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
        String name = req.getParameter("name");
        String birthdayRaw = req.getParameter("birthday");

        if (login == null || password == null ||
                name == null || birthdayRaw == null) {
            session.setAttribute(INFORM_KEY, "Не передан какой-либо параметр.");
            req.getRequestDispatcher(URL_FORWARD_1_KEY).forward(req, resp);
            return;
        }

        if (storage.isLoginExist(login)) {
            session.setAttribute(INFORM_KEY, "Такой логин занят:(");
            req.getRequestDispatcher(URL_FORWARD_1_KEY).forward(req, resp);
            return;
        }

        LocalDate birthday = LocalDate.parse(birthdayRaw);

        storage.addUserToStorage(new User(login, password, name, birthday));

        session.setAttribute(USER_KEY, login);
        session.setAttribute(INFORM_KEY, "Аккаунт успешно создан.");

        req.getRequestDispatcher(URL_FORWARD_2_KEY).forward(req, resp);
    }
}
