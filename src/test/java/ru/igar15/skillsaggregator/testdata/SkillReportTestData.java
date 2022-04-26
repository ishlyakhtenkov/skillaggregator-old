package ru.igar15.skillsaggregator.testdata;

import ru.igar15.skillsaggregator.model.SkillReport;
import ru.igar15.skillsaggregator.to.SkillReportTo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static ru.igar15.skillsaggregator.model.Selection.FIRST_100_VACANCIES;

public class SkillReportTestData {
    private static final String PROFESSION_NAME = "java";
    private static final String CITY = "Moscow";
    private static final int ANALYZED_VACANCIES_AMOUNT = 100;
    public static SkillReport skillReport;
    public static SkillReportTo skillReportTo;

    public static Map<String, Integer> skillCounter = new HashMap<>();

    static {
        makeSkillReport();
        makeSkillReportTo();
        makeSkillCounter();
    }

    private static void makeSkillReport() {
        Map<String, Integer> skillCounter = Map.of("java", 100, "english", 25, "spring", 90, "hibernate", 70,
                "sql", 50, "docker", 10);
        skillReport = new SkillReport(PROFESSION_NAME, CITY, ANALYZED_VACANCIES_AMOUNT, skillCounter, FIRST_100_VACANCIES);
    }

    private static void makeSkillReportTo() {
        Map<String, Integer> skillStatistic = new LinkedHashMap<>();
        skillStatistic.put("java", 100);
        skillStatistic.put("spring", 90);
        skillStatistic.put("hibernate", 70);
        skillStatistic.put("sql", 50);
        skillStatistic.put("english", 25);
        skillStatistic.put("docker", 10);
        skillReportTo = new SkillReportTo(PROFESSION_NAME, CITY, LocalDate.now(), ANALYZED_VACANCIES_AMOUNT,
                FIRST_100_VACANCIES, skillStatistic);
    }

