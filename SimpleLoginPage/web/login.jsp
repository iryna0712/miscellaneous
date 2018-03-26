<%--
  Created by IntelliJ IDEA.
  User: rogoza.iryna
  Date: 13.03.2018
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body style="background-color: lightgray">

    <form method="post" style="margin-left: 40px; margin-top: 20px" action="/loggingIn">
        <h1>Input your info </h1>

        Full name:
        <input name="fullname" type="text" value="<%= session.getAttribute("fullname")%>" required>
        Password:
        <input name="password" type="password" maxlength="12" required>
        <br><br>

        <br>
        <input type="submit" value="Login">

        <br>
        <div>
            <a href="/">Go to main</a>
        </div>
    </form>

</body>
</html>
