<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="WEB-INF/jsp/fragments/headTag.jsp"/>
<body class="d-flex flex-column min-vh-100">
    <div class="jumbotron">
        <div class="container">
            <h2>Good day!</h2>
            <h3>It is an aggregator of key job skills!</h3>
            <h3>Please input profession name and city (city is optional), where you want to find!</h3>
            <h3>It will show you which skills are most in demand for the selected profession!</h3>
            <h3>It takes information from <a href="https://hh.ru">Head hunters Russia</a>, so if you want to find with city,</h3>
            <h3>enter its name in Russian.</h3>
            <h3>It may take time! So wait a little bit.</h3>
        </div>
    </div>

    <div class="container">
        <form method="get" action="keySkills">
            <div class="form-group">
                <label for="name" class="col-form-label">Profession name:</label>
                <div class="row">
                    <div class="col-3">
                        <input type="text" class="form-control" id="name" name="name" required>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="city" class="col-form-label">Vacancy city:</label>
                <div class="row">
                    <div class="col-3">
                        <input type="text" class="form-control" id="city" name="city">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="selection" class="col-form-label">Selection:</label>
                <div class="row">
                    <div class="col-3">
                        <select name="selection" class="form-control" id="selection">
                            <option value="2" selected>First 100 (~ 25 sec wait)</option>
                            <option value="10">First 500 (~ 2 min wait)</option>
                            <option value="40">First 2000 (~ 8 min wait)</option>
                        </select>
                    </div>
                </div>
            </div>
            <button class="btn btn-primary" type="submit">Request</button>
        </form>
    </div>
    <br>

    <jsp:include page="WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>
