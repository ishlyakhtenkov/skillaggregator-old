<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<div class="jumbotron pt-4">
    <div class="container">
        <jsp:useBean id="report" type="ru.igar15.vacancyaggregator.to.SkillsReportTo" scope="request"/>
        <h5><a href="${pageContext.request.contextPath}">Home</a></h5>
        <hr>
        <br>
        <h4>Vacancy name: ${report.name}</h4>
        <h4>City: ${report.city}</h4>
        <h4>Selection: First ${report.selection * 50} vacancies</h4>
        <h4>Processed date: ${report.date}</h4>
        <h4>Number of processed vacancies: ${report.vacanciesAmount}</h4>
        <br>
        <c:if test="${report.keySkills.size() == 0}">
            <h3 class="text-danger" align="center">Processed vacancies do not have key skills</h3>
        </c:if>
        <c:if test="${report.keySkills.size() > 0}">
            <h3 align="center">Key skills</h3>
            <table class="table table-striped">
                <thead>
                <tr align="center">
                    <th><h5>Technology</h5></th>
                    <th><h5>Percentage of vacancies where required</h5></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${report.keySkills}" var="entry">
                    <tr align="center">
                        <td>${entry.key}</td>
                        <td>${entry.value}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
