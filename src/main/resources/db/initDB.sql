DROP TABLE IF EXISTS skills_reports;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE skills_reports
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL,
    city VARCHAR NOT NULL,
    vacancies_amount INTEGER NOT NULL,
    skills VARCHAR NOT NULL,
    date DATE DEFAULT now() NOT NULL,
    selection INTEGER NOT NULL
);
CREATE UNIQUE INDEX skills_reports_unique_name_city_date_selection_idx ON skills_reports (name, city, date, selection);