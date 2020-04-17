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
        <title>User Page</title>
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
        <h1>Welcome, ${sessionScope.ACCOUNT.userName}</h1>

        <c:url var="logoutLink" value="MainController">
            <c:param name="btnAction" value="Logout"></c:param>
        </c:url>
        <h3><a href="${logoutLink}">Logout</a></h3>

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
                            <th>Add To Cart</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="list" varStatus="counter" items="${requestScope.SEARCHROOM}">
                        <form action="MainController" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}
                                    <input type="hidden" name="txtRoomID" value="${list.roomID}"/>
                                    <input type="hidden" name="txtHotelID" value="${list.hotelID}"/>
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

                                <td>
                                    <c:forEach var="listType" varStatus="counter2" items="${sessionScope.TYPE}">
                                        <c:if test="${list.typeID == listType.typeID}">
                                            ${listType.typeName}
                                        </c:if>
                                    </c:forEach>
                                    <input type="hidden" name="txtTypeID" value="${list.typeID}"/>

                                </td>
                                <td>
                                    ${list.quantity}
                                </td>
                                <td>
                                    ${list.price * sessionScope.DAYNUM}
                                    <input type="hidden" name="txtPrice" value="${list.price}"/>
                                </td>
                                <td>${sessionScope.DAYNUM}</td>
                                <td>
                                    <input type="hidden" name="txtSearch" value="${param.txtSearch}">
                                    <input type="hidden" name="txtAddress" value="${param.txtAddress}">
                                    <input type="hidden" name="txtAmount" value="${param.txtAmount}">
                                    <input type="submit" name="btnAction" value="AddToCart"/>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:if>
    <h3><a href="viewCart.jsp">View Cart</a></h3><br><br>
    <h4>${requestScope.ADDOK}</h4>
</body>
</html>