    private static void makeSkillCounter() {
        skillCounter.put("JAVA", 82);
        skillCounter.put("STREAMING ALGORITHMS", 2);
        skillCounter.put("JAVA НЕ НИЖЕ 8", 2);
        skillCounter.put("MS ACCESS", 2);
        skillCounter.put("JAVA EE", 14);
        skillCounter.put("ORACLE", 8);
        skillCounter.put("QA", 2);
        skillCounter.put("КАДРОВЫЙ МЕНЕДЖМЕНТ", 2);
        skillCounter.put("УПРАВЛЕНИЕ КОМАНДОЙ", 2);
        skillCounter.put("NGINX", 4);
        skillCounter.put("SPRING SECURITY", 2);
        skillCounter.put("МОДУЛЬНОЕ ТЕСТИРОВАНИЕ", 2);
        skillCounter.put("АНГЛИЙСКИЙ — A2 — ЭЛЕМЕНТАРНЫЙ", 2);
        skillCounter.put("APACHE HTTP SERVER", 2);
        skillCounter.put("АНГЛИЙСКИЙ — A1 — НАЧАЛЬНЫЙ", 2);
        skillCounter.put("SPRING", 4);
        skillCounter.put("JSON API", 2);
        skillCounter.put("ТЕХНИЧЕСКИЙ ПЕРЕВОД", 2);
        skillCounter.put("MONGODB", 2);
        skillCounter.put("SPRING FRAMEWORK", 56);
        skillCounter.put("JENKINS", 2);
        skillCounter.put("MAVEN", 2);
        skillCounter.put("СУБД", 2);
        skillCounter.put("АНГЛИЙСКИЙ ЯЗЫК", 6);
        skillCounter.put("KAFKA", 6);
        skillCounter.put("ТЕСТИРОВАНИЕ", 2);
        skillCounter.put("NOSQL", 4);
        skillCounter.put("POSTGRESQL", 36);
        skillCounter.put("GRPC", 2);
        skillCounter.put("JAVASCRIPT", 6);
        skillCounter.put("АНГЛИЙСКИЙ — B1 — СРЕДНИЙ", 2);
        skillCounter.put("JAVA SE", 12);
        skillCounter.put("ОРИЕНТАЦИЯ НА РЕЗУЛЬТАТ", 4);
        skillCounter.put("SVN", 4);
        skillCounter.put("PYTHON", 2);
        skillCounter.put("ООП", 6);
        skillCounter.put("SOFTWARE DEVELOPMENT", 2);
        skillCounter.put("ДЕЛОВАЯ ПЕРЕПИСКА", 2);
        skillCounter.put("WILDFLY", 4);
        skillCounter.put("CONSUL", 2);
        skillCounter.put("ANSIBLE", 2);
        skillCounter.put("РЕФАКТОРИНГ КОДА", 2);
        skillCounter.put("APACHE MAVEN", 12);
        skillCounter.put("GWT", 2);
        skillCounter.put("UNIT TESTING", 4);
        skillCounter.put("BACKEND", 4);
        skillCounter.put("TEAMCITY", 2);
        skillCounter.put("BOOTSTRAP", 2);
        skillCounter.put("CI/CD", 2);
        skillCounter.put("DESIGN PATTERNS", 2);
        skillCounter.put("JQUERY", 2);
        skillCounter.put("KOTLIN", 6);
        skillCounter.put("LINUX", 14);
        skillCounter.put("HTML", 2);
        skillCounter.put("JIRA", 2);
        skillCounter.put("GRADLE", 2);
        skillCounter.put("ОБУЧЕНИЕ И РАЗВИТИЕ", 2);
        skillCounter.put("TOMCAT", 2);
        skillCounter.put("SPRING BOOT 2", 2);
        skillCounter.put("SCALA", 4);
        skillCounter.put("DOCKER", 12);
        skillCounter.put("MULTITHREAD PROGRAMMING", 2);
        skillCounter.put("SOAP", 8);
        skillCounter.put("HIBERNATE ORM", 30);
        skillCounter.put("UNIX", 2);
        skillCounter.put("SQL", 34);
        skillCounter.put("GIT", 38);
        skillCounter.put("UML", 2);
        skillCounter.put("СТРАТЕГИЧЕСКОЕ МЫШЛЕНИЕ", 2);
        skillCounter.put("JAVA 8", 2);
        skillCounter.put("SOAP/REST", 2);
        skillCounter.put("SWAGGER", 2);
        skillCounter.put("MVC", 2);
        skillCounter.put("ELASTIC SEARCH", 2);
        skillCounter.put("CASSANDRA", 2);
        skillCounter.put("JAVA SPRING FRAMEWORK", 2);
        skillCounter.put("JAVA SCRIPT, GIT,POSTGRESQL, ELASTIC SEARCH;", 2);
        skillCounter.put("RABBITMQ", 4);
        skillCounter.put("APACHE TOMCAT", 6);
        skillCounter.put("JSON", 2);
        skillCounter.put("ORM", 4);
        skillCounter.put("ORACLE PL/SQL", 2);
        skillCounter.put("INTELLIJ IDEA", 2);
        skillCounter.put("CИСТЕМЫ УПРАВЛЕНИЯ БАЗАМИ ДАННЫХ", 2);
        skillCounter.put("АНГЛИЙСКИЙ — B2 — СРЕДНЕ-ПРОДВИНУТЫЙ", 12);
        skillCounter.put("LUA", 2);
        skillCounter.put("АДМИНИСТРИРОВАНИЕ СЕРВЕРОВ LINUX", 2);
        skillCounter.put("IOC", 2);
        skillCounter.put("AWS", 2);
        skillCounter.put("ТЕЛЕФОННЫЕ ПЕРЕГОВОРЫ", 2);
        skillCounter.put("CSS", 2);
        skillCounter.put("MYSQL", 4);
        skillCounter.put("MS SQL SERVER", 2);
        skillCounter.put("REST", 16);
        skillCounter.put("JUNIT", 4);
        skillCounter.put("AMAZON WEB SERVICES", 2);
        skillCounter.put("ПАРУС", 2);
        skillCounter.put("XML", 4);
        skillCounter.put("KUBERNETES", 6);
        skillCounter.put("REDIS", 4);
        skillCounter.put("API", 4);
        skillCounter.put("РАБОТА В КОМАНДЕ", 2);
        skillCounter.put("РАЗРАБОТКА ПО", 2);
    }
}
