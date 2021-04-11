<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="WEB-INF/jsp/fragments/headTag.jsp"/>
<body>
<div class="jumbotron">
    <div class="container">
        <h5><a href="${pageContext.request.contextPath}">Home</a></h5>
        <hr>
        <br>
        <h3>An error occurred while processing the request!</h3>
        <h3>Please try again</h3>
    </div>
</div>
<jsp:include page="WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>
