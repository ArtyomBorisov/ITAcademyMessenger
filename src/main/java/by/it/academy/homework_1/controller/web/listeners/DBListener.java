package by.it.academy.homework_1.controller.web.listeners;

import by.it.academy.homework_1.storage.hibernate.api.HibernateDBInitializer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DBListener implements ServletContextListener {

    private HibernateDBInitializer hibernateDBInitializer = HibernateDBInitializer.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            hibernateDBInitializer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
