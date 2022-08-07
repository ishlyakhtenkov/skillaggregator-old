<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <jsp:include page="fragments/headTag.jsp"/>

    <body class="d-flex flex-column min-vh-100">
        <div class="jumbotron pt-4">
            <div class="container">
                <jsp:useBean id="skillReportTo" type="ru.igar15.skillsaggregator.to.SkillReportTo" scope="request"/>
                <h5><a href="${pageContext.request.contextPath}">Home</a></h5>
                <hr>
                <br>
                <h4>Profession name: ${skillReportTo.professionName}</h4>
                <h4>City: ${skillReportTo.city}</h4>
                <h4>Selection: First ${skillReportTo.selection.vacanciesAmount} vacancies</h4>
                <h4>Processed date: ${skillReportTo.date}</h4>
                <h4>Analyzed vacancies amount: ${skillReportTo.analyzedVacanciesAmount}</h4>
            </div>
        </div>

        <div class="container">
            <c:if test="${skillReportTo.skillStatistic.size() == 0}">
                <h3 class="text-danger" align="center">Analyzed vacancies do not have key skills</h3>
            </c:if>
            <c:if test="${skillReportTo.skillStatistic.size() > 0}">
                <h3 class="text-center">Key Skills</h3>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th class="text-center align-middle"><h5>Technology</h5></th>
                        <th class="text-center align-middle"><h5>Percentage of vacancies where required</h5></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${skillReportTo.skillStatistic}" var="entry">
                        <tr>
                            <td class="text-center align-middle">${entry.key}</td>
                            <td class="text-center align-middle">${entry.value} %</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>

        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>
