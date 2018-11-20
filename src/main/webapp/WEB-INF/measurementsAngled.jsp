<%-- 
    Document   : measurements
    Created on : 13-11-2018, 10:34:36
    Author     : porse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet"
              type = "text/css"
              href = "indexStyle.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Valg af mål</title>
    </head>
    <body>
        <%=request.getAttribute("menu")%>
        <div class ="pageHeader">
            <h2>Skråt tag</h2>
        </div>

        <div class="pageContent">
            <form action="FrontController" method="POST">
                <h5>Højde:</h5>
                <select name="height">
                    <option value="200">200 cm</option>
                    <option value="220" selected>220 cm</option>
                    <option value="240">240 cm</option>
                    <option value="260">260 cm</option>
                    <option value="280">280 cm</option>
                    <option value="300">300 cm</option>
                </select>

                <h5>Bredde:</h5>
                <select name="width">
                    <option value="200">200 cm</option>
                    <option value="220">220 cm</option>
                    <option value="240"selected>240 cm</option>
                    <option value="260">260 cm</option>
                    <option value="280">280 cm</option>
                    <option value="300">300 cm</option>
                    <option value="320">320 cm</option>
                    <option value="340">340 cm</option>
                    <option value="360">360 cm</option>
                    <option value="380">380 cm</option>
                    <option value="500">500 cm</option>            
                </select>

                <h5>Længde:</h5>
                <select name="length">
                    <option value="400">400 cm</option>
                    <option value="420">420 cm</option>
                    <option value="440">440 cm</option>
                    <option value="460">460 cm</option>
                    <option value="480">480 cm</option>
                    <option value="500"selected>500 cm</option>
                </select>
                <h5>Vinkel: </h5>
                <select name="angle">
                    <option value="10" selected>10 grader</option>
                    <option value="20">20 grader</option>
                    <option value="30">30 grader</option>
                    <option value="40">40 grader</option>
                </select>
                <input type="submit" value="Submit">
                <input type="hidden" name="command" value="SelectAngled">
            </form>
        </div>
    </body>
</html>
