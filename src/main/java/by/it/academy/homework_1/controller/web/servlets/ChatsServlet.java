package by.it.academy.homework_1.controller.web.servlets;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.storage.StorageMessage;
import by.it.academy.homework_1.storage.api.IStorageMessage;
import by.it.academy.homework_1.view.MessageService;
import by.it.academy.homework_1.view.api.IMessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChatsServlet", urlPatterns = "/chats")
public class ChatsServlet extends HttpServlet {

    private final IMessageService messageService;
    private final String USER_KEY = "user";
    private final String MESSAGES_KEY = "messages";
    private final String URL_FORWARD_KEY = "/views/chats.jsp";

    public ChatsServlet() {
        this.messageService = MessageService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        String loginTo = (String) session.getAttribute(USER_KEY);

        List<Message> messagesToUser = this.messageService.get(loginTo);

        session.setAttribute(MESSAGES_KEY, messagesToUser);

        req.getRequestDispatcher(URL_FORWARD_KEY).forward(req, resp);
    }
}
