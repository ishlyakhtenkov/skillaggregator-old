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
        <h3>Please input vacancy name and city (not necessary), where you want to find!</h3>
        <h3>It will show you which skills are most in demand for the selected vacancy!</h3>
        <h3>It takes information from <a href="https://hh.ru">Head hunters Russia</a>, so if you want to find with city,</h3>
        <h3>enter its name in Russian.</h3>
        <h3>It may take time! So wait a little bit.</h3>
        <br>
        <form method="get" action="keySkills">
            <div class="form-group">
                <label for="name" class="col-form-label">Vacancy name:</label>
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
</div>
</body>
</html>
