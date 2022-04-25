<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="WEB-INF/jsp/fragments/headTag.jsp"/>

    <body class="d-flex flex-column min-vh-100">
        <div class="jumbotron">
            <div class="container">
                <p class="h3 p-2 bg-secondary text-white text-center">&nbsp;&nbsp;&nbsp;&nbsp;Here you can find out what key skills you need to have for the specified profession.</br>
                    The query result will contain a list of key skills and the percentage of vacancies that require their knowledge.
                </p>
                <p class="h4 mb-3 mt-4">
                    &nbsp;&nbsp;&nbsp;&nbsp;For information on key skills, follow these steps*:
                </p>
                <h5>&nbsp;&nbsp;&nbsp;&nbsp;1. Enter the name of the profession you are interested in.</h5>
                <h5>&nbsp;&nbsp;&nbsp;&nbsp;2. Enter the name of the city in which to search for vacancies**.</h5>
                <h5>&nbsp;&nbsp;&nbsp;&nbsp;3. Select the number of vacancies for analysis (the waiting time depends significantly on this parameter).</h5>
                <h5>&nbsp;&nbsp;&nbsp;&nbsp;4. Click the "Request" button and wait for the result.</h5>
                <p class="font-italic mt-3">
                    &nbsp;&nbsp;&nbsp;&nbsp;* Please note that vacancies are taken from the <a href="https://hh.ru">Head Hunters Russia</a> portal, so (if possible) enter the name of the profession and city in Russian:
                    </br>
                    &nbsp;&nbsp;&nbsp;&nbsp;** If you do not specify a city, the search will be performed in all cities of Russia.
                </p>
            </div>
        </div>

        <div class="container d-flex justify-content-center">
            <form class="text-center" method="get" action="skillReport">
                <div class="form-group">
                    <label for="name" class="col-form-label">Profession name:</label>
                    <input type="text" class="form-control" id="name" name="professionName" required>
                </div>
                <div class="form-group">
                    <label for="city" class="col-form-label">Vacancy city:</label>
                    <input type="text" class="form-control" id="city" name="city">
                </div>
                <div class="form-group">
                    <label for="selection" class="col-form-label">Selection:</label>
                    <select name="selection" class="form-control" id="selection">
                        <option value="FIRST_100_VACANCIES" selected>First 100 (~ 25 sec wait)</option>
                        <option value="FIRST_500_VACANCIES">First 500 (~ 2 min wait)</option>
                        <option value="FIRST_2000_VACANCIES">First 2000 (~ 8 min wait)</option>
                    </select>
                </div>
                <button class="btn btn-primary" type="submit">Get Skill Report</button>
            </form>
        </div>
        <br>

        <jsp:include page="WEB-INF/jsp/fragments/footer.jsp"/>
    </body>
</html>
