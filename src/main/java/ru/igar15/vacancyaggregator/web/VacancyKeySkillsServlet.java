package ru.igar15.vacancyaggregator.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.igar15.vacancyaggregator.model.VacancyKeySkillsReport;
import ru.igar15.vacancyaggregator.service.VacancyKeySkillsReportService;
import ru.igar15.vacancyaggregator.util.VacancyKeySkillsReportUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class VacancyKeySkillsServlet extends HttpServlet {
    private final Logger log = LoggerFactory.getLogger(getClass());

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
        int selection = Integer.parseInt(req.getParameter("selection"));
        log.info("get report for vacancy={}, city={}, selection={}", name, city, selection);
        Optional<VacancyKeySkillsReport> report = service.getReportToday(name, city, selection);
        if (report.isPresent()) {
            req.setAttribute("report", VacancyKeySkillsReportUtil.getTo(report.get()));
            req.getRequestDispatcher("WEB-INF/jsp/keySkillsReport.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("WEB-INF/jsp/notFound.jsp").forward(req, resp);
        }
    }
}