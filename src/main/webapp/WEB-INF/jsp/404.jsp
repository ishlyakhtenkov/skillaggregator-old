<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body class="d-flex flex-column min-vh-100">
<div class="jumbotron">
    <div class="container">
        <h5><a href="${pageContext.request.contextPath}">Home</a></h5>
        <hr>
        <br>
        <h3>The page is not exist!</h3>
        <h1>404</h1>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
