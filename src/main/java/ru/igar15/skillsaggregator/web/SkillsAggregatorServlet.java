package ru.igar15.skillsaggregator.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.igar15.skillsaggregator.model.SkillsReport;
import ru.igar15.skillsaggregator.service.SkillsReportService;
import ru.igar15.skillsaggregator.util.SkillsReportUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class SkillsAggregatorServlet extends HttpServlet {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private SkillsReportService service;

    @Override
    public void init() {
        AnnotationConfigApplicationContext context =
                (AnnotationConfigApplicationContext) getServletContext().getAttribute("appContext");
        service = context.getBean(SkillsReportService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String city = req.getParameter("city");
        int selection = getSelection(req);
        log.info("get report for vacancy={}, city={}, selection={}", name, city, selection);
        if (name.isBlank()) {
            req.getRequestDispatcher("WEB-INF/jsp/noVacancies.jsp").forward(req, resp);
        } else {
            Optional<SkillsReport> report = service.getReportToday(name, city, selection);
            if (report.isPresent()) {
                req.setAttribute("report", SkillsReportUtil.getTo(report.get()));
                req.getRequestDispatcher("WEB-INF/jsp/skillsReport.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("WEB-INF/jsp/noVacancies.jsp").forward(req, resp);
            }
        }
    }

    private int getSelection(HttpServletRequest req) {
        int selection;
        try {
            selection = Integer.parseInt(req.getParameter("selection"));
        } catch (NumberFormatException e) {
            selection = 2;
        }
        if (selection != 2 && selection != 10 && selection != 40) {
            selection = 2;
        }
        return selection;
    }
}