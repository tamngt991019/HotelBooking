<%-- 
    Document   : create
    Created on : Mar 16, 2020, 12:35:55 PM
    Author     : Kami.Sureiya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <style>
            body a{
                color: black;
                text-decoration: none;
            }
            a:active{
                color: red;
            }
        </style>
    </head>
    <body>
        <h1>Create New Account !</h1>
        <form action="MainController" method="POST">
            User ID: <input type="text" name="txtUserID"/> ${requestScope.ERROR_OBJECT.userIDError}<br>
            User Name: <input type="text" name="txtUserName"/> ${requestScope.ERROR_OBJECT.userNameError}<br>
            Password: <input type="password" name="txtPassword"/> ${requestScope.ERROR_OBJECT.passwordError}<br>
            RePassword: <input type="password" name="txtRePassword"/> ${requestScope.ERROR_OBJECT.rePasswordError}<br>
            <input type="submit" name="btnAction" value="Register"/>
            <input type="reset" value="Reset"/>
        </form>
            <h3><a href="login.jsp">Back Login</a></h3>
        <c:url var="backHomeLink" value="MainController">
            <c:param name="btnAction" value="Logout"></c:param>
        </c:url>
        <h3><a href="${backHomeLink}">Back To Home</a></h3>
    </body>
</html>
