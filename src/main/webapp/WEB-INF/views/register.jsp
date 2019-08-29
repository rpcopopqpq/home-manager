<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Register Page</title>
</head>
<body>
<h1>User Register Page</h1>
<hr>

<%
    if (request.getParameter("mrn") != null) {
        out.println("?? ??? MRN:" + request.getParameter("mrn"));
    }

%>


<div class="form">
    <form action="/user/register" method="post">
        <table>
            <tr>
                <td>User ID</td>
                <td><input type="text" id="userId" name="userId"></td>
            </tr>
            <tr>
                <td>Enter password</td>
                <td>
                    <input type="password" id="password" name="password">
                    <input type="password" id="confirmPassword" name="confirmPassword">
                </td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="text" id="email" name="email"></td>
            </tr>
            <tr>
                <td>First Name</td>
                <td><input type="text" id="firstName" name="firstName"></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td><input type="text" id="lastName" name="lastName"></td>
            </tr>
            <tr>
                <td colspan="2">
                <td><input type="submit" value="Submit"></td>
                </td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
