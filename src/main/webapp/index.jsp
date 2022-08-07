<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="WEB-INF/jsp/fragments/headTag.jsp"/>

    <body class="d-flex flex-column min-vh-100">
        <div class="jumbotron">
            <div class="container">
                <h3 class="bg-secondary text-white text-center p-3">
                    Here you can find out what key skills you need to have for the specified profession
                </h3>
                <h5 class="mb-3 mt-4">For information on key skills follow these steps:</h5>
                <ol>
                    <li>Enter the name of the profession you are interested in.*</li>
                    <li>Enter the name of the city in which to search for vacancies.**</li>
                    <li>Select the number of vacancies for analysis (the waiting time depends significantly on this parameter).</li>
                    <li>Click the <q>Request</q> button and wait for the result.</li>
                </ol>
                <p class="font-italic mt-3">
                    * Please note that vacancies are taken from the <a href="https://hh.ru">Head Hunters Russia</a> portal,
                    so (if possible) enter the name of the profession and city in Russian.<br>
                    ** If you do not specify a city, the search will be performed in all cities of Russia.
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
                <button class="btn btn-primary mt-md-3 mt-2 mb-4" type="submit">Request</button>
            </form>
        </div>
        <br>

        <jsp:include page="WEB-INF/jsp/fragments/footer.jsp"/>
    </body>
</html>
