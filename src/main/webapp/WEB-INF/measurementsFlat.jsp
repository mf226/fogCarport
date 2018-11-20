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
            <h2>Fladt tag</h2>
        </div>
        <div class="sketch">
        </div>
        <div class="pageContent">
            <h4>Venligst vælg den ønskede størrelse af din carport.</h4>
            <h5>Højde:</h5>
            <form action="FrontController" method="POST">
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
                    <option value="300">300 cm</option>
                    <option value="320">320 cm</option>
                    <option value="340"selected>340 cm</option>
                    <option value="360">360 cm</option>
                    <option value="380">380 cm</option>
                    <option value="400">400 cm</option>
                    <option value="420">420 cm</option>
                    <option value="440">440 cm</option>
                    <option value="460">460 cm</option>
                    <option value="480">480 cm</option>
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
                <h5></h5>
                <input type="submit" value="Submit">
                <input type="hidden" name="command" value="SelectFlat">
            </form>
        </div>
    </body>
</html>
