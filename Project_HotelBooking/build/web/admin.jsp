<%-- 
    Document   : admin
    Created on : Mar 18, 2020, 2:30:46 PM
    Author     : Kami.Sureiya
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
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
        <h1>Welcome, ${sessionScope.ACCOUNT.userName}</h1>

        <c:url var="logoutLink" value="MainController">
            <c:param name="btnAction" value="Logout"></c:param>
        </c:url>
        <h3><a href="${logoutLink}">Logout</a></h3>

        <c:url var="roomManageLink" value="MainController">
            <c:param name="btnAction" value="ManageRoom"></c:param>
        </c:url>
        <h3><a href="${roomManageLink}">Go To Room Management >>></a></h3>

        <c:url var="userManageLink" value="MainController">
            <c:param name="btnAction" value="ManageUser"></c:param>
        </c:url>
        <h3><a href="${userManageLink}">Go To User Management >>></a></h3>

    </body>
</html>
