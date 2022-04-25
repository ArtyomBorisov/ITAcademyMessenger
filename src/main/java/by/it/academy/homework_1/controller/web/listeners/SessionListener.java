package by.it.academy.homework_1.controller.web.listeners;

import by.it.academy.homework_1.services.api.IStatisticsService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        final WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(
                httpSessionEvent.getSession().getServletContext());

        springContext.getBean(IStatisticsService.class).incSessionCount();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        final WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(
                httpSessionEvent.getSession().getServletContext());

        springContext.getBean(IStatisticsService.class).decSessionCount();
    }
}
