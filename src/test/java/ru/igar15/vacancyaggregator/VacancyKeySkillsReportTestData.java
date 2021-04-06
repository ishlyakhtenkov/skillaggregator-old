package ru.igar15.vacancyaggregator;

import ru.igar15.vacancyaggregator.model.VacancyKeySkillsReport;

public class VacancyKeySkillsReportTestData {
    private static final String KEY_SKILLS = "JAVA=%=100 %\nSPRING FRAMEWORK=%=49 %\nGIT=%=40 %\nPOSTGRESQL=%=31 %";

    public static final VacancyKeySkillsReport report = new VacancyKeySkillsReport("NAME", "CITY",
            2, 100, KEY_SKILLS);

    public static final String AGGREGATOR_REPORT_KEY_SKILLS = "JAVA=%=82 %\n" +
            "SPRING FRAMEWORK=%=56 %\n" +
            "GIT=%=38 %\n" +
            "POSTGRESQL=%=36 %\n" +
            "SQL=%=34 %\n" +
            "HIBERNATE ORM=%=30 %\n" +
            "АНГЛИЙСКИЙ ЯЗЫК=%=24 %\n" +
            "REST=%=16 %\n" +
            "JAVA EE=%=14 %\n" +
            "LINUX=%=14 %\n" +
            "JAVA SE=%=12 %\n" +
            "APACHE MAVEN=%=12 %\n" +
            "DOCKER=%=12 %\n" +
            "ORACLE=%=8 %\n" +
            "SOAP=%=8 %\n" +
            "KAFKA=%=6 %\n" +
            "JAVASCRIPT=%=6 %\n" +
            "ООП=%=6 %\n" +
            "KOTLIN=%=6 %\n" +
            "APACHE TOMCAT=%=6 %\n" +
            "KUBERNETES=%=6 %\n" +
            "NGINX=%=4 %\n" +
            "SPRING=%=4 %\n" +
            "NOSQL=%=4 %\n" +
            "ОРИЕНТАЦИЯ НА РЕЗУЛЬТАТ=%=4 %\n" +
            "SVN=%=4 %\n" +
            "WILDFLY=%=4 %\n" +
            "UNIT TESTING=%=4 %\n" +
            "BACKEND=%=4 %\n" +
            "SCALA=%=4 %\n" +
            "RABBITMQ=%=4 %\n" +
            "ORM=%=4 %\n" +
            "MYSQL=%=4 %\n" +
            "JUNIT=%=4 %\n" +
            "XML=%=4 %\n" +
            "REDIS=%=4 %\n" +
            "API=%=4 %\n" +
            "STREAMING ALGORITHMS=%=2 %\n" +
            "JAVA НЕ НИЖЕ 8=%=2 %\n" +
            "MS ACCESS=%=2 %\n" +
            "QA=%=2 %\n" +
            "КАДРОВЫЙ МЕНЕДЖМЕНТ=%=2 %\n" +
            "УПРАВЛЕНИЕ КОМАНДОЙ=%=2 %\n" +
            "SPRING SECURITY=%=2 %\n" +
            "МОДУЛЬНОЕ ТЕСТИРОВАНИЕ=%=2 %\n" +
            "APACHE HTTP SERVER=%=2 %\n" +
            "JSON API=%=2 %\n" +
            "ТЕХНИЧЕСКИЙ ПЕРЕВОД=%=2 %\n" +
            "MONGODB=%=2 %\n" +
            "JENKINS=%=2 %\n" +
            "MAVEN=%=2 %\n" +
            "СУБД=%=2 %\n" +
            "ТЕСТИРОВАНИЕ=%=2 %\n" +
            "GRPC=%=2 %\n" +
            "PYTHON=%=2 %\n" +
            "SOFTWARE DEVELOPMENT=%=2 %\n" +
            "ДЕЛОВАЯ ПЕРЕПИСКА=%=2 %\n" +
            "CONSUL=%=2 %\n" +
            "ANSIBLE=%=2 %\n" +
            "РЕФАКТОРИНГ КОДА=%=2 %\n" +
            "GWT=%=2 %\n" +
            "TEAMCITY=%=2 %\n" +
            "BOOTSTRAP=%=2 %\n" +
            "CI/CD=%=2 %\n" +
            "DESIGN PATTERNS=%=2 %\n" +
            "JQUERY=%=2 %\n" +
            "HTML=%=2 %\n" +
            "JIRA=%=2 %\n" +
            "GRADLE=%=2 %\n" +
            "ОБУЧЕНИЕ И РАЗВИТИЕ=%=2 %\n" +
            "TOMCAT=%=2 %\n" +
            "SPRING BOOT 2=%=2 %\n" +
            "MULTITHREAD PROGRAMMING=%=2 %\n" +
            "UNIX=%=2 %\n" +
            "UML=%=2 %\n" +
            "СТРАТЕГИЧЕСКОЕ МЫШЛЕНИЕ=%=2 %\n" +
            "JAVA 8=%=2 %\n" +
            "SWAGGER=%=2 %\n" +
            "SOAP/REST=%=2 %\n" +
            "MVC=%=2 %\n" +
            "ELASTIC SEARCH=%=2 %\n" +
            "CASSANDRA=%=2 %\n" +
            "JAVA SPRING FRAMEWORK=%=2 %\n" +
            "JAVA SCRIPT, GIT,POSTGRESQL, ELASTIC SEARCH;=%=2 %\n" +
            "JSON=%=2 %\n" +
            "ORACLE PL/SQL=%=2 %\n" +
            "INTELLIJ IDEA=%=2 %\n" +
            "CИСТЕМЫ УПРАВЛЕНИЯ БАЗАМИ ДАННЫХ=%=2 %\n" +
            "LUA=%=2 %\n" +
            "АДМИНИСТРИРОВАНИЕ СЕРВЕРОВ LINUX=%=2 %\n" +
            "IOC=%=2 %\n" +
            "AWS=%=2 %\n" +
            "ТЕЛЕФОННЫЕ ПЕРЕГОВОРЫ=%=2 %\n" +
            "CSS=%=2 %\n" +
            "MS SQL SERVER=%=2 %\n" +
            "AMAZON WEB SERVICES=%=2 %\n" +
            "ПАРУС=%=2 %\n" +
            "РАБОТА В КОМАНДЕ=%=2 %\n" +
            "РАЗРАБОТКА ПО=%=2 %";

    public static final VacancyKeySkillsReport aggregatorReport = new VacancyKeySkillsReport("java", "moscow",
            1, 50, AGGREGATOR_REPORT_KEY_SKILLS);

//    public static final VacancyKeySkillsReport reportWithoutVacancies = new VacancyKeySkillsReport("NAME", "CITY",
//            2, 0, "");

    public static VacancyKeySkillsReport getNew() {
        return new VacancyKeySkillsReport("NAME", "CITY",2, 100, KEY_SKILLS);
    }
}