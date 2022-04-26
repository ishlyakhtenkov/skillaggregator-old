package ru.igar15.skillsaggregator.testdata;

import org.jsoup.Jsoup;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ru.igar15.skillsaggregator.aggregator.HtmlPageLoader;
import ru.igar15.skillsaggregator.aggregator.SkillAggregator;
import ru.igar15.skillsaggregator.model.SkillReport;
import ru.igar15.skillsaggregator.to.SkillReportTo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static ru.igar15.skillsaggregator.model.Selection.FIRST_100_VACANCIES;

public class SkillReportTestData {
    public static final String JAVA_PROFESSION_NAME = "JAVA";
    public static final String SELLER_PROFESSION_NAME = "SELLER";
    public static final String NOT_EXISTED_PROFESSION_NAME = "XYZ_ABC";
    public static final String MOSCOW_CITY = "MOSCOW";
    public static final String LONDON_CITY = "LONDON";
    public static final int ONE_HUNDRED = 100;
    public static final String UTF_8 = "UTF-8";
    public static final String VACANCIES_PAGE_PATH_NAME = "src/test/resources/html/vacancies_page.html";
    public static final String NO_VACANCIES_PAGE_PATH_NAME = "src/test/resources/html/no_vacancies_page.html";
    public static final String VACANCY_PAGES_DIRECTORY_PATH = "src/test/resources/html/vacancyPages/";
    public static final String VACANCY_PAGE_FILE_EXTENSION = ".html";
    public static final int SKILL_REPORT_1_ID = 100000;
    public static final int SKILL_REPORT_2_ID = 100001;
    public static SkillReport skillReport1;
    public static SkillReport skillReport2;
    public static SkillReport vacancyTestPagesSkillReport;
    public static SkillReportTo skillReportTo;

    static {
        createSkillReports();
        createSkillReportTo();
        createVacancyTestPagesSkillReport();
    }

    private static void createSkillReports() {
        Map<String, Integer> skillReport1SkillCounter = Map.of("java", 100, "english", 25, "spring", 90, "hibernate", 70,
                "sql", 50, "docker", 10);
        skillReport1 = new SkillReport(SKILL_REPORT_1_ID, JAVA_PROFESSION_NAME, MOSCOW_CITY, LocalDate.now(), ONE_HUNDRED,
                skillReport1SkillCounter, FIRST_100_VACANCIES);
        Map<String, Integer> skillReport2SkillCounter = Map.of("sales", 80, "politeness", 70, "arithmetic", 20);
        skillReport2 = new SkillReport(SKILL_REPORT_2_ID, SELLER_PROFESSION_NAME, LONDON_CITY, LocalDate.now(), ONE_HUNDRED,
                skillReport2SkillCounter, FIRST_100_VACANCIES);
    }

    private static void createSkillReportTo() {
        Map<String, Integer> skillStatistic = new LinkedHashMap<>();
        skillStatistic.put("java", 100);
        skillStatistic.put("spring", 90);
        skillStatistic.put("hibernate", 70);
        skillStatistic.put("sql", 50);
        skillStatistic.put("english", 25);
        skillStatistic.put("docker", 10);
        skillReportTo = new SkillReportTo(JAVA_PROFESSION_NAME, MOSCOW_CITY, LocalDate.now(), ONE_HUNDRED,
                FIRST_100_VACANCIES, skillStatistic);
    }

