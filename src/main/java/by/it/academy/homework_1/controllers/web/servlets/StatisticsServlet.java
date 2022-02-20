package by.it.academy.homework_1.controllers.web.servlets;

import by.it.academy.homework_1.service.CounterSession;
import by.it.academy.homework_1.service.StorageMessageService;
import by.it.academy.homework_1.service.StorageUserService;
import by.it.academy.homework_1.service.api.ICounter;
import by.it.academy.homework_1.service.api.IStorageMessageService;
import by.it.academy.homework_1.service.api.IStorageUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "StatisticsServlet", urlPatterns = "/statistics")
public class StatisticsServlet extends HttpServlet {

    private final IStorageMessageService storageMessage;
    private final IStorageUserService storageUser;
    private final ICounter counterSession;
    private final String MESSAGE_KEY = "messages";
    private final String USER_KEY = "users";
    private final String COUNTER_KEY = "counter";
    private final String URL_FORWARD = "/views/statistics.jsp";

    public StatisticsServlet() {
        this.storageMessage = StorageMessageService.getInstance();
        this.storageUser = StorageUserService.getInstance();
        this.counterSession = CounterSession.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        session.setAttribute(MESSAGE_KEY, storageMessage.getCountMessages());
        session.setAttribute(USER_KEY, storageUser.getCountUsers());
        session.setAttribute(COUNTER_KEY, counterSession.getCount());

        req.getRequestDispatcher(URL_FORWARD).forward(req, resp);
    }
}
