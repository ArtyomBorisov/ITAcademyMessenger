//package by.it.academy.homework_1.controller.web.listeners;
//
//import by.it.academy.homework_1.view.api.IStatisticsService;
//
//import javax.servlet.http.HttpSessionEvent;
//import javax.servlet.http.HttpSessionListener;
//
//public class SessionListener implements HttpSessionListener {
//
//    private final IStatisticsService statisticsService;
//
//    public SessionListener(IStatisticsService statisticsService) {
//        this.statisticsService = statisticsService;
//    }
//
//    @Override
//    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
//        statisticsService.incSessionCount();
//    }
//
//    @Override
//    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
//        statisticsService.decSessionCount();
//    }
//}
