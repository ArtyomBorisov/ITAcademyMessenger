package by.it.academy.homework_1.controller.web.servlets;

import by.it.academy.homework_1.view.StatisticsService;
import by.it.academy.homework_1.view.api.IStatisticsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StatisticsServlet", urlPatterns = "/statistics")
public class StatisticsServlet extends HttpServlet {

    private final IStatisticsService statisticsService;
    private final String STATS_KEY = "stats";
    private final String URL_FORWARD = "/views/statistics.jsp";

    public StatisticsServlet() {
        this.statisticsService = StatisticsService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        req.setAttribute(STATS_KEY, this.statisticsService.getStats());

        req.getRequestDispatcher(URL_FORWARD).forward(req, resp);
    }
}
