package ru.igar15.skillsaggregator.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        sce.getServletContext().setAttribute("appContext", context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        AnnotationConfigApplicationContext appContext =
                (AnnotationConfigApplicationContext) sce.getServletContext().getAttribute("appContext");
        appContext.close();
    }
}