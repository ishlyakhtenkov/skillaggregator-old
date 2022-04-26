package ru.igar15.skillsaggregator.aggregator;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ru.igar15.skillsaggregator.model.SkillReport;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.igar15.skillsaggregator.model.Selection.FIRST_100_VACANCIES;
import static ru.igar15.skillsaggregator.testdata.SkillReportTestData.skillCounter;

class SkillAggregatorTest {

    @Test
    void someTest() throws IOException {
        mockHtmlPageLoader();
        SkillAggregator skillAggregator = new SkillAggregator("java", "moscow", FIRST_100_VACANCIES);
        SkillReport skillReport = skillAggregator.makeSkillReport();
        assertEquals("java", skillReport.getProfessionName());
        assertEquals("moscow", skillReport.getCity());
        assertEquals(100, skillReport.getAnalyzedVacanciesAmount());
        assertEquals(skillCounter, skillReport.getSkillCounter());
    }

    private void mockHtmlPageLoader() throws IOException {
        MockedStatic<HtmlPageLoader> mockedPageLoader = Mockito.mockStatic(HtmlPageLoader.class);
        String vacanciesPage1Url = String.format(SkillAggregator.VACANCIES_PAGE_URL_PATTERN, "java", "moscow", 0);
        String vacanciesPage2Url = String.format(SkillAggregator.VACANCIES_PAGE_URL_PATTERN, "java", "moscow", 1);
        File vacanciesPageFile = new File("src/test/resources/html/vacancies_page.html");
        mockedPageLoader.when(() -> HtmlPageLoader.loadPage(vacanciesPage1Url)).thenReturn(Jsoup.parse(vacanciesPageFile, "UTF-8"));
        mockedPageLoader.when(() -> HtmlPageLoader.loadPage(vacanciesPage2Url)).thenReturn(Jsoup.parse(vacanciesPageFile, "UTF-8"));

        String vacancyPagesDirectoryPath = "src/test/resources/html/vacancyPages/";
        String[] vacancyPageFileNames = new File(vacancyPagesDirectoryPath).list();
        for (String vacancyPageFileName : vacancyPageFileNames) {
            String vacancyId = vacancyPageFileName.replace(".html", "");
            String vacancyUrl = String.format(SkillAggregator.VACANCY_PAGE_URL_PATTERN, vacancyId);
            File vacancyPageFile = new File(vacancyPagesDirectoryPath + vacancyPageFileName);
            mockedPageLoader.when(() -> HtmlPageLoader.loadPage(vacancyUrl)).thenReturn(Jsoup.parse(vacancyPageFile, "UTF-8"));
        }
    }
}