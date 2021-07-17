[![Codacy Badge](https://app.codacy.com/project/badge/Grade/ecf9b458ac7c47d2ad52ab82e06a4dc7)](https://www.codacy.com/gh/igar15/skillsaggregator/dashboard)
[![Build Status](https://api.travis-ci.com/igar15/skillsaggregator.svg?branch=master)](https://travis-ci.com/github/igar15/skillsaggregator)

Skills Aggregator project 
=================================

Java Enterprise project to view the key skills required for the specified profession.  

### Technology stack used: 
* Maven
* Java Servlets API
* Spring
* Spring Data JPA (Hibernate)
* JSoup
* JUnit 5
* Mockito
* JSP
* JSTL
* Bootstrap 4

### Project key logic:
* System main purpose: show the key skills required for the specified profession.
* User enters the name of the profession he is interested in, and the city where to search.
* Optionally, user can select the sample size for analysis. The sample size significantly affects the waiting time for the result.
* After processing the data, the program provides the user with a list of key skills for the specified profession, as well as the percentage of vacancies that require their knowledge.
* The program compiles a list of key skills based on vacancy data from the <a href="https://hh.ru/">Head hunters Russia</a> portal.