package by.it.academy.homework_1.controller.web.servlets;

import by.it.academy.homework_1.storage.StorageMessage;
import by.it.academy.homework_1.storage.api.IStorageMessage;
import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.view.MessageService;
import by.it.academy.homework_1.view.api.IMessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "MessageServlet", urlPatterns = "/message")
public class MessageServlet extends HttpServlet {

    private final IMessageService messageService;
    private final String USER_KEY = "user";
    private final String INFORM_KEY = "inf";
    private final String URL_FORWARD_KEY_1 = "/views/mainPage.jsp";
    private final String URL_FORWARD_KEY_2 = "/views/message.jsp";

    public MessageServlet() {
        this.messageService = MessageService.getInstance();
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

        messageService.add(new Message(loginFrom, loginTo, messageText, LocalDateTime.now()));

        session.setAttribute(INFORM_KEY, "Сообщение отправлено");

        req.getRequestDispatcher(URL_FORWARD_KEY_1).forward(req, resp);
    }
}
