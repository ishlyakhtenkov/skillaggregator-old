DROP TABLE IF EXISTS skills_reports;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE skills_reports
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    profession_name VARCHAR NOT NULL,
    city VARCHAR NOT NULL,
    analyzed_vacancies_amount INTEGER NOT NULL,
    required_skills VARCHAR NOT NULL,
    date DATE DEFAULT now() NOT NULL,
    selection VARCHAR NOT NULL
);
CREATE UNIQUE INDEX skills_reports_unique_profession_name_city_date_selection_idx ON skills_reports (profession_name, city, date, selection);