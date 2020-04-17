<%-- 
    Document   : createRoom
    Created on : Mar 20, 2020, 12:45:11 PM
    Author     : Kami.Sureiya
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Room Page</title>
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
        <h1>Create New Room</h1>
        <form action="MainController" method="POST">
            Room ID will be created automatically !<br><br>

            Hotel: 
            <select name="cbxHotelID">
                <option value="0">Choose...</option>
                <c:forEach var="list" varStatus="counter" items="${sessionScope.HOTEL}">
                    <option value="${list.hotelID}">${list.hotelID}-${list.hotelName}</option>
                </c:forEach>
            </select>${requestScope.ERROR_OBJECT.hotelIDError}<br><br>

            Type Room: 
            <select name="cbxTypeID">
                <option value="0">Choose...</option>
                <c:forEach var="list" varStatus="counter" items="${sessionScope.TYPE}">
                    <option value="${list.typeID}">${list.typeID}-${list.typeName}</option>
                </c:forEach>
            </select>${requestScope.ERROR_OBJECT.typeRoomError}<br><br>

            Quantity: <input type="number" name="txtQuantity"/>${requestScope.ERROR_OBJECT.quantityError}<br><br>

            Unit Price: <input type="number" name="txtUnitPrice"/>${requestScope.ERROR_OBJECT.priceError}<br><br>

            <input type="submit" name="btnAction" value="CreateRoom"/>
            <input type="reset" value="Reset"/>
        </form>
            <h4>${requestScope.CREATEOK}</h4>
        <c:url var="manageRoomLink" value="ManageRoomController">
        </c:url>
        <h3><a href="${manageRoomLink}"><<< Back To Room Management</a></h3>
    </body>
</html>
