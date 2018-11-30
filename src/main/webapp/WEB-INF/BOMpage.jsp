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
        <title>Stykliste</title>
    </head>
    <body>
        <%=request.getAttribute("menu")%>
        <div class ="pageHeader">
            <h2 class="header">Stykliste</h2>
        </div>
        <div class ="pageContent">
            <%=request.getAttribute("table")%>
        </div>
        
        
        <div class="sketchBE">
            <%=request.getAttribute("sketchBE")%>
        </div>
        <div class="sketchSV">
            <%=request.getAttribute("sketchSV")%>
        </div>
        

    </body>
</html>
