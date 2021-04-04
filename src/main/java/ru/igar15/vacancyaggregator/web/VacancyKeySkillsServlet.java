package ru.igar15.vacancyaggregator.web;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.igar15.vacancyaggregator.model.VacancyKeySkillsReport;
import ru.igar15.vacancyaggregator.service.VacancyKeySkillsReportService;
import ru.igar15.vacancyaggregator.util.VacancyKeySkillsReportUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VacancyKeySkillsServlet extends HttpServlet {

    private VacancyKeySkillsReportService service;

    @Override
    public void init() throws ServletException {
        AnnotationConfigApplicationContext context =
                (AnnotationConfigApplicationContext) getServletContext().getAttribute("appContext");
        service = context.getBean(VacancyKeySkillsReportService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String city = req.getParameter("city");
        VacancyKeySkillsReport report = service.getReportToday(name, city);
        req.setAttribute("report", VacancyKeySkillsReportUtil.getTo(report));
        req.getRequestDispatcher("WEB-INF/jsp/keySkillsReport.jsp").forward(req, resp);
    }
}