package ru.igar15.skillsaggregator.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.igar15.skillsaggregator.model.Selection;
import ru.igar15.skillsaggregator.model.SkillReport;
import ru.igar15.skillsaggregator.service.SkillReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SkillReportServlet extends HttpServlet {
    private static final String PROFESSION_NAME_PARAM = "professionName";
    private static final String CITY_PARAM = "city";
    private static final String SELECTION_PARAM = "selection";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private SkillReportService service;

    @Override
    public void init() {
        AnnotationConfigApplicationContext context =
                (AnnotationConfigApplicationContext) getServletContext().getAttribute("appContext");
        service = context.getBean(SkillReportService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String professionName = req.getParameter(PROFESSION_NAME_PARAM);
        String city = req.getParameter(CITY_PARAM);
        Selection selection = Selection.valueOf(req.getParameter(SELECTION_PARAM));
        log.info("get skill report for profession={}, city={}, selection={}", professionName, city, selection);
        if (professionName.isBlank()) {
            req.getRequestDispatcher("WEB-INF/jsp/noVacancies.jsp").forward(req, resp);
        } else {
            SkillReport skillReport = getSkillReport(professionName, city, selection);
            req.setAttribute("skillReport", skillReport);
            req.getRequestDispatcher("WEB-INF/jsp/skillReport.jsp").forward(req, resp);
        }
    }

    private SkillReport getSkillReport(String professionName, String city, Selection selection) throws IOException {
        try {
            return service.getSkillReportForToday(professionName.toUpperCase(), city.toUpperCase(), selection);
        } catch (Exception e) {
            log.error("Error when getting skill report for profession={}, city={}, selection={}. Cause={}",
                    professionName, city, selection, e.getMessage());
            throw e;
        }
    }
}