    private static void createVacancyTestPagesSkillReport() {
        Map<String, Integer> vacancyTestPagesSkillCounter = new HashMap<>();
        vacancyTestPagesSkillCounter.put("JAVA", 82);
        vacancyTestPagesSkillCounter.put("STREAMING ALGORITHMS", 2);
        vacancyTestPagesSkillCounter.put("JAVA НЕ НИЖЕ 8", 2);
        vacancyTestPagesSkillCounter.put("MS ACCESS", 2);
        vacancyTestPagesSkillCounter.put("JAVA EE", 14);
        vacancyTestPagesSkillCounter.put("ORACLE", 8);
        vacancyTestPagesSkillCounter.put("QA", 2);
        vacancyTestPagesSkillCounter.put("КАДРОВЫЙ МЕНЕДЖМЕНТ", 2);
        vacancyTestPagesSkillCounter.put("УПРАВЛЕНИЕ КОМАНДОЙ", 2);
        vacancyTestPagesSkillCounter.put("NGINX", 4);
        vacancyTestPagesSkillCounter.put("SPRING SECURITY", 2);
        vacancyTestPagesSkillCounter.put("МОДУЛЬНОЕ ТЕСТИРОВАНИЕ", 2);
        vacancyTestPagesSkillCounter.put("АНГЛИЙСКИЙ — A2 — ЭЛЕМЕНТАРНЫЙ", 2);
        vacancyTestPagesSkillCounter.put("APACHE HTTP SERVER", 2);
        vacancyTestPagesSkillCounter.put("АНГЛИЙСКИЙ — A1 — НАЧАЛЬНЫЙ", 2);
        vacancyTestPagesSkillCounter.put("SPRING", 4);
        vacancyTestPagesSkillCounter.put("JSON API", 2);
        vacancyTestPagesSkillCounter.put("ТЕХНИЧЕСКИЙ ПЕРЕВОД", 2);
        vacancyTestPagesSkillCounter.put("MONGODB", 2);
        vacancyTestPagesSkillCounter.put("SPRING FRAMEWORK", 56);
        vacancyTestPagesSkillCounter.put("JENKINS", 2);
        vacancyTestPagesSkillCounter.put("MAVEN", 2);
        vacancyTestPagesSkillCounter.put("СУБД", 2);
        vacancyTestPagesSkillCounter.put("АНГЛИЙСКИЙ ЯЗЫК", 6);
        vacancyTestPagesSkillCounter.put("KAFKA", 6);
        vacancyTestPagesSkillCounter.put("ТЕСТИРОВАНИЕ", 2);
        vacancyTestPagesSkillCounter.put("NOSQL", 4);
        vacancyTestPagesSkillCounter.put("POSTGRESQL", 36);
        vacancyTestPagesSkillCounter.put("GRPC", 2);
        vacancyTestPagesSkillCounter.put("JAVASCRIPT", 6);
        vacancyTestPagesSkillCounter.put("АНГЛИЙСКИЙ — B1 — СРЕДНИЙ", 2);
        vacancyTestPagesSkillCounter.put("JAVA SE", 12);
        vacancyTestPagesSkillCounter.put("ОРИЕНТАЦИЯ НА РЕЗУЛЬТАТ", 4);
        vacancyTestPagesSkillCounter.put("SVN", 4);
        vacancyTestPagesSkillCounter.put("PYTHON", 2);
        vacancyTestPagesSkillCounter.put("ООП", 6);
        vacancyTestPagesSkillCounter.put("SOFTWARE DEVELOPMENT", 2);
        vacancyTestPagesSkillCounter.put("ДЕЛОВАЯ ПЕРЕПИСКА", 2);
        vacancyTestPagesSkillCounter.put("WILDFLY", 4);
        vacancyTestPagesSkillCounter.put("CONSUL", 2);
        vacancyTestPagesSkillCounter.put("ANSIBLE", 2);
        vacancyTestPagesSkillCounter.put("РЕФАКТОРИНГ КОДА", 2);
        vacancyTestPagesSkillCounter.put("APACHE MAVEN", 12);
        vacancyTestPagesSkillCounter.put("GWT", 2);
        vacancyTestPagesSkillCounter.put("UNIT TESTING", 4);
        vacancyTestPagesSkillCounter.put("BACKEND", 4);
        vacancyTestPagesSkillCounter.put("TEAMCITY", 2);
        vacancyTestPagesSkillCounter.put("BOOTSTRAP", 2);
        vacancyTestPagesSkillCounter.put("CI/CD", 2);
        vacancyTestPagesSkillCounter.put("DESIGN PATTERNS", 2);
        vacancyTestPagesSkillCounter.put("JQUERY", 2);
        vacancyTestPagesSkillCounter.put("KOTLIN", 6);
        vacancyTestPagesSkillCounter.put("LINUX", 14);
        vacancyTestPagesSkillCounter.put("HTML", 2);
        vacancyTestPagesSkillCounter.put("JIRA", 2);
        vacancyTestPagesSkillCounter.put("GRADLE", 2);
        vacancyTestPagesSkillCounter.put("ОБУЧЕНИЕ И РАЗВИТИЕ", 2);
        vacancyTestPagesSkillCounter.put("TOMCAT", 2);
        vacancyTestPagesSkillCounter.put("SPRING BOOT 2", 2);
        vacancyTestPagesSkillCounter.put("SCALA", 4);
        vacancyTestPagesSkillCounter.put("DOCKER", 12);
        vacancyTestPagesSkillCounter.put("MULTITHREAD PROGRAMMING", 2);
        vacancyTestPagesSkillCounter.put("SOAP", 8);
        vacancyTestPagesSkillCounter.put("HIBERNATE ORM", 30);
        vacancyTestPagesSkillCounter.put("UNIX", 2);
        vacancyTestPagesSkillCounter.put("SQL", 34);
        vacancyTestPagesSkillCounter.put("GIT", 38);
        vacancyTestPagesSkillCounter.put("UML", 2);
        vacancyTestPagesSkillCounter.put("СТРАТЕГИЧЕСКОЕ МЫШЛЕНИЕ", 2);
        vacancyTestPagesSkillCounter.put("JAVA 8", 2);
        vacancyTestPagesSkillCounter.put("SOAP/REST", 2);
        vacancyTestPagesSkillCounter.put("SWAGGER", 2);
        vacancyTestPagesSkillCounter.put("MVC", 2);
        vacancyTestPagesSkillCounter.put("ELASTIC SEARCH", 2);
        vacancyTestPagesSkillCounter.put("CASSANDRA", 2);
        vacancyTestPagesSkillCounter.put("JAVA SPRING FRAMEWORK", 2);
        vacancyTestPagesSkillCounter.put("JAVA SCRIPT, GIT,POSTGRESQL, ELASTIC SEARCH;", 2);
        vacancyTestPagesSkillCounter.put("RABBITMQ", 4);
        vacancyTestPagesSkillCounter.put("APACHE TOMCAT", 6);
        vacancyTestPagesSkillCounter.put("JSON", 2);
        vacancyTestPagesSkillCounter.put("ORM", 4);
        vacancyTestPagesSkillCounter.put("ORACLE PL/SQL", 2);
        vacancyTestPagesSkillCounter.put("INTELLIJ IDEA", 2);
        vacancyTestPagesSkillCounter.put("CИСТЕМЫ УПРАВЛЕНИЯ БАЗАМИ ДАННЫХ", 2);
        vacancyTestPagesSkillCounter.put("АНГЛИЙСКИЙ — B2 — СРЕДНЕ-ПРОДВИНУТЫЙ", 12);
        vacancyTestPagesSkillCounter.put("LUA", 2);
        vacancyTestPagesSkillCounter.put("АДМИНИСТРИРОВАНИЕ СЕРВЕРОВ LINUX", 2);
        vacancyTestPagesSkillCounter.put("IOC", 2);
        vacancyTestPagesSkillCounter.put("AWS", 2);
        vacancyTestPagesSkillCounter.put("ТЕЛЕФОННЫЕ ПЕРЕГОВОРЫ", 2);
        vacancyTestPagesSkillCounter.put("CSS", 2);
        vacancyTestPagesSkillCounter.put("MYSQL", 4);
        vacancyTestPagesSkillCounter.put("MS SQL SERVER", 2);
        vacancyTestPagesSkillCounter.put("REST", 16);
        vacancyTestPagesSkillCounter.put("JUNIT", 4);
        vacancyTestPagesSkillCounter.put("AMAZON WEB SERVICES", 2);
        vacancyTestPagesSkillCounter.put("ПАРУС", 2);
        vacancyTestPagesSkillCounter.put("XML", 4);
        vacancyTestPagesSkillCounter.put("KUBERNETES", 6);
        vacancyTestPagesSkillCounter.put("REDIS", 4);
        vacancyTestPagesSkillCounter.put("API", 4);
        vacancyTestPagesSkillCounter.put("РАБОТА В КОМАНДЕ", 2);
        vacancyTestPagesSkillCounter.put("РАЗРАБОТКА ПО", 2);
        vacancyTestPagesSkillReport = new SkillReport(JAVA_PROFESSION_NAME, MOSCOW_CITY, ONE_HUNDRED,
                vacancyTestPagesSkillCounter, FIRST_100_VACANCIES);
    }

