package by.it.academy.homework_1.controllers.web.servlets;

import by.it.academy.homework_1.service.StorageMessageService;
import by.it.academy.homework_1.service.api.IStorageMessageService;
import by.it.academy.homework_1.service.dto.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MessageServlet", urlPatterns = "/message")
public class MessageServlet extends HttpServlet {

    private final IStorageMessageService storage;
    private final String USER_KEY = "user";
    private final String INFORM_KEY = "inf";
    private final String URL_FORWARD_KEY_1 = "/views/mainPage.jsp";
    private final String URL_FORWARD_KEY_2 = "/views/message.jsp";

    public MessageServlet() {
        this.storage = StorageMessageService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(URL_FORWARD_KEY_2).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);

        String loginFrom = (String) session.getAttribute(USER_KEY);
        String loginTo = req.getParameter("loginTo");
        String messageText = req.getParameter("messageText");

        storage.addToStorage(new Message(loginFrom, loginTo, messageText));

        session.setAttribute(INFORM_KEY, "Сообщение отправлено");

        req.getRequestDispatcher(URL_FORWARD_KEY_1).forward(req, resp);
    }
}