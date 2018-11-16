<%-- 
    Document   : registerpage
    Created on : Nov 13, 2018, 9:49:28 AM
    Author     : jonab
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
        <title>register page</title>
    </head>
    <body>
        <%=request.getAttribute("menu")%>
        <div class ="pageHeader">
            <h2>Register</h2>
        </div>
        <div class="pageContent">
            <form name="register" action="FrontController" method="POST">
                <input type="hidden" name="command" value="register">
                <input type="text" name="email" placeholder="Enter email">
                <br>
                <input type="password" name="password" placeholder="Enter password">
                <br>
                <input type="password" name="password1" placeholder="Confirm password">
                <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>
