<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: rogoza.iryna
  Date: 14.03.2018
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Info submitted</title>
</head>
<body style="background-color: lightgray">

    <div style="margin-left: 40px; margin-top: 20px">
        <h2>You are logged in!</h2>

        <%
            //one-to-one patameters
            String[] attributeArray = {"fullname", "password"};

            for (String attribute : attributeArray) {
                session.setAttribute("fullname", request.getParameter("fullname"));
            }

            //one-to-many parameter
            String[] languages = request.getParameterValues("language");
            session.setAttribute("languages", languages);
        %>

        <div style="margin-top: 40px">
            <a href="/">Go to main</a>
        </div>
    </div>

</body>
</html>
