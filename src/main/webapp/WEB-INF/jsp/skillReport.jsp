<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <jsp:include page="fragments/headTag.jsp"/>

    <body class="d-flex flex-column min-vh-100">
        <div class="jumbotron pt-4">
            <div class="container">
                <jsp:useBean id="skillReport" type="ru.igar15.skillsaggregator.model.SkillReport" scope="request"/>
                <h5><a href="${pageContext.request.contextPath}">Home</a></h5>
                <hr>
                <br>
                <h4>Profession name: ${skillReport.professionName}</h4>
                <h4>City: ${skillReport.city}</h4>
                <h4>Selection: First ${skillReport.selection.vacanciesAmount} vacancies</h4>
                <h4>Processed date: ${skillReport.date}</h4>
                <h4>Analyzed vacancies amount: ${skillReport.analyzedVacanciesAmount}</h4>
            </div>
        </div>

        <div class="container">
            <c:if test="${skillReport.skillCounter.size() == 0}">
                <h3 class="text-danger" align="center">Analyzed vacancies do not have key skills</h3>
            </c:if>
            <c:if test="${skillReport.skillCounter.size() > 0}">
                <h3 align="center">Key Skills</h3>
                <table class="table table-striped">
                    <thead>
                    <tr align="center">
                        <th><h5>Technology</h5></th>
                        <th><h5>Percentage of vacancies where required</h5></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${skillReport.skillCounter}" var="entry">
                        <tr align="center">
                            <td>${entry.key}</td>
                            <td>${entry.value}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>

        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>
