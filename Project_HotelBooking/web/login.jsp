<%-- 
    Document   : login
    Created on : Mar 16, 2020, 11:16:02 AM
    Author     : Kami.Sureiya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="project.dtos.UserErrorDTO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
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
        <h1>LOGIN</h1>
        <form action="MainController" method="POST" >
            User ID:  <input type="text" name="txtUserID" > ${requestScope.ERROR_OBJECT.userIDError} </br>
            Password: <input type="password" name="txtPassword">${requestScope.ERROR_OBJECT.passwordError}</br>
            <input type="submit" value="Login" name="btnAction">
            <input type="reset" value="Reset">

        </form>
        <h3><a href="register.jsp">REGISTER</a></h3>
        <c:url var="backHomeLink" value="MainController">
            <c:param name="btnAction" value="Logout"></c:param>
        </c:url>
        <h3><a href="${backHomeLink}">Back To Home</a></h3>
        <h4>${requestScope.LOGIN_ERROR}</h4>
    </body>
</html>
