DROP TABLE IF EXISTS vacancy_key_skills_reports;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE vacancy_key_skills_reports
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL,
    city VARCHAR NOT NULL,
    vacancies_amount INTEGER NOT NULL,
    key_skills VARCHAR NOT NULL,
    date DATE DEFAULT now() NOT NULL
);
CREATE UNIQUE INDEX vacancy_key_skills_reports_unique_name_city_date_idx ON vacancy_key_skills_reports (name, city, date);