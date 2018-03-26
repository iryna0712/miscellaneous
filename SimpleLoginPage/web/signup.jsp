<%--
  Created by IntelliJ IDEA.
  User: rogoza.iryna
  Date: 13.03.2018
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>
        function validate() {
            var length = document.forms["signup_form"]["password"].value.length;

            if (length < 8) {
                alert("The password legth should be at least 8 characters!")
                return false;
            }
        }
    </script>

    <title>Title</title>
</head>
<body style="background-color: lightgray">

    <form name="signup_form" method="post" style="margin-left: 40px; margin-top: 20px" action="submit.jsp" onsubmit="return validate()">
        <h1>Input your info </h1>

        Full name:
        <input name="fullname" type="text" required>
        <br><br>

        Gender:
        <input name="gender" type="radio" value="male" checked>Male
        <input name="gender" type="radio" value="female">Female
        <input name="gender" type="radio" value="unknown">Unknown
        <br><br>

        Languages known:
        <input name="language" type="checkbox" value="english">English
        <input name="language" type="checkbox" value="french">French
        <input name="language" type="checkbox" value="japanese">Japanese
        <br><br>

        Country:
        <select name="country">
            <option value="pakistan">Pakistan</option>
            <option value="india">India</option>
            <option value="world">World is my home</option>
        </select>
        <br><br><br>

        Password:
        <input name="password" type="password" maxlength="12"required>
        <br><br>

        <input type="submit" value="Submit">
    </form>

</body>
</html>
