<%-- 
    Document   : home
    Created on : Mar 27, 2020, 1:14:17 PM
    Author     : Kami.Sureiya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <style>
            body a{
                color: black;
                text-decoration: none;
            }
            a:active{
                color: red;
            }
            table{
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h1>Welcome to Hotel Booking Website </h1>
        <h3><a href="login.jsp">LOGIN</a></h3>
        <h3><a href="register.jsp">REGISTER</a></h3>
        <form action="MainController" method="POST">
            Search Hotel Name: <input type="text" name="txtSearch" value="${sessionScope.SEARCH_ROOM_VALUE}">
            <input type="submit" value="SearchRoom" name="btnAction" />
            &NegativeMediumSpace; ${requestScope.SEARCH_ROOM_ERROR}<br><br>

            Area : <input type="text" name="txtAddress" value="${sessionScope.ADDRESS}"/>${requestScope.ADDRESS_ERROR}<br>
            Check in : <input type="date" name="txtCheckIn" value="${sessionScope.CHECKIN}"/>${requestScope.DATE_ERROR.checkInDateError}<br>
            Check out: <input type="date" name="txtCheckOut" value="${sessionScope.CHECKOUT}"/>${requestScope.DATE_ERROR.checkOutDateError}<br>
            Amount of Room: <input type="number" name="txtAmount" value="${sessionScope.AMOUNT}"/>${requestScope.AMOUNT_ERROR}<br>
            <input type="submit" value="SearchByDate" name="btnAction" />${requestScope.SEARCH_DATE_ERROR}<br><br>

            <input type="submit" name="btnAction" value="PreviousRoom"/>
            &NegativeMediumSpace; ${sessionScope.PAGENUM} &NegativeMediumSpace;
            <input type="submit" name="btnAction" value="NextRoom"/> <br><br>
        </form>
    <c:if test="${requestScope.SEARCHROOM != null}">
        <c:if test="${not empty requestScope.SEARCHROOM}">

            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Hotel Name</th>
                        <th>Hotel Address</th>
                        <th>Type Room</th>
                        <th>Quantity</th>
                        <th>Unit Price</th>
                        <th>No. Days</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="list" varStatus="counter" items="${requestScope.SEARCHROOM}">
                    <tr>
                        <td>
                            ${counter.count}
                        </td>

                    <c:forEach var="listHotel" varStatus="counter1" items="${sessionScope.HOTEL}">
                        <c:if test="${list.hotelID == listHotel.hotelID}">
                            <td>
                                ${listHotel.hotelName}
                            </td>
                            <td>
                                ${listHotel.address}
                            </td>
                        </c:if>
                    </c:forEach>
                    <c:forEach var="listType" varStatus="counter2" items="${sessionScope.TYPE}">
                        <c:if test="${list.typeID == listType.typeID}">
                            <td>
                                ${listType.typeName}

                            </td>
                        </c:if>
                    </c:forEach>
                    <td>
                        ${list.quantity}
                    </td>
                    <td>
                        ${list.price * sessionScope.DAYNUM}
                        
                    </td>
                    <td>${sessionScope.DAYNUM}</td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:if>
</body>
</html>
