<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vacancy Aggregator</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<div class="jumbotron">
    <div class="container">
        <h2>Good day!</h2>
        <h3>It is an aggregator of key job skills!</h3>
        <h3>Please input vacancy name and city, where you want to find!</h3>
        <h3>We will show you which skills are most in demand for the selected vacancy!</h3>
        <h3>We take information from <a href="https://hh.ru">Head hunters Russia</a></h3>
        <h3>It may take time! So wait a little bit.</h3>
        <br>
        <form method="get" action="keySkills">
            <div class="form-group">
                <label for="name" class="col-form-label">Vacancy name:</label>
                <div class="row">
                    <div class="col-3">
                        <input type="text" class="form-control" id="name" name="name">
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
                            <option value="2" selected>First 95 (~ 25 sec wait)</option>
                            <option value="10">First 495 (~ 2 min wait)</option>
                            <option value="40">First 1995 (~ 8 min wait)</option>
                        </select>
                    </div>
                </div>
            </div>
            <button class="btn btn-primary" type="submit">Request</button>
        </form>
    </div>
</div>
</body>
</html>