    public static MockedStatic<HtmlPageLoader> mockHtmlPageLoader() throws IOException {
        MockedStatic<HtmlPageLoader> mockedPageLoader = Mockito.mockStatic(HtmlPageLoader.class);
        String vacanciesPage1Url = String.format(SkillAggregator.VACANCIES_PAGE_URL_PATTERN, JAVA_PROFESSION_NAME, MOSCOW_CITY, 0);
        String vacanciesPage2Url = String.format(SkillAggregator.VACANCIES_PAGE_URL_PATTERN, JAVA_PROFESSION_NAME, MOSCOW_CITY, 1);
        File vacanciesPageFile = new File(VACANCIES_PAGE_PATH_NAME);
        mockedPageLoader.when(() -> HtmlPageLoader.loadPage(vacanciesPage1Url)).thenReturn(Jsoup.parse(vacanciesPageFile, UTF_8));
        mockedPageLoader.when(() -> HtmlPageLoader.loadPage(vacanciesPage2Url)).thenReturn(Jsoup.parse(vacanciesPageFile, UTF_8));

        String[] vacancyPageFileNames = new File(VACANCY_PAGES_DIRECTORY_PATH).list();
        for (String vacancyPageFileName : vacancyPageFileNames) {
            String vacancyId = vacancyPageFileName.replace(VACANCY_PAGE_FILE_EXTENSION, "");
            String vacancyUrl = String.format(SkillAggregator.VACANCY_PAGE_URL_PATTERN, vacancyId);
            File vacancyPageFile = new File(VACANCY_PAGES_DIRECTORY_PATH + vacancyPageFileName);
            mockedPageLoader.when(() -> HtmlPageLoader.loadPage(vacancyUrl)).thenReturn(Jsoup.parse(vacancyPageFile, UTF_8));
        }

        String noVacanciesPage1Url = String.format(SkillAggregator.VACANCIES_PAGE_URL_PATTERN, NOT_EXISTED_PROFESSION_NAME, MOSCOW_CITY, 0);
        String noVacanciesPage2Url = String.format(SkillAggregator.VACANCIES_PAGE_URL_PATTERN, NOT_EXISTED_PROFESSION_NAME, MOSCOW_CITY, 1);
        File noVacanciesPageFile = new File(NO_VACANCIES_PAGE_PATH_NAME);
        mockedPageLoader.when(() -> HtmlPageLoader.loadPage(noVacanciesPage1Url)).thenReturn(Jsoup.parse(noVacanciesPageFile, UTF_8));
        mockedPageLoader.when(() -> HtmlPageLoader.loadPage(noVacanciesPage2Url)).thenReturn(Jsoup.parse(noVacanciesPageFile, UTF_8));
        return mockedPageLoader;
    }
}